public class Acceso
{
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minutos;
    
    /**
     * Acceso Constructor
     *
     * @param momentoDeAcceso A parameter
     */
    public Acceso(String momentoDeAcceso)
    {
        String[] partesDeMomentoAcceso = momentoDeAcceso.split(" ");
        ano = Integer.parseInt(partesDeMomentoAcceso[0]);
        mes = Integer.parseInt(partesDeMomentoAcceso[1]);
        dia = Integer.parseInt(partesDeMomentoAcceso[2]);
        hora = Integer.parseInt(partesDeMomentoAcceso[3]);
        minutos = Integer.parseInt(partesDeMomentoAcceso[4]);
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
}