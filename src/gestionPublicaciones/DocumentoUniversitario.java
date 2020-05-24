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

/*Clase Padre de la que heredan las Tesis y los Proyectos*/
public abstract class DocumentoUniversitario extends Publicacion implements Serializable {
    
/*Se indica el tribunal del proyecto.*/
private String tribunal; 
/* Nombre del Organismo o Universidad.*/
private String entidad;
/*Indica el departamento responsable*/
private String departamento;
/*Indica la nota sacada.*/
private double calificacion;
/*Indica la fecha de lectura*/
private Date fechaLectura;

    /**Constructor de la clase, hereda de Publicacion
     * 
     * @param tribunal 
     * @param entidad
     * @param departamento
     * @param calificacion
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
    public DocumentoUniversitario(String tribunal, String entidad, String departamento,
            double calificacion, Date fechaLectura, String codigo, String isbn, 
            String titulo, String Autores, Date fechaPublicacion, int paginas, String materia, 
            String coleccion, String Observaciones, String nombreImagen) throws PublicacionException {
        super(codigo, isbn, titulo, Autores, fechaPublicacion, paginas, materia, coleccion, Observaciones, nombreImagen);
        
         if (tribunal.equals("")||entidad.equals("")||departamento.equals(""))
            throw new PublicacionException (PublicacionException.DATOS_DOCUMENTO);
        this.tribunal = tribunal;
        this.entidad = entidad;
        this.departamento = departamento;
        this.calificacion = calificacion;
        this.fechaLectura = fechaLectura;
    }

   

    public double getCalificación() {
        return calificacion;
    }

    public void setCalificación(double calificación) {
        this.calificacion = calificación;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public Date getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(Date fechaLectura) {
        this.fechaLectura = fechaLectura;
    }

    public String getTribunal() {
        return tribunal;
    }

    public void setTribunal(String tribunal) {
        this.tribunal = tribunal;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+
                "tribunal: " + tribunal + "\n"+
                "entidad: " + entidad + "\n"+
                "departamento: " + departamento + "\n"+
                "calificación: " + calificacion + "\n"+
                "fechaLectura: " + formateoFechas(fechaLectura);
    }


}
