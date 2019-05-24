/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendaangelitodesktop;

import dao.Usuario;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tiendaangelitodesktop.editar.FrmEditarUsuario;

/**
 *
 * @author vladi
 */
public class FrmAdministrarUsuario extends javax.swing.JDialog {
    ResultSet rst;
    Usuario usu = new Usuario();
    DefaultTableModel model;
    Frame parent;
    int idUsuario=-2;
    int idEmpleado=-2;

    /**
     * Creates new form FrmAdministrarUsuarios
     */
    public FrmAdministrarUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.parent = parent;
        this.setLocationRelativeTo(null);
        llenarTabla();
        llenarRoles();
    }
    
    private void llenarTabla(){
        Object datos[] = new Object[6];
        rst = null;
        rst = usu.usuariosAsignados();
        model = new DefaultTableModel();
        model.addColumn("idEmpleado");
        model.addColumn("idUsuario");
        model.addColumn("Empleado");
        model.addColumn("Alias");
        model.addColumn("idRol");
        model.addColumn("Rol");
        try {
            while (rst.next()){
                datos[0] = rst.getString(1);
                datos[1] = rst.getString(2);
                datos[2] = rst.getString(3) + " " + rst.getString(4);
                datos[3] = rst.getString(5);
                datos[4] = rst.getString(6);
                datos[5] = rst.getString(7);
                model.addRow(datos);
            }
            
            tblUsuarios.setModel(model);
            tblUsuarios.getColumnModel().getColumn(0).setMaxWidth(0);
            tblUsuarios.getColumnModel().getColumn(0).setMinWidth(0);
            tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblUsuarios.getColumnModel().getColumn(1).setMaxWidth(0);
            tblUsuarios.getColumnModel().getColumn(1).setMinWidth(0);
            tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblUsuarios.getColumnModel().getColumn(4).setMaxWidth(0);
            tblUsuarios.getColumnModel().getColumn(4).setMinWidth(0);
            tblUsuarios.getColumnModel().getColumn(4).setPreferredWidth(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void filtrarTabla(String filtro){
        Object datos[] = new Object[6];
        rst = null;
        rst = usu.usuariosAsignados(filtro);
        model = new DefaultTableModel();
        model.addColumn("idEmpleado");
        model.addColumn("idUsuario");
        model.addColumn("Empleado");
        model.addColumn("Alias");
        model.addColumn("idRol");
        model.addColumn("Rol");
        try {
            while (rst.next()){
                datos[0] = rst.getString(1);
                datos[1] = rst.getString(2);
                datos[2] = rst.getString(3) + " " + rst.getString(4);
                datos[3] = rst.getString(5);
                datos[4] = rst.getString(6);
                datos[5] = rst.getString(7);
                model.addRow(datos);
            }
            
            tblUsuarios.setModel(model);
            tblUsuarios.getColumnModel().getColumn(0).setMaxWidth(0);
            tblUsuarios.getColumnModel().getColumn(0).setMinWidth(0);
            tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblUsuarios.getColumnModel().getColumn(1).setMaxWidth(0);
            tblUsuarios.getColumnModel().getColumn(1).setMinWidth(0);
            tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblUsuarios.getColumnModel().getColumn(4).setMaxWidth(0);
            tblUsuarios.getColumnModel().getColumn(4).setMinWidth(0);
            tblUsuarios.getColumnModel().getColumn(4).setPreferredWidth(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void llenarRoles(){
        String datos;
        ResultSet rst = null;
        rst = usu.obtenerRoles();
        cmbRol.removeAll();
        try {
            while (rst.next()){
                datos = rst.getString(1);
                cmbRol.addItem(datos);
            }    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
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

        lblEmpleado = new javax.swing.JLabel();
        lblAlias = new javax.swing.JLabel();
        txfEmpleado = new javax.swing.JTextField();
        txfAlias = new javax.swing.JTextField();
        btnAsignarUsuario = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblFiltrar = new javax.swing.JLabel();
        txfFiltrar = new javax.swing.JTextField();
        scpnlUsuarios = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        btnAgregarNuevoEmpleado = new javax.swing.JButton();
        lblPass = new javax.swing.JLabel();
        txfPass = new javax.swing.JPasswordField();
        btnEliminarUsuario = new javax.swing.JButton();
        lblRol = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        btnEditarUsuario = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblEmpleado.setText("Empleado:");

        lblAlias.setText("Alias:");

        txfEmpleado.setEditable(false);
        txfEmpleado.setEnabled(false);

        btnAsignarUsuario.setText("Asignar Usuario");
        btnAsignarUsuario.setEnabled(false);
        btnAsignarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarUsuarioActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados"));

        lblFiltrar.setText("Filtrar:");

        txfFiltrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txfFiltrarKeyReleased(evt);
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
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        scpnlUsuarios.setViewportView(tblUsuarios);

        btnAgregarNuevoEmpleado.setText("Agregar Nuevo Empleado");
        btnAgregarNuevoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNuevoEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpnlUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lblFiltrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txfFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnAgregarNuevoEmpleado))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFiltrar)
                    .addComponent(txfFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarNuevoEmpleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpnlUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        lblPass.setText("Contraseña:");

        btnEliminarUsuario.setText("Eliminar Usuario");
        btnEliminarUsuario.setEnabled(false);
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        lblRol.setText("Rol:");

        btnEditarUsuario.setText("Editar Usuario");
        btnEditarUsuario.setEnabled(false);
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAsignarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txfEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txfAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPass)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txfPass, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(cmbRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmpleado)
                    .addComponent(txfEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol)
                    .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlias)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txfAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPass)
                        .addComponent(txfPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAsignarUsuario)
                    .addComponent(btnEliminarUsuario)
                    .addComponent(btnEditarUsuario)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarNuevoEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNuevoEmpleadoActionPerformed
        FrmPersonas per = new FrmPersonas(this.parent, true);
        per.setVisible(true);
    }//GEN-LAST:event_btnAgregarNuevoEmpleadoActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        int idRow = tblUsuarios.getSelectedRow();
        idUsuario = -2;
        idEmpleado = -2;
        
        if(tblUsuarios.getModel().getValueAt(idRow, 1)==null){
            btnAsignarUsuario.setEnabled(true);
            btnEliminarUsuario.setEnabled(false);
            btnEditarUsuario.setEnabled(false);
            idEmpleado = Integer.parseInt(tblUsuarios.getModel().getValueAt(idRow, 0).toString());
            idUsuario = -2;
            txfAlias.setText("");
            cmbRol.setSelectedIndex(1);
        } else {
            btnAsignarUsuario.setEnabled(true);
            btnEliminarUsuario.setEnabled(true);
            btnEditarUsuario.setEnabled(true);
            idUsuario = Integer.parseInt(tblUsuarios.getModel().getValueAt(idRow, 1).toString());
            idEmpleado = Integer.parseInt(tblUsuarios.getModel().getValueAt(idRow, 0).toString());
            JOptionPane.showMessageDialog(this, "El empleado ya posee un usuario, pero puede asignarle otro.");
        }
        
        txfEmpleado.setText(tblUsuarios.getModel().getValueAt(idRow, 0).toString());
        txfEmpleado.setText(txfEmpleado.getText()+" "+tblUsuarios.getModel().getValueAt(idRow, 2));
        txfEmpleado.setText(txfEmpleado.getText()+" "+tblUsuarios.getModel().getValueAt(idRow, 3));
        
        
        
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
        if(JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar el usuario?")==JOptionPane.YES_OPTION){
            usu.eliminarUsuario(idUsuario);
            limpiar();
        }
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void btnAsignarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarUsuarioActionPerformed
        int idTipoUsuario=2;
        String alias="";
        String pass="";
        
        alias = txfAlias.getText();
        for(int i=0;i<txfPass.getPassword().length;i++){
            pass=pass+txfPass.getPassword()[i];
        }
        
        try {
            rst = usu.obtenerIdRol(cmbRol.getSelectedItem().toString());
            while(rst.next()){
                idTipoUsuario = rst.getInt(1);
            }
            usu.agregarUsuario(idEmpleado, idTipoUsuario, alias, pass);
            JOptionPane.showMessageDialog(this, "Usuario asignado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }finally{
            limpiar();
        }
    }//GEN-LAST:event_btnAsignarUsuarioActionPerformed

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioActionPerformed
        if(JOptionPane.showConfirmDialog(this, "¿Esta seguro de editar el usuario?")==JOptionPane.YES_OPTION){
            FrmEditarUsuario edUsuario = new FrmEditarUsuario (this.parent, true, idUsuario);
            edUsuario.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Operación abortada");
        }
    }//GEN-LAST:event_btnEditarUsuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txfFiltrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfFiltrarKeyReleased
        String filtro = txfFiltrar.getText();
        filtrarTabla(filtro);
    }//GEN-LAST:event_txfFiltrarKeyReleased

    
    private void limpiar(){
        llenarTabla();
        txfAlias.setText("");
        txfEmpleado.setText("");
        txfFiltrar.setText("");
        txfPass.setText("");
        cmbRol.setSelectedIndex(1);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAdministrarUsuario dialog = new FrmAdministrarUsuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarNuevoEmpleado;
    private javax.swing.JButton btnAsignarUsuario;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAlias;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblRol;
    private javax.swing.JScrollPane scpnlUsuarios;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txfAlias;
    private javax.swing.JTextField txfEmpleado;
    private javax.swing.JTextField txfFiltrar;
    private javax.swing.JPasswordField txfPass;
    // End of variables declaration//GEN-END:variables
}
