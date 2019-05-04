/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendaangelitodesktop;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author vladi
 */
public class FrmPrincipal extends javax.swing.JFrame {
    //FrmLogin login;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
    }
    
    private void desactivarPanel(){
        btnCompras.setEnabled(false);
        btnDesconectar.setEnabled(false);
        btnFacturar.setEnabled(false);
        btnProductos.setEnabled(false);
        btnRegistrar.setEnabled(false);
        btnReportes.setEnabled(false);
        btnSalir.setEnabled(false);
    }
    
    private void activarPanelAdm(){
        btnCompras.setEnabled(true);
        btnDesconectar.setEnabled(true);
        btnFacturar.setEnabled(true);
        btnProductos.setEnabled(true);
        btnRegistrar.setEnabled(true);
        btnReportes.setEnabled(true);
        btnSalir.setEnabled(true);
    }
    
    private void activarPanelVendedor(){
        btnCompras.setEnabled(false);
        btnDesconectar.setEnabled(true);
        btnFacturar.setEnabled(true);
        btnProductos.setEnabled(false);
        btnRegistrar.setEnabled(true);
        btnReportes.setEnabled(false);
        btnSalir.setEnabled(false);
    }
    
    private void reposicionarBtn(){
        int x=32;
        int y=18;
        
        reposicionarBtn(btnFacturar,8,4);
        reposicionarBtn(btnCompras,14,4);
        reposicionarBtn(btnRegistrar,20,4);
        reposicionarBtn(btnReportes,11,9);
        reposicionarBtn(btnProductos,17,9);
        reposicionarBtn(btnDesconectar,22,13);
        reposicionarBtn(btnSalir,27,13);
        
    }
    
    private void reposicionarBtn(JButton btn, int x, int y){
        btn.setLocation(width*x/32, height*y/18);
    }
    
    private void redimensionarBtn(){
        redimensionarBtn(btnCompras);
        redimensionarBtn(btnDesconectar);
        redimensionarBtn(btnFacturar);
        redimensionarBtn(btnProductos);
        redimensionarBtn(btnRegistrar);
        redimensionarBtn(btnReportes);
        redimensionarBtn(btnSalir);
    }
    
    private void redimensionarBtn(JButton btn){
        int width=(this.width/32)*4;
        int height=(this.height/18)*3;
        btn.setSize(width, height);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnReportes = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnDesconectar = new javax.swing.JButton();
        btnCompras = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Tienda Angelito");
        setExtendedState(MAXIMIZED_BOTH);
        setName("Principal"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Tienda Angelito");

        btnReportes.setText("Reportes");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        btnProductos.setText("Productos");
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        btnFacturar.setText("Facturar");
        btnFacturar.setMaximumSize(new java.awt.Dimension(1000, 1000));
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnDesconectar.setText("Desconectar");
        btnDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarActionPerformed(evt);
            }
        });

        btnCompras.setText("Compras");
        btnCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprasActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnReportes)
                        .addGap(18, 18, 18)
                        .addComponent(btnProductos)
                        .addGap(18, 18, 18)
                        .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCompras)
                        .addGap(18, 18, 18)
                        .addComponent(btnDesconectar)
                        .addGap(10, 10, 10)
                        .addComponent(btnRegistrar))
                    .addComponent(btnSalir))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReportes)
                    .addComponent(btnProductos)
                    .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCompras)
                    .addComponent(btnDesconectar)
                    .addComponent(btnRegistrar))
                .addGap(11, 11, 11)
                .addComponent(btnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        FrmFactura fac=new FrmFactura(this, false);
        fac.setVisible(true);
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        redimensionarBtn();
        reposicionarBtn();
        desactivarPanel();
        Variables.login = new FrmLogin(this,true);
        Variables.login.setVisible(true);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        switch (Variables.tipoUsuario){
            case 0:
                desactivarPanel();
                break;
            case 1:
                activarPanelAdm();
                break;
            case 2:
                activarPanelVendedor();
                break;
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        FrmAdministrarProducto prod= new FrmAdministrarProducto(this,true);
        prod.setVisible(true);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasActionPerformed
        // Agregar formulario compras
    }//GEN-LAST:event_btnComprasActionPerformed

    private void btnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarActionPerformed
        desactivarPanel();
        Variables.idUsuario=0;
        Variables.tipoUsuario=0;
        Variables.login.setVisible(true);
    }//GEN-LAST:event_btnDesconectarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        FrmPersonas per=new FrmPersonas(this,true);
        per.setVisible(true);
    }//GEN-LAST:event_btnRegistrarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnDesconectar;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
