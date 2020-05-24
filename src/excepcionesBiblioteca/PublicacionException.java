/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcionesBiblioteca;

/**
 *
 * @author jorge
 */
public class PublicacionException extends Exception {
    
    public static String DATOS_GENERICOS = "\nTiene que rellenar todos los campos genéricos de la publicación con al menos un caracter.";
    public static String PAGINAS_ERROR = "\nEl numero de paginas ha de ser un numero mayor a cero.";
    public static String PUBLICACION_PRESTADA = "\nEsa publicacion esta en prestamo.";
    public static String DATOS_LIBRO = "\nTiene que rellenar todos los campos ESPECIFICOS PARA EL LIBRO:"+"\n editorial, edicion, localidad, localización, contenido";
    public static String DATOS_DOCUMENTO = "\nTiene que rellenar todos los campos: Tribunal, entidad, departamento";
    public static String DATOS_TESIS = "\nTiene que rellenar el programa de la tesis";
    public static String DATOS_PROYECTO = "\nTiene que rellenar la titulación";
    
    public PublicacionException() {
        super("Se ha producido una excepción en la aplicación.");

    }
    public PublicacionException(String txt) {
        super(txt);
    }
}
