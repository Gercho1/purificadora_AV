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
import modelo.Productos;
import modelo.ProductosDAO;
import vista.Jproductos;

public class ProductosControllers implements ActionListener, MouseListener, KeyListener {

    private Productos pro;
    private ProductosDAO prodao;
    private Jproductos formpro;

    DefaultTableModel modelo = new DefaultTableModel();

    public ProductosControllers(Productos pro, ProductosDAO prodao, Jproductos formpro) {
        this.pro = pro;
        this.prodao = prodao;
        this.formpro = formpro;
        
        /*botones*/
        this.formpro.btnguardar.addActionListener(this);
        this.formpro.btnmodificar.addActionListener(this);
        this.formpro.btnmodificar.setEnabled(false);
        this.formpro.btnlimpiar.addActionListener(this);
        this.formpro.btnbaja.addActionListener(this);
        this.formpro.btnalta.addActionListener(this);
        this.formpro.btnlimpiar.addActionListener(this);
        /*texfields*/
        this.formpro.txtbusqueda.addKeyListener(this);
        this.formpro.txtid.setVisible(false);
        this.formpro.txtestado.setVisible(false);
        this.formpro.txtestado.setText("ALTA");
        this.formpro.txtproducto.requestFocus();
        
        this.formpro.jtproductos.addMouseListener(this);
        Listaraltas(formpro.jtproductos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == formpro.btnguardar) {
            if (formpro.txtproducto.getText().isEmpty() || formpro.txtprecio.getText().isEmpty()
                    || formpro.cbestado.getSelectedIndex() == 1) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            }
            else if (formpro.txtid.getText().equals("")) {
                guardar();
            } else {
                JOptionPane.showMessageDialog(null, "No puede guardar un registro ya guardado, debe actualizarlo");
            }
        }
        if (e.getSource() == formpro.btnmodificar) {
            if (formpro.txtproducto.getText().isEmpty() || formpro.txtprecio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            }
            else{
                modificar();
                this.formpro.btnguardar.setEnabled(true);
                this.formpro.btnmodificar.setEnabled(false);
            }
        }
        if (e.getSource() == formpro.btnalta) {
            limpiar();
            limpiartabla();
            Listaraltas(formpro.jtproductos);
            formpro.txtproducto.requestFocus();
            formpro.txtestado.setText("ALTA");
        }
        if (e.getSource() == formpro.btnbaja) {
            limpiar();
            limpiartabla();
            Listarbajas(formpro.jtproductos);
            formpro.txtproducto.requestFocus();
            formpro.txtestado.setText("BAJA");

        }
        if(e.getSource() == formpro.btnlimpiar){
            limpiar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == formpro.jtproductos) {
            int fila = formpro.jtproductos.rowAtPoint(e.getPoint());
            formpro.txtid.setText(formpro.jtproductos.getValueAt(fila, 0).toString());
            formpro.txtproducto.setText(formpro.jtproductos.getValueAt(fila, 1).toString());
            formpro.txtdescripcion.setText(formpro.jtproductos.getValueAt(fila, 2).toString());
            formpro.txtprecio.setText(formpro.jtproductos.getValueAt(fila, 3).toString());
            formpro.cbestado.setSelectedItem(formpro.jtproductos.getValueAt(fila, 4).toString());
            this.formpro.btnguardar.setEnabled(false);
            this.formpro.btnmodificar.setEnabled(true);
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
        if (e.getSource() == formpro.txtbusqueda) {
            if (formpro.txtbusqueda.getText().isEmpty()) {
                String a = formpro.txtbusqueda.getText();
                String b = formpro.txtestado.getText();
                limpiartabla();
                buscar(formpro.jtproductos, a, b);
            } else {
                String a = formpro.txtbusqueda.getText();
                String b = formpro.txtestado.getText();
                limpiartabla();
                buscar(formpro.jtproductos, a, b);
            }
        }

    }
    private void guardar() {
        try {
            String producto = formpro.txtproducto.getText();
            String descripcion = formpro.txtdescripcion.getText();
            double precio = Double.parseDouble(formpro.txtprecio.getText());
            String estado = formpro.cbestado.getSelectedItem().toString();
            pro.setNombre(producto);
            pro.setDescripcion(descripcion);
            pro.setPrecioventa(precio);
            pro.setEstado(estado);
            if (prodao.guardar(pro) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(formpro.jtproductos);
                formpro.txtproducto.requestFocus();
                JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar el producto");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void modificar() {
        try {
            String producto = formpro.txtproducto.getText();
            String descripcion = formpro.txtdescripcion.getText();
            double precio = Double.parseDouble(formpro.txtprecio.getText());
            String estado = formpro.cbestado.getSelectedItem().toString();
            int id = Integer.parseInt(formpro.txtid.getText());
            pro.setNombre(producto);
            pro.setDescripcion(descripcion);
            pro.setPrecioventa(precio);
            pro.setEstado(estado);
            pro.setId(id);
            if (prodao.modificar(pro) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(formpro.jtproductos);
                formpro.txtproducto.requestFocus();
                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el producto");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void Listaraltas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Productos> lista = prodao.listarAlta();
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getDescripcion();
            object[3] = lista.get(i).getPrecioventa();
            object[4] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formpro.jtproductos.setModel(modelo);
    }

    private void Listarbajas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Productos> lista = prodao.listarBaja();
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getDescripcion();
            object[3] = lista.get(i).getPrecioventa();
            object[4] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formpro.jtproductos.setModel(modelo);
    }

    private void buscar(JTable tabla, String a, String b) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Productos> lista = prodao.buscar(a, b);
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getDescripcion();
            object[3] = lista.get(i).getPrecioventa();
            object[4] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formpro.jtproductos.setModel(modelo);
    }

    private void limpiar() {
        formpro.txtid.setText("");
        formpro.txtproducto.setText("");
        formpro.txtdescripcion.setText("");
        formpro.txtprecio.setText("");
        formpro.cbestado.setSelectedIndex(0);
        formpro.btnguardar.setEnabled(true);
        formpro.btnmodificar.setEnabled(false);
    }

    private void limpiartabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

}
