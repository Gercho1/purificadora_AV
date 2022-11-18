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
import modelo.Gastosvarios;
import modelo.GastosvariosDAO;
import vista.Jgastosvarios;

public class GastosvariosControllers implements ActionListener, MouseListener, KeyListener {

    private Gastosvarios gasto;
    private GastosvariosDAO gastodao;
    private Jgastosvarios formgasto;

    DefaultTableModel modelo = new DefaultTableModel();

    public GastosvariosControllers(Gastosvarios gasto, GastosvariosDAO gastodao, Jgastosvarios formgasto) {
        this.gasto = gasto;
        this.gastodao = gastodao;
        this.formgasto = formgasto;
        
        /*botones*/
        this.formgasto.btnguardar.addActionListener(this);
        this.formgasto.btnmodificar.addActionListener(this);
        this.formgasto.btnmodificar.setEnabled(false);
        this.formgasto.btnbaja.addActionListener(this);
        this.formgasto.btlimpiar.addActionListener(this);
        this.formgasto.btnalta1.setVisible(false);
        this.formgasto.btnbaja.setVisible(false);
        this.formgasto.btlimpiar.addActionListener(this);
        /*texfields*/
        this.formgasto.txtbusqueda.addKeyListener(this);
        this.formgasto.txtid.setVisible(false);
        this.formgasto.txtestado.setVisible(false);
        this.formgasto.txtestado.setText("ALTA");
        
        this.formgasto.jtgastosvarios.addMouseListener(this);
        Listaraltas(formgasto.jtgastosvarios);
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formgasto.btnguardar) {
            if (formgasto.txttipopago.getText().isEmpty() || formgasto.txttotal.getText().isEmpty()
                    || formgasto.cbestado.getSelectedIndex() == 1) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            } else if (formgasto.txtid.getText().equals("")) {
                guardar();
            } else {
                JOptionPane.showMessageDialog(null, "No se puede guardar el mismo registro, debe seleccionar modificar");
            }
        }
        if (e.getSource() == formgasto.btnmodificar) {
            if (formgasto.txttipopago.getText().isEmpty() || formgasto.txttotal.getText().isEmpty()
                    ) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            } else {
                modificar();
                this.formgasto.txttotal.setEditable(true);
                this.formgasto.dategastos.setEnabled(false);
                this.formgasto.btnmodificar.setEnabled(true);
                this.formgasto.btnguardar.setEnabled(false);
            }
        }
        if (e.getSource() == formgasto.btnalta1) {
            limpiar();
            limpiartabla();
            Listaraltas(formgasto.jtgastosvarios);
            formgasto.txttipopago.requestFocus();
            formgasto.txtestado.setText("ALTA");
        }
        if (e.getSource() == formgasto.btnbaja) {
            limpiar();
            limpiartabla();
            Listarbajas(formgasto.jtgastosvarios);
            formgasto.txttipopago.requestFocus();
            formgasto.txtestado.setText("BAJA");
        }
        if(e.getSource() == formgasto.btlimpiar){
        limpiar();
    }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == formgasto.jtgastosvarios) {
            limpiar();
            int fila = formgasto.jtgastosvarios.rowAtPoint(e.getPoint());
            formgasto.txtid.setText(formgasto.jtgastosvarios.getValueAt(fila, 0).toString());
            formgasto.dategastos.setDate(Date.valueOf(formgasto.jtgastosvarios.getValueAt(fila, 1).toString()));
            formgasto.txttipopago.setText(formgasto.jtgastosvarios.getValueAt(fila, 2).toString());
            formgasto.txtotro.setText(formgasto.jtgastosvarios.getValueAt(fila, 3).toString());
            formgasto.txtdescripcion.setText(formgasto.jtgastosvarios.getValueAt(fila, 4).toString());
            formgasto.txttotal.setText(formgasto.jtgastosvarios.getValueAt(fila, 5).toString());
            formgasto.cbestado.setSelectedItem(formgasto.jtgastosvarios.getValueAt(fila, 6).toString());
            this.formgasto.txttotal.setEditable(false);
            this.formgasto.btnmodificar.setEnabled(true);
            this.formgasto.btnguardar.setEnabled(true);
            this.formgasto.dategastos.setEnabled(false);
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
        if (e.getSource() == formgasto.txtbusqueda) {
            if (formgasto.txtbusqueda.getText().isEmpty()) {
                String a = formgasto.txtbusqueda.getText();
                String b = formgasto.txtestado.getText();
                limpiartabla();
                buscar(formgasto.jtgastosvarios, a, b);
            } else {
                String a = formgasto.txtbusqueda.getText();
                String b = formgasto.txtestado.getText();
                limpiartabla();
                buscar(formgasto.jtgastosvarios, a, b);
            }
        }
    }

    private void guardar() {
        try {
//            LocalDate date = LocalDate.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
int dia, mes, ano;
            dia = formgasto.dategastos.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes = formgasto.dategastos.getCalendar().get(Calendar.MONTH) + 1;
            ano = formgasto.dategastos.getCalendar().get(Calendar.YEAR);
            String fechain;
            fechain = ano + "-" + mes + "-" + dia;
            String tipopago = formgasto.txttipopago.getText();
            String otro = formgasto.txtotro.getText();
            String descripcion = formgasto.txtdescripcion.getText();
            double total = Double.parseDouble(formgasto.txttotal.getText());
            String estado = formgasto.cbestado.getSelectedItem().toString();
            gasto.setFecha(Date.valueOf(fechain));
            gasto.setTipopago(tipopago);
            gasto.setDocumento(otro);
            gasto.setDescripcion(descripcion);
            gasto.setTotal(total);
            gasto.setEstado(estado);
            if (gastodao.guardar(gasto) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(formgasto.jtgastosvarios);
                formgasto.txttipopago.requestFocus();
                JOptionPane.showMessageDialog(null, "Gasto agregado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar el gasto");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void modificar() {
        try {
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String tipopago = formgasto.txttipopago.getText();
            String otro = formgasto.txtotro.getText();
            String descripcion = formgasto.txtdescripcion.getText();
            double total = Double.parseDouble(formgasto.txttotal.getText());
            String estado = formgasto.cbestado.getSelectedItem().toString();
            int id = Integer.parseInt(formgasto.txtid.getText());
            gasto.setId(id);
            gasto.setFecha(Date.valueOf(date.format(formatter)));
            gasto.setTipopago(tipopago);
            gasto.setDocumento(otro);
            gasto.setDescripcion(descripcion);
            gasto.setTotal(total);
            gasto.setEstado(estado);
            if (gastodao.modificar(gasto) == 1) {
                limpiar();
                limpiartabla();
                Listaraltas(formgasto.jtgastosvarios);
                formgasto.txttipopago.requestFocus();
                JOptionPane.showMessageDialog(null, "Gasto actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el gasto");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void Listaraltas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Gastosvarios> lista = gastodao.listarAlta();
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getFecha();
            object[2] = lista.get(i).getTipopago();
            object[3] = lista.get(i).getDocumento();
            object[4] = lista.get(i).getDescripcion();
            object[5] = lista.get(i).getTotal();
            object[6] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formgasto.jtgastosvarios.setModel(modelo);
    }

    private void Listarbajas(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Gastosvarios> lista = gastodao.listarBaja();
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getFecha();
            object[2] = lista.get(i).getTipopago();
            object[3] = lista.get(i).getDocumento();
            object[4] = lista.get(i).getDescripcion();
            object[5] = lista.get(i).getTotal();
            object[6] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formgasto.jtgastosvarios.setModel(modelo);
    }

    private void buscar(JTable tabla, String a, String b) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Gastosvarios> lista = gastodao.buscar(a, b);
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getFecha();
            object[2] = lista.get(i).getTipopago();
            object[3] = lista.get(i).getDocumento();
            object[4] = lista.get(i).getDescripcion();
            object[5] = lista.get(i).getTotal();
            object[6] = lista.get(i).getEstado();
            modelo.addRow(object);
        }
        formgasto.jtgastosvarios.setModel(modelo);
    }

    private void limpiar() {
        formgasto.txtid.setText("");
        formgasto.txttipopago.setText("");
        formgasto.txtotro.setText("");
        formgasto.txtdescripcion.setText("");
        formgasto.txttotal.setText("");
        formgasto.cbestado.setSelectedIndex(0);
        formgasto.txttotal.setEditable(true);
        formgasto.btnguardar.setEnabled(true);
        formgasto.btnmodificar.setEnabled(false);
        formgasto.dategastos.setDate(null);
        formgasto.dategastos.setEnabled(true);
        
    }

    private void limpiartabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
