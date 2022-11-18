package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Clientes;
import modelo.ClientesDAO;
import vista.Jclientes;

public class ClientesControllers implements ActionListener, MouseListener, KeyListener {

    private Clientes cliente;
    private ClientesDAO clientedao;
    private Jclientes formcliente;

    DefaultTableModel modelo = new DefaultTableModel();
        


    public ClientesControllers(Clientes cliente, ClientesDAO clientedao, Jclientes formcliente) {
        this.cliente = cliente;
        this.clientedao = clientedao;
        this.formcliente = formcliente;
        /*botones*/
        this.formcliente.btnguardar.addActionListener(this);
        this.formcliente.btnmodificar.addActionListener(this);
        this.formcliente.btnmodificar.setEnabled(false);
        this.formcliente.btnalta.addActionListener(this);
        this.formcliente.btnbaja.addActionListener(this);
        this.formcliente.btnlimpiar.addActionListener(this);
        /*texfields*/
        this.formcliente.txtbusqueda.addKeyListener(this);
        this.formcliente.txtid.setVisible(false);
        this.formcliente.txtestado.setVisible(false);
        this.formcliente.txtnombres.requestFocus();
        this.formcliente.txtestado.setText("ALTA");
        
        this.formcliente.jtcliente.addMouseListener(this);
        Listaraltas(formcliente.jtcliente);
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formcliente.btnguardar) {
            if (formcliente.txtnombres.getText().isEmpty() || formcliente.txtapellidos.getText().isEmpty()
                    || formcliente.txtdireccion.getText().isEmpty()|| formcliente.cbestado.getSelectedIndex() == 1 ) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            } else if (formcliente.txtid.getText().equals("")) {
                guardar();
            } else {
                JOptionPane.showMessageDialog(null, "No puede guardar un registro ya guardado, debe actualizarlo");
            } 
        }
        if (e.getSource() == formcliente.btnmodificar) {
            if (formcliente.txtnombres.getText().isEmpty() || formcliente.txtapellidos.getText().isEmpty()
                    || formcliente.txtdireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            } else{
                modificar();
                formcliente.btnmodificar.setEnabled(true);
                formcliente.btnguardar.setEnabled(false);
            }
        }
        if (e.getSource() == formcliente.btnalta) {
            limpiar();
            limpiartabla();
            Listaraltas(formcliente.jtcliente);
            formcliente.txtnombres.requestFocus();
            formcliente.txtestado.setText("ALTA");
        }
        if (e.getSource() == formcliente.btnbaja) {
            limpiar();
            limpiartabla();
            Listarbajas(formcliente.jtcliente);
            formcliente.txtnombres.requestFocus();
            formcliente.txtestado.setText("BAJA");
    }
        if(e.getSource() == formcliente.btnlimpiar){
            limpiar();
        }
}

@Override
        public void mouseClicked(MouseEvent e) {
        if (e.getSource() == formcliente.jtcliente) {
            int fila = formcliente.jtcliente.rowAtPoint(e.getPoint());
            formcliente.txtid.setText(formcliente.jtcliente.getValueAt(fila, 0).toString());
            formcliente.txtnombres.setText(formcliente.jtcliente.getValueAt(fila, 1).toString());
            formcliente.txtapellidos.setText(formcliente.jtcliente.getValueAt(fila, 2).toString());
            formcliente.txtdireccion.setText(formcliente.jtcliente.getValueAt(fila, 3).toString());
            formcliente.txttelefono.setText(formcliente.jtcliente.getValueAt(fila, 4).toString());
            formcliente.cbestado.setSelectedItem(formcliente.jtcliente.getValueAt(fila, 5).toString());
            
            formcliente.btnmodificar.setEnabled(true);
            formcliente.btnguardar.setEnabled(false);
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
        if (e.getSource() == formcliente.txtbusqueda) {
            if (formcliente.txtbusqueda.getText().isEmpty()) {
                String a = formcliente.txtbusqueda.getText();
                String b = formcliente.txtestado.getText();
                limpiartabla();
                buscar(formcliente.jtcliente, a, b);
            } else {
                String a = formcliente.txtbusqueda.getText();
                String b = formcliente.txtestado.getText();
                limpiartabla();
                buscar(formcliente.jtcliente, a, b);
            }
        }
    }

    private void guardar() {
        try {
            String nombres = formcliente.txtnombres.getText();
            String apellidos = formcliente.txtapellidos.getText();
            String direccion = formcliente.txtdireccion.getText();
            int telefono = Integer.parseInt(formcliente.txttelefono.getText());
            String estado = formcliente.cbestado.getSelectedItem().toString();
            cliente.setNombres(nombres);
            cliente.setApellidos(apellidos);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            cliente.setEstado(estado);
            if (clientedao.guardar(cliente) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(formcliente.jtcliente);
                formcliente.txtnombres.requestFocus();
                JOptionPane.showMessageDialog(null, "Cliente agregado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar cliente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }
    }

    private void modificar() {
        try {
            int id = Integer.parseInt(formcliente.txtid.getText());
            String nombres = formcliente.txtnombres.getText();
            String apellidos = formcliente.txtapellidos.getText();
            String direccion = formcliente.txtdireccion.getText();
            int telefono = Integer.parseInt(formcliente.txttelefono.getText());
            String estado = formcliente.cbestado.getSelectedItem().toString();
            cliente.setId(id);
            cliente.setNombres(nombres);
            cliente.setApellidos(apellidos);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            cliente.setEstado(estado);
            if (clientedao.modificar(cliente) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(formcliente.jtcliente);
                formcliente.txtnombres.requestFocus();
                JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar cliente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void Listaraltas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Clientes> lista = clientedao.listarAlta();
        Object[] object = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getDireccion();
            object[4] = lista.get(i).getTelefono();
            object[5] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formcliente.jtcliente.setModel(modelo);
    }

    private void Listarbajas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Clientes> lista = clientedao.listarBaja();
        Object[] object = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getDireccion();
            object[4] = lista.get(i).getTelefono();
            object[5] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formcliente.jtcliente.setModel(modelo);
    }

    private void buscar(JTable tabla, String a, String b) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Clientes> lista = clientedao.buscar(a, b);
        Object[] object = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getDireccion();
            object[4] = lista.get(i).getTelefono();
            object[5] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formcliente.jtcliente.setModel(modelo);
    }

    private void limpiar() {
        formcliente.txtid.setText("");
        formcliente.txtnombres.setText("");
        formcliente.txtapellidos.setText("");
        formcliente.txtdireccion.setText("");
        formcliente.txttelefono.setText("");
        formcliente.cbestado.setSelectedIndex(0);
        formcliente.btnguardar.setEnabled(true);
        formcliente.btnmodificar.setEnabled(false);
    }

    private void limpiartabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
