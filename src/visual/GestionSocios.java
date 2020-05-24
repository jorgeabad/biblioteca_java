/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import excepcionesBiblioteca.SociosException;
import gestionSocios.Socio;
import gestionSocios.UtilSocios;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author jorge
 */
public class GestionSocios extends javax.swing.JInternalFrame {

    /**
     * Creates new form GestionSocios
     */
    public GestionSocios() {
        initComponents();
        GregorianCalendar hoy = new GregorianCalendar();
        Date hoy2 = hoy.getTime();
        FechaIn.setValue(hoy2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cuadroNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cuadroDni = new javax.swing.JTextField();
        listaCategoria = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButtonAltaSocio = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        FechaIn = new javax.swing.JSpinner();

        setClosable(true);
        setTitle("GESTION SOCIOS - - BIBLIOTECA UNIVERSITARIA");
        setToolTipText("Gestion de socios, Altas, Bajas, Consultas");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/book.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 255, 255));

        jLabel1.setText("DNI");

        cuadroNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadroNombreActionPerformed(evt);
            }
        });

        jLabel2.setText("NOMBRE Y APELLIDOS");

        cuadroDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadroDniActionPerformed(evt);
            }
        });

        listaCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "PROFESOR", "ALUMNO" }));
        listaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaCategoriaActionPerformed(evt);
            }
        });

        jLabel4.setText("FECHA INSCRIPCIÓN");

        jButtonAltaSocio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/Adduser32.png"))); // NOI18N
        jButtonAltaSocio.setText("ALTA SOCIO");
        jButtonAltaSocio.setToolTipText("Boton para dar de alta al socio en la biblioteca");
        jButtonAltaSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAltaSocioActionPerformed(evt);
            }
        });

        jLabel3.setText("CATEGORIA");

        FechaIn.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1338846393892L), null, null, java.util.Calendar.DAY_OF_YEAR));
        FechaIn.setEditor(new javax.swing.JSpinner.DateEditor(FechaIn, "dd/MM/yyyy"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(listaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cuadroNombre)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(FechaIn, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cuadroDni, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jButtonAltaSocio)))
                                .addGap(0, 98, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cuadroDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cuadroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(FechaIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jButtonAltaSocio)
                .addGap(75, 75, 75))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cuadroNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadroNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadroNombreActionPerformed

    private void cuadroDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadroDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadroDniActionPerformed

    private void jButtonAltaSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAltaSocioActionPerformed
       
        try{                                                 
            // TODO add your handling code here:
            String dni=cuadroDni.getText();
            String nombre=cuadroNombre.getText();
            Date fechaInscripcion=(Date)FechaIn.getValue();
            String categoria=listaCategoria.getSelectedItem().toString();
            
            Socio s=new Socio(nombre, dni, categoria, fechaInscripcion);
            try{
            UtilSocios.altaSocio(s);
            System.out.println(s.toString());
            JOptionPane.showMessageDialog(this, "Se ha dado de alta al socio " + s.toString(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }catch (SociosException se){
                JOptionPane.showMessageDialog(this, "Error al tramitar el Alta " + se.toString(), "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }catch (SociosException ex){
            JOptionPane.showMessageDialog(this, "Error formato " + ex.toString(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAltaSocioActionPerformed

    private void listaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaCategoriaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_listaCategoriaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner FechaIn;
    private javax.swing.JTextField cuadroDni;
    private javax.swing.JTextField cuadroNombre;
    private javax.swing.JButton jButtonAltaSocio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox listaCategoria;
    // End of variables declaration//GEN-END:variables
}