package controlador;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.UsuarioDAO;
import modelo.Usuario;
import vista.Inicio;
import vista.Login;

public class LoginControllers implements ActionListener {

    private Usuario user;
    private UsuarioDAO usuariodao;
    private Login login;

    public LoginControllers(Usuario usuario, UsuarioDAO usuariodao, Login login) {
        this.user = usuario;
        this.usuariodao = usuariodao;
        this.login = login;
        this.login.btnentrar.addActionListener(this);
        this.login.btnsalir.addActionListener(this);
    }

    public void iniciar() {
        login.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.btnentrar) {
            if (login.txtusuario.getText().equals("") || String.valueOf(login.txtcontrasena.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            } else {
                String usuario = login.txtusuario.getText();
                String clave = String.valueOf(login.txtcontrasena.getPassword());
                    user = usuariodao.autenticacion(usuario, clave);
                    if (user != null) {
                        Inicio home = new Inicio();
//                        Jindicador indi = new Jindicador();
                        this.login.dispose();
                        home.show();
                        home.setExtendedState(Frame.MAXIMIZED_BOTH);
//                        Inicio.escritorio.add(indi);
//                        Dimension desktopSize = Inicio.escritorio.getSize();
//                        Dimension FrameSize = indi.getSize();
//                        indi.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
//                        indi.show();
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrecta");
                    }
                
            }
        }
        if (e.getSource() == login.btnsalir) {
            System.exit(0);
        }
    }

}
