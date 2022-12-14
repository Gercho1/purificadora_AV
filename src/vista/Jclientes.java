/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ClientesControllers;
import modelo.Clientes;
import modelo.ClientesDAO;

/**
 *
 * @author gchoj
 */
public class Jclientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form Jclientes
     */
    
    Clientes cliente = new Clientes();
    ClientesDAO clientesdao= new ClientesDAO();
    
    
    public Jclientes() {
        initComponents();
        ClientesControllers cr = new ClientesControllers(cliente, clientesdao, this);
        
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtnombres = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        txtid = new javax.swing.JTextField();
        txtestado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbestado = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtcliente = new javax.swing.JTable();
        btnexit = new javax.swing.JButton();
        txtbusqueda = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnalta = new javax.swing.JButton();
        btnbaja = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnlimpiar = new javax.swing.JButton();

        setTitle("Clientes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro de cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel1.setText("Nombres");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));
        jPanel2.add(txtnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 220, -1));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel2.setText("Apellidos");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));
        jPanel2.add(txtapellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 220, -1));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel3.setText("Direcci??n");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));
        jPanel2.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 220, -1));

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel4.setText("Estado");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));
        jPanel2.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 220, -1));

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
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 60, 60));

        btnmodificar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/updating.png"))); // NOI18N
        btnmodificar.setText("Modificar");
        btnmodificar.setBorder(null);
        btnmodificar.setContentAreaFilled(false);
        btnmodificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnmodificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnmodificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(btnmodificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 60, 60));
        jPanel2.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 20, 50, -1));
        jPanel2.add(txtestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 60, -1));

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel6.setText("Tel??fono");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        cbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALTA", "BAJA" }));
        jPanel2.add(cbestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 70, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 410));

        jtcliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombres", "Apellidos", "Direcci??n", "Tel??fono", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtcliente);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 700, 380));

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
        jPanel1.add(btnexit, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 50, 40));
        jPanel1.add(txtbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 160, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/searching-bar (3).png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 30, 30));

        btnalta.setText("Ver clientes de ALTA");
        jPanel1.add(btnalta, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 430, -1, 30));

        btnbaja.setText("Ver clientes de BAJA");
        btnbaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbajaActionPerformed(evt);
            }
        });
        jPanel1.add(btnbaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 430, -1, 30));

        jLabel7.setText("Buscar por nombre");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        btnlimpiar.setText("Limpiar");
        jPanel1.add(btnlimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnguardarMouseEntered
        //    btnguardar.setContentAreaFilled(true);
    }//GEN-LAST:event_btnguardarMouseEntered

    private void btnguardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnguardarMouseExited
        //    btnguardar.setContentAreaFilled(false);
    }//GEN-LAST:event_btnguardarMouseExited

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        this.hide();
    }//GEN-LAST:event_btnexitActionPerformed

    private void btnbajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbajaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnalta;
    public javax.swing.JButton btnbaja;
    private javax.swing.JButton btnexit;
    public javax.swing.JButton btnguardar;
    public javax.swing.JButton btnlimpiar;
    public javax.swing.JButton btnmodificar;
    public javax.swing.JComboBox<String> cbestado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtcliente;
    public javax.swing.JTextField txtapellidos;
    public javax.swing.JTextField txtbusqueda;
    public javax.swing.JTextField txtdireccion;
    public javax.swing.JTextField txtestado;
    public javax.swing.JTextField txtid;
    public javax.swing.JTextField txtnombres;
    public javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
