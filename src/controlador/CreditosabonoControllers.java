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
import modelo.Clientes;
import modelo.Creditos;
import modelo.Creditosabono;
import modelo.CreditosabonoDAO;
import vista.Jcreditoabono;

public class CreditosabonoControllers implements ActionListener, MouseListener, KeyListener {

    private Jcreditoabono formcred;
    private Creditosabono credabono;
    private CreditosabonoDAO credao;
    private Clientes clientes;

    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo1 = new DefaultTableModel();

    public CreditosabonoControllers(Jcreditoabono formcred, Creditosabono credabono, CreditosabonoDAO credao, Clientes clientes) {
        this.formcred = formcred;
        this.credabono = credabono;
        this.credao = credao;
        this.clientes = clientes;
        /*botones*/
        this.formcred.btnguardar.addActionListener(this);
        this.formcred.btnmodificar.addActionListener(this);
        this.formcred.btlimpiar.addActionListener(this);
        this.formcred.btnmodificar.setEnabled(false);
        
        /*texfields*/
        this.formcred.txtbusqueda.addKeyListener(this);
        this.formcred.txtidcliente.addKeyListener(this);
        /*texfield oculto*/
        this.formcred.txtid.setVisible(false);
        /*texfield bloqueado*/
        this.formcred.txtidcliente.setEditable(false);
        this.formcred.txtidcredito.setEditable(false);
        this.formcred.txtnombres.setEditable(false);
        this.formcred.txtdireccion.setEditable(false);
        /*tabla*/
        this.formcred.jtcredito.addMouseListener(this);
        this.formcred.jtabonocredito.addMouseListener(this);
        /*metodos*/

        ListaraltasC(formcred.jtcredito);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formcred.btnguardar) {
            if (formcred.txtabono.getText().isEmpty() || formcred.cbestado.getSelectedIndex() == 1) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            } else {
                guardar();
            }
        }
        if (e.getSource() == formcred.btnmodificar) {
            if (formcred.txtabono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos obligatorios");
            } else {
                modificar();
                this.formcred.btnguardar.setEnabled(true);
                this.formcred.btnmodificar.setEnabled(false);
                this.formcred.dateabono.setEnabled(true);
            }
        }
        
        if(e.getSource() == formcred.btlimpiar){
            limpiar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == formcred.jtcredito) {
            int fila = formcred.jtcredito.rowAtPoint(e.getPoint());
            formcred.txtidcredito.setText(formcred.jtcredito.getValueAt(fila, 0).toString());
            formcred.txtidcliente.setText(formcred.jtcredito.getValueAt(fila, 1).toString());
            String nombre = formcred.jtcredito.getValueAt(fila, 2).toString();
            String apellido = formcred.jtcredito.getValueAt(fila, 3).toString();
            formcred.txtnombres.setText(nombre + " " + apellido);
            formcred.txtdireccion.setText(formcred.jtcredito.getValueAt(fila, 4).toString());
            int a = Integer.parseInt(formcred.txtidcredito.getText());
            limpiartabla1();
            ListaraltasA(formcred.jtabonocredito, a);
            formcred.txtabono.setText("");
            formcred.dateabono.requestFocus();
            formcred.txtabono.setEditable(true);
            formcred.btnmodificar.setEnabled(false);
            formcred.btnguardar.setEnabled(true);
            formcred.dateabono.setEnabled(true);
            formcred.dateabono.setDate(null);
            
        }
        if (e.getSource() == formcred.jtabonocredito) {
            int fila = formcred.jtcredito.rowAtPoint(e.getPoint());
            formcred.txtid.setText(formcred.jtabonocredito.getValueAt(fila, 0).toString());
            formcred.txtidcredito.setText(formcred.jtabonocredito.getValueAt(fila, 1).toString());
            formcred.dateabono.setDate(Date.valueOf(formcred.jtabonocredito.getValueAt(fila, 2).toString()));
            formcred.txtabono.setText(formcred.jtabonocredito.getValueAt(fila, 3).toString());
            formcred.cbestado.setSelectedItem(formcred.jtabonocredito.getValueAt(fila, 4).toString());
            this.formcred.txtabono.setEditable(false);
            this.formcred.btnguardar.setEnabled(false);
            this.formcred.btnmodificar.setEnabled(true);
            this.formcred.dateabono.setEnabled(false);

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
        if (e.getSource() == formcred.txtidcliente) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!"".equals(formcred.txtidcliente.getText())) {
                    int cod = Integer.parseInt(formcred.txtidcliente.getText());
                    clientes = credao.BuscarPro(cod);
                    if (clientes.getNombres() != null) {
                        formcred.txtnombres.setText(clientes.getNombres() + " " + clientes.getApellidos());
                        formcred.txtdireccion.setText(clientes.getDireccion());
                        formcred.txtabono.requestFocus();
                    } else {
                        formcred.txtnombres.setText("");
                        JOptionPane.showMessageDialog(null, "El ID del producto no existe");
                    }
                }
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == formcred.txtbusqueda) {
            if (formcred.txtbusqueda.getText().isEmpty()) {
                String a = formcred.txtbusqueda.getText();
                limpiartabla();
                buscar(formcred.jtcredito, a);
            } else {
                String a = formcred.txtbusqueda.getText();
                limpiartabla();
                buscar(formcred.jtcredito, a);
            }
        }
    }

    private void guardar() {
        try {
            int idcredito = Integer.parseInt(formcred.txtidcredito.getText());
//            LocalDate date = LocalDate.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int dia, mes, ano;
            dia = formcred.dateabono.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes = formcred.dateabono.getCalendar().get(Calendar.MONTH) + 1;
            ano = formcred.dateabono.getCalendar().get(Calendar.YEAR);
            String fechain;
            fechain = ano + "-" + mes + "-" + dia;
            double abono = Double.parseDouble(formcred.txtabono.getText());
            String estado = formcred.cbestado.getSelectedItem().toString();
            int a = Integer.parseInt(formcred.txtidcredito.getText());
            credabono.setIdcredito(idcredito);
            credabono.setFecha(Date.valueOf(fechain));
            credabono.setAbono(abono);
            credabono.setEstado(estado);
            if (credao.guardar(credabono) == 1) {
                limpiar();
                limpiartabla1();
                ListaraltasA(formcred.jtabonocredito, a);
                limpiartabla();
                ListaraltasC(formcred.jtcredito);
                formcred.txtabono.requestFocus();
                JOptionPane.showMessageDialog(null, "Abono agregado correctamente al crédito");
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar el abono");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void modificar() {
        try {
            int idcredito = Integer.parseInt(formcred.txtidcredito.getText());
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            double abono = Double.parseDouble(formcred.txtabono.getText());
            String estado = formcred.cbestado.getSelectedItem().toString();
            int id = Integer.parseInt(formcred.txtid.getText());
            int a = Integer.parseInt(formcred.txtidcredito.getText());
            credabono.setId(id);
            credabono.setIdcredito(idcredito);
            credabono.setFecha(Date.valueOf(date.format(formatter)));
            credabono.setAbono(abono);
            credabono.setEstado(estado);
            if (credao.modificar(credabono) == 1) {
                limpiar();
                limpiartabla1();
                ListaraltasA(formcred.jtabonocredito, a);
                limpiartabla();
                ListaraltasC(formcred.jtcredito);
                formcred.txtabono.requestFocus();
                this.formcred.txtabono.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Abono actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el abono");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void ListaraltasA(JTable tabla, int a) {
        modelo1 = (DefaultTableModel) tabla.getModel();
        List<Creditosabono> lista = credao.listarAltaAC(a);
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getIdcredito();
            object[2] = lista.get(i).getFecha();
            object[3] = lista.get(i).getAbono();
            object[4] = lista.get(i).getEstado();
            modelo1.addRow(object);
        }
        formcred.jtabonocredito.setModel(modelo1);
    }

    private void ListaraltasC(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Creditos> lista = credao.listarAltaC();
        Object[] object = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getIdcliente();
            object[2] = lista.get(i).getNombres();
            object[3] = lista.get(i).getApellidos();
            object[4] = lista.get(i).getDireccion();
            object[5] = lista.get(i).getFecha();
            object[6] = lista.get(i).getTotal();
            object[7] = lista.get(i).getPendiente();
            modelo.addRow(object);
        }
        formcred.jtcredito.setModel(modelo);
    }

    private void buscar(JTable tabla, String a) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Creditos> lista = credao.buscar(a);
        Object[] object = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getIdcliente();
            object[2] = lista.get(i).getNombres();
            object[3] = lista.get(i).getApellidos();
            object[4] = lista.get(i).getDireccion();
            object[5] = lista.get(i).getFecha();
            object[6] = lista.get(i).getTotal();
            object[7] = lista.get(i).getPendiente();
            modelo.addRow(object);
        }
        formcred.jtcredito.setModel(modelo);
    }

    private void limpiar() {
        formcred.txtidcredito.setText("");
        formcred.txtidcliente.setText("");
        formcred.txtnombres.setText("");
        formcred.txtdireccion.setText("");
        formcred.txtabono.setText("");
        formcred.cbestado.setSelectedIndex(0);
        formcred.txtabono.setEditable(true);
        formcred.dateabono.setEnabled(true);
        this.formcred.dateabono.setDate(null);
        this.formcred.btnguardar.setEnabled(true);
        this.formcred.btnmodificar.setEnabled(false);
    }

    private void limpiartabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void limpiartabla1() {
        for (int i = 0; i < modelo1.getRowCount(); i++) {
            modelo1.removeRow(i);
            i = i - 1;
        }
    }

}
