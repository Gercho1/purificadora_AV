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
import modelo.Proveedores;
import modelo.ProveedoresDAO;
import vista.Jproveedores;

public class ProveedorControllers implements ActionListener, MouseListener, KeyListener {

    private Proveedores prov;
    private ProveedoresDAO provdao;
    private Jproveedores formprov;

    public ProveedorControllers(Proveedores prov, ProveedoresDAO provdao, Jproveedores formprov) {
        this.prov = prov;
        this.provdao = provdao;
        this.formprov = formprov;
        
        /*botones*/
        this.formprov.btnguardar.addActionListener(this);
        this.formprov.btnmodificar.addActionListener(this);
        this.formprov.btnmodificar.setEnabled(false);
        this.formprov.btnalta.addActionListener(this);
        this.formprov.btnbaja.addActionListener(this);
        this.formprov.btnlimpiar.addActionListener(this);
        /*texfields*/
        this.formprov.txtbusqueda.addKeyListener(this);
        this.formprov.txtestado.setVisible(false);
        this.formprov.txtestado.setText("ALTA");
        this.formprov.txtid.setVisible(false);
        this.formprov.txtnombres.requestFocus();
        
        this.formprov.jtproveedor.addMouseListener(this);
        Listaraltas(formprov.jtproveedor);
        
    }
    DefaultTableModel modelo = new DefaultTableModel();

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == formprov.btnguardar) {
            if (formprov.txtnombres.getText().isEmpty() || formprov.txtapellidos.getText().isEmpty()
                    || formprov.cbestado.getSelectedIndex() == 1 || formprov.txttelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            } else if (formprov.txtid.getText().equals("")) {
                guardar();
            } else {
                JOptionPane.showMessageDialog(null, "No puede guardar un registro ya guardado, debe actualizarlo");
            }
        }
        if (e.getSource() == formprov.btnmodificar) {
            if (formprov.txtnombres.getText().isEmpty() || formprov.txtapellidos.getText().isEmpty()
                    || formprov.txttelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            } else {
                modificar();
                formprov.btnguardar.setEnabled(true);
                formprov.btnmodificar.setEnabled(false);
            }
        }
        if (e.getSource() == formprov.btnalta) {
            limpiar();
            limpiartabla();
            Listaraltas(formprov.jtproveedor);
            formprov.txtnombres.requestFocus();
            formprov.txtestado.setText("ALTA");
        }
        if (e.getSource() == formprov.btnbaja) {
            limpiar();
            limpiartabla();
            Listarbajas(formprov.jtproveedor);
            formprov.txtnombres.requestFocus();
            formprov.txtestado.setText("BAJA");

        }
        if(e.getSource() == formprov.btnlimpiar){
            limpiar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == formprov.jtproveedor) {
            int fila = formprov.jtproveedor.rowAtPoint(e.getPoint());
            formprov.txtid.setText(formprov.jtproveedor.getValueAt(fila, 0).toString());
            formprov.txtnombres.setText(formprov.jtproveedor.getValueAt(fila, 1).toString());
            formprov.txtapellidos.setText(formprov.jtproveedor.getValueAt(fila, 2).toString());
            formprov.txtrazon.setText(formprov.jtproveedor.getValueAt(fila, 3).toString());
            formprov.txttelefono.setText(formprov.jtproveedor.getValueAt(fila, 4).toString());
            formprov.txtcorreo.setText(formprov.jtproveedor.getValueAt(fila, 5).toString());
            formprov.txtdescripcion.setText(formprov.jtproveedor.getValueAt(fila, 6).toString());
            formprov.cbestado.setSelectedItem(formprov.jtproveedor.getValueAt(fila, 7).toString());
            
            formprov.btnmodificar.setEnabled(true);
            formprov.btnguardar.setEnabled(false);
            
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

        if (e.getSource() == formprov.txtbusqueda) {
            if (formprov.txtbusqueda.getText().isEmpty()) {
                String a = formprov.txtbusqueda.getText();
                String b = formprov.txtestado.getText();
                limpiartabla();
                buscar(formprov.jtproveedor, a, b);
            } else {
                String a = formprov.txtbusqueda.getText();
                String b = formprov.txtestado.getText();
                limpiartabla();
                buscar(formprov.jtproveedor, a, b);
            }
        }

    }

    private void guardar() {
        try {
            String nombres = formprov.txtnombres.getText();
            String apellidos = formprov.txtapellidos.getText();
            String razon = formprov.txtrazon.getText();
            int telefono = Integer.parseInt(formprov.txttelefono.getText());
            String correo = formprov.txtcorreo.getText();
            String descripcion = formprov.txtdescripcion.getText();
            String estado = formprov.cbestado.getSelectedItem().toString();
            prov.setNombres(nombres);
            prov.setApellidos(apellidos);
            prov.setRazon(razon);
            prov.setTelefono(telefono);
            prov.setCorreo(correo);
            prov.setDescripcion(descripcion);
            prov.setEstado(estado);
            if (provdao.guardar(prov) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(formprov.jtproveedor);
                formprov.txtnombres.requestFocus();
                JOptionPane.showMessageDialog(null, "Proveedor agregado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar el proveedor");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void modificar() {
        try {
            int id = Integer.parseInt(formprov.txtid.getText());
            String nombres = formprov.txtnombres.getText();
            String apellidos = formprov.txtapellidos.getText();
            String razon = formprov.txtrazon.getText();
            int telefono = Integer.parseInt(formprov.txttelefono.getText());
            String correo = formprov.txtcorreo.getText();
            String descripcion = formprov.txtdescripcion.getText();
            String estado = formprov.cbestado.getSelectedItem().toString();
            prov.setNombres(nombres);
            prov.setApellidos(apellidos);
            prov.setRazon(razon);
            prov.setTelefono(telefono);
            prov.setCorreo(correo);
            prov.setDescripcion(descripcion);
            prov.setEstado(estado);
            prov.setId(id);
            if (provdao.modificar(prov) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(formprov.jtproveedor);
                formprov.txtnombres.requestFocus();
                JOptionPane.showMessageDialog(null, "Proveedor actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el proveedor");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void Listaraltas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Proveedores> lista = provdao.listarAlta();
        Object[] object = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getRazon();
            object[4] = lista.get(i).getTelefono();
            object[5] = lista.get(i).getCorreo();
            object[6] = lista.get(i).getDescripcion();
            object[7] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formprov.jtproveedor.setModel(modelo);
    }

    private void Listarbajas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Proveedores> lista = provdao.listarBaja();
        Object[] object = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getRazon();
            object[4] = lista.get(i).getTelefono();
            object[5] = lista.get(i).getCorreo();
            object[6] = lista.get(i).getDescripcion();
            object[7] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formprov.jtproveedor.setModel(modelo);
    }

    private void buscar(JTable tabla, String a, String b) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Proveedores> lista = provdao.buscar(a, b);
        Object[] object = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getRazon();
            object[4] = lista.get(i).getTelefono();
            object[5] = lista.get(i).getCorreo();
            object[6] = lista.get(i).getDescripcion();
            object[7] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formprov.jtproveedor.setModel(modelo);
    }

    private void limpiar() {
        formprov.txtid.setText("");
        formprov.txtnombres.setText("");
        formprov.txtapellidos.setText("");
        formprov.txtrazon.setText("");
        formprov.txttelefono.setText("");
        formprov.txtcorreo.setText("");
        formprov.txtdescripcion.setText("");
        formprov.cbestado.setSelectedIndex(0);
        formprov.btnguardar.setEnabled(true);
        formprov.btnmodificar.setEnabled(false);
    }

    private void limpiartabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
