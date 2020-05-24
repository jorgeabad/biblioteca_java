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
public class Tesis extends DocumentoUniversitario implements Serializable{
    private String Programa;// Indica el programa de doctorado en el que se adscribe la tesis.

    public Tesis(String Programa, String tribunal, String entidad, String departamento,
            double calificacion, Date fechaLectura, String codigo, String isbn, String titulo,
            String Autores, Date fechaPublicacion, int paginas, String materia, String coleccion, 
            String Observaciones, String nombreImagen) throws PublicacionException {
        super(tribunal, entidad, departamento, calificacion, fechaLectura,
                codigo, isbn, titulo, Autores, fechaPublicacion, paginas, materia, coleccion, Observaciones, nombreImagen);
       if (Programa.equals(""))throw new PublicacionException (PublicacionException.DATOS_TESIS);
        this.Programa = Programa;
    }

  

    public String getPrograma() {
        return Programa;
    }

    public void setPrograma(String Programa) {
        this.Programa = Programa;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+"TESIS: "+ "\n"+ "Programa: " + Programa;
    }


}

