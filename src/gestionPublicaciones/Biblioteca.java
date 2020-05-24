/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPublicaciones;

import excepcionesBiblioteca.BibliotecaException;
import java.io.*;
import java.text.DateFormat;
import java.util.*;
import javax.swing.JOptionPane;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import com.itextpdf.text.pdf.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jorge
 */
public class Biblioteca {
    
   private static  ArrayList<Publicacion> arrayBiblioteca=new ArrayList<>();
   private static Publicacion objPublicacion;

    public static ArrayList<Publicacion> getArrayBiblioteca() {
        return arrayBiblioteca;
    }

    public static void setArrayBiblioteca(ArrayList<Publicacion> arrayBiblioteca) {
        Biblioteca.arrayBiblioteca = arrayBiblioteca;
    }

    public static Publicacion getObjPublicacion() {
        return objPublicacion;
    }

    public static void setObjPublicacion(Publicacion objPublicacion) {
        Biblioteca.objPublicacion = objPublicacion;
    }
   
   public static ArrayList<Publicacion> ordenaBiblioteca() {
        Comparator compararCodigo = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Publicacion p1 = (Publicacion) o1;
                Publicacion p2 = (Publicacion) o2;
                return (p1.getCodigo()).compareTo((p2.getCodigo()));          
            }  
        };
        Collections.sort(arrayBiblioteca, compararCodigo);
        return arrayBiblioteca;
    }
   
    public static Publicacion busquedaCodigo(String codigo)throws BibliotecaException{
         Comparator compararCodigo = new Comparator() {
             @Override
            public int compare(Object o1, Object o2) {
                Publicacion p1 = (Publicacion) o1;
                Publicacion p2 = (Publicacion) o2;
                return p1.getCodigo().compareTo(p2.getCodigo());           
            }
        };
        //Ordenamos el array por su codigo
        Collections.sort(arrayBiblioteca, compararCodigo);
        //creamos una producto con el codigo a buscar.
        Publicacion p = new Publicacion() {};
        p.setCodigo(codigo);
        int pos = Collections.binarySearch(arrayBiblioteca, p, compararCodigo);
        if (pos >= 0) {
            objPublicacion = arrayBiblioteca.get(pos);
        } else {
            throw new BibliotecaException(BibliotecaException.PUBLICACION_FUERA);
        }
        return objPublicacion;
   }
   
   public static boolean estaElCodigo(String codigo){
         Comparator compararCodigo = new Comparator() {
             @Override
            public int compare(Object o1, Object o2) {
                Publicacion p1 = (Publicacion) o1;
                Publicacion p2 = (Publicacion) o2;
                return (p1.getCodigo()).compareTo((p2.getCodigo()));         
            }
        };
        //Ordenamos el array por su codigo
        Collections.sort(arrayBiblioteca, compararCodigo);
        //creamos una producto con el codigo a buscar.
        Publicacion p = new Libro();
        p.setCodigo(codigo);
        int pos = Collections.binarySearch(arrayBiblioteca, p, compararCodigo);
        if (pos >= 0) {
            return true;
        } else {
            return false;
        }
       
   }
    
    //Metodos para dar de alta y baja productos en el ArrayList almacen.
    public static void altaPublicacion(Publicacion publicacion)throws BibliotecaException {
         if (!estaElCodigo(publicacion.getCodigo())){
            arrayBiblioteca.add(publicacion);
        } else {
             throw new BibliotecaException(BibliotecaException.PUBLICACION_DENTRO);
        }
    }
    
    public static void bajaProducto(Publicacion publicacion)throws BibliotecaException{
        if (arrayBiblioteca.contains(publicacion)) {
            arrayBiblioteca.remove(publicacion);
        } else {
             throw new BibliotecaException(BibliotecaException.PUBLICACION_FUERA);
        }
    }
    
     public static void guardarDatos() {
        try {
            //Si hay datos los guardamos...
            if (!arrayBiblioteca.isEmpty()) {
                try (FileOutputStream ostreamPer = new FileOutputStream("Publicaciones.dat")) {
                    ObjectOutputStream oosProd = new ObjectOutputStream(ostreamPer);
                    //guardamos el array de personas
                    oosProd.writeObject(arrayBiblioteca);
                }
            } else {
                System.out.println("Error: No hay datos...");
            }

        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//fin 
     
        public static void cargarDatos() {
        try {
            try (FileInputStream istreamPer = new FileInputStream("Publicaciones.dat")) {
                ObjectInputStream oisProd = new ObjectInputStream(istreamPer);
                arrayBiblioteca = (ArrayList) oisProd.readObject();
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//fin cargarDatos

          
        
        
    @Override
    public String toString() {
        return "Biblioteca{" + '}';
    }
    
}
