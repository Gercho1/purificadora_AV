package vista;

import com.sun.awt.AWTUtilities;
import controlador.LoginControllers;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import modelo.TextPrompt;
import modelo.Usuario;
import modelo.UsuarioDAO;

public class Login extends javax.swing.JFrame {

    Usuario user = new Usuario();
    UsuarioDAO userdao = new UsuarioDAO();

    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        LoginControllers logincontroller = new LoginControllers(user, userdao, this);
        TextPrompt user = new TextPrompt("Campo Obligatorio", txtusuario);
        TextPrompt password = new TextPrompt("Campo Obligatorio", txtcontrasena);
//        Shape forma = new RoundRectangle2D.Double(0,0,this.getBounds().width,this.getBounds().height,27,27);
//        AWTUtilities.setWindowShape(this, forma);

    }
    
    public Image getIconImage(){
       Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Icono/soltar (1).png"));
       return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnsalir = new javax.swing.JButton();
        jplogin = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txtcontrasena = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        btnentrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setIconImage(getIconImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnsalir.setBackground(new java.awt.Color(61, 128, 211));
        btnsalir.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnsalir.setForeground(new java.awt.Color(255, 255, 255));
        btnsalir.setText("Salir");
        getContentPane().add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 590, -1, -1));

        jplogin.setBackground(new java.awt.Color(255, 255, 255));
        jplogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jplogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(72, 186, 252));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/logochiac2.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, 153));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("BIENVENIDO");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, 30));

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Agua viva para el mundo");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Agua viva para el mundo");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));

        jplogin.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 330));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/user.png"))); // NOI18N
        jplogin.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, 80));

        jPanel2.setBackground(new java.awt.Color(116, 204, 253));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Iniciar Sesión");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 170, 30));

        jplogin.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 310, 60));

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(72, 186, 252));
        jLabel7.setText("Usuario");
        jplogin.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(72, 186, 252));
        jLabel8.setText("Contraseña");
        jplogin.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, -1, -1));

        txtusuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtusuario.setBorder(null);
        jplogin.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 250, 30));
        jplogin.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 250, 20));

        txtcontrasena.setBorder(null);
        jplogin.add(txtcontrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 250, 30));
        jplogin.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 250, 20));

        btnentrar.setBackground(new java.awt.Color(61, 128, 211));
        btnentrar.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        btnentrar.setForeground(new java.awt.Color(255, 255, 255));
        btnentrar.setText("Ingresar");
        btnentrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jplogin.add(btnentrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 100, -1));

        getContentPane().add(jplogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 630, 330));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondo/background-1696064_1280.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnentrar;
    public javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel jplogin;
    public javax.swing.JPasswordField txtcontrasena;
    public javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}