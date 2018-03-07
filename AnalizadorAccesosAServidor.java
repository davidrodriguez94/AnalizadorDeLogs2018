import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class AnalizadorAccesosAServidor
{
    private static final int CODIGO_EXITOSO = 200;
    private ArrayList<Acceso> accesos;
    
    
    public AnalizadorAccesosAServidor() 
    {
        accesos = new ArrayList<>();
    }
    
    
    public void analizarArchivoDeLog(String archivo)
    {
        accesos.clear();
        File archivoALeer = new File(archivo);
        try {
            Scanner sc = new Scanner(archivoALeer);
            while (sc.hasNextLine()) {
                String lineaLeida = sc.nextLine();               
                Acceso accesoActual = new Acceso(lineaLeida);              
                accesos.add(accesoActual);
            }
            sc.close();
        }
        catch (Exception e) {
            System.out.println("Ocurrio algun error al leer el archivo.");
        }
    }
    
    
    public int obtenerHoraMasAccesos() 
    {
        int valorADevolver = -1;
        
        if (!accesos.isEmpty()) {
            int[] accesosPorHora = new int[24];
    
            for (Acceso accesoActual : accesos) {
                int horaAccesoActual = accesoActual.getHora();
                accesosPorHora[horaAccesoActual] = accesosPorHora[horaAccesoActual] + 1;
            }
            
            int numeroDeAccesosMasAlto = accesosPorHora[0];
            int horaDeAccesosMasAlto = 0;
            for (int i = 0; i < accesosPorHora.length; i++) {
                if (accesosPorHora[i] >= numeroDeAccesosMasAlto) {
                    numeroDeAccesosMasAlto = accesosPorHora[i];
                    horaDeAccesosMasAlto = i;
                }
            }
            
            valorADevolver = horaDeAccesosMasAlto;                      
        }
        
        return valorADevolver;
    }

    
    
    public String paginaWebMasSolicitada() 
    {
        String webMasSolicitada = null;
        
        int maxNumAccesosHastaAhora = 0;
        
        for (Acceso acceso : accesos) {
            
            String urlActual = acceso.getPaginaWeb();
            int contadorAccesosUrlActual = 0;
            
            for(Acceso accesoBucleInterno : accesos) {
                if (urlActual.equals(accesoBucleInterno.getPaginaWeb())){
                    contadorAccesosUrlActual++;
                }

            }
            
            if(contadorAccesosUrlActual >= maxNumAccesosHastaAhora){
                webMasSolicitada = urlActual;
                maxNumAccesosHastaAhora = contadorAccesosUrlActual;
            }
        }
        
        if(webMasSolicitada == null) {
            System.out.println("No hay datos de acceso.");
        }
        return webMasSolicitada;
    }
    
    public String clienteConMasAccesosExitosos()
    {
        String ipClienteMasAccesosExitosos = null;
        HashMap<String, Integer> mapa = new HashMap<>(); 
        
        for(Acceso acceso : accesos) {
            if(acceso.getCodigo() == CODIGO_EXITOSO) {
                String ipAccesoActual = acceso.getDireccionIp();
                
                Integer valorAsociadoALaKey = mapa.get(ipAccesoActual);
                if(valorAsociadoALaKey == null){
                    //La ip no estaba en el mapa aun: la metemos asociada a un 1
                    mapa.put(ipAccesoActual,1);
                }
                else {
                    //Ya estaba en el mapa: incrementamos el valor asociado de accesos
                    mapa.put(ipAccesoActual,mapa.get(ipAccesoActual)+1);
                }
            }
        }
        
        int maximoAccesosHastaElMomento = 0;
        
        
        for (String ipActual : mapa.keySet()) {
            if ((mapa.get(ipActual) > maximoAccesosHastaElMomento) || 
            ((mapa.get(ipActual) == maximoAccesosHastaElMomento) && (esMasAltaLaPrimeraIp(ipActual, ipClienteMasAccesosExitosos)))) {
                maximoAccesosHastaElMomento = mapa.get(ipActual);
                ipClienteMasAccesosExitosos = ipActual;
            }
        } 
        
        return ipClienteMasAccesosExitosos;
    }
    
    
    private boolean esMasAltaLaPrimeraIp(String ipPrimera, String ipSegunda) 
    {
        boolean esMasAltaLaPrimera = false;
        
        if (ipSegunda == null) {
            esMasAltaLaPrimera = true;
        }
        else {
        
            String[] octetosIP1 = ipPrimera.split("\\.");
            String[] octetosIP2 = ipSegunda.split("\\.");
        
    
            int indiceOctetoActual = 0;
            boolean yaTengoUnaConclusion = false;
            while(indiceOctetoActual < 4 && !yaTengoUnaConclusion) {
                
                int octetoIP1Actual = Integer.parseInt(octetosIP1[indiceOctetoActual]);
                int octetoIP2Actual = Integer.parseInt(octetosIP2[indiceOctetoActual]);
                
                if (octetoIP1Actual > octetoIP2Actual) {
                    esMasAltaLaPrimera = true;
                    yaTengoUnaConclusion = true;
                }
                else if (octetoIP1Actual < octetoIP2Actual) {
                    yaTengoUnaConclusion = true;
                }
                
                indiceOctetoActual += 1;
            }
        }
        

        return esMasAltaLaPrimera;
    }
    
    
    
    
    


}
