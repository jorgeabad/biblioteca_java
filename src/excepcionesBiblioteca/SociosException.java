/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcionesBiblioteca;

/**
 *
 * @author jorge
 */
public class SociosException extends Exception {
    
    public static String DATOS_GENERICOS = "\nTiene que rellenar todos los campos y seleccionar una categoría para dar de alta a un socio.";
    public static String MAPA_SIN_SOCIO= "\nEse CODIGO NO ESTA DADO DE ALTA.";
    public static String MAPA_CON_SOCIO= "\nEse socio ya está dado de alta.";
    public static String DATOS_DNI = "\nEl dni se debe cumplimentar con 8 digitos (D) y una letra (L) todo junto DDDDDDDDL.";
    

    public SociosException() {
        super("Se ha producido una excepción en la aplicación.");

    }
    public SociosException(String txt) {
        super(txt);
    }
}