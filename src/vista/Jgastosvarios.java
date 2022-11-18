/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.GastosvariosControllers;
import modelo.Gastosvarios;
import modelo.GastosvariosDAO;

/**
 *
 * @author gchoj
 */
public class Jgastosvarios extends javax.swing.JInternalFrame {

    Gastosvarios gav = new Gastosvarios();
    GastosvariosDAO gavdao = new GastosvariosDAO();
    
    
    public Jgastosvarios() {
        initComponents();
        GastosvariosControllers gvc = new GastosvariosControllers(gav, gavdao, this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttipopago = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        btnmodificar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        txtid = new javax.swing.JTextField();
        txtestado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbestado = new javax.swing.JComboBox<>();
        txtotro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dategastos = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtgastosvarios = new javax.swing.JTable();
        txtbusqueda = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnexit = new javax.swing.JButton();
        btlimpiar = new javax.swing.JButton();
        btnbaja = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnalta1 = new javax.swing.JButton();

        setTitle("Gastos varios");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso gastos varios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Fecha *");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));
        jPanel1.add(txttipopago, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 170, -1));

        jLabel4.setText("No. Documento");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        txtdescripcion.setColumns(20);
        txtdescripcion.setLineWrap(true);
        txtdescripcion.setRows(5);
        txtdescripcion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtdescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 270, 130));

        jLabel5.setText("Estado *");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));
        jPanel1.add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 140, -1));

        btnmodificar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/updating.png"))); // NOI18N
        btnmodificar.setText("Modificar");
        btnmodificar.setBorder(null);
        btnmodificar.setContentAreaFilled(false);
        btnmodificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnmodificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnmodificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(btnmodificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 60, 60));

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
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 60, 60));
        jPanel1.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 40, -1));
        jPanel1.add(txtestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 50, -1));

        jLabel6.setText("Descripción");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel8.setText("Total *");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        cbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALTA", "BAJA" }));
        jPanel1.add(cbestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 180, -1));
        jPanel1.add(txtotro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 170, -1));

        jLabel3.setText("Tipo de pago *");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));
        jPanel1.add(dategastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 170, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 290, 510));

        jtgastosvarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Tipo Pago", "No. Documento", "Descripcion", "Total", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtgastosvarios);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 47, 750, 460));
        jPanel2.add(txtbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 160, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/searching-bar (3).png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 30, 30));

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
        jPanel2.add(btnexit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 0, 40, 40));

        btlimpiar.setText("Limpiar");
        jPanel2.add(btlimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 90, -1));

        btnbaja.setText("Ver gastos de BAJA");
        btnbaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbajaActionPerformed(evt);
            }
        });
        jPanel2.add(btnbaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 520, -1, -1));

        jLabel2.setText("Buscar por fecha");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, -1));

        btnalta1.setText("Ver gastos de ALTA");
        jPanel2.add(btnalta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 520, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 560));

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
    public javax.swing.JButton btlimpiar;
    public javax.swing.JButton btnalta1;
    public javax.swing.JButton btnbaja;
    private javax.swing.JButton btnexit;
    public javax.swing.JButton btnguardar;
    public javax.swing.JButton btnmodificar;
    public javax.swing.JComboBox<String> cbestado;
    public com.toedter.calendar.JDateChooser dategastos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jtgastosvarios;
    public javax.swing.JTextField txtbusqueda;
    public javax.swing.JTextArea txtdescripcion;
    public javax.swing.JTextField txtestado;
    public javax.swing.JTextField txtid;
    public javax.swing.JTextField txtotro;
    public javax.swing.JTextField txttipopago;
    public javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
