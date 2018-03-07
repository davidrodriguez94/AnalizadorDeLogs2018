public class Acceso
{
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minutos;
    private String paginaWeb;
    private String ip;
    private int codigo;

    /**
     * Acceso Constructor
     *
     * @param momentoDeAcceso A parameter
     */
    public Acceso(String momentoDeAcceso)
    {
        String[] partesDeMomentoAcceso = momentoDeAcceso.split(" ");
        ip = partesDeMomentoAcceso[0];
        ano = Integer.parseInt(partesDeMomentoAcceso[1].substring(1,partesDeMomentoAcceso[1].length()));
        mes = Integer.parseInt(partesDeMomentoAcceso[2]);
        dia = Integer.parseInt(partesDeMomentoAcceso[3]);
        hora = Integer.parseInt(partesDeMomentoAcceso[4]);
        minutos = Integer.parseInt(partesDeMomentoAcceso[5].substring(0,partesDeMomentoAcceso[0].length()-1));
        paginaWeb = partesDeMomentoAcceso[6];
        codigo = Integer.parseInt(partesDeMomentoAcceso[7]);
    }

    public int getAno() 
    {
        return ano;
    }

    public int getMes()
    {
        return mes;
    }

    public int getDia()
    {
        return dia;
    }

    public int getHora()
    {
        return hora;
    }

    public int getMinutos()
    {
        return minutos;
    }
    
    public String getIp()
    {
        return ip;
    }

    public String getPaginaWeb()
    {
        return paginaWeb;
    }
    
    public int getCodigo()
    {
        return codigo;
    }
}