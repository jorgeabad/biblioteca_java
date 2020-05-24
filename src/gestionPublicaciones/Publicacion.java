/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPublicaciones;

import excepcionesBiblioteca.BibliotecaException;
import excepcionesBiblioteca.PublicacionException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author jorge
 */

/*Clase Publicacion*/
public abstract class Publicacion implements Serializable {

// Atributos de la publicación 
private String codigo;
private String isbn;
private String titulo;
private String Autores;
private Date fechaPublicacion;
private int paginas;
private String materia;
private String coleccion;
private String Observaciones;
/*Estado de la publicación si no se en cuentra prestado será true*/
private boolean estado;
/*Asociado a un prestamos*/
private Prestamo datosPrestamo;
private String nombreImagen;


    /**Constructor
     * 
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
     * @throws PublicacionException el valor de alguna parámetro no es correcto.
     */
    public Publicacion (String codigo, String isbn, String titulo, String Autores,
            Date fechaPublicacion, int paginas, String materia, String coleccion,
            String Observaciones, String nombreImagen) throws PublicacionException{
        if (codigo.equals("")||isbn.equals("")||Autores.equals("")||titulo.equals("")||
            materia.equals("")||coleccion.equals("")||Observaciones.equals("")||nombreImagen.equals(""))throw new PublicacionException (PublicacionException.DATOS_GENERICOS);
        if (paginas<=0)throw new PublicacionException (PublicacionException.PAGINAS_ERROR);
        this.codigo = codigo;
        this.isbn = isbn;
        this.titulo = titulo;
        this.Autores = Autores;
        this.fechaPublicacion = fechaPublicacion;
        this.paginas = paginas;
        this.materia = materia;
        this.coleccion = coleccion;
        this.Observaciones = Observaciones;
        this.estado = true;
        this.datosPrestamo = null;
        this.nombreImagen=nombreImagen;
    }
Publicacion(){}

    public String getNombreImagen() {
        return nombreImagen;
    }
    
    public String getAutores() {
        return Autores;
    }

    public void setAutores(String Autores) {
        this.Autores = Autores;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getColeccion() {
        return coleccion;
    }

    public void setColeccion(String coleccion) {
        this.coleccion = coleccion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Prestamo getDatosPrestamo() {
        return datosPrestamo;
    }

    /**Método para realizar un prestamo de la publicación a un socio
     * 
     * @param datosPrestamo un objeto de la clase prestamo compuesta por un socio, una publicación
     */
    public void prestarPublicacion(Prestamo datosPrestamo) {
        /*Asignamos el objeto prestamos al Socio que compone el Prestamo*/
        datosPrestamo.prestadoS.anotarPrestamo(datosPrestamo);
        /*Asignamos el objeto prestamo al objeto prestamo de la publicacion*/
        this.datosPrestamo = datosPrestamo;
        /*El estado pasa a false pues a quedado prestado*/
        this.estado=false;
    }
    
    /**Método para devolver un publicación
     * 
     * @param fechaDevolucion fecha en la que se va ha realizar la devolución
     * @return mensaje devueve una cadena informando si ha habido retraso, los dias de retraso y la fecha en la que se ha devuelto.
     * @throws BibliotecaException 
     */
    public String devolverPublicacion(Date fechaDevolucion)throws BibliotecaException{
        /*Almacenara la información de la devolución*/
        String mensaje;
        /*Compera la fecha de la devolución con la fecha en la que finalizaba 
         * el prestamo asociado a la publicacion, si la fecha de devolucion es mayor
         * se calcula la diferencia de las dos fechas en dias y se devuelve el mensaje oportuno.
         */
        if (this.datosPrestamo.FechaFin.compareTo(fechaDevolucion)<0){
            /*fecha finalización prestamo*/
            Date f1=datosPrestamo.FechaFin;
            /*fecha en la que se ha devuelto*/
            Date f2=fechaDevolucion;
            /*diferencia en milisegundos*/
            long demora = f2.getTime() - f1.getTime();
            /*cast a int pasamos a dias*/
            int dias=((int)(demora/(3600*24*1000)));
            /*numero de dias de retraso reales ajustando las horas redondeando
             *la devolucion se contea siempre a las 00:00 del dia que se devuelve.
             */
            int demoraEnDias=dias+1;

            mensaje="la devolución hecha el día "+formateoFechas(fechaDevolucion)+" fuera de plazo en "+demoraEnDias+" días";
        this.datosPrestamo.prestadoS.eliminarPrestamo(datosPrestamo);    
        this.datosPrestamo=null;
        this.estado=true;
        
    }else{
        mensaje="la devolucion está hecha en plazo, el día "+formateoFechas(fechaDevolucion);
        this.datosPrestamo.prestadoS.eliminarPrestamo(datosPrestamo);
         this.datosPrestamo=null;
        this.estado=true;
        }
        return mensaje;
    }
    
    /**Método para formatear una fecha al formato de dd/MM/yyyy
     * 
     * @param f la fecha que queremos formatear para presentarla
     * @return devuelve un String con el dd/MM/yyyyy dia/mes/año
     */
public String formateoFechas(Date f){
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fFormateada=formatoFecha.format(f);
        System.out.println(fFormateada);
        return fFormateada;
    }

/**Información textual del prestamo en función de su estado
 * 
 * @param estado true representa publicacion libre y false prestada  
 * @return El mensaje testual Libre o Prestado.
 */

public String estadotoString(boolean estado){
    String strEstado;
    if(estado){
    strEstado="Libre";
    }else{
    strEstado="Prestado";
    }
        return strEstado;
    }
    
/**Método que devuelve información textual del prestamo asociado 
 * 
 * @return devuelve la informacion que hay en el prestamo 
 * asociado referente a los plazos y a los datos del socio al que se le ha prestado
 */    
public String dameprestamo(){
    
return this.datosPrestamo.datosParaPublicacion();
}

    @Override
    /**Devuelve información textual de la publicación
     * @return los datos de la publicación formateados y en un String.
     */
     
    public String toString() {
        return "Codigo: " + codigo + "\n"+"isbn: " + isbn + "\n"+
                "Titulo: " + titulo + "\n"+"Autores: " + Autores +"\n"+
                "Fecha de la Publicacion: " + formateoFechas(fechaPublicacion) +"\n"+ "nº paginas: " +
                paginas +"\n"+ "Materia: " + materia + "\n"+"Coleccion: " + coleccion +"\n"+
                "Observaciones: " + Observaciones +"\n"+ "Estado: " + estadotoString(estado);
    }



}