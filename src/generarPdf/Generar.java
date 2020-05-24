/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generarPdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gestionPublicaciones.Prestamo;
import gestionPublicaciones.Publicacion;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author jorge
 */
public class Generar {
    
    public static void generaDevolucionPdf(Prestamo objPrestamo, String mensaje)throws Exception{
    /*Primero definimos todas las cadenas del encabezado del documento y los demas datos a utilizar*/
        String encabezado="    FICHA PUBLICACION DE LA BIBLIOTECA UNIVERSITARIA"+"\n"+ " tfn 918888888--www.uah.es---CIF-12345678B"+"\n"+"\n"+"\n"+"\n" +"\n";
     /*colocaremos tambien la fecha de creacion*/
      Calendar c = Calendar.getInstance();

        Date d = c.getTime();
        Locale locIT = new Locale("es");
        DateFormat dfIT = DateFormat.getDateTimeInstance(2, 2, locIT);
        String fecha1=dfIT.format(d);

        /*Fuente utilizada para formatear el encabezado en este caso sera arial 12 en negrita*/
        Font fuente= new Font(Font.getFamily("ARIAL"), 10, Font.BOLD);
    /*Definimos un texto que se incluira antes de la tabla*/
String datosPublicacion=objPrestamo.getPrestadoP().toString();
String datosSocio=objPrestamo.datosParaPublicacion();
String devolucion=mensaje;
/*Ahora almacenaremos la imagen que ira en la parte superior y la alineamos a la izquierda*/
Image imagen=Image.getInstance(("anagrama2.jpg"));
imagen.scaleToFit(150, 150);
//imagen.setAlignment(Chunk.ALIGN_RIGHT);
imagen.setAlignment(Image.LEFT | Image.TEXTWRAP);
 
 /*Ahora que ya tenemos el contenido del documento empezaremos a estructurarlo*/

try{ /*Primero pasaremos nuestras cadenas a elementos de iText*/
/*definimos una frase que sera el string encabezado y le aplicamos la fuente*/
Paragraph linea = new Paragraph(encabezado,fuente);
 
/*Ahora definimos la tabla donde el arguemento recibido indica el numero de columnas y la propiedad setWidthPercentage permite indicarle que ocupe todo el ancho de la pagina*/
PdfPTable tabla=new PdfPTable(1); tabla.setWidthPercentage(100);


/*Ahora que ya tenemos todos los elemtos los agregamos al documento, para ello primeramente definimos un docuemnto e indicando el tamaño*/
Document documento = new Document(PageSize.LETTER);
/*Definimos el nombre del archivo de salida con extension .PDF*/ String file=("devolucion/"+objPrestamo.getPrestadoP().getCodigo()+".pdf");
 /*a traves del siguiente metodo (getInstance)y un flujo de salida del paquete .io asociamos el documento de iText con el archivo de java*/
 PdfWriter.getInstance(documento, new FileOutputStream(file));
 /*Definimos las celdas que seran los encabezados de la tabla*/
 PdfPCell celda1 =new PdfPCell (new Paragraph("Datos de la publicacion",FontFactory.getFont("arial",10,Font.BOLD,BaseColor.RED)));
 PdfPCell celda2 =new PdfPCell (new Paragraph("Datos del socio y fechas",FontFactory.getFont("arial",10,Font.BOLD,BaseColor.RED)));
 PdfPCell celda3 =new PdfPCell (new Paragraph("Información de la devolución",FontFactory.getFont("arial",10,Font.BOLD,BaseColor.RED)));
 

/*Abrimos el documento y agregamos los elementos en el orden que deben aparecer*/
 documento.open();
documento.add(imagen);
documento.add(linea);
;
try
{
	Image Foto = Image.getInstance("imagenes/"+objPrestamo.getPrestadoP().getNombreImagen());
	Foto.scaleToFit(220, 220);
	Foto.setAlignment(Chunk.ALIGN_RIGHT);
        documento.add(Foto);
}
catch ( Exception e )
{
	e.printStackTrace();
}


tabla.addCell(celda1);
tabla.addCell(datosPublicacion);
tabla.addCell(celda2);
tabla.addCell(datosSocio);
tabla.addCell(celda3);
tabla.addCell(devolucion);

documento.add(tabla);

/*Ahora a traves de un for insertaremos el contenido del arreglo en la tabla*/
/*for(int i=0;i<columna1.length;i++){ tabla.addCell(columna1[i]); tabla.addCell(columna2[i]); } documento.add(tabla);
documento.add(tabla2);*/
 /*Cerramos el documento*/ documento.close(); /* LaunchApplication.execute(file);//Ignoren esta linea de codigo es una de mis clases para pruebas*/

}catch(DocumentException e){ JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
 return; }catch(IOException e){ JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
 return; }
 }

