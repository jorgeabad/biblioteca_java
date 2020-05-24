/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualAccesoSocios;

import visual.*;
import visual.*;
import excepcionesBiblioteca.BibliotecaException;
import excepcionesBiblioteca.PublicacionException;
import excepcionesBiblioteca.SociosException;
import generarPdf.Generar;
import gestionPublicaciones.*;
import gestionSocios.Socio;
import gestionSocios.UtilSocios;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jorge
 */
public class ConsultaPublicacionesSocio extends javax.swing.JInternalFrame {

    /**
     * Creates new form ventanaAlta3
     */
    private ArrayList<Publicacion> arrayBiblioaux; //Referencia al ArrayList de publicaciones de la clase Biblioteca
    private ListIterator li; //Iterador para recorrer el ArrayList en ambas direcciones
    private Publicacion objPublicacion; //Referencia a un objeto de tipo publicacion del ArrayList
    
    public ConsultaPublicacionesSocio() {
        initComponents();
        consultarTodo();
    }
    private void consultarTodo() {
        try {
            //referenciamos al ArrayList de la Biblioteca
             arrayBiblioaux= Biblioteca.getArrayBiblioteca();
            //creamos el iterador sobre el ArrayList
            li = arrayBiblioaux.listIterator();
            //si no hay publicaciones...
            if (arrayBiblioaux.size() < 1) {
                JOptionPane.showMessageDialog(this, "No hay publicaciones.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                ButtonSiguiente.setEnabled(false);
                ButtonAnterior.setEnabled(false);
              
                return;
            } else {
                ButtonSiguiente.setEnabled(true);
                ButtonAnterior.setEnabled(true);
              
            }
            //presentamos el primer producto
            if (li.hasNext()) {
                objPublicacion = (Publicacion) li.next();
            }
            if (objPublicacion != null) {
                presentaDatosEspecificos(objPublicacion);
            } else {
                JOptionPane.showMessageDialog(this, "No hay publicaciones.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + e.toString());
        }
    }//fin consultarTodo
    
    public void ocultaPaneles(){
    jPanelLibro.setVisible(false);
    jPanelProyecto.setVisible(false);
    jPanelRevistas.setVisible(false);
    jPanelTesis.setVisible(false);
    }
    
    
    
    public void presentaDatosGenericos(Publicacion publi){
    cuadroCodigo.setText(publi.getCodigo());
    cuadroIsbn.setText(publi.getIsbn());
    cuadroAutores.setText(publi.getAutores());
    cuadroTitulo.setText(publi.getTitulo());
    cuadroColeccion.setText(publi.getColeccion());
    areaObservaciones.setText(publi.getObservaciones());
    cuadroMateria.setText(publi.getMateria());
    Fecha.setValue(publi.getFechaPublicacion());
    cuadroPaginas.setText(String.valueOf(publi.getPaginas()));
     jLabelImagen.setSize(200, 266);
            ImageIcon imagen = new ImageIcon("imagenes/"+publi.getNombreImagen());//nueva variable imagen de tipo ImagenIcon con el valor de la img seleccionada
            
			//Se redimensiona y se asigna a la var imgRedimensionada.
            ImageIcon imgRedimensionada = new ImageIcon(imagen.getImage().getScaledInstance(jLabelImagen.getWidth(), jLabelImagen.getHeight(), 1));
            jLabelImagen.setIcon(imgRedimensionada);//colocamos la imagen en el jLabel.
    if (publi.isEstado()){
        CheckLibre.setSelected(true);
        
        CheckPrestado.setSelected(false);
        AreaPrestamo.setText("");
        
    }else{
        AreaPrestamo.setText(publi.dameprestamo());
        CheckLibre.setSelected(false);
       
        CheckPrestado.setSelected(true);
    }
    
    
    }
    public void presentaDatosEspecificos(Publicacion publi){
    presentaDatosGenericos(publi);
    if (publi.getClass().getSimpleName().equals("Libro")){
    Libro l1=(Libro)publi;
    ocultaPaneles();
    cuadroPublicacion.setText("Libro");
    jPanelLibro.setVisible(true);
    cuadroEditorial.setText(l1.getEditorial());
    cuadroEdicion.setText(l1.getEdicion());
    cuadroLocalizacion.setText(l1.getLocalizacion());
    cuadroLocalidad.setText(l1.getLocalidad());
    cuadroContenido.setText(l1.getContenido());
    
    }
    if (publi.getClass().getSimpleName().equals("Revista")){
    Revista r1=(Revista)publi;
    ocultaPaneles();
    cuadroPublicacion.setText("Revista");
    jPanelRevistas.setVisible(true);
    cuadroPeriodicidad.setText(r1.getPeriodicidad());
    cuadroLocalizacionR.setText(r1.getLocalizacion());
    FechaRevista.setValue((r1.getAnyoDisponible()));
    cuadroNumero.setText(String.valueOf(r1.getNumero()));
    cuadroVolumen.setText(String.valueOf(r1.getVolumen()));
    
    }
    if (publi.getClass().getSimpleName().equals("Proyecto")){
    Proyecto p1=(Proyecto)publi;
    ocultaPaneles();
    cuadroPublicacion.setText("Proyecto");
    jPanelProyecto.setVisible(true);
    cuadroTitulacionP.setText(p1.getTitulación());
    cuadroDepartamentoP.setText(p1.getDepartamento());
    cuadroCalificacionP.setText(String.valueOf(p1.getCalificación()));
    cuadroTribunalP.setText(p1.getTribunal());
    FechaLectura.setValue(p1.getFechaLectura());
    cuadroEntidadP.setText(p1.getEntidad());
    
    }
    
    if (publi.getClass().getSimpleName().equals("Tesis")){
    Tesis t1=(Tesis)publi;
    ocultaPaneles();
    cuadroPublicacion.setText("Tesis Doctoral");
    jPanelTesis.setVisible(true);
    cuadroProgramaT.setText(t1.getPrograma());
    cuadroEntidadT.setText(t1.getEntidad());
    cuadroDepartamentoT.setText(t1.getDepartamento());
    cuadroCalificacionT.setText(String.valueOf(t1.getCalificación()));
    cuadroTribunalT.setText(t1.getTribunal());
    FechaLecturaT.setValue(t1.getFechaLectura());
    
    }
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPublicacion = new javax.swing.JPanel();
        cuadroTitulo = new javax.swing.JTextField();
        cuadroPublicacion = new javax.swing.JTextField();
        cuadroAutores = new javax.swing.JTextField();
        cuadroIsbn = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaObservaciones = new javax.swing.JTextArea();
        jLabel64 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        comboBoxPublicaciones = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        cuadroPaginas = new javax.swing.JTextField();
        cuadroCodigo = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        cuadroColeccion = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        cuadroMateria = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        Fecha = new javax.swing.JSpinner();
        jPanelTiposFondo = new javax.swing.JPanel();
        jPanelProyecto = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        cuadroTribunalP = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        cuadroCalificacionP = new javax.swing.JTextField();
        cuadroEntidadP = new javax.swing.JTextField();
        cuadroDepartamentoP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cuadroTitulacionP = new javax.swing.JTextField();
        FechaLectura = new javax.swing.JSpinner();
        jPanelTesis = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        cuadroTribunalT = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        cuadroCalificacionT = new javax.swing.JTextField();
        cuadroEntidadT = new javax.swing.JTextField();
        cuadroDepartamentoT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cuadroProgramaT = new javax.swing.JTextArea();
        FechaLecturaT = new javax.swing.JSpinner();
        jPanelRevistas = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        cuadroPeriodicidad = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        cuadroLocalizacionR = new javax.swing.JTextField();
        cuadroNumero = new javax.swing.JTextField();
        cuadroVolumen = new javax.swing.JTextField();
        FechaRevista = new javax.swing.JSpinner();
        jPanelLibro = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        cuadroEditorial = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        cuadroLocalizacion = new javax.swing.JTextField();
        cuadroContenido = new javax.swing.JTextField();
        cuadroEdicion = new javax.swing.JTextField();
        cuadroLocalidad = new javax.swing.JTextField();
        jPanelImagen = new javax.swing.JPanel();
        jLabelImagen = new javax.swing.JLabel();
        jLabelRuta = new javax.swing.JLabel();
        jPanelControl = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        ButtonSiguiente = new javax.swing.JButton();
        ButtonAnterior = new javax.swing.JButton();
        jPanelPrestamo = new javax.swing.JPanel();
        CheckLibre = new javax.swing.JCheckBox();
        jLabel86 = new javax.swing.JLabel();
        CheckPrestado = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        AreaPrestamo = new javax.swing.JTextArea();
        jLabel85 = new javax.swing.JLabel();
        jPanelControles = new javax.swing.JPanel();
        jPanelBusquedaFicha = new javax.swing.JPanel();
        jButtonBuscaPublicacion = new javax.swing.JButton();
        jButtonGenerarFicha = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setTitle("BIBLOTECA UNIVERSITARIA - - MODIFICACION DE PUBLICACIONES");
        setToolTipText("ALTA DE PUBLICACIONES");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/book.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1500, 1000));

        jPanelPublicacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS PUBLICACION", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
        jPanelPublicacion.setToolTipText("DATOS GENERALES PUBLICACIÓN");

        cuadroPublicacion.setEditable(false);

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel60.setText("Codigo");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel61.setText("ISBN");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel65.setText("Materia");

        areaObservaciones.setColumns(20);
        areaObservaciones.setRows(5);
        jScrollPane3.setViewportView(areaObservaciones);

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel64.setText("Páginas");

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel63.setText("Autores");

        comboBoxPublicaciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tipo Publicación", "Libros", "Revistas", "Proyectos", "Tesis Doctorales" }));
        comboBoxPublicaciones.setEnabled(false);
        comboBoxPublicaciones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxPublicacionesItemStateChanged(evt);
            }
        });
        comboBoxPublicaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxPublicacionesActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel62.setText("Titulo");

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel68.setText("Fecha publicación:");

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel67.setText("Observaciones:");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel66.setText("Colección");

        Fecha.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1338846393892L), new java.util.Date(81420000L), new java.util.Date(), java.util.Calendar.DAY_OF_YEAR));
        Fecha.setEditor(new javax.swing.JSpinner.DateEditor(Fecha, "dd/MM/yyyy"));

        javax.swing.GroupLayout jPanelPublicacionLayout = new javax.swing.GroupLayout(jPanelPublicacion);
        jPanelPublicacion.setLayout(jPanelPublicacionLayout);
        jPanelPublicacionLayout.setHorizontalGroup(
            jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPublicacionLayout.createSequentialGroup()
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPublicacionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(comboBoxPublicaciones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cuadroCodigo)
                        .addComponent(cuadroTitulo)
                        .addComponent(cuadroAutores)
                        .addComponent(cuadroIsbn)
                        .addComponent(cuadroPaginas)
                        .addComponent(cuadroMateria)
                        .addComponent(cuadroColeccion)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                        .addComponent(cuadroPublicacion))
                    .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelPublicacionLayout.setVerticalGroup(
            jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPublicacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cuadroPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxPublicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadroCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(cuadroIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadroTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadroAutores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelPublicacionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadroPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadroMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadroColeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelPublicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPublicacionLayout.createSequentialGroup()
                        .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(133, 133, 133))
                    .addGroup(jPanelPublicacionLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanelTiposFondo.setBackground(new java.awt.Color(204, 255, 255));
        jPanelTiposFondo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));
        jPanelTiposFondo.setForeground(new java.awt.Color(204, 255, 255));
        jPanelTiposFondo.setToolTipText("");
        jPanelTiposFondo.setLayout(new java.awt.CardLayout());

        jPanelProyecto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS PROYECTO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
        jPanelProyecto.setToolTipText("DATOS DEL PROYECTO");

        jLabel111.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel111.setText("Tribunal");

        jLabel112.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel112.setText("Departamento");

        jLabel113.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel113.setText("Entidad");

        jLabel114.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel114.setText("Calificación");

        jLabel115.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel115.setText("Fecha lectura");

        cuadroEntidadP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadroEntidadPActionPerformed(evt);
            }
        });

        cuadroDepartamentoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadroDepartamentoPActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Titulación");

        FechaLectura.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1338847422650L), new java.util.Date(-3420000L), new java.util.Date(1359586860000L), java.util.Calendar.DAY_OF_MONTH));
        FechaLectura.setEditor(new javax.swing.JSpinner.DateEditor(FechaLectura, "dd/MM/yyyy"));

        javax.swing.GroupLayout jPanelProyectoLayout = new javax.swing.GroupLayout(jPanelProyecto);
        jPanelProyecto.setLayout(jPanelProyectoLayout);
        jPanelProyectoLayout.setHorizontalGroup(
            jPanelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProyectoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel115)
                    .addComponent(jLabel111)
                    .addComponent(jLabel112)
                    .addComponent(jLabel113)
                    .addComponent(jLabel114))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cuadroTribunalP, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(cuadroDepartamentoP)
                    .addComponent(cuadroEntidadP)
                    .addComponent(cuadroCalificacionP)
                    .addComponent(cuadroTitulacionP)
                    .addComponent(FechaLectura))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanelProyectoLayout.setVerticalGroup(
            jPanelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProyectoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(cuadroTribunalP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112)
                    .addComponent(cuadroDepartamentoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel113)
                    .addComponent(cuadroEntidadP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel114)
                    .addComponent(cuadroCalificacionP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel115)
                    .addComponent(FechaLectura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cuadroTitulacionP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jPanelTiposFondo.add(jPanelProyecto, "card7");

        jPanelTesis.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS TESIS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
        jPanelTesis.setToolTipText("DATOS DE LA TESIS");

        jLabel101.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel101.setText("Tribunal");

        jLabel102.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel102.setText("Departamento");

        jLabel103.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel103.setText("Entidad");

        jLabel104.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel104.setText("Calificación");

        jLabel105.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel105.setText("Fecha lectura");

        cuadroEntidadT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadroEntidadTActionPerformed(evt);
            }
        });

        cuadroDepartamentoT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadroDepartamentoTActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Programa");

        cuadroProgramaT.setColumns(20);
        cuadroProgramaT.setRows(5);
        jScrollPane1.setViewportView(cuadroProgramaT);

        FechaLecturaT.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1338847422650L), new java.util.Date(-3420000L), new java.util.Date(1359586860000L), java.util.Calendar.DAY_OF_MONTH));
        FechaLecturaT.setEditor(new javax.swing.JSpinner.DateEditor(FechaLecturaT, "dd/MM/yyyy"));

        javax.swing.GroupLayout jPanelTesisLayout = new javax.swing.GroupLayout(jPanelTesis);
        jPanelTesis.setLayout(jPanelTesisLayout);
        jPanelTesisLayout.setHorizontalGroup(
            jPanelTesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTesisLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelTesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel101)
                    .addComponent(jLabel102)
                    .addComponent(jLabel103)
                    .addComponent(jLabel104)
                    .addGroup(jPanelTesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel105)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cuadroTribunalT)
                    .addComponent(cuadroDepartamentoT)
                    .addComponent(cuadroEntidadT)
                    .addComponent(cuadroCalificacionT)
                    .addComponent(jScrollPane1)
                    .addComponent(FechaLecturaT, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanelTesisLayout.setVerticalGroup(
            jPanelTesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTesisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(cuadroTribunalT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102)
                    .addComponent(cuadroDepartamentoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(cuadroEntidadT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104)
                    .addComponent(cuadroCalificacionT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel105)
                    .addComponent(FechaLecturaT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jPanelTiposFondo.add(jPanelTesis, "card7");

        jPanelRevistas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS REVISTA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
        jPanelRevistas.setToolTipText("DATOS DE LA REVISTA");

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel84.setText("Periodicidad");

        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel87.setText("Volumen");

        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel88.setText("Número");

        jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel89.setText("Año Disponible");

        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel90.setText("Localizacion");

        cuadroNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadroNumeroActionPerformed(evt);
            }
        });

        cuadroVolumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadroVolumenActionPerformed(evt);
            }
        });

        FechaRevista.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.YEAR));
        FechaRevista.setEditor(new javax.swing.JSpinner.DateEditor(FechaRevista, "yyyy"));

        javax.swing.GroupLayout jPanelRevistasLayout = new javax.swing.GroupLayout(jPanelRevistas);
        jPanelRevistas.setLayout(jPanelRevistasLayout);
        jPanelRevistasLayout.setHorizontalGroup(
            jPanelRevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRevistasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelRevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel90)
                    .addComponent(jLabel84)
                    .addComponent(jLabel87)
                    .addComponent(jLabel88)
                    .addComponent(jLabel89))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cuadroPeriodicidad)
                    .addComponent(cuadroVolumen)
                    .addComponent(cuadroNumero)
                    .addComponent(cuadroLocalizacionR, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(FechaRevista))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanelRevistasLayout.setVerticalGroup(
            jPanelRevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRevistasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(cuadroPeriodicidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelRevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(cuadroVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelRevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(cuadroNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelRevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(FechaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelRevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(cuadroLocalizacionR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(187, Short.MAX_VALUE))
        );

        jPanelTiposFondo.add(jPanelRevistas, "card7");

        jPanelLibro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS LIBRO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
        jPanelLibro.setToolTipText("DATOS DEL LIBRO");

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel79.setText("Editorial");

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel80.setText("Localidad");

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel81.setText("Edicion");

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel82.setText("Contenido");

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel83.setText("Localizacion");

        cuadroEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadroEdicionActionPerformed(evt);
            }
        });

        cuadroLocalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadroLocalidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLibroLayout = new javax.swing.GroupLayout(jPanelLibro);
        jPanelLibro.setLayout(jPanelLibroLayout);
        jPanelLibroLayout.setHorizontalGroup(
            jPanelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLibroLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel83)
                    .addComponent(jLabel79)
                    .addComponent(jLabel80)
                    .addComponent(jLabel81)
                    .addComponent(jLabel82))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cuadroEditorial)
                    .addComponent(cuadroLocalidad)
                    .addComponent(cuadroEdicion)
                    .addComponent(cuadroContenido)
                    .addComponent(cuadroLocalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanelLibroLayout.setVerticalGroup(
            jPanelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(cuadroEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(cuadroLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(cuadroEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(cuadroContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(cuadroLocalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(187, Short.MAX_VALUE))
        );

        jPanelTiposFondo.add(jPanelLibro, "card4");

        jPanelImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IMAGEN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanelImagen.setToolTipText("IMAGEN");

        javax.swing.GroupLayout jPanelImagenLayout = new javax.swing.GroupLayout(jPanelImagen);
        jPanelImagen.setLayout(jPanelImagenLayout);
        jPanelImagenLayout.setHorizontalGroup(
            jPanelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelImagenLayout.setVerticalGroup(
            jPanelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagenLayout.createSequentialGroup()
                .addComponent(jLabelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabelRuta.setBackground(new java.awt.Color(204, 255, 255));
        jLabelRuta.setText("Ruta");
        jLabelRuta.setToolTipText("Ruta de la imagen");
        jLabelRuta.setAutoscrolls(true);

        jPanelControl.setLayout(new java.awt.GridLayout(2, 0));

        ButtonSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/siguiente.gif"))); // NOI18N
        ButtonSiguiente.setToolTipText("PUBLICACIÓN SIGUIENTE>>");
        ButtonSiguiente.setOpaque(false);
        ButtonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSiguienteActionPerformed(evt);
            }
        });

        ButtonAnterior.setForeground(new java.awt.Color(51, 51, 51));
        ButtonAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/anterior.gif"))); // NOI18N
        ButtonAnterior.setToolTipText("<<PUBLICACIÓN ANTERIOR");
        ButtonAnterior.setOpaque(false);
        ButtonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAnteriorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(ButtonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addComponent(ButtonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ButtonSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ButtonAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelPrestamo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRESTAMO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
        jPanelPrestamo.setToolTipText("DATOS DEL PRESTAMO");

        CheckLibre.setBackground(new java.awt.Color(102, 255, 102));
        CheckLibre.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        CheckLibre.setSelected(true);
        CheckLibre.setText("LIBRE");
        CheckLibre.setEnabled(false);

        jLabel86.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel86.setText("ESTADO");

        CheckPrestado.setBackground(new java.awt.Color(153, 255, 153));
        CheckPrestado.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        CheckPrestado.setForeground(new java.awt.Color(102, 255, 51));
        CheckPrestado.setText("PRESTADO");
        CheckPrestado.setEnabled(false);

        AreaPrestamo.setColumns(20);
        AreaPrestamo.setEditable(false);
        AreaPrestamo.setRows(5);
        jScrollPane4.setViewportView(AreaPrestamo);

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel85.setText("DATOS DEL PRESTAMO");

        javax.swing.GroupLayout jPanelPrestamoLayout = new javax.swing.GroupLayout(jPanelPrestamo);
        jPanelPrestamo.setLayout(jPanelPrestamoLayout);
        jPanelPrestamoLayout.setHorizontalGroup(
            jPanelPrestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrestamoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPrestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrestamoLayout.createSequentialGroup()
                        .addGroup(jPanelPrestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CheckPrestado, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(CheckLibre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(57, Short.MAX_VALUE))
                    .addGroup(jPanelPrestamoLayout.createSequentialGroup()
                        .addComponent(jLabel86)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel85)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelPrestamoLayout.setVerticalGroup(
            jPanelPrestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrestamoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPrestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(jLabel85))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPrestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrestamoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(CheckLibre)
                        .addGap(27, 27, 27)
                        .addComponent(CheckPrestado)
                        .addGap(642, 642, 642))
                    .addGroup(jPanelPrestamoLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanelControles.setLayout(new java.awt.GridLayout(1, 1));

        jPanelBusquedaFicha.setLayout(new java.awt.GridLayout(2, 0));

        jButtonBuscaPublicacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/Find32.png"))); // NOI18N
        jButtonBuscaPublicacion.setText("Buscar");
        jButtonBuscaPublicacion.setToolTipText("Busca la publicación por su código");
        jPanelBusquedaFicha.add(jButtonBuscaPublicacion);

        jButtonGenerarFicha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/pdf.png"))); // NOI18N
        jButtonGenerarFicha.setText("Generar ficha");
        jButtonGenerarFicha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarFichaActionPerformed(evt);
            }
        });
        jPanelBusquedaFicha.add(jButtonGenerarFicha);

        jButtonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/Accept32.png"))); // NOI18N
        jButtonModificar.setText("Actualizar Datos");
        jButtonModificar.setToolTipText("guarda los cabios hechos en la publicación");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelPublicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelTiposFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jButtonModificar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(312, 312, 312)
                                .addComponent(jPanelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanelPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelBusquedaFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(716, 716, 716)))
                .addComponent(jPanelControles, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(617, 617, 617))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanelTiposFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanelControles, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)))
                                .addComponent(jPanelBusquedaFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(994, 994, 994)))
                        .addComponent(jPanelControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cuadroEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadroEdicionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadroEdicionActionPerformed

    private void cuadroLocalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadroLocalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadroLocalidadActionPerformed

    private void cuadroNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadroNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadroNumeroActionPerformed

    private void cuadroVolumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadroVolumenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadroVolumenActionPerformed

    private void cuadroEntidadTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadroEntidadTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadroEntidadTActionPerformed

    private void cuadroDepartamentoTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadroDepartamentoTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadroDepartamentoTActionPerformed

    private void cuadroEntidadPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadroEntidadPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadroEntidadPActionPerformed

    private void cuadroDepartamentoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadroDepartamentoPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadroDepartamentoPActionPerformed

    private void comboBoxPublicacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPublicacionesActionPerformed
        // TODO add your handling code here:
       String Lista;
        Lista = (String) comboBoxPublicaciones.getSelectedItem();
        if(Lista.equals("Libros")){
            jPanelLibro.setVisible(true);
            jPanelProyecto.setVisible(false);
            jPanelRevistas.setVisible(false);
            jPanelTesis.setVisible(false);
            cuadroPublicacion.setText("Libro");
            }
        if(Lista.equals("Proyectos")){
            jPanelProyecto.setVisible(true);
            jPanelRevistas.setVisible(false);
            jPanelTesis.setVisible(false);
            jPanelLibro.setVisible(false);
            cuadroPublicacion.setText("Proyecto");
        }
         if(Lista.equals("Tesis Doctorales")){
            jPanelProyecto.setVisible(false);
            jPanelProyecto.setVisible(false);
            jPanelRevistas.setVisible(false);
            jPanelTesis.setVisible(true);
            cuadroPublicacion.setText("Tesis Doctoral");
        }
        
         if(Lista.equals("Revistas")){
            jPanelProyecto.setVisible(false);
            jPanelLibro.setVisible(false);
            jPanelRevistas.setVisible(true);
            jPanelTesis.setVisible(false);
            cuadroPublicacion.setText("Revista");
        }
    }//GEN-LAST:event_comboBoxPublicacionesActionPerformed

    private void comboBoxPublicacionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxPublicacionesItemStateChanged
        // TODO add your handling code here:
        if (comboBoxPublicaciones.equals("Libro")) {
            jPanelLibro.setVisible(true);
        }
    }//GEN-LAST:event_comboBoxPublicacionesItemStateChanged

    private void ButtonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSiguienteActionPerformed
        // TODO add your handling code here:
        ButtonAnterior.setEnabled(true);
        if (li.hasNext()) {
            objPublicacion = (Publicacion) li.next();
            if (objPublicacion != null) {
                presentaDatosEspecificos(objPublicacion);
            } else {
                JOptionPane.showMessageDialog(this, "No hay mas publicaciones el la Biblioteca.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ultima publicación.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            ButtonSiguiente.setEnabled(false);
        }
    }//GEN-LAST:event_ButtonSiguienteActionPerformed

    private void ButtonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAnteriorActionPerformed
        // TODO add your handling code here:
        ButtonSiguiente.setEnabled(true);
        if (li.hasPrevious()) {
            objPublicacion = (Publicacion) li.previous();
            if (objPublicacion != null) {
                presentaDatosEspecificos(objPublicacion);
            } else {
                JOptionPane.showMessageDialog(this, "No hay publicaciones.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Primera publicación.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            ButtonAnterior.setEnabled(false);
        }
    }//GEN-LAST:event_ButtonAnteriorActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // TODO add your handling code here:
        consultarTodo();
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonGenerarFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarFichaActionPerformed
        try {
            // TODO add your handling code here:
            Generar.generaFichaPdf(objPublicacion);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Generando ficha: " + ex.toString(), "Generación de ficha en pdf", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGenerarFichaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaPrestamo;
    private javax.swing.JButton ButtonAnterior;
    private javax.swing.JButton ButtonSiguiente;
    private javax.swing.JCheckBox CheckLibre;
    private javax.swing.JCheckBox CheckPrestado;
    private javax.swing.JSpinner Fecha;
    private javax.swing.JSpinner FechaLectura;
    private javax.swing.JSpinner FechaLecturaT;
    private javax.swing.JSpinner FechaRevista;
    private javax.swing.JTextArea areaObservaciones;
    private javax.swing.JComboBox comboBoxPublicaciones;
    private javax.swing.JTextField cuadroAutores;
    private javax.swing.JTextField cuadroCalificacionP;
    private javax.swing.JTextField cuadroCalificacionT;
    private javax.swing.JTextField cuadroCodigo;
    private javax.swing.JTextField cuadroColeccion;
    private javax.swing.JTextField cuadroContenido;
    private javax.swing.JTextField cuadroDepartamentoP;
    private javax.swing.JTextField cuadroDepartamentoT;
    private javax.swing.JTextField cuadroEdicion;
    private javax.swing.JTextField cuadroEditorial;
    private javax.swing.JTextField cuadroEntidadP;
    private javax.swing.JTextField cuadroEntidadT;
    private javax.swing.JTextField cuadroIsbn;
    private javax.swing.JTextField cuadroLocalidad;
    private javax.swing.JTextField cuadroLocalizacion;
    private javax.swing.JTextField cuadroLocalizacionR;
    private javax.swing.JTextField cuadroMateria;
    private javax.swing.JTextField cuadroNumero;
    private javax.swing.JTextField cuadroPaginas;
    private javax.swing.JTextField cuadroPeriodicidad;
    private javax.swing.JTextArea cuadroProgramaT;
    private javax.swing.JTextField cuadroPublicacion;
    private javax.swing.JTextField cuadroTitulacionP;
    private javax.swing.JTextField cuadroTitulo;
    private javax.swing.JTextField cuadroTribunalP;
    private javax.swing.JTextField cuadroTribunalT;
    private javax.swing.JTextField cuadroVolumen;
    private javax.swing.JButton jButtonBuscaPublicacion;
    private javax.swing.JButton jButtonGenerarFicha;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelRuta;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelBusquedaFicha;
    private javax.swing.JPanel jPanelControl;
    private javax.swing.JPanel jPanelControles;
    private javax.swing.JPanel jPanelImagen;
    private javax.swing.JPanel jPanelLibro;
    private javax.swing.JPanel jPanelPrestamo;
    private javax.swing.JPanel jPanelProyecto;
    private javax.swing.JPanel jPanelPublicacion;
    private javax.swing.JPanel jPanelRevistas;
    private javax.swing.JPanel jPanelTesis;
    private javax.swing.JPanel jPanelTiposFondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
