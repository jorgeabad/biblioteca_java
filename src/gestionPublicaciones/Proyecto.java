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
public class Proyecto extends DocumentoUniversitario implements Serializable{
    /* Indica la titulación a la que pertenece el proyecto.*/
   private String Titulacion;

   /**Constructor
    * 
    * @param Titulacion
    * @param tribunal
    * @param entidad
    * @param departamento
    * @param calificación
    * @param fechaLectura
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
    public Proyecto(String Titulacion, String tribunal, String entidad, String departamento, double calificación, Date fechaLectura, String codigo, String isbn, String titulo, String Autores, Date fechaPublicacion, int paginas, String materia, String coleccion, String Observaciones, String nombreImagen) throws PublicacionException {
        super(tribunal, entidad, departamento, calificación, fechaLectura, codigo, isbn, titulo, Autores, fechaPublicacion, paginas, materia, coleccion, Observaciones, nombreImagen);
       if (Titulacion.equals(""))throw new PublicacionException (PublicacionException.DATOS_PROYECTO);
        this.Titulacion = Titulacion;
    }
    
   
    public String getTitulación() {
        return Titulacion;
    }

    public void setTitulación(String Titulación) {
        this.Titulacion = Titulación;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+"PROYECTO" + "\n"+"Titulación: " + Titulacion;
    }
   
   
}
