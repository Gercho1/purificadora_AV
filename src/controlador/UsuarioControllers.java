package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.Jusuarios;

public class UsuarioControllers implements ActionListener, MouseListener, KeyListener {

    private Usuario user;
    private UsuarioDAO userdao;
    private Jusuarios usuarios;

    DefaultTableModel modelo = new DefaultTableModel();

    public UsuarioControllers(Usuario user, UsuarioDAO userdao, Jusuarios usuarios) {
        this.user = user;
        this.userdao = userdao;
        this.usuarios = usuarios;
        /*botones*/
        this.usuarios.btnguardar.addActionListener(this);
        this.usuarios.btnmodificar.addActionListener(this);
        this.usuarios.btnmodificar.setEnabled(false);
        this.usuarios.btnalta.addActionListener(this);
        this.usuarios.btnbaja.addActionListener(this);
        this.usuarios.btnlimpiar.addActionListener(this);
        this.usuarios.btnlimpiar.addActionListener(this);
        
        /*texfields*/
        this.usuarios.txtbusqueda.addKeyListener(this);
        this.usuarios.txtid.setVisible(false);
        this.usuarios.txtestado.setVisible(false);
        this.usuarios.txtestado.setText("ALTA");
        this.usuarios.txtnombres.requestFocus();
        
        this.usuarios.jtusuarios.addMouseListener(this);
        this.usuarios.jtusuarios.addMouseListener(this);
        Listaraltas(usuarios.jtusuarios);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == usuarios.btnguardar) {
            if (usuarios.txtnombres.getText().isEmpty() || usuarios.txtapellidos.getText().isEmpty()
                    || usuarios.txtusuario.getText().isEmpty() || usuarios.txtclave.getText().isEmpty()
                    || usuarios.cbtipo.getSelectedIndex() == 0 || usuarios.cbestado.getSelectedIndex() == 1) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            }
            else if (usuarios.txtid.getText().equals("")) {
                guardar();
            } else {
                JOptionPane.showMessageDialog(null, "No se puede guardar el mismo registro, debe seleccionar modificar");
            }
        }
        if (e.getSource() == usuarios.btnmodificar) {
            if (usuarios.txtnombres.getText().isEmpty() || usuarios.txtapellidos.getText().isEmpty()
                    || usuarios.txtusuario.getText().isEmpty() || usuarios.txtclave.getText().isEmpty()
                    || usuarios.cbtipo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            } else {
                modificar();
                this.usuarios.btnmodificar.setEnabled(false);
                this.usuarios.btnguardar.setEnabled(true);
            }
        }
        if (e.getSource() == usuarios.btnalta) {
            limpiar();
            limpiartabla();
            Listaraltas(usuarios.jtusuarios);
            usuarios.txtnombres.requestFocus();
            usuarios.txtestado.setText("ALTA");
        }
        if (e.getSource() == usuarios.btnbaja) {
            limpiar();
            limpiartabla();
            Listarbajas(usuarios.jtusuarios);
            usuarios.txtnombres.requestFocus();
            usuarios.txtestado.setText("BAJA");
        }
        if(e.getSource() == usuarios.btnlimpiar){
            limpiar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == usuarios.jtusuarios) {
            int fila = usuarios.jtusuarios.rowAtPoint(e.getPoint());
            usuarios.txtid.setText(usuarios.jtusuarios.getValueAt(fila, 0).toString());
            usuarios.txtnombres.setText(usuarios.jtusuarios.getValueAt(fila, 1).toString());
            usuarios.txtapellidos.setText(usuarios.jtusuarios.getValueAt(fila, 2).toString());
            usuarios.txtusuario.setText(usuarios.jtusuarios.getValueAt(fila, 3).toString());
            usuarios.txtclave.setText(usuarios.jtusuarios.getValueAt(fila, 4).toString());
            usuarios.cbtipo.setSelectedItem(usuarios.jtusuarios.getValueAt(fila, 5).toString());
            usuarios.cbestado.setSelectedItem(usuarios.jtusuarios.getValueAt(fila, 6).toString());
            this.usuarios.btnguardar.setEnabled(false);
            this.usuarios.btnmodificar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Error al seleccionar");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == usuarios.txtbusqueda) {
            if (usuarios.txtbusqueda.getText().isEmpty()) {
                String a = usuarios.txtbusqueda.getText();
                String b = usuarios.txtestado.getText();
                limpiartabla();
                buscar(usuarios.jtusuarios, a, b);
            } else {
                String a = usuarios.txtbusqueda.getText();
                String b = usuarios.txtestado.getText();
                limpiartabla();
                buscar(usuarios.jtusuarios, a, b);
            }
        }

    }

    private void guardar() {
        try {
            String nombre = usuarios.txtnombres.getText();
            String apellido = usuarios.txtapellidos.getText();
            String usuario = usuarios.txtusuario.getText();
            String clave = usuarios.txtclave.getText();
            String tipo = usuarios.cbtipo.getSelectedItem().toString();
            String estado = usuarios.cbestado.getSelectedItem().toString();
            user.setNombres(nombre);
            user.setApellidos(apellido);
            user.setUsuario(usuario);
            user.setClave(clave);
            user.setTipo_Usuario(tipo);
            user.setEstado(estado);
            if (userdao.agregar(user) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(usuarios.jtusuarios);
                usuarios.txtnombres.requestFocus();
                JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar usuario");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos");
        }

    }

    private void modificar() {

        try {
            int id = Integer.parseInt(usuarios.txtid.getText());
            String nombre = usuarios.txtnombres.getText();
            String apellido = usuarios.txtapellidos.getText();
            String usuario = usuarios.txtusuario.getText();
            String clave = usuarios.txtclave.getText();
            String tipo = usuarios.cbtipo.getSelectedItem().toString();
            String estado = usuarios.cbestado.getSelectedItem().toString();
            user.setID(id);
            user.setNombres(nombre);
            user.setApellidos(apellido);
            user.setUsuario(usuario);
            user.setClave(clave);
            user.setTipo_Usuario(tipo);
            user.setEstado(estado);

            if (userdao.modificar(user) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(usuarios.jtusuarios);
                usuarios.txtnombres.requestFocus();
                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar usuario");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos");
        }

    }

    private void Listaraltas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Usuario> lista = userdao.listarAlta();
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getID();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getUsuario();
            object[4] = lista.get(i).getClave();
            object[5] = lista.get(i).getTipo_Usuario();
            object[6] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        usuarios.jtusuarios.setModel(modelo);
    }

    private void Listarbajas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Usuario> lista = userdao.listarBaja();
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getID();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getUsuario();
            object[4] = lista.get(i).getClave();
            object[5] = lista.get(i).getTipo_Usuario();
            object[6] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        usuarios.jtusuarios.setModel(modelo);
    }

    private void buscar(JTable tabla, String a, String b) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Usuario> lista = userdao.buscar(a, b);
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getID();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getUsuario();
            object[4] = lista.get(i).getClave();
            object[5] = lista.get(i).getTipo_Usuario();
            object[6] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        usuarios.jtusuarios.setModel(modelo);
    }

    private void limpiar() {
        usuarios.txtid.setText("");
        usuarios.txtnombres.setText("");
        usuarios.txtapellidos.setText("");
        usuarios.txtusuario.setText("");
        usuarios.txtclave.setText("");
        usuarios.cbtipo.setSelectedIndex(0);
        usuarios.cbestado.setSelectedIndex(0);
        usuarios.btnguardar.setEnabled(true);
        usuarios.btnmodificar.setEnabled(false);
    }

    private void limpiartabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

}
