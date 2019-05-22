/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendaangelitodesktop.editar;

import dao.Persona;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tiendaangelitodesktop.Variables;

/**
 *
 * @author DELL
 */
public class FrmEditarPersona extends javax.swing.JDialog {
    Persona per;
    int idPersona;
    ResultSet rst;
    boolean editar=false;
    boolean cliente=false, empleado=false, proveedor=false;

    
    int totPerVentas=0;
    int totPerCompras=0;
    int totUsuFactura=0;
    int totUsuCompras=0;
    
    int cambioCli=0;
    int cambioPro=0;
    int cambioEmp=0;
    /**
     * Creates new form FrmEditarPersona
     */
    
    public FrmEditarPersona(java.awt.Frame parent, boolean modal){
        super(parent, modal);
    }

    public FrmEditarPersona(java.awt.Frame parent, boolean modal, int idPersona, boolean editar) {
        super(parent, modal);
        initComponents();
        per = new Persona();
        this.idPersona = idPersona;
        this.editar = editar;
        setUpFormulario();
        llenarFormulario();
        
    }
    
    private void setUpFormulario(){
        txfNombre.setEditable(editar);
        txfApellido.setEditable(editar);
        txfDUI.setEditable(editar);
        txfNIT.setEditable(editar);
        txfDireccion.setEditable(editar);
        txfTelefono.setEditable(editar);
        txfAFP.setEditable(editar);
        txfISSS.setEditable(editar);
        txfEmail.setEditable(editar);
        chkCliente.setEnabled(editar);
        chkEmpleado.setEnabled(editar);
        chkProveedor.setEnabled(editar);
        
        
        
        
        
        if(!editar){
            btnAceptar.setVisible(false);
            btnCancelar.setText("Regresar");
        }
        
        switch (Variables.tipoUsuario) {
            case 0:
                this.dispose();
                break;
            case 1:
                break;
            case 2:
                chkCliente.setEnabled(false);
                chkEmpleado.setEnabled(false);
                chkProveedor.setEnabled(false);
                break;
            default:
                break;
        }
        
    }
    
