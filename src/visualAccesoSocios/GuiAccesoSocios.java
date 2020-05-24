/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualAccesoSocios;

import excepcionesBiblioteca.SociosException;
import gestionPublicaciones.Biblioteca;
import gestionSocios.Socio;
import gestionSocios.UtilSocios;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jorge
 */
public class GuiAccesoSocios extends javax.swing.JFrame {

    /**
     * Creates new form GuiAccesoSocios
     */
    private JFrame principal;
    private Socio objSocio;
    public GuiAccesoSocios(JFrame ventana) {
        initComponents();
        principal=ventana;
        principal.setVisible(false);
        UtilSocios.cargarDatosSocios();
        Biblioteca.cargarDatos();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        jDialogoValidarSocio.setVisible(true);
        jDialogoValidarSocio.setLocationRelativeTo(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogoValidarSocio = new javax.swing.JDialog();
        cuadroCodigo = new javax.swing.JTextField();
        jButtonAceptarCodigo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jDesktopPaneSocios = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jDialogoValidarSocio.setAlwaysOnTop(true);
        jDialogoValidarSocio.setMinimumSize(new java.awt.Dimension(500, 500));

        jButtonAceptarCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/Accept32.png"))); // NOI18N
        jButtonAceptarCodigo.setText("Aceptar");
        jButtonAceptarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarCodigoActionPerformed(evt);
            }
        });

        jLabel1.setText("Su codigo de socio : ");

        javax.swing.GroupLayout jDialogoValidarSocioLayout = new javax.swing.GroupLayout(jDialogoValidarSocio.getContentPane());
        jDialogoValidarSocio.getContentPane().setLayout(jDialogoValidarSocioLayout);
        jDialogoValidarSocioLayout.setHorizontalGroup(
            jDialogoValidarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogoValidarSocioLayout.createSequentialGroup()
                .addGroup(jDialogoValidarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogoValidarSocioLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(jDialogoValidarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(cuadroCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDialogoValidarSocioLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jButtonAceptarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jDialogoValidarSocioLayout.setVerticalGroup(
            jDialogoValidarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogoValidarSocioLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cuadroCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButtonAceptarCodigo)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/User32.png"))); // NOI18N
        jMenu1.setText("Mis Datos");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem2.setText("Ver sus datos y prestamos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Catalogo Biblioteca");
        jMenu2.setToolTipText("");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem1.setText("Consulte las publicaciones");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/Home.png"))); // NOI18N
        jMenu3.setText("Inicio");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagenes/Back32.png"))); // NOI18N
        jMenuItem3.setText("Volver a inicio");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneSocios, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneSocios, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAceptarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarCodigoActionPerformed
        try {
            // TODO add your handling code here:
            String codigoAcceso=cuadroCodigo.getText();
            Socio s=UtilSocios.buscarSocio(codigoAcceso);
            ConsultaSocio2 cs2 = new ConsultaSocio2(s);
        this.jDesktopPaneSocios.add(cs2);
        objSocio=s;
        cs2.show();
        } catch (SociosException ex) {
            JOptionPane.showMessageDialog(jDialogoValidarSocio, "Error: " + ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAceptarCodigoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        ConsultaSocio2 cs2 = new ConsultaSocio2(objSocio);
        this.jDesktopPaneSocios.add(cs2);
        
        cs2.show();
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        ConsultaPublicacionesSocio cps = new ConsultaPublicacionesSocio();
        this.jDesktopPaneSocios.add(cps);
        
        cps.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
         this.setVisible(false);
        principal.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cuadroCodigo;
    private javax.swing.JButton jButtonAceptarCodigo;
    private javax.swing.JDesktopPane jDesktopPaneSocios;
    private javax.swing.JDialog jDialogoValidarSocio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables
}
