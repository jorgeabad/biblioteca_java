/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionSocios;

import excepcionesBiblioteca.SociosException;
import java.io.*;
import java.util.HashMap;

/**
 *
 * @author jorge
 */


/*Clase para la gestión de socios*/
public class UtilSocios implements Serializable {
    /*Atributo que al macena un objeto de la clase socio*/
    static Socio objSocio;
    
    /*tabla hash para almacenar socios cuya clave será el String codigo*/
    static HashMap<String,Socio> mapaSocios=new HashMap<>();
    /*Atributo que almacena la parte entera del codigo para generar codigos para profesores*/
    static int p;
    /*Atributo que almacena la parte entera del codigo para generar codigos para alumnos*/
    static int a;
    
    
    
    /*@return parte entera del codigo de alumno*/
    public static int getA() {
        return a;
    }
     /*@return parte entera del codigo de profesor*/
    public static int getP() {
        return p;
    }
    /*@return devuelve un objeto un mapa hash donde se encuentran los socios almacenados*/
    public static HashMap<String, Socio> getMapaSocios() {
        return mapaSocios;
    }
    
    public static void setMapaSocios(HashMap<String, Socio> mapaSocios) {
        UtilSocios.mapaSocios = mapaSocios;
    }
   
    
    /**Método para añadir nuevos socios
     * @param s socio a dar de alta
     * @throws SociosException El socio ya esta dado de alta, en funcion de su codigo.
     */
    public static void altaSocio(Socio s)throws SociosException{
    //Si ya hay un objeto en la tabla con ese identificador lanzamos la excepción
    if (mapaSocios.containsKey(s.getCodigoSocio())) throw new SociosException(SociosException.MAPA_CON_SOCIO);
        else mapaSocios.put(s.getCodigoSocio(), s);
    }
    
    /**Método para dar de baja a un socio mediante su codigo
     * @param codigoSocio El codigo de socio que se desea eliminar
     * @throws SociosException Ese codigo no se encuentra en almacenado en el mapa
     */
    public static void bajaSocio(String codigoSocio)throws SociosException{
    if (mapaSocios.containsKey(codigoSocio)){
         /*elimina al socio*/
    mapaSocios.remove(codigoSocio);
    /*Si no hay un objeto en la tabla con ese codigo lanzamos la excepción*/
    } else throw new SociosException(SociosException.MAPA_SIN_SOCIO);
    }
    
    /**Método para buscar un socio por su codigo dentro del mapa
     * 
     * @param codigoSocio El codigo que queremos buscar
     * @return objSocio devuelve un objeto de la clase socio, un socio.
     * @throws SociosException El codigo buscado no se encuentra en el mapa.
     */
    public static Socio buscarSocio(String codigoSocio)throws SociosException{
    //Si ya hay un objeto en la tabla con ese identificador lanzamos la excepción
    if (mapaSocios.containsKey(codigoSocio)){
    objSocio=mapaSocios.get(codigoSocio);
    return objSocio;
    } else throw new SociosException(SociosException.MAPA_SIN_SOCIO);
  }
    
    /**Método para generar los codigos que se asignaran a los profesores
     * si el valor de p es menor a 10000 no hay profesores y se le asigna al primero
     * ese valor, en caso contrario se le suma el valor 1. Se usa el metodo cundo se instancie un objeto profesor.
     * @return codigoP una cadena formado por el valor de p mas el caracter 'P' que identifica a los profesores
     */
    public static String generadorDeCodigoP(){
        String codigoP;
        if (p<10000){
        p=10000;
        }
        else{
        p++;
       
        }
         codigoP=p+"P";
        return codigoP;
    }
    
     /**Método para generar los codigos que se asignaran a los alumnos
     * si el valor de p es menor a 10000 no hay profesores y se le asigna al primero
     * ese valor, en caso contrario se le suma el valor 1. Se usa el metodo cundo se instancie un objeto alumno.
     * @return codigoA una cadena formado por el valor de a mas el caracter 'A' que identifica a los alumnos
     */
     public static String generadorDeCodigoA(){
         String codigoA;
        if (a<10000){
        a=10000;
        }
        else{
        a++;
        }
    codigoA=a+"A";
    return codigoA;
    }
     
     /**Método para guardar en archivo el HashMap mapaSocios y los atributos a, y p
      * contadores que almacenan el valor de los socios creados, como alumno y profesores
      * y permiten generar los codigos para ellos
      */
     public static void guardarDatosSocios() {
        try {
            //Si hay datos los guardamos...
            if (!mapaSocios.isEmpty()) {
                /**Creamos un stream para guardar los datos en el archivo socios.dat*/
                try (FileOutputStream ostreamSocios = new FileOutputStream("Socios.dat")) {
                    /*Creamos un objeto para cada atributo a almacenar*/
                    ObjectOutputStream oosMapa = new ObjectOutputStream(ostreamSocios);
                    ObjectOutputStream oosa = new ObjectOutputStream(ostreamSocios);
                    ObjectOutputStream oosp = new ObjectOutputStream(ostreamSocios);
                    /*Guardamos los tres atributos en el archivo*/
                    oosMapa.writeObject(mapaSocios);
                    oosp.writeObject(p);
                    oosa.writeObject(a);
                }
            } else {
                //no hay datos para almacenar
                System.out.println("Error: No hay datos...");
            }

        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//fin 
      
     
        /**Método para recuperar los datos desde el archivo Socios.dat*/
         public static void cargarDatosSocios() {
        try {
            /*leer bytes de el fichero Socios.dat*/
            try (FileInputStream istreamSocios = new FileInputStream("Socios.dat")) {
                /*Instanciamos tres objetos para recuperar los datos*/
                ObjectInputStream oisMapa = new ObjectInputStream(istreamSocios);
                ObjectInputStream oisp = new ObjectInputStream(istreamSocios);
                ObjectInputStream oisa = new ObjectInputStream(istreamSocios);
                /*Asignamos a los atributos el valor leido convirtiendo los objetos
                 * a la clase correspondiente*/
                mapaSocios = (HashMap) oisMapa.readObject();
                p=(Integer) oisp.readObject();
                a=(Integer)oisa.readObject();
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//fin cargarDatos
   
}