    public static void generaFichaPdf(Publicacion objPublicacion)throws Exception{
    /*Primero definimos todas las cadenas del encabezado del documento y los demas datos a utilizar*/
        String encabezado="    FICHA PUBLICACION DE LA BIBLIOTECA UNIVERSITARIA"+"\n"+ " tfn 918888888--www.uah.es---CIF-12345678B"+"\n"+"\n"+"\n"+"\n" +"\n";
     /*colocaremos tambien la fecha de creacion*/
      Calendar c = Calendar.getInstance();

        Date d = c.getTime();
        Locale locIT = new Locale("es");
        DateFormat dfIT = DateFormat.getDateTimeInstance(2, 2, locIT);
        String fecha1=dfIT.format(d);

        /*Fuente utilizada para formatear el encabezado en este caso sera arial 12 en negrita*/
        Font fuente= new Font(Font.getFamily("ARIAL"), 10, Font.BOLD);
    /*Definimos un texto que se incluira antes de la tabla*/
        String Para="Publicación con código "+objPublicacion.getCodigo()+"\n"+"\n";


 /*Tambien haremos una tabla a partir de un par de arreglos en otro caso podria haber sido un resultset de una base de datos*/

/*String[] columna1={ "Vendido a","Con Identificador","El dia ","Marca","Color","Modelo","Matricula DGT","Fecha de fabricación","Desglose"};
String[] columna2={ Nombre,Id,Venta,Marca,Color,Modelo,MatriculaDgt,Fecha,Observaciones};*/
String datosPublicacion=objPublicacion.toString();

/*Ahora almacenaremos la imagen que ira en la parte superior y la alineamos a la izquierda*/
Image imagen=Image.getInstance(("anagrama2.jpg"));
imagen.scaleToFit(150, 150);
//imagen.setAlignment(Chunk.ALIGN_RIGHT);
imagen.setAlignment(Image.LEFT | Image.TEXTWRAP);
 
 /*Ahora que ya tenemos el contenido del documento empezaremos a estructurarlo*/

try{ /*Primero pasaremos nuestras cadenas a elementos de iText*/
/*definimos una frase que sera el string encabezado y le aplicamos la fuente*/
Paragraph linea = new Paragraph(encabezado,fuente);
 /*Definimos un parrafo*/ Phrase para=new Phrase(Para);
/*Pasamos la fecha a un String y la agregamos a un parrafo*/
Paragraph fecha=new Paragraph(String.valueOf(fecha1)+"\n"+"\n");
/*Ahora definimos la tabla donde el arguemento recibido indica el numero de columnas y la propiedad setWidthPercentage permite indicarle que ocupe todo el ancho de la pagina*/
PdfPTable tabla=new PdfPTable(1); tabla.setWidthPercentage(100);


/*Ahora que ya tenemos todos los elemtnos es hjora de agregarlos al documento, para ello primeramente definimos un docuemnto e indicando el tamaño*/
Document documento = new Document(PageSize.LETTER);
/*Definimos el nombre del archivo de salida con extension .PDF*/ String file=("fichas/"+objPublicacion.getCodigo()+".pdf");
 /*a traves del siguiente metodo (getInstance)y un flujo de salida del paquete .io asociamos el documento de iText con el archivo de java*/
 PdfWriter.getInstance(documento, new FileOutputStream(file));
 /*Definimos las celdas que seran los encabezados de la tabla*/
 PdfPCell celda1 =new PdfPCell (new Paragraph("Datos de la publicacion",FontFactory.getFont("arial",10,Font.BOLD,BaseColor.RED)));
 

/*Abrimos el documento y agregamos los elementos en el orden que deben aparecer*/
 documento.open();
documento.add(imagen);
documento.add(linea);
;
try
{
	Image Foto = Image.getInstance("imagenes/"+objPublicacion.getNombreImagen());
	Foto.scaleToFit(220, 220);
	Foto.setAlignment(Chunk.ALIGN_RIGHT);
        documento.add(Foto);
}
catch ( Exception e )
{
	e.printStackTrace();
}

documento.add(para);
documento.add(fecha);
tabla.addCell(celda1);
tabla.addCell(datosPublicacion);
documento.add(tabla);

/*Ahora a traves de un for insertaremos el contenido del arreglo en la tabla*/
/*for(int i=0;i<columna1.length;i++){ tabla.addCell(columna1[i]); tabla.addCell(columna2[i]); } documento.add(tabla);
documento.add(tabla2);*/
 /*Cerramos el documento*/ documento.close(); /* LaunchApplication.execute(file);//Ignoren esta linea de codigo es una de mis clases para pruebas*/

}catch(DocumentException e){ JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
 return; }catch(IOException e){ JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
 return; }
 }
public static void generaPrestamoPdf(Prestamo objPrestamo)throws Exception{
    /*Primero definimos todas las cadenas del encabezado del documento y los demas datos a utilizar*/
        String encabezado="    FICHA PUBLICACION DE LA BIBLIOTECA UNIVERSITARIA"+"\n"+ " tfn 918888888--www.uah.es---CIF-12345678B"+"\n"+"\n"+"\n"+"\n" +"\n";
     /*colocaremos tambien la fecha de creacion*/
      Calendar c = Calendar.getInstance();

        Date d = c.getTime();
        Locale locIT = new Locale("es");
        DateFormat dfIT = DateFormat.getDateTimeInstance(2, 2, locIT);
        String fecha1=dfIT.format(d);

        /*Fuente utilizada para formatear el encabezado en este caso sera arial 12 en negrita*/
        Font fuente= new Font(Font.getFamily("ARIAL"), 10, Font.BOLD);
    /*Definimos un texto que se incluira antes de la tabla*/
        


 /*Tambien haremos una tabla a partir de un par de arreglos en otro caso podria haber sido un resultset de una base de datos*/

/*String[] columna1={ "Vendido a","Con Identificador","El dia ","Marca","Color","Modelo","Matricula DGT","Fecha de fabricación","Desglose"};
String[] columna2={ Nombre,Id,Venta,Marca,Color,Modelo,MatriculaDgt,Fecha,Observaciones};*/
String datosPublicacion=objPrestamo.getPrestadoP().toString();
String datosSocio=objPrestamo.datosParaPublicacion();
/*Ahora almacenaremos la imagen que ira en la parte superior y la alineamos a la izquierda*/
Image imagen=Image.getInstance(("anagrama2.jpg"));
imagen.scaleToFit(150, 150);
//imagen.setAlignment(Chunk.ALIGN_RIGHT);
imagen.setAlignment(Image.LEFT | Image.TEXTWRAP);
 
 /*Ahora que ya tenemos el contenido del documento empezaremos a estructurarlo*/

try{ /*Primero pasaremos nuestras cadenas a elementos de iText*/
/*definimos una frase que sera el string encabezado y le aplicamos la fuente*/
Paragraph linea = new Paragraph(encabezado,fuente);
 
/*Ahora definimos la tabla donde el arguemento recibido indica el numero de columnas y la propiedad setWidthPercentage permite indicarle que ocupe todo el ancho de la pagina*/
PdfPTable tabla=new PdfPTable(1); tabla.setWidthPercentage(100);


/*Ahora que ya tenemos todos los elemtnos es hjora de agregarlos al documento, para ello primeramente definimos un docuemnto e indicando el tamaño*/
Document documento = new Document(PageSize.LETTER);
/*Definimos el nombre del archivo de salida con extension .PDF*/ String file=("prestamos/"+objPrestamo.getPrestadoP().getCodigo()+".pdf");
 /*a traves del siguiente metodo (getInstance)y un flujo de salida del paquete .io asociamos el documento de iText con el archivo de java*/
 PdfWriter.getInstance(documento, new FileOutputStream(file));
 /*Definimos las celdas que seran los encabezados de la tabla*/
 PdfPCell celda1 =new PdfPCell (new Paragraph("Datos de la publicacion",FontFactory.getFont("arial",10,Font.BOLD,BaseColor.RED)));
 PdfPCell celda2 =new PdfPCell (new Paragraph("Datos del socio y fechas",FontFactory.getFont("arial",10,Font.BOLD,BaseColor.RED)));
 

/*Abrimos el documento y agregamos los elementos en el orden que deben aparecer*/
 documento.open();
documento.add(imagen);
documento.add(linea);
;
try
{
	Image Foto = Image.getInstance("imagenes/"+objPrestamo.getPrestadoP().getNombreImagen());
	Foto.scaleToFit(220, 220);
	Foto.setAlignment(Chunk.ALIGN_RIGHT);
        documento.add(Foto);
}
catch ( Exception e )
{
	e.printStackTrace();
}


tabla.addCell(celda1);
tabla.addCell(datosPublicacion);
tabla.addCell(celda2);
tabla.addCell(datosSocio);

documento.add(tabla);

/*Ahora a traves de un for insertaremos el contenido del arreglo en la tabla*/
/*for(int i=0;i<columna1.length;i++){ tabla.addCell(columna1[i]); tabla.addCell(columna2[i]); } documento.add(tabla);
documento.add(tabla2);*/
 /*Cerramos el documento*/ documento.close(); /* LaunchApplication.execute(file);//Ignoren esta linea de codigo es una de mis clases para pruebas*/

}catch(DocumentException e){ JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
 return; }catch(IOException e){ JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
 return; }
 }

}
