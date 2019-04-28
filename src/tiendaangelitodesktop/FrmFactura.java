/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendaangelitodesktop;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.Cliente;
import dao.Producto;
import java.math.BigDecimal;
import vladlidar.gui.Txfields;

/**
 *
 * PENDIENTE:   -FALTA VALIDAR OPERACIONES EN LA BASE DE DATOS, DESCONTAR PRODUCTOS FACTURADOS
 *              -AGREGAR EL INICIO DE FORMULARIOS DE AGREGAR PRODUCTOS Y CLIENTES
 *              -GENERAR REPORTES Y HACER COMMIT DE LA FACTURA EN LA BASE DE DATOS
 *              (INSERT EN FACTURA Y DETALLES DE FACTURA).
 * @author vladi
 */
public class FrmFactura extends javax.swing.JDialog {
    ResultSet rst = null;
    DefaultTableModel model; 
    Cliente cli = new Cliente();
    Producto prod = new Producto();
    java.awt.Frame parent;
    Txfields vtxf= new Txfields();
    int idCliente=-1;
    String idProducto="";

    /**
     * Creates new form FrmFactura
     */
    public FrmFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        initComponents();
        llenarClientes();
        llenarProductos();
        iniciarDetalles();
        validaciones();
    }
    
    private void llenarClientes(){
        Object datos[] = new Object[3];
        rst = null;
        rst = cli.clientes();
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("DUI");
        model.addColumn("Nombre");
        try {
            while (rst.next()){
                datos[0] = rst.getString(1);
                datos[1] = rst.getObject(2);
                datos[2] = rst.getString(6) + " " + rst.getString(7);
                model.addRow(datos);
            }
            
            tblClientes.setModel(model);
            tblClientes.getColumnModel().getColumn(0).setMaxWidth(0);
            tblClientes.getColumnModel().getColumn(0).setMinWidth(0);
            tblClientes.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void filtrarClientes(String filtro){
        Object datos[] = new Object[3];
        rst = null;
        rst = cli.clientes(filtro);
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("DUI");
        model.addColumn("Nombre");
        try {
            while (rst.next()){
                datos[0] = rst.getString(1);
                datos[1] = rst.getObject(2);
                datos[2] = rst.getString(6) + " " + rst.getString(7);
                model.addRow(datos);
            }
            
            tblClientes.setModel(model);
            tblClientes.getColumnModel().getColumn(0).setMaxWidth(0);
            tblClientes.getColumnModel().getColumn(0).setMinWidth(0);
            tblClientes.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void llenarProductos(){
        Object datos[] = new Object[5];
        rst = null;
        rst = prod.productos();
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Producto");
        model.addColumn("Descripcion");
        model.addColumn("Precio");
        model.addColumn("Mayoreo");
        try {
            while (rst.next()){
                datos[0] = rst.getString(1);
                datos[1] = rst.getObject(2);
                datos[2] = rst.getString(3);
                datos[3] = "$ "+rst.getString(4);
                datos[4] = "#"+rst.getString(7) + " $"+rst.getString(5);
                model.addRow(datos);
            }
            
            tblProductos.setModel(model);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void filtrarProductos(String filtro){
        Object datos[] = new Object[5];
        rst = null;
        rst = prod.productos(filtro);
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Producto");
        model.addColumn("Descripcion");
        model.addColumn("Precio");
        model.addColumn("Mayoreo");
        try {
            while (rst.next()){
                datos[0] = rst.getString(1);
                datos[1] = rst.getObject(2);
                datos[2] = rst.getString(3);
                datos[3] = "$ "+rst.getString(4);
                datos[4] = "#"+rst.getString(7) + " $"+rst.getString(5);
                model.addRow(datos);
            }
            
            tblProductos.setModel(model);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void iniciarDetalles(){
        model = new DefaultTableModel();
        model.addColumn("Cod. Producto");
        model.addColumn("Producto");
        model.addColumn("Descripción");
        model.addColumn("Cantidad");
        model.addColumn("Precio $");
        model.addColumn("Subtotal $");
        tblDetalles.setModel(model);
    }
    
    private void agregarDetalle(String idProducto, int cantidad){
        Object datos[] = new Object[6];
        model = (DefaultTableModel) tblDetalles.getModel();
        rst = null;
        rst = prod.productosPorCodigo(idProducto);
        try {
            while (rst.next()){
                datos[0] = idProducto;
                datos[1] = rst.getObject(2);
                datos[2] = rst.getString(3);
                datos[3] = cantidad;
                if(cantidad>=(Integer)rst.getObject(7)){
                    datos[4] = rst.getObject(5);
                } else {
                    datos[4] = rst.getObject(4);
                }
                datos[5] = (Integer) datos[3] * Double.parseDouble(datos[4].toString());
                
                model.addRow(datos);
            }
            
            tblDetalles.setModel(model);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
        resumen();
    }
    
    private void resumen(){
        int cantidad=0;
        double total=0;
        for(int i=0;i<tblDetalles.getRowCount();i++){
            cantidad+=(Integer)tblDetalles.getModel().getValueAt(i, 3);
            total+=Double.parseDouble(tblDetalles.getModel().getValueAt(i,5).toString());
        }
        lblCantidadProd.setText(" "+cantidad);
        lblTotal.setText("$ "+total);
    }
    
    private void validaciones(){
        vtxf.soloNumeros(txfCantidad);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCliente = new javax.swing.JPanel();
        txfCliente = new javax.swing.JTextField();
        btnRegistrarCliente = new javax.swing.JButton();
        btnSeleccionarCliente = new javax.swing.JButton();
        spnlClientes = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        pnlProducto = new javax.swing.JPanel();
        txfProducto = new javax.swing.JTextField();
        btnNuevoProducto = new javax.swing.JButton();
        btnAgregarProducto = new javax.swing.JButton();
        spnlProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        txfCantidad = new javax.swing.JTextField();
        pnlDetalle = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        lblClienteSeleccionado = new javax.swing.JLabel();
        spnlDetalles = new javax.swing.JScrollPane();
        tblDetalles = new javax.swing.JTable();
        btnQuitar = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblCP = new javax.swing.JLabel();
        lblCantidadProd = new javax.swing.JLabel();
        lblT = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        txfCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfClienteActionPerformed(evt);
            }
        });
        txfCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txfClienteKeyReleased(evt);
            }
        });

        btnRegistrarCliente.setText("Registrar");
        btnRegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarClienteActionPerformed(evt);
            }
        });

        btnSeleccionarCliente.setText("Seleccionar");
        btnSeleccionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarClienteActionPerformed(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spnlClientes.setViewportView(tblClientes);

        javax.swing.GroupLayout pnlClienteLayout = new javax.swing.GroupLayout(pnlCliente);
        pnlCliente.setLayout(pnlClienteLayout);
        pnlClienteLayout.setHorizontalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnlClientes)
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addComponent(txfCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegistrarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeleccionarCliente)))
                .addContainerGap())
        );
        pnlClienteLayout.setVerticalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarCliente)
                    .addComponent(btnRegistrarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spnlClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlProducto.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        txfProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfProductoActionPerformed(evt);
            }
        });

        btnNuevoProducto.setText("Nuevo");
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        btnAgregarProducto.setText("Agregar");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spnlProductos.setViewportView(tblProductos);

        txfCantidad.setText("0");
        txfCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfCantidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProductoLayout = new javax.swing.GroupLayout(pnlProducto);
        pnlProducto.setLayout(pnlProductoLayout);
        pnlProductoLayout.setHorizontalGroup(
            pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnlProductos)
                    .addGroup(pnlProductoLayout.createSequentialGroup()
                        .addComponent(txfProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregarProducto)))
                .addContainerGap())
        );
        pnlProductoLayout.setVerticalGroup(
            pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductoLayout.createSequentialGroup()
                .addGroup(pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarProducto)
                    .addComponent(btnNuevoProducto)
                    .addComponent(txfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(spnlProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        lblCliente.setText("Cliente:");

        lblClienteSeleccionado.setText("Seleccione cliente");

        tblDetalles.setModel(new javax.swing.table.DefaultTableModel(
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
        spnlDetalles.setViewportView(tblDetalles);

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        btnFacturar.setText("Facturar");
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));

        lblCP.setText("Cantidad de productos:");

        lblCantidadProd.setText("0");

        lblT.setText("Total:");

        lblTotal.setText("$0.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCantidadProd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblCP)
                .addComponent(lblCantidadProd)
                .addComponent(lblT)
                .addComponent(lblTotal))
        );

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spnlDetalles)
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(lblCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblClienteSeleccionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnQuitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFacturar)))
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente)
                    .addComponent(lblClienteSeleccionado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spnlDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFacturar)
                    .addComponent(btnQuitar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarClienteActionPerformed
        seleccionarCliente();
    }//GEN-LAST:event_btnSeleccionarClienteActionPerformed

    private void txfClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfClienteActionPerformed
        seleccionarCliente();
    }//GEN-LAST:event_txfClienteActionPerformed

    private void txfClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfClienteKeyReleased
        String filtro = txfCliente.getText();
        filtrarClientes(filtro);
    }//GEN-LAST:event_txfClienteKeyReleased

    private void btnRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarClienteActionPerformed
        //Agregar formulario para agregar clientes
    }//GEN-LAST:event_btnRegistrarClienteActionPerformed

    private void txfProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfProductoActionPerformed
        if(Integer.parseInt(txfCantidad.getText())>0){
            agregarDetalle(seleccionarProducto(),Integer.parseInt(txfCantidad.getText()));
        }
    }//GEN-LAST:event_txfProductoActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        if(Integer.parseInt(txfCantidad.getText())>0){
            agregarDetalle(seleccionarProducto(),Integer.parseInt(txfCantidad.getText()));
        }
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void txfCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfCantidadActionPerformed
        if(Integer.parseInt(txfCantidad.getText())>0){
            agregarDetalle(seleccionarProducto(),Integer.parseInt(txfCantidad.getText()));
        }
    }//GEN-LAST:event_txfCantidadActionPerformed

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        // agregar formulario de productos
    }//GEN-LAST:event_btnNuevoProductoActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        int selectedRow = tblDetalles.getSelectedRow();
        
        if(selectedRow==-1){
            JOptionPane.showMessageDialog(null, "Seleccione el registro que desea quitar de la factura");
        }else{
            ((DefaultTableModel)tblDetalles.getModel()).removeRow(selectedRow);
            resumen();
        }
        
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        // Generar Reporte
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void seleccionarCliente(){
        int selectedRow=-1;
        String nombreCliente="";
        
        selectedRow = tblClientes.getSelectedRow();
        
        if(selectedRow==-1){
            nombreCliente = tblClientes.getModel().getValueAt(0, 2).toString();
            idCliente = Integer.parseInt(tblClientes.getModel().getValueAt(0, 0).toString());
        }else{
            nombreCliente = tblClientes.getModel().getValueAt(selectedRow,2).toString();
            idCliente = (Integer) tblClientes.getModel().getValueAt(selectedRow, 0);
        }
        
        lblClienteSeleccionado.setText(nombreCliente);
        
    }
    
    private String seleccionarProducto(){
        int selectedRow=-1;
        
        selectedRow = tblProductos.getSelectedRow();
        
        if(selectedRow==-1){
            return tblProductos.getModel().getValueAt(0, 0).toString();
        }else{
            return tblProductos.getModel().getValueAt(selectedRow, 0).toString();
        }
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
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmFactura dialog = new FrmFactura(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnRegistrarCliente;
    private javax.swing.JButton btnSeleccionarCliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCP;
    private javax.swing.JLabel lblCantidadProd;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblClienteSeleccionado;
    private javax.swing.JLabel lblT;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlCliente;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlProducto;
    private javax.swing.JScrollPane spnlClientes;
    private javax.swing.JScrollPane spnlDetalles;
    private javax.swing.JScrollPane spnlProductos;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblDetalles;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txfCantidad;
    private javax.swing.JTextField txfCliente;
    private javax.swing.JTextField txfProducto;
    // End of variables declaration//GEN-END:variables
}
