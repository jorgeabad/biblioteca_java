/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcionesBiblioteca;

/**
 *
 * @author jorge
 */
public class BibliotecaException extends Exception {
    
    
    public static String PUBLICACION_DENTRO = "\nEse codigo de publicacion ya exixste en la Biblioteca.";
    public static String PUBLICACION_FUERA = "\nEse codigo de publicación no se encuentra en la biblioteca.";
    public static String PUBLICACION_PRESTADA = "\nEsa publicacion esta en prestamo.";
    

    public BibliotecaException() {
        super("Se ha producido una excepción en la aplicación.");

    }
    public BibliotecaException(String txt) {
        super(txt);
    }
}
