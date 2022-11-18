package vista;

import controlador.VentasControllers;
import java.awt.Dimension;
import javax.swing.JComponent;
import modelo.Productos;
import modelo.Usuario;
import modelo.Ventas;
import modelo.VentasDAO;
import modelo.VentasDetalle;

public class Jventasdetalle extends javax.swing.JInternalFrame {
    Ventas ven = new Ventas();
    VentasDetalle vendeta = new VentasDetalle();
    Usuario user = new Usuario();
    Productos prod = new Productos();
    VentasDAO vendao = new VentasDAO();
//    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
//    private Dimension DimensionBarra = null;

    public Jventasdetalle() {
        initComponents();
//        QuitarLaBarraTitulo();
        VentasControllers ventascontrollers = new VentasControllers(vendeta, ven, user, prod, vendao, this);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jtdetalleventa = new javax.swing.JTable();
        btnpdf = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jltotalventa = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtventa = new javax.swing.JTable();
        cbestado = new javax.swing.JComboBox<>();
        btnmodificar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtdetalevista = new javax.swing.JTable();
        btnexit = new javax.swing.JButton();
        txtidventa = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        btneliminar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        txtprecio = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        btnseleccionar = new javax.swing.JButton();
        txtapellidovendedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtnombrevendedor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtproducto = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtidproducto = new javax.swing.JTextField();
        txtidvendedor = new javax.swing.JTextField();
        btnseleccionarprod = new javax.swing.JButton();
        dateventa = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtvendedor = new javax.swing.JTable();
        txtbusqueda = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnseleccionarv = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnseleccionarp = new javax.swing.JButton();
        btncancelarp = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtproducto = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setTitle("Ventas");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtdetalleventa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Cantidad", "Precio", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtdetalleventa);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 520, 220));

        btnpdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/pdf.png"))); // NOI18N
        btnpdf.setBorder(null);
        btnpdf.setContentAreaFilled(false);
        btnpdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpdfActionPerformed(evt);
            }
        });
        jPanel1.add(btnpdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 50, 50));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel1.setText("Total Venta");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 500, -1, -1));

        jltotalventa.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jltotalventa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jltotalventa.setText("----");
        jPanel1.add(jltotalventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, 100, -1));

        jtventa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID venta", "ID vendedor", "Nombres", "Apellidos", "Fecha", "Total", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtventa);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 660, 240));

        cbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción", "BAJA" }));
        jPanel1.add(cbestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 180, -1));

        btnmodificar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/updating.png"))); // NOI18N
        btnmodificar.setText("Modificar");
        btnmodificar.setBorder(null);
        btnmodificar.setContentAreaFilled(false);
        btnmodificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnmodificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(btnmodificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, 100, 50));

        jtdetalevista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID venta", "Producto", "Cantidad", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jtdetalevista);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 660, 230));

        btnexit.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        btnexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/cross.png"))); // NOI18N
        btnexit.setBorder(null);
        btnexit.setContentAreaFilled(false);
        btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });
        jPanel1.add(btnexit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 40, 40));
        jPanel1.add(txtidventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 60, -1));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btneliminar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/bin.png"))); // NOI18N
        btneliminar.setText("Eliminar");
        btneliminar.setBorder(null);
        btneliminar.setContentAreaFilled(false);
        btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 60, 60));

        btnguardar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/diskette.png"))); // NOI18N
        btnguardar.setText("Guardar");
        btnguardar.setBorder(null);
        btnguardar.setContentAreaFilled(false);
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.setVerifyInputWhenFocusTarget(false);
        btnguardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnguardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnguardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnguardarMouseExited(evt);
            }
        });
        jPanel3.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 60, 60));
        jPanel3.add(txtprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 60, -1));

        jLabel18.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel18.setText("Precio");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel17.setText("Cantidad");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, -1));
        jPanel3.add(txtcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 60, -1));

        btnseleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/selecciones-de-personal.png"))); // NOI18N
        btnseleccionar.setContentAreaFilled(false);
        jPanel3.add(btnseleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        jPanel3.add(txtapellidovendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 180, -1));

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel4.setText("Apellidos");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, -1));
        jPanel3.add(txtnombrevendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 180, 20));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel3.setText("Fecha de la venta");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, 20));
        jPanel3.add(txtproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 200, -1));

        jLabel16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel16.setText("Producto");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));
        jPanel3.add(txtidproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 40, -1));

        txtidvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidvendedorActionPerformed(evt);
            }
        });
        jPanel3.add(txtidvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 30, -1));

        btnseleccionarprod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/agregar-producto.png"))); // NOI18N
        btnseleccionarprod.setContentAreaFilled(false);
        jPanel3.add(btnseleccionarprod, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel3.add(dateventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 180, -1));

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel6.setText("Nombres");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, 20));

        jTabbedPane1.addTab("Registro de producto", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtvendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombres", "Apellidos", "Tipo Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jtvendedor);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 490, 140));
        jPanel4.add(txtbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 160, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/searching-bar (3).png"))); // NOI18N
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 30, 30));

        btnseleccionarv.setText("Seleccionar");
        jPanel4.add(btnseleccionarv, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        btncancelar.setText("Cancelar");
        jPanel4.add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 100, -1));

        jLabel5.setText("Buscar por nombre");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jTabbedPane1.addTab("Selección vendedor", jPanel4);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnseleccionarp.setText("Seleccionar");
        jPanel2.add(btnseleccionarp, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        btncancelarp.setText("Cancelar");
        jPanel2.add(btncancelarp, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 100, -1));

        jtproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Descripcion", "Precio venta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jtproducto);
        if (jtproducto.getColumnModel().getColumnCount() > 0) {
            jtproducto.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 500, 160));

        jTabbedPane1.addTab("Selección producto", jPanel2);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 520, 270));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        this.hide();
    }//GEN-LAST:event_btnexitActionPerformed

    private void btnguardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnguardarMouseExited
        //    btnguardar.setContentAreaFilled(false);
    }//GEN-LAST:event_btnguardarMouseExited

    private void btnguardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnguardarMouseEntered
        //    btnguardar.setContentAreaFilled(true);
    }//GEN-LAST:event_btnguardarMouseEntered

    private void btnpdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpdfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnpdfActionPerformed

    private void txtidvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidvendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidvendedorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btncancelar;
    public javax.swing.JButton btncancelarp;
    public javax.swing.JButton btneliminar;
    private javax.swing.JButton btnexit;
    public javax.swing.JButton btnguardar;
    public javax.swing.JButton btnmodificar;
    public javax.swing.JButton btnpdf;
    public javax.swing.JButton btnseleccionar;
    public javax.swing.JButton btnseleccionarp;
    public javax.swing.JButton btnseleccionarprod;
    public javax.swing.JButton btnseleccionarv;
    public javax.swing.JComboBox<String> cbestado;
    public com.toedter.calendar.JDateChooser dateventa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JLabel jltotalventa;
    public javax.swing.JTable jtdetalevista;
    public javax.swing.JTable jtdetalleventa;
    public javax.swing.JTable jtproducto;
    public javax.swing.JTable jtvendedor;
    public javax.swing.JTable jtventa;
    public javax.swing.JTextField txtapellidovendedor;
    public javax.swing.JTextField txtbusqueda;
    public javax.swing.JTextField txtcantidad;
    public javax.swing.JTextField txtidproducto;
    public javax.swing.JTextField txtidvendedor;
    public javax.swing.JTextField txtidventa;
    public javax.swing.JTextField txtnombrevendedor;
    public javax.swing.JTextField txtprecio;
    public javax.swing.JTextField txtproducto;
    // End of variables declaration//GEN-END:variables
}