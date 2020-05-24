/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPublicaciones;

import excepcionesBiblioteca.PublicacionException;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jorge
 */
public class Libro extends Publicacion implements Serializable{
    /**Identifica la editorial que ha publicado el libro.*/
private String editorial; 
private String localidad;// Indica la localidad de la publicación.
private String contenido; //En este apartado se reflejara un resumen de la obra.
private String edicion;// En este apartado se reflejara la edición de la obra.
private String localizacion;// En este apartado se reflejara la ubicación del ejemplar físicamente.
private Prestamo prestamoP;

public Libro(String editorial, String localidad, String contenido, String edicion, 
            String localizacion, String codigo, String isbn, String titulo, String Autores,
            Date fechaPublicacion, int paginas, String materia, String coleccion, String Observaciones, 
            String nombreImagen) throws PublicacionException {
        
    super(codigo, isbn, titulo, Autores, fechaPublicacion, paginas, materia, coleccion, Observaciones, nombreImagen);
        
        if (editorial.equals("")||localidad.equals("")||contenido.equals("")||edicion.equals("")||
            localizacion.equals(""))
            throw new PublicacionException (PublicacionException.DATOS_LIBRO);
        
        this.editorial = editorial;
        this.localidad = localidad;
        this.contenido = contenido;
        this.edicion = edicion;
        this.localizacion = localizacion;
        this.prestamoP=null;
    }

   
public Libro(){}

    public void setPrestamoP(Prestamo prestamoP) {
        this.prestamoP = prestamoP;
    }

    public Prestamo getPrestamoP() {
        return prestamoP;
    }
   

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+"LIBRO" +"\n"+ "editorial: " + editorial + "\n"+
                "localidad: " +localidad +"\n"+
                "contenido: " + contenido +"\n"+
                "edicion: " + edicion +"\n"+
                "localizacion: " + localizacion;
    }


}
