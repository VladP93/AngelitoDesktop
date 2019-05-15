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
import dao.Factura;
import dao.Conexion;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vladlidar.gui.Txfields;

/**
 *
 * @author vladi
 */
public class FrmFactura extends javax.swing.JDialog {
    ResultSet rst = null;
    DefaultTableModel model; 
    Cliente cli = new Cliente();
    Producto prod = new Producto();
    Factura fac = new Factura();
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
        lblFacturaNo.setText(" "+numFactura());
        validaciones();
        this.setModal(false);
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
        Object datos[] = new Object[6];
        rst = null;
        rst = prod.productos();
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Producto");
        model.addColumn("Descripcion");
        model.addColumn("Existencia");
        model.addColumn("Precio");
        model.addColumn("Mayoreo");
        try {
            while (rst.next()){
                datos[0] = rst.getString(1);
                datos[1] = rst.getObject(2);
                datos[2] = rst.getString(3);
                datos[3] = rst.getString(8);
                datos[4] = "$ "+rst.getString(4);
                datos[5] = "#"+rst.getString(7) + " $"+rst.getString(5);
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
        Object datos[] = new Object[6];
        rst = null;
        rst = prod.productos(filtro);
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Producto");
        model.addColumn("Descripcion");
        model.addColumn("Existencia");
        model.addColumn("Precio");
        model.addColumn("Mayoreo");
        try {
            while (rst.next()){
                datos[0] = rst.getString(1);
                datos[1] = rst.getObject(2);
                datos[2] = rst.getString(3);
                datos[3] = rst.getString(8);
                datos[4] = "$ "+rst.getString(4);
                datos[5] = "#"+rst.getString(7) + " $"+rst.getString(5);
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
        boolean duplicado=false;
        if(tblDetalles.getModel().getRowCount()>0){
            for(int i=0;i<tblDetalles.getModel().getRowCount();i++){
                if(tblDetalles.getModel().getValueAt(i, 0).toString().equals(idProducto)){
                    duplicado=true;
                    detalleDuplpicado(idProducto, cantidad, i);
                }
            }
        }
        if((!duplicado) && evaluarExistencias(idProducto, cantidad)){
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
    }
    
    private void detalleDuplpicado(String idProducto, int cantidad, int row){
        int suma = (Integer) tblDetalles.getModel().getValueAt(row, 3) + cantidad;
        eliminarRegistro(row);
        agregarDetalle(idProducto, suma);
    }
    
    private boolean evaluarExistencias(String idProducto, int cantidad){
        boolean retorno=false;
        if(tblProductos.getModel().getRowCount()>0){
            for(int i=0;i<tblProductos.getModel().getRowCount();i++){
                if(tblProductos.getModel().getValueAt(i,0).toString().equals(idProducto)){
                    if(cantidad<=Integer.parseInt(tblProductos.getModel().getValueAt(i, 3).toString())){
                        retorno = true;
                    }
                }
            }
        }
        if(!retorno)JOptionPane.showMessageDialog(null, "Existencias insuficientes");
        return retorno;
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
        lblBuscarCliente = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblClienteSeleccionado = new javax.swing.JLabel();
        pnlProducto = new javax.swing.JPanel();
        txfProducto = new javax.swing.JTextField();
        btnNuevoProducto = new javax.swing.JButton();
        btnAgregarProducto = new javax.swing.JButton();
        spnlProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        txfCantidad = new javax.swing.JTextField();
        lblBuscarProd = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        pnlDetalle = new javax.swing.JPanel();
        spnlDetalles = new javax.swing.JScrollPane();
        tblDetalles = new javax.swing.JTable();
        btnQuitar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblCP = new javax.swing.JLabel();
        lblCantidadProd = new javax.swing.JLabel();
        lblT = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblFN = new javax.swing.JLabel();
        lblFacturaNo = new javax.swing.JLabel();
        btnFacturar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalExclusionType(null);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

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

        btnRegistrarCliente.setText("Registrar nuevo Cliente");
        btnRegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarClienteActionPerformed(evt);
            }
        });

        btnSeleccionarCliente.setText("Seleccionar Cliente");
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

        lblBuscarCliente.setText("Buscar Cliente:");

        lblCliente.setText("Cliente Seleccionado para esta factura:");

        lblClienteSeleccionado.setText("Seleccione cliente");

        javax.swing.GroupLayout pnlClienteLayout = new javax.swing.GroupLayout(pnlCliente);
        pnlCliente.setLayout(pnlClienteLayout);
        pnlClienteLayout.setHorizontalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnlClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addComponent(lblBuscarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfCliente)
                        .addGap(52, 52, 52)
                        .addComponent(btnRegistrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addComponent(btnSeleccionarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(lblCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClienteSeleccionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlClienteLayout.setVerticalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarCliente)
                    .addComponent(lblBuscarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spnlClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionarCliente)
                    .addComponent(lblCliente)
                    .addComponent(lblClienteSeleccionado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlProducto.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        txfProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfProductoActionPerformed(evt);
            }
        });
        txfProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txfProductoKeyReleased(evt);
            }
        });

        btnNuevoProducto.setText("Añadir NuevoProducto");
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        btnAgregarProducto.setText("Agregar a Detalle");
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

        lblBuscarProd.setText("Buscar Producto:");

        lblCantidad.setText("Cantidad:");

        javax.swing.GroupLayout pnlProductoLayout = new javax.swing.GroupLayout(pnlProducto);
        pnlProducto.setLayout(pnlProductoLayout);
        pnlProductoLayout.setHorizontalGroup(
            pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnlProductos)
                    .addGroup(pnlProductoLayout.createSequentialGroup()
                        .addGroup(pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProductoLayout.createSequentialGroup()
                                .addComponent(lblBuscarProd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCantidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlProductoLayout.setVerticalGroup(
            pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductoLayout.createSequentialGroup()
                .addGroup(pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoProducto)
                    .addComponent(txfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscarProd)
                    .addComponent(lblCantidad))
                .addGap(18, 18, 18)
                .addComponent(spnlProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarProducto)
                .addContainerGap())
        );

        pnlDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

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

        btnQuitar.setText("Quitar Detalle");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));

        lblCP.setText("Cantidad de productos:");

        lblCantidadProd.setText("0");

        lblT.setText("Total:");

        lblTotal.setText("$0.00");

        lblFN.setText("Factura N°:");

        lblFacturaNo.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFacturaNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCantidadProd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(lblTotal)
                .addComponent(lblFN)
                .addComponent(lblFacturaNo))
        );

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spnlDetalles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(spnlDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnFacturar.setText("Facturar");
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFacturar)
                    .addComponent(btnCancelar))
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
        FrmPersonas frame = new FrmPersonas(this.parent,true);
        frame.setVisible(true);
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
        txfCantidad.setText("0");
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void txfCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfCantidadActionPerformed
        if(Integer.parseInt(txfCantidad.getText())>0){
            agregarDetalle(seleccionarProducto(),Integer.parseInt(txfCantidad.getText()));
        }
    }//GEN-LAST:event_txfCantidadActionPerformed

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        if(Variables.tipoUsuario==1){
            FrmAdministrarProducto frame = new FrmAdministrarProducto(this.parent,true);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no autorizado");
        }
        
    }//GEN-LAST:event_btnNuevoProductoActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        int idFactura = numFactura();
        int idPersona = idCliente;
        int idUsuario = Variables.idUsuario;
        String idProducto="";
        int cantidad=0;
        
        if(idCliente!=-1){
            if(tblDetalles.getModel().getRowCount()<0){
                JOptionPane.showMessageDialog(null, "No hay elemntos a facturar");
            }else{
                for(int i=0;i<tblDetalles.getModel().getRowCount();i++){
                    try {
                        idProducto = tblDetalles.getModel().getValueAt(i, 0).toString();
                        cantidad = Integer.parseInt(tblDetalles.getModel().getValueAt(i, 3).toString());
                        rst = null;
                        rst = fac.facturar(idFactura, idPersona, idUsuario, idProducto, cantidad);
                        rst.next();
                        JOptionPane.showMessageDialog(null, rst.getString(1));
                    } catch (SQLException ex) {
                        Logger.getLogger(FrmFactura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente");
        }
        actualizar();
        generarReporte(idFactura);
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void txfProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfProductoKeyReleased
        filtrarProductos(txfProducto.getText());
    }//GEN-LAST:event_txfProductoKeyReleased

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        //this.setModal(true);
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog (null, "Se perderán los datos de la presente factura. "
        + "¿Está usted seguro que desea salir? ","WARNING", dialogButton);
        
        if(dialogButton == JOptionPane.YES_OPTION) {
            this.dispose();
        }else if(dialogButton == JOptionPane.NO_OPTION) {
            remove(dialogButton);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        int selectedRow = tblDetalles.getSelectedRow();

        if(selectedRow==-1){
            JOptionPane.showMessageDialog(null, "Seleccione el registro que desea quitar de la factura");
        }else{
            eliminarRegistro(selectedRow);
            resumen();
        }

    }//GEN-LAST:event_btnQuitarActionPerformed

    private void generarReporte(int idFactura){
        Conexion con = new Conexion();
        Connection conn;
        try{
            conn = con.conectar();
            
            JasperReport reporte = null;
            
            reporte = (JasperReport) JRLoader.loadObject(this.getClass().getResource("../recursos/reportes/Factura.jasper"));
            Map params = new HashMap();
            params.put("idFactura",idFactura);
            JasperPrint jprint=JasperFillManager.fillReport(reporte, params, conn);
            this.setModal(false);
            JasperViewer view = new JasperViewer(jprint,false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
        } catch (JRException e){
            JOptionPane.showMessageDialog(null, e.getMessage() +" ; "+e.getMessageKey());
            e.printStackTrace();
        } finally{
            con.desconectar();
        }
    }
    
    private void actualizar(){
        txfCantidad.setText("0");
        txfCliente.setText("");
        txfProducto.setText("");
        llenarClientes();
        llenarProductos();
        iniciarDetalles();
        lblFacturaNo.setText(" "+numFactura());
    }
    
    private int numFactura(){
        int noF=0;
        try{
            rst = null;
            rst = fac.ultimaFactura();
            while(rst.next()){
                noF=rst.getInt(1);
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        noF+=1;
        return noF;
    }
    
    private void seleccionarCliente(){
        int selectedRow=-1;
        String nombreCliente="";
        
        selectedRow = tblClientes.getSelectedRow();
        
        if(selectedRow==-1){
            nombreCliente = tblClientes.getModel().getValueAt(0, 2).toString();
            idCliente = Integer.parseInt(tblClientes.getModel().getValueAt(0, 0).toString());
        }else{
            nombreCliente = tblClientes.getModel().getValueAt(selectedRow,2).toString();
            idCliente = Integer.parseInt(tblClientes.getModel().getValueAt(selectedRow, 0).toString());
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
    
    private void eliminarRegistro(int row){
        ((DefaultTableModel)tblDetalles.getModel()).removeRow(row);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnRegistrarCliente;
    private javax.swing.JButton btnSeleccionarCliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBuscarCliente;
    private javax.swing.JLabel lblBuscarProd;
    private javax.swing.JLabel lblCP;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCantidadProd;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblClienteSeleccionado;
    private javax.swing.JLabel lblFN;
    private javax.swing.JLabel lblFacturaNo;
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
