/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import ayuda.Constantes;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import objetos.Escritor;
import objetos.Usuario;
import objetos.Usuario_b;

/**
 *
 * @author ivanc
 */
public class frmUsuarios extends javax.swing.JFrame {

    private JFrame principal;
    //private Usuario usuario;
    private Usuario_b usuario;
    private Escritor e;
    private Socket servidor;

    public frmUsuarios(JFrame principal, Usuario_b usuario, Escritor e, Socket servidor) {
        initComponents();
        this.principal = principal;
        this.usuario = usuario;
        this.e = e;
        this.servidor = servidor;
    }

    /**
     * Creates new form frmUsuarios
     */
    public frmUsuarios() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        btnBorrarUsuario = new javax.swing.JButton();
        btnActivarUsuario = new javax.swing.JButton();
        btnAddUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Usuarios");
        setMaximumSize(new java.awt.Dimension(761, 355));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlPrincipalComponentShown(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Correo", "Nick", "Rol", "Activo"
            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        btnBorrarUsuario.setText("Borrar usuario");
        btnBorrarUsuario.setEnabled(false);
        btnBorrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarUsuarioActionPerformed(evt);
            }
        });

        btnActivarUsuario.setText("Activar");
        btnActivarUsuario.setEnabled(false);
        btnActivarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarUsuarioActionPerformed(evt);
            }
        });

        btnAddUsuario.setText("Añadir usuario");
        btnAddUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnVolver))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBorrarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActivarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(12, 12, 12))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnAddUsuario)
                        .addGap(18, 18, 18)
                        .addComponent(btnBorrarUsuario)
                        .addGap(18, 18, 18)
                        .addComponent(btnActivarUsuario)
                        .addGap(51, 51, 51)
                        .addComponent(btnVolver)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        principal.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnBorrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarUsuarioActionPerformed
        try {
            DefaultTableModel tm = (DefaultTableModel) tblUsuarios.getModel();
            String email = String.valueOf(tm.getValueAt(tblUsuarios.getSelectedRow(), 0));
            e.escribir(true);
            e.escribir(Constantes.BORRAR_USUARIO);
            e.escribir(email);
            JOptionPane.showMessageDialog(null, "Usuario eliminado");
            limpiarTabla();
            cargarUsuarios();
        } catch (Exception e) {
            Logger.getLogger(frmUsuarios.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnBorrarUsuarioActionPerformed

    private void btnActivarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarUsuarioActionPerformed
        try {
            DefaultTableModel tm = (DefaultTableModel) tblUsuarios.getModel();
            String activo = String.valueOf(tm.getValueAt(tblUsuarios.getSelectedRow(), 3));
            String email = String.valueOf(tm.getValueAt(tblUsuarios.getSelectedRow(), 1));
            int accion;
            if (activo.equals("true")) {
                accion = Constantes.DESACTIVAR_USUARIO;
            } else {
                accion = Constantes.ACTIVAR_USUARIO;
            }
            e.escribir(true);
            e.escribir(accion);
            e.escribir(email);
            JOptionPane.showMessageDialog(null, "USUARIO activado");
            limpiarTabla();
            cargarUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnActivarUsuarioActionPerformed

    private void btnAddUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUsuarioActionPerformed
        frmRegistro frm = new frmRegistro(this, servidor, e);
        this.setVisible(false);
        frm.setVisible(true);
    }//GEN-LAST:event_btnAddUsuarioActionPerformed

    private void pnlPrincipalComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlPrincipalComponentShown

    }//GEN-LAST:event_pnlPrincipalComponentShown

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        DefaultTableModel tm = (DefaultTableModel) tblUsuarios.getModel();
        String fila = String.valueOf(tm.getValueAt(tblUsuarios.getSelectedRow(), 0));
        if (!fila.equals("null")) {
            btnActivarUsuario.setEnabled(true);
            btnBorrarUsuario.setEnabled(true);
        } else {
            btnActivarUsuario.setEnabled(false);
            btnBorrarUsuario.setEnabled(false);
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try {
            e.escribir(false);
            servidor.close();
        } catch (Exception ex) {
            Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            cargarUsuariosB();
        } catch (Exception ex) {
            Logger.getLogger(frmUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivarUsuario;
    private javax.swing.JButton btnAddUsuario;
    private javax.swing.JButton btnBorrarUsuario;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblUsuarios;
    // End of variables declaration//GEN-END:variables

    
    private void limpiarTabla() {
        DefaultTableModel tb = (DefaultTableModel) tblUsuarios.getModel();
        int a = tblUsuarios.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }
    
    private void cargarUsuarios(){
        try {
            DefaultTableModel tm = (DefaultTableModel) tblUsuarios.getModel();
            e.escribir(true);
            e.escribir(Constantes.CARGAR_USUARIOS);
            int fila = 0;
            limpiarTabla();
            while ((boolean) e.leer()) {
                Usuario_b u = (Usuario_b) e.leer();
                tm.addRow(new Object[4]);
                addUserB(u,fila);
                fila++;
            }
        } catch (Exception ex) {
            Logger.getLogger(frmUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addUserB(Usuario_b u, int fila) {
        tblUsuarios.setValueAt(u.getEmail(), fila, 0);
        tblUsuarios.setValueAt(u.getNombre(), fila, 1);
        String rol;
        if (usuario.getRol() == 1) {
            rol = "Administrador";
        } else {
            rol = "Gestor";
        }
        tblUsuarios.setValueAt(rol, fila, 2);
        tblUsuarios.setValueAt(u.isActivo(), fila, 3);
    }

    private void addUser(Usuario u, int fila) {
        tblUsuarios.setValueAt(usuario.getEmail(), fila, 0);
        tblUsuarios.setValueAt(usuario.getNombre(), fila, 1);
        String rol;
        if (usuario.getRol() == 1) {
            rol = "Administrador";
        } else {
            rol = "Gestor";
        }
        tblUsuarios.setValueAt(rol, fila, 3);
    }

    private void cargarUsuariosB(){
        try {
            DefaultTableModel tm = (DefaultTableModel) tblUsuarios.getModel();
            e.escribir(true);
            e.escribir(Constantes.CARGAR_USUARIOS);
            int fila = 0;
            limpiarTabla();
            while ((boolean) e.leer()) {
                Usuario_b u = (Usuario_b) e.leer();
                tm.addRow(new Object[4]);
                addUserB(u, fila);
                fila++;
            }
        } catch (Exception ex) {
            Logger.getLogger(frmUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
