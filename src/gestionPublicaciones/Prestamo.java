/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPublicaciones;
import gestionSocios.Socio;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 *
 * @author jorge
 */
public class Prestamo implements Serializable {
  /*Objeto socio asociado al préstamo*/
  Socio prestadoS;  
  /*Publicacion Prestada*/
  Publicacion prestadoP;
  /*Fecha en la que comienza el préstamo*/
  Date FechaInicio; 
  /*Fecha en la que finaliza el préstamo*/
  Date FechaFin;
  /*dias que se presta la publicación en función del la clase de publicación y categoría del socio*/
  int plazo;

    public Publicacion getPrestadoP() {
        return prestadoP;
    }

    public Socio getPrestadoS() {
        return prestadoS;
    }
  
  /**Metodo que suma los dias a una fecha
   * 
   * @param f la fecha incial del prestamo
   * @param dias los dias que hay que añadir a f
   * @return devuelve la fecha de finalización del préstamo, clase Date
   */
public static Date sumarDiasAFecha(Date f, int dias) {  
    Calendar c = Calendar.getInstance();
    c.setTime(f);
    c.add(Calendar.DATE, dias);
    return c.getTime(); 
    }

    /**Constructor
     * en función de la categoria del socio  y de la clase de publicación se asignan los plazos
     * @param prestadoS Objeto Socio
     * @param prestadoP  Objeto Publicacion
     * @param FechaInicio Date en la que se inicia el préstamo
     */
    public Prestamo(Socio prestadoS, Publicacion prestadoP, Date FechaInicio) {
        this.prestadoS = prestadoS;
        this.prestadoP = prestadoP;
        this.FechaInicio = FechaInicio;
       
        /*en función de la categoria del socio  y de la clase de publicación 
         * se asignan los plazos*/
        if (prestadoS.getCategoria().equals("PROFESOR")){
            if (prestadoP.getClass().getSimpleName().equals("Libro")){
            this.plazo=10;
            }
            if (prestadoP.getClass().getSimpleName().equals("Revista")){
            this.plazo=6;
            }
            if (prestadoP.getClass().getSuperclass().getSimpleName().equals("DocumentoUniversitario")){
            this.plazo=12;
            }
        }
        if (prestadoS.getCategoria().equals("ALUMNO")){
            if (prestadoP.getClass().getSimpleName().equals("Libro")){
            this.plazo=6;
            }
            if (prestadoP.getClass().getSimpleName().equals("Revista")){
            this.plazo=3;
            }
            if (prestadoP.getClass().getSuperclass().getSimpleName().equals("DocumentoUniversitario")){
            this.plazo=10;
            }
        }
        
        this.FechaFin = sumarDiasAFecha(this.FechaInicio, this.plazo);
    }

    
    /*Formateo de fechas para presentar por pantalla*/
    public String formateoFechas(Date f){
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fFormateada=formatoFecha.format(f);
        System.out.println(f);
        System.out.println(fFormateada);
        return fFormateada;
    }
    
    /**Método información textual
     * 
     * @return devuelve datos de la publicación para informar al socio y los plazos y fechas
     */
    public String datosParaSocio(){
    return "Prestamo: "+ "Titulo: "+prestadoP.getTitulo()+" Codigo: "+prestadoP.getCodigo()+"\n"+
            "FechaInicio: " + formateoFechas(FechaInicio) + ", FechaFin: " + formateoFechas(FechaFin) + ", dias prestado: " + plazo+"\n"; 
    }
    /**Información textual para presentar en la publicación
     * 
     * @return String con los datos del socio al que esta prestada y los fechas y plazos.
     */
    public String datosParaPublicacion(){
    return "Prestado a :"+ prestadoS.toString()+"\n"+
            "Fecha de Inicio: " + formateoFechas(FechaInicio) + ", Fecha Fin Prestamo: " + formateoFechas(FechaFin) + ", plazo: " + plazo; 
    }
    
    @Override
    public String toString() {
        return "Prestamo: " + "prestadoS=" + prestadoS + ", prestadoP=" + prestadoP + ", FechaInicio=" + FechaInicio + ", FechaFin=" + FechaFin + ", plazo=" + plazo + '}';
    }
  
  
}
