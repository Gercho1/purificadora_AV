package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Compras;
import modelo.ComprasDAO;
import modelo.Proveedores;
import vista.Jcompras;

public class ComprasControllers implements ActionListener, MouseListener, KeyListener {

    private Compras compra;
    private ComprasDAO compradao;
    private Jcompras formcompra;
    private Proveedores prov;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelop = new DefaultTableModel();

    public ComprasControllers(Compras compra, ComprasDAO compradao, Jcompras formcompra, Proveedores prov) {
        this.compra = compra;
        this.compradao = compradao;
        this.formcompra = formcompra;
        this.prov = prov;
        /*botones*/
        this.formcompra.btnguardar.addActionListener(this);
        this.formcompra.btnmodificar.addActionListener(this);
        this.formcompra.btnmodificar.setEnabled(false);
        this.formcompra.btnalta.addActionListener(this);
        this.formcompra.btnbaja.addActionListener(this);
        this.formcompra.btncancelar.addActionListener(this);
        this.formcompra.btnseleccionar.addActionListener(this);
        this.formcompra.btnseleccionarv.addActionListener(this);
        this.formcompra.btnalta.setVisible(false);
        this.formcompra.btnbaja.setVisible(false);
        this.formcompra.btlimpiar.addActionListener(this);
        /*texfields*/
        this.formcompra.txtbusqueda.addKeyListener(this);
        this.formcompra.txtestado.setText("ALTA");
        this.formcompra.txtbusquedap.addKeyListener(this);
        /*texfield bloqueado*/
        this.formcompra.txtnombres.setEditable(false);
        this.formcompra.txtrazon.setEditable(false);
        /*texfield oculto*/
        this.formcompra.txtestado.setVisible(false);
        this.formcompra.txtid.setVisible(false);
        this.formcompra.txtidproveedor.setVisible(false);
        /*tabla*/
        this.formcompra.jtcompras.addMouseListener(this);
        /*metodos*/
 /*Botones*/
 /*Botones*/
 /*Botones*/
        Listaraltas(formcompra.jtcompras);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formcompra.btnguardar) {
            if (formcompra.txtidproveedor.getText().isEmpty() || formcompra.txtfactura.getText().isEmpty()
                    || formcompra.txttotal.getText().isEmpty() || formcompra.cbestado.getSelectedIndex() == 1) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            } else{
                guardar();
            }
        }
        if (e.getSource() == formcompra.btnmodificar) {
            if (formcompra.txtidproveedor.getText().isEmpty() || formcompra.txtfactura.getText().isEmpty()
                    || formcompra.txttotal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            } else {
                modificar();
                this.formcompra.txttotal.setEditable(true);
                this.formcompra.btnguardar.setEnabled(true);
                this.formcompra.btnmodificar.setEnabled(false);
                this.formcompra.datecompra.setEnabled(true);
            }
        }
        if (e.getSource() == formcompra.btnalta) {
            limpiar();
            limpiartabla();
            Listaraltas(formcompra.jtcompras);
            formcompra.txtidproveedor.requestFocus();
            formcompra.txtestado.setText("ALTA");
        }
        if (e.getSource() == formcompra.btnbaja) {
            limpiar();
            limpiartabla();
            Listarbajas(formcompra.jtcompras);
            formcompra.txtidproveedor.requestFocus();
            formcompra.txtestado.setText("BAJA");

        }
        if (e.getSource() == formcompra.btnseleccionar) {

            formcompra.jTabbedPane1.setSelectedIndex(1);
            limpiartablap();
            ListaraltasProv(formcompra.jtproveedor);
        }
        if (e.getSource() == formcompra.btnseleccionarv) {
            if (formcompra.jtproveedor.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor");
            } else {
                int fila = formcompra.jtproveedor.getSelectedRow();
                formcompra.txtidproveedor.setText(formcompra.jtproveedor.getValueAt(fila, 0).toString());
                String nombres = formcompra.jtproveedor.getValueAt(fila, 1).toString();
                String apellidos = formcompra.jtproveedor.getValueAt(fila, 2).toString();
                formcompra.txtnombres.setText(nombres + " " + apellidos);
                formcompra.txtrazon.setText(formcompra.jtproveedor.getValueAt(fila, 3).toString());
                formcompra.jTabbedPane1.setSelectedIndex(0);
            }

        }
        if (e.getSource() == formcompra.btncancelar) {
            formcompra.jTabbedPane1.setSelectedIndex(0);
        }
        if (e.getSource() == formcompra.btlimpiar) {
            limpiar();
            this.formcompra.txttotal.setEnabled(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == formcompra.jtcompras) {
            int fila = formcompra.jtcompras.rowAtPoint(e.getPoint());
            formcompra.txtid.setText(formcompra.jtcompras.getValueAt(fila, 0).toString());
            formcompra.txtidproveedor.setText(formcompra.jtcompras.getValueAt(fila, 1).toString());
            String nombres = formcompra.jtcompras.getValueAt(fila, 2).toString();
            String apellidos = formcompra.jtcompras.getValueAt(fila, 3).toString();
            formcompra.txtnombres.setText(nombres + " " + apellidos);
            formcompra.txtrazon.setText(formcompra.jtcompras.getValueAt(fila, 4).toString());
            formcompra.datecompra.setDate(Date.valueOf(formcompra.jtcompras.getValueAt(fila, 5).toString()));
            formcompra.txtfactura.setText(formcompra.jtcompras.getValueAt(fila, 6).toString());
            formcompra.txtdescripcion.setText(formcompra.jtcompras.getValueAt(fila, 7).toString());
            formcompra.txttotal.setText(formcompra.jtcompras.getValueAt(fila, 8).toString());
            formcompra.cbestado.setSelectedItem(formcompra.jtcompras.getValueAt(fila, 9).toString());
            this.formcompra.txttotal.setEditable(false);
            this.formcompra.btnmodificar.setEnabled(true);
            this.formcompra.btnguardar.setEnabled(false);
            this.formcompra.datecompra.setEnabled(false);
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
        if (e.getSource() == formcompra.txtbusquedap) {
            if (formcompra.txtbusquedap.getText().isEmpty()) {
                String a = formcompra.txtbusquedap.getText();
                limpiartablap();
                buscarp(formcompra.jtproveedor, a);
            } else {
                String a = formcompra.txtbusquedap.getText();
                limpiartablap();
                buscarp(formcompra.jtproveedor, a);
            }
        }
        if (e.getSource() == formcompra.txtbusqueda) {
            if (formcompra.txtbusqueda.getText().isEmpty()) {
                String a = formcompra.txtbusqueda.getText();
                String b = formcompra.txtestado.getText();
                limpiartabla();
                buscar(formcompra.jtcompras, a, b);
            } else {
                String a = formcompra.txtbusqueda.getText();
                String b = formcompra.txtestado.getText();
                limpiartabla();
                buscar(formcompra.jtcompras, a, b);
            }
        }
    }

    private void guardar() {
        try {

            int idproveedor = Integer.parseInt(formcompra.txtidproveedor.getText());
//            LocalDate date = LocalDate.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int dia, mes, ano;
            dia = formcompra.datecompra.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes = formcompra.datecompra.getCalendar().get(Calendar.MONTH) + 1;
            ano = formcompra.datecompra.getCalendar().get(Calendar.YEAR);
            String fechain;
            fechain = ano + "-" + mes + "-" + dia;
            String factura = formcompra.txtfactura.getText();
            String descripcion = formcompra.txtdescripcion.getText();
            double total = Double.parseDouble(formcompra.txttotal.getText());
            String estado = formcompra.cbestado.getSelectedItem().toString();
            compra.setIdproveedor(idproveedor);
            compra.setFecha(Date.valueOf(fechain));
            compra.setFactura(factura);
            compra.setDescripcion(descripcion);
            compra.setTotal(total);
            compra.setEstado(estado);
            if (compradao.guardar(compra) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(formcompra.jtcompras);
                formcompra.txtidproveedor.requestFocus();
                JOptionPane.showMessageDialog(null, "Compra agregado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar la compra");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void modificar() {
        try {
            int idproveedor = Integer.parseInt(formcompra.txtidproveedor.getText());
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String factura = formcompra.txtfactura.getText();
            String descripcion = formcompra.txtdescripcion.getText();
            double total = Double.parseDouble(formcompra.txttotal.getText());
            String estado = formcompra.cbestado.getSelectedItem().toString();
            int id = Integer.parseInt(formcompra.txtid.getText());
            compra.setId(id);
            compra.setIdproveedor(idproveedor);
            compra.setFecha(Date.valueOf(date.format(formatter)));
            compra.setFactura(factura);
            compra.setDescripcion(descripcion);
            compra.setTotal(total);
            compra.setEstado(estado);
            if (compradao.modificar(compra) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(formcompra.jtcompras);
                formcompra.txtidproveedor.requestFocus();
                JOptionPane.showMessageDialog(null, "Compra actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar la compra");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void Listaraltas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Compras> lista = compradao.listarAlta();
        Object[] object = new Object[10];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getIdproveedor();
            object[2] = lista.get(i).getNombres();
            object[3] = lista.get(i).getApellidos();
            object[4] = lista.get(i).getRazon();
            object[5] = lista.get(i).getFecha();
            object[6] = lista.get(i).getFactura();
            object[7] = lista.get(i).getDescripcion();
            object[8] = lista.get(i).getTotal();
            object[9] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formcompra.jtcompras.setModel(modelo);
    }

    private void Listarbajas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Compras> lista = compradao.listarBaja();
        Object[] object = new Object[10];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getIdproveedor();
            object[2] = lista.get(i).getNombres();
            object[3] = lista.get(i).getApellidos();
            object[4] = lista.get(i).getRazon();
            object[5] = lista.get(i).getFecha();
            object[6] = lista.get(i).getFactura();
            object[7] = lista.get(i).getDescripcion();
            object[8] = lista.get(i).getTotal();
            object[9] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formcompra.jtcompras.setModel(modelo);
    }

    private void buscar(JTable tabla, String a, String b) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Compras> lista = compradao.buscar(a, b);
        Object[] object = new Object[10];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getIdproveedor();
            object[2] = lista.get(i).getNombres();
            object[3] = lista.get(i).getApellidos();
            object[4] = lista.get(i).getRazon();
            object[5] = lista.get(i).getFecha();
            object[6] = lista.get(i).getFactura();
            object[7] = lista.get(i).getDescripcion();
            object[8] = lista.get(i).getTotal();
            object[9] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formcompra.jtcompras.setModel(modelo);
    }

    private void buscarp(JTable tabla, String a) {
        modelop = (DefaultTableModel) tabla.getModel();
        List<Proveedores> lista = compradao.buscarProv(a);
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getRazon();
            object[4] = lista.get(i).getDescripcion();
            modelop.addRow(object);
        }
        formcompra.jtproveedor.setModel(modelop);
    }

    private void ListaraltasProv(JTable tabla) {
        modelop = (DefaultTableModel) tabla.getModel();
        List<Proveedores> lista = compradao.listarAltaProv();
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getRazon();
            object[4] = lista.get(i).getDescripcion();
            modelop.addRow(object);
        }
        formcompra.jtproveedor.setModel(modelop);
    }

    private void limpiar() {
        formcompra.txtid.setText("");
        formcompra.txtidproveedor.setText("");
        formcompra.txtnombres.setText("");
        formcompra.txtfactura.setText("");
        formcompra.txtrazon.setText("");
        formcompra.txtdescripcion.setText("");
        formcompra.txttotal.setText("");
        formcompra.cbestado.setSelectedIndex(0);
        formcompra.datecompra.setDate(null);
        this.formcompra.btnguardar.setEnabled(true);
        this.formcompra.btnmodificar.setEnabled(false);
        this.formcompra.datecompra.setEnabled(true);
    }

    private void limpiartabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void limpiartablap() {
        for (int i = 0; i < modelop.getRowCount(); i++) {
            modelop.removeRow(i);
            i = i - 1;
        }
    }
}
