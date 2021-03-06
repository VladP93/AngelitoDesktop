/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendaangelitodesktop;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import dao.Persona;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import tiendaangelitodesktop.editar.FrmEditarPersona;
/**
 *
 * @author DELL
 */
public class FrmPersonas extends javax.swing.JDialog {
    ResultSet rst = null;
    Persona per = new Persona ();
    DefaultTableModel model;
    java.awt.Frame parent;
    
    

    /**
     * Creates new form FrmPersonas
     */
    public FrmPersonas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        initComponents();
        this.setLocationRelativeTo(null);
        llenarTabla1();
        setEntidades();
        lblAlerta.setText("");
        if(Variables.tipoUsuario!=1){
            btnAsignar.setVisible(false);
        }
    }
    
    private void setEntidades(){
        switch (Variables.tipoUsuario) {
            case 1:
                chkCliente.setEnabled(true);
                chkEmpleado.setEnabled(true);
                chkProveedor.setEnabled(true);
                break;
            case 2:
                chkCliente.setEnabled(true);
                chkCliente.setSelected(true);
                chkEmpleado.setEnabled(false);
                chkProveedor.setEnabled(false);
                txfAFP.setEnabled(false);
                txfISSS.setEnabled(false);
                break;
            default:
                chkCliente.setEnabled(false);
                chkEmpleado.setEnabled(false);
                chkProveedor.setEnabled(false);
                break;
        }
    }
    
    public void llenarTabla1(){
        Object datos[] = new Object[10];
        rst = null;
        rst = per.personasEntidad();
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Dirección");
        model.addColumn("Teléfono");
        model.addColumn("Tipo");
        model.addColumn("Cliente");
        model.addColumn("Proveedor");
        model.addColumn("Empleado");
        model.addColumn("Usuario");
        model.addColumn("Rol");
        
        try {
            while (rst.next()){
                datos[0] = rst.getString(1);
                datos[1] = rst.getObject(2) + " " + rst.getObject(3);
                datos[2] = rst.getObject(4);
                datos[3] = rst.getObject(5);
                datos[4] = rst.getObject(6);
                datos[5] = rst.getObject(7);
                datos[6] = rst.getObject(8);
                datos[7] = rst.getObject(9);
                datos[8] = rst.getObject(10);
                datos[9] = rst.getObject(11);
                model.addRow(datos);
            }
            tblPersona.setModel(model);
            tblPersona.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPersona.getColumnModel().getColumn(0).setMinWidth(0);
            tblPersona.getColumnModel().getColumn(0).setPreferredWidth(0);
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

        Cargos = new javax.swing.ButtonGroup();
        pnlRegistro = new javax.swing.JPanel();
        lblEntidad = new javax.swing.JLabel();
        chkCliente = new javax.swing.JCheckBox();
        chkEmpleado = new javax.swing.JCheckBox();
        chkProveedor = new javax.swing.JCheckBox();
        btnAgregar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        scrRegistro = new javax.swing.JScrollPane();
        tblPersona = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnDetalles = new javax.swing.JButton();
        lblAlerta = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txfNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        txfApellido = new javax.swing.JTextField();
        lblDUI = new javax.swing.JLabel();
        txfDUI = new javax.swing.JTextField();
        lblNIT = new javax.swing.JLabel();
        txfNIT = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txfDireccion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cmbTipo = new javax.swing.JComboBox<>();
        lblTelefono = new javax.swing.JLabel();
        txfTelefono = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txfEmail = new javax.swing.JTextField();
        lblAFP = new javax.swing.JLabel();
        txfAFP = new javax.swing.JTextField();
        lblISSS = new javax.swing.JLabel();
        txfISSS = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        btnAsignar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        pnlRegistro.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos personales"));

        lblEntidad.setText("Entidad:");

        chkCliente.setText("Cliente");

        chkEmpleado.setText("Empleado");

        chkProveedor.setText("Proveedor");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tblPersona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tblPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblPersonaKeyPressed(evt);
            }
        });
        scrRegistro.setViewportView(tblPersona);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setText("Volver al menú");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnDetalles.setText("Ver Detalles");
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });

        lblAlerta.setText("_");

        txfNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txfNombreFocusLost(evt);
            }
        });

        lblNombre.setText("Nombre:");

        lblApellido.setText("Apellido:");

        lblDUI.setText("DUI:");

        lblNIT.setText("NIT:");

        lblDireccion.setText("Dirección:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblApellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDUI, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNIT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(txfDireccion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txfNIT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txfDUI, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txfApellido, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDUI)
                    .addComponent(txfDUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNIT)
                    .addComponent(txfNIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txfDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Natural", "Jurídica" }));

        lblTelefono.setText("Telefono:");

        lblEmail.setText("Email:");

        lblAFP.setText("AFP:");

        lblISSS.setText("ISSS:");

        lblTipo.setText("Tipo:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefono)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAFP, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblISSS, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txfISSS)
                    .addComponent(cmbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfEmail)
                    .addComponent(txfAFP)
                    .addComponent(txfTelefono))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(lblISSS)
                    .addComponent(txfISSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnAsignar.setText("Asignación de usuarios");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRegistroLayout = new javax.swing.GroupLayout(pnlRegistro);
        pnlRegistro.setLayout(pnlRegistroLayout);
        pnlRegistroLayout.setHorizontalGroup(
            pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                        .addComponent(btnAsignar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAlerta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDetalles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir))
                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                        .addComponent(scrRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                        .addComponent(lblEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(chkCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkEmpleado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregar)))
                .addContainerGap())
        );
        pnlRegistroLayout.setVerticalGroup(
            pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar)
                        .addComponent(btnLimpiar))
                    .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkCliente)
                        .addComponent(chkEmpleado)
                        .addComponent(chkProveedor)
                        .addComponent(lblEntidad)))
                .addGap(10, 10, 10)
                .addComponent(scrRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnSalir)
                    .addComponent(btnEliminar)
                    .addComponent(btnDetalles)
                    .addComponent(lblAlerta)
                    .addComponent(btnAsignar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        llenarTabla1();
    }//GEN-LAST:event_formWindowGainedFocus

    private void txfNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txfNombreFocusLost
        if(txfNombre.getText().matches("([a-z]||[A-Z])+ ([a-z]||[A-Z])+ ([a-z]||[A-Z])+")
            || txfNombre.getText().matches("([a-z]||[A-Z])+ ([a-z]||[A-Z])+")
            || txfNombre.getText().matches("([a-z]||[A-Z])+")){
            txfNombre.setForeground(Color.black);
            lblAlerta.setText("");
        }else{
            txfNombre.setForeground(Color.red);
            lblAlerta.setText("Nombre no válido");
        }
    }//GEN-LAST:event_txfNombreFocusLost

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        int idPersona=0;
        if(tblPersona.getSelectedRow()==-1){
            JOptionPane.showMessageDialog(this, "Seleccione el registro a detallar");
        } else {
            idPersona = Integer.parseInt(tblPersona.getValueAt(tblPersona.getSelectedRow(), 0).toString());
            FrmEditarPersona ed = new FrmEditarPersona(new java.awt.Frame(), true, idPersona, false);
            ed.setVisible(true);
        }
    }//GEN-LAST:event_btnDetallesActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int idPersona=0;
        boolean cli=false;
        boolean emp=false;
        boolean pro=false;
        int totPerVentas=0;
        int totPerCompras=0;
        int totUsuFactura=0;
        int totUsuCompras=0;

        if(tblPersona.getSelectedRow()==-1){
            JOptionPane.showMessageDialog(this, "Seleccione el registro que desea eliminar");
        } else {

            ResultSet perConVentas = null;
            ResultSet perConCompras = null;
            ResultSet usuConVentas = null;
            ResultSet usuConCompras = null;

            idPersona = Integer.parseInt(tblPersona.getValueAt(tblPersona.getSelectedRow(), 0).toString());
            perConVentas = per.obtenerSiCliente(idPersona);
            perConCompras = per.obtenerSiProveedor(idPersona);
            usuConVentas = per.obtenerSiUsuarioFac(idPersona);
            usuConCompras = per.obtenerSiUsuarioComp(idPersona);

            try {
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

                if (totPerVentas>0){
                    JOptionPane.showMessageDialog(this, "Esta persona es cliente de la tienda y tiene Ventas asociadas a su registro. No se puede eliminar");

                }else if (totPerCompras>0){
                    JOptionPane.showMessageDialog(this, "Esta persona es proveedor de la tienda y tiene Compras asociadas a su registro. No se puede eliminar");

                }else if (totUsuFactura>0){
                    JOptionPane.showMessageDialog(this, "Esta persona ha registrado Ventas para la tienda. No se puede eliminar");

                }else if (totUsuCompras>0){
                    JOptionPane.showMessageDialog(this, "Esta persona ha registrado Compras para la tienda. No se puede eliminar");

                }else{
                    int result = JOptionPane.showConfirmDialog (null, "¿Está usted seguro que desea "
                        + "eliminar a esta persona del sistema?","WARNING",
                        JOptionPane.YES_NO_OPTION);

                    if(result == JOptionPane.YES_OPTION) {
                        per.eliminarPersona(idPersona);
                        JOptionPane.showMessageDialog(this, "Registro eliminado");
                    }

                }

            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int idPersona = 0;
        if(tblPersona.getSelectedRow()!=-1){
            idPersona = Integer.parseInt(tblPersona.getValueAt(tblPersona.getSelectedRow(), 0).toString());
            FrmEditarPersona ed = new FrmEditarPersona(new java.awt.Frame(), true, idPersona, true);
            ed.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione el registro que desea editar");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String nombre, apellido, dui, nit, direccion, telefono, email, afp, isss;
        int nat, idPersona;

        idPersona = getIdPersona();
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

        if(cmbTipo.getSelectedIndex()==0){
            nat=1;
        }else{
            nat=0;
        }

        if(nombre.isEmpty()){
            JOptionPane.showMessageDialog(this, "Error en nombre");
        } else if(direccion.isEmpty()){
            JOptionPane.showMessageDialog(this, "Error en dirección");
        } else {
            if(apellido==null || dui==null || nit==null || telefono==null || email==null || afp==null || isss==null){
                if(JOptionPane.showConfirmDialog(this, "El formulario contiene campos vacíos.\n¿Desea continuar?")==JOptionPane.YES_OPTION){
                    if(!chkCliente.isSelected() && !chkEmpleado.isSelected() && !chkProveedor.isSelected()){
                        if(JOptionPane.showConfirmDialog(this, "El registro no pertenece a ninguna entidad,\n"
                            + "se registrará la persona pero no se reconocerá como cliente, empleado o proveedor.\n"
                            + "¿Desea continuar?")==JOptionPane.YES_OPTION){
                        per.agregarPersona(idPersona, dui, nit, afp, isss, nombre, apellido,direccion, telefono, email,nat);
                        JOptionPane.showMessageDialog(null, "Resgistro ingresado exitosamente");
                    } else{
                        JOptionPane.showMessageDialog(this, "Operación abortada");
                    }
                } else {
                    per.agregarPersona(idPersona, dui, nit, afp, isss, nombre, apellido,direccion, telefono, email,nat);
                    insertarEntidades(idPersona);
                    JOptionPane.showMessageDialog(null, "Resgistro ingresado exitosamente");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Operación abortada");
            }
        } else {
            per.agregarPersona(idPersona, dui, nit, afp, isss, nombre, apellido,direccion, telefono, email,nat);
            JOptionPane.showMessageDialog(null, "Resgistro ingresado exitosamente");
        }
        }

        llenarTabla1();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tblPersonaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPersonaKeyPressed
       if(evt.getKeyChar() == KeyEvent.VK_TAB){
           btnDetalles.requestFocus();
       }
    }//GEN-LAST:event_tblPersonaKeyPressed

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        FrmAdministrarUsuario adus = new FrmAdministrarUsuario(this.parent, true);
        adus.setVisible(true);
    }//GEN-LAST:event_btnAsignarActionPerformed

    private void insertarEntidades(int idPersona){
        if(chkCliente.isSelected()){
            per.agregarCliente(idPersona);
        }
        if(chkEmpleado.isSelected()){
            per.agregarEmpleado(idPersona);
        }
        if(chkProveedor.isSelected()){
            per.agregarProveedor(idPersona);
        }
    }
    
    private int getIdPersona(){
        int idPersona=-1;
        try{
            rst = null;
            rst = per.cantidadPersonas();
            while(rst.next()){
                idPersona = rst.getInt(1);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        return idPersona+1;
    }
    
    private void limpiar(){
        txfNombre.setText("");
        txfApellido.setText("");
        txfDUI.setText("");
        txfNIT.setText("");
        txfDireccion.setText("");
        txfTelefono.setText("");
        txfEmail.setText("");
        txfAFP.setText("");
        txfISSS.setText("");
        cmbTipo.setSelectedIndex(0);
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
            java.util.logging.Logger.getLogger(FrmPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmPersonas dialog = new FrmPersonas(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup Cargos;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkCliente;
    private javax.swing.JCheckBox chkEmpleado;
    private javax.swing.JCheckBox chkProveedor;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAFP;
    private javax.swing.JLabel lblAlerta;
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
    private javax.swing.JPanel pnlRegistro;
    private javax.swing.JScrollPane scrRegistro;
    private javax.swing.JTable tblPersona;
    private javax.swing.JTextField txfAFP;
    private javax.swing.JTextField txfApellido;
    private javax.swing.JTextField txfDUI;
    private javax.swing.JTextField txfDireccion;
    private javax.swing.JTextField txfEmail;
    private javax.swing.JTextField txfISSS;
    private javax.swing.JTextField txfNIT;
    private javax.swing.JTextField txfNombre;
    private javax.swing.JTextField txfTelefono;
    // End of variables declaration//GEN-END:variables
}