    private void llenarFormulario(){
        try{
            rst = null;
            rst = per.personasEntidad(idPersona);
            

            
            ResultSet perConVentas = null;
            ResultSet perConCompras = null;
            ResultSet usuConVentas = null;
            ResultSet usuConCompras = null;
            
            perConVentas = per.obtenerSiCliente(idPersona);
            perConCompras = per.obtenerSiProveedor(idPersona);
            usuConVentas = per.obtenerSiUsuarioFac(idPersona);
            usuConCompras = per.obtenerSiUsuarioComp(idPersona);
            
            while (perConVentas.next()){
                totPerVentas=perConVentas.getInt(1);
            }
            while (perConCompras.next()){
                totPerCompras=perConCompras.getInt(1);
            }
            while (usuConVentas.next()){
                totUsuFactura=usuConVentas.getInt(1);
            }
            while (usuConCompras.next()){
                totUsuCompras=usuConCompras.getInt(1);
            }
            
            while(rst.next()){
                txfDUI.setText(rst.getString(1));
                txfNIT.setText(rst.getString(2));
                txfAFP.setText(rst.getString(3));
                txfISSS.setText(rst.getString(4));
                txfNombre.setText(rst.getString(5));
                txfApellido.setText(rst.getString(6));
                txfDireccion.setText(rst.getString(7));
                txfTelefono.setText(rst.getString(8));
                txfEmail.setText(rst.getString(9));
                txfTipo.setText(rst.getString(10));
                if(rst.getString(11).equals("SI")){
                    chkCliente.setSelected(true);
                    cliente=true;
                }
                if(rst.getString(12).equals("SI")){
                    chkProveedor.setSelected(true);
                    proveedor=true;
                }
                if(rst.getString(13).equals("SI")){
                    chkEmpleado.setSelected(true);   
                    empleado=true;
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txfNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txfApellido = new javax.swing.JTextField();
        lblDUI = new javax.swing.JLabel();
        txfDUI = new javax.swing.JTextField();
        lblNIT = new javax.swing.JLabel();
        txfNIT = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txfDireccion = new javax.swing.JTextField();
        lblEntidad = new javax.swing.JLabel();
        chkCliente = new javax.swing.JCheckBox();
        chkEmpleado = new javax.swing.JCheckBox();
        chkProveedor = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        txfISSS = new javax.swing.JTextField();
        lblISSS = new javax.swing.JLabel();
        lblAFP = new javax.swing.JLabel();
        txfAFP = new javax.swing.JTextField();
        txfEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txfTelefono = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblTipo = new javax.swing.JLabel();
        txfTipo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNombre.setText("Nombre:");

        lblApellido.setText("Apellido:");

        lblDUI.setText("DUI:");

        lblNIT.setText("NIT:");

        lblDireccion.setText("Dirección:");

        lblEntidad.setText("Entidad:");

        chkCliente.setText("Cliente");

        chkEmpleado.setText("Empleado");

        chkProveedor.setText("Proveedor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblDUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(lblApellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txfNIT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txfDUI)
                            .addComponent(txfDireccion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txfApellido)
                            .addComponent(txfNombre)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblEntidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNIT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(chkCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkEmpleado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfDUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDUI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNIT)
                    .addComponent(txfNIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEntidad)
                    .addComponent(chkCliente)
                    .addComponent(chkEmpleado)
                    .addComponent(chkProveedor))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblISSS.setText("ISSS:");

        lblAFP.setText("AFP:");

        lblEmail.setText("Email:");

        lblTelefono.setText("Telefono:");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblTipo.setText("Tipo:");

        txfTipo.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAFP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblISSS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txfTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                        .addComponent(txfEmail)
                        .addComponent(txfAFP)
                        .addComponent(txfISSS)
                        .addComponent(txfTipo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAFP)
                    .addComponent(txfAFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfISSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblISSS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(txfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        String nombre, apellido, dui, nit, direccion, telefono, email, afp, isss; 
        nombre =txfNombre.getText();
        apellido = txfApellido.getText();
        dui = txfDUI.getText();
        nit = txfNIT.getText();
        direccion = txfDireccion.getText();
        telefono = txfTelefono.getText();
        email = txfEmail.getText();
        afp = txfAFP.getText();
        isss = txfISSS.getText();
        
        //<editor-fold defaultstate="collapsed" desc="Nullables">
    if(apellido.isEmpty()){
        apellido=null;
    }else{
        apellido="'"+apellido+"'";
    }
    if(dui.isEmpty()){
        dui=null;
    }else{
        dui="'"+dui+"'";
    }
    if(nit.isEmpty()){
        nit=null;
    }else{
        nit="'"+nit+"'";
    }
    if(telefono.isEmpty()){
        telefono=null;
    }else{
        telefono="'"+telefono+"'";
    }
    if(email.isEmpty()){
        email=null;
    }else{
        email="'"+email+"'";
    }
    if(afp.isEmpty()){
        afp=null;
    }else{
        afp="'"+afp+"'";
    }
    if(isss.isEmpty()){
        isss=null;
    }else{
        isss="'"+isss+"'";
    }
    //</editor-fold>
        
        if(nombre.isEmpty()){
            JOptionPane.showMessageDialog(this, "Error en nombre");
        } else if(direccion.isEmpty()){
            JOptionPane.showMessageDialog(this, "Error en dirección");
        } else {
            if(apellido==null || dui==null || nit==null || telefono==null || email==null || afp==null || isss==null ){
                if(JOptionPane.showConfirmDialog(this, "El formulario contiene campos vacíos.\n¿Desea continuar?")==JOptionPane.YES_OPTION){
                    modificarEntidades();
                    if (cambioCli==1 && cambioEmp==1 && cambioPro ==1){
                        modificarReg(dui, nit, afp, isss, nombre, apellido, direccion, telefono, email);
                        cambioCli=0;
                        cambioPro=0;
                        cambioEmp=0;
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Operación abortada");
                }
            } else{
                modificarEntidades();
                if (cambioCli==1 && cambioEmp==1 && cambioPro ==1){
                    modificarReg(dui, nit, afp, isss, nombre, apellido, direccion, telefono, email);
                    cambioCli=0;
                    cambioPro=0;
                    cambioEmp=0;
                }
                        
            }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void modificarEntidades(){
        if(cliente!=chkCliente.isSelected()){
            if(chkCliente.isSelected()){
                per.agregarCliente(idPersona);
                cambioCli=1;
            } else{
                if (totPerVentas>0){
                    JOptionPane.showMessageDialog(this, "Este Cliente tiene Ventas asociadas a su registro. No se puede eliminar como Cliente");
                    chkCliente.setSelected(true);
                    chkCliente.setEnabled(false);
                    cambioCli=0;
                    
                }else{
                    per.eliminarCliente(idPersona);
                    cambioCli=1;
                }
            }
            
        }else{
            cambioCli=1;
        }
        if(empleado!=chkEmpleado.isSelected()){
            if(chkEmpleado.isSelected()){
                per.agregarEmpleado(idPersona);
                cambioEmp=1;
            } else{
                if (totUsuFactura>0){
                    JOptionPane.showMessageDialog(this, "Este Empleado tiene Ventas realizadas en su registro. No se puede eliminar como Empleado");
                    chkEmpleado.setSelected(true);
                    chkEmpleado.setEnabled(false);
                    cambioEmp=0;
                    
                }else if(totUsuCompras>0){
                    JOptionPane.showMessageDialog(this, "Este Empleado tiene Compras realizadas en su registro. No se puede eliminar como Empleado");
                    chkEmpleado.setSelected(true);
                    chkEmpleado.setEnabled(false);
                    cambioEmp=0;
                    
                
                }else{
                    per.eliminarEmpleado(idPersona);
                    cambioEmp=1;
                }
            }
        }else{
            cambioEmp=1;
        }
        
        if(proveedor!=chkProveedor.isSelected()){
            if(chkProveedor.isSelected()){
                per.agregarProveedor(idPersona);
                cambioPro=1;
            } else{
                if (totPerCompras>0){
                    JOptionPane.showMessageDialog(this, "Este Proveedor tiene Compras asociadas a su registro. No se puede eliminar como Proveedor");
                    chkProveedor.setSelected(true);
                    chkProveedor.setEnabled(false);
                    cambioPro=0;
                }else{
                    per.eliminarProveedor(idPersona);
                    cambioPro=1;
                }
                
                
            }
        }else{
            cambioPro=1;
        }
    }
    
    private void modificarReg(String dui, String nit, String afp, String isss, String nombre, String apellido, String direccion, String telefono, String email){
        per.editarPersona(idPersona, dui, nit, afp, isss, nombre, apellido,direccion, telefono, email);
        JOptionPane.showMessageDialog(this, "Registro modificado con éxito");
        this.dispose();
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
            java.util.logging.Logger.getLogger(FrmEditarPersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEditarPersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEditarPersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEditarPersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmEditarPersona dialog = new FrmEditarPersona(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox chkCliente;
    private javax.swing.JCheckBox chkEmpleado;
    private javax.swing.JCheckBox chkProveedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAFP;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblDUI;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEntidad;
    private javax.swing.JLabel lblISSS;
    private javax.swing.JLabel lblNIT;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextField txfAFP;
    private javax.swing.JTextField txfApellido;
    private javax.swing.JTextField txfDUI;
    private javax.swing.JTextField txfDireccion;
    private javax.swing.JTextField txfEmail;
    private javax.swing.JTextField txfISSS;
    private javax.swing.JTextField txfNIT;
    private javax.swing.JTextField txfNombre;
    private javax.swing.JTextField txfTelefono;
    private javax.swing.JTextField txfTipo;
    // End of variables declaration//GEN-END:variables
}
