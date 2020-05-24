/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionSocios;

import excepcionesBiblioteca.SociosException;
import gestionPublicaciones.Prestamo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 *
 * @author jorge
 */
public class Socio implements Serializable{
 String nombreSocio;// Nombre y apellidos del profesor o el alumno.
 String dni; //DNI del socio profesor o alumno.
String codigoSocio;/* La Biblioteca Universitaria debe asignar un código único a cada
socio. El código debe estar formado por cinco cifras (empezando por 10000) y una
letra P si se trata de un profesor o A si se trata de un alumno.*/
String categoria; // del socio: Se indicara si se trata de alumno o profesor.
Date FechaInscripcion; //La fecha en que el socio se ha dado de alta.

/*Estructura que almacena los prestamos que va teniendo un socio*/
private ArrayList<Prestamo> arrayDePrestamos=new ArrayList<>();//prestamos que tiene el socio

    public Socio(String nombreSocio, String dni, String categoria, Date FechaInscripcion) throws SociosException{
        boolean cumplePatronDni=Pattern.matches(("\\d{8}"+"\\p{Alpha}{1}"), dni);
        if (!cumplePatronDni) throw new SociosException (SociosException.DATOS_DNI);
        if (nombreSocio.equals("")||categoria.equals("")||dni.equals(""))throw new SociosException (SociosException.DATOS_GENERICOS);
        this.nombreSocio = nombreSocio;
        this.dni = dni;
        
        this.categoria = categoria;
        if (categoria.equals("PROFESOR")){
        this.codigoSocio = UtilSocios.generadorDeCodigoP();
        }
         if (categoria.equals("ALUMNO")){
        this.codigoSocio = UtilSocios.generadorDeCodigoA();
        }
        this.FechaInscripcion = FechaInscripcion;
    }

    

    public Date getFechaInscripcion() {
        return FechaInscripcion;
    }

    public void setFechaInscripción(Date FechaInscripcion) {
        this.FechaInscripcion = FechaInscripcion;
    }

    public ArrayList<Prestamo> getArrayDePrestamos() {
        return arrayDePrestamos;
    }
    
    
    /**Devuelve en una cadena lcon información textual de los prestamos almacenados en el Arraylist
     * 
     * @return prestamos devuelve un String con la información importante de los prestamos asociados al socio
     */
    public String getListadoPrestamos() {
        String prestamos = "";
        Iterator<Prestamo> li=arrayDePrestamos.iterator();
        for (int i=1;i<=(arrayDePrestamos.size());i++){
        if (li.hasNext()) {
            Prestamo objPrestamo = (Prestamo) li.next();
            if (objPrestamo != null) {
                prestamos=prestamos+"\n"+i+" "+(objPrestamo.datosParaSocio());
                //System.out.println(objproducto.toString());
            } 
            
        }else {
                prestamos="No tiene prestamos.";
            }
        }
        return prestamos;
    }
    
    /**Método que inserta un nuevo prestamo en el ArrayList
     * 
     * @param prestamo El nuevo prestamo que tiene el socio
     */
    public void anotarPrestamo(Prestamo prestamo) {
        arrayDePrestamos.add(prestamo);
        
    }
    
    /**Método para eliminar el prestamo una vez devuelto la publicación
     * 
     * @param prestamo objeto que se ha de eliminar del ArrayList
     */
     public void eliminarPrestamo(Prestamo prestamo) {
        arrayDePrestamos.remove(prestamo);
        
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoría) {
        this.categoria = categoría;
    }

    public String getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(String codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }
    
    /**Formatea un date a string para presentarla al usuario
     * 
     * @param f fecha a formatear
     * @return String con este formato "dd/MM/yyyy"
     */
    public String formateoFechaIn(Date f){
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fFormateada=formatoFecha.format(f);
        System.out.println(fFormateada);
        return fFormateada;
    }
    

    @Override
    public String toString() {
        return  "Nombre " + nombreSocio + 
                "\n"+" dni " + dni +
                "\n"+ " codigoSocio " + codigoSocio +
                "\n"+" categoría " + categoria + 
                "\n"+" FechaInscripción " + formateoFechaIn(FechaInscripcion);
    }

}
