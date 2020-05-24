/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPublicaciones;

import excepcionesBiblioteca.PublicacionException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author jorge
 */
public class Revista extends Publicacion implements Serializable{
    /*Se indica si es trimestral, semestral o anual*/
  private String periodicidad;
  /*Numero del volumen*/
  private int volumen; 
  /*Indica el número de la publicación*/
  private int numero;
  /*Se indicara el año desde el cual esta disponible la revista.*/
  private Date AnyoDisponible;
  /*En este apartado se reflejara la ubicación del ejemplar físicamente.*/
  private String Localizacion; 
/**Constructor
 * 
 * @param periodicidad
 * @param volumen
 * @param numero
 * @param AnyoDisponible
 * @param Localizacion
 * @param codigo
 * @param isbn
 * @param titulo
 * @param Autores
 * @param fechaPublicacion
 * @param paginas
 * @param materia
 * @param coleccion
 * @param Observaciones
 * @param nombreImagen
 * @throws PublicacionException 
 */
    public Revista(String periodicidad, int volumen, int numero, Date AnyoDisponible, 
            String Localizacion, String codigo, String isbn, String titulo, String Autores,
            Date fechaPublicacion, int paginas, String materia, String coleccion, String Observaciones, 
            String nombreImagen) throws PublicacionException {
        super(codigo, isbn, titulo, Autores, fechaPublicacion, paginas, materia, coleccion, Observaciones, nombreImagen);
        this.periodicidad = periodicidad;
        this.volumen = volumen;
        this.numero = numero;
        this.AnyoDisponible = AnyoDisponible;
        this.Localizacion = Localizacion;
    }

    

    public Date getAnyoDisponible() {
        return AnyoDisponible;
    }

    public void setAnyoDisponible(Date AnyoDisponible) {
        this.AnyoDisponible = AnyoDisponible;
    }

    public String getLocalizacion() {
        return Localizacion;
    }

    public void setLocalización(String Localización) {
        this.Localizacion = Localización;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }
    
    /**Método que formate un objeto Date a un String
     * 
     * @param f fecha a formatear
     * @return String en formato yyyyy año
     */
    public String formateoFechasRevista(Date f){
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy", Locale.getDefault());
        String fFormateada=formatoFecha.format(f);
        System.out.println(fFormateada);
        return fFormateada;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+"REVISTA" + "\n"+"periodicidad: " + periodicidad + "\n"+"volumen: " + volumen +"\n"+
                "numero: " + numero + "\n"+" Año Disponible: " + formateoFechasRevista(AnyoDisponible) +"\n"+
                "Localización: " + Localizacion ;
    }
    

}
