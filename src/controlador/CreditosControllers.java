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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Clientes;
import modelo.Creditodetalle;
import modelo.Creditos;
import modelo.CreditosDAO;
import modelo.Creditosabono;
import modelo.Productos;
import modelo.Usuario;
import vista.Jcreditosdetalle;

public class CreditosControllers implements ActionListener, MouseListener, KeyListener {

    private Creditos cred;
    private CreditosDAO credao;
    private Creditodetalle credetalle;
    private Jcreditosdetalle formcred;
    private Productos prod;

    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modeloC = new DefaultTableModel();
    DefaultTableModel modeloA = new DefaultTableModel();
    DefaultTableModel modeloCli = new DefaultTableModel();
    DefaultTableModel modelprod = new DefaultTableModel();
    DefaultTableModel modelovend = new DefaultTableModel();
    int item;
    double Totalpagar = 0.00;

    public CreditosControllers(Creditos cred, CreditosDAO credao, Creditodetalle credetalle, Jcreditosdetalle formcred, Productos prod) {
        this.cred = cred;
        this.credao = credao;
        this.credetalle = credetalle;
        this.formcred = formcred;
        this.prod = prod;
        /*Botones*/
        this.formcred.btnguardar.addActionListener(this);
        this.formcred.btneliminar.addActionListener(this);
        this.formcred.btnseleccionarcliente.addActionListener(this);
        this.formcred.btnmodificar.addActionListener(this);
        this.formcred.btnseleccionarv.addActionListener(this);
        this.formcred.btncancelar.addActionListener(this);
        this.formcred.btnseleccionarprod.addActionListener(this);
        this.formcred.btnseleccionarcliente.addActionListener(this);
        this.formcred.btnseleccionarp.addActionListener(this);
        this.formcred.btncancelarp.addActionListener(this);
        this.formcred.btnseleccionarvendedor.addActionListener(this);
        this.formcred.btnseleccionarven.addActionListener(this);
        this.formcred.btncancelarven.addActionListener(this);
        /*Texfields*/
        this.formcred.txtcantidad.addKeyListener(this);
        this.formcred.txtbusqueda.addKeyListener(this);
        /*Texfields ocultos*/
        this.formcred.txtidcredito.setVisible(false);
        this.formcred.txtidcliente.setVisible(false);
        this.formcred.txtidproducto.setVisible(false);
        this.formcred.txtidvendedor.setVisible(false);
        this.formcred.jtabono.setVisible(false);
        /*Texfields bloqueados*/
        this.formcred.txtidcliente.setEditable(false);
        this.formcred.txtidproducto.setEditable(false);
        this.formcred.txtproducto.setEditable(false);
        this.formcred.txtprecio.setEditable(false);
        this.formcred.txtnombrecliente.setEditable(false);
        this.formcred.txtdireccion.setEditable(false);
        this.formcred.txtnombrevendedor.setEditable(false);
        this.formcred.txtapellidovendedor.setEditable(false);
        /*Tabla*/
        this.formcred.jtcreditos.addMouseListener(this);
        
        /*Metodos*/
        ListaraltasCred(formcred.jtcreditos);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formcred.btneliminar) {
            Eliminar();
        }
        if (e.getSource() == formcred.btnguardar) {
            if (formcred.txtnombrecliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un cliente antes de guardar");
            } else {
                Guardar();
                Limpiar();
                limpiartabla();
            }

        }
        if (e.getSource() == formcred.btnmodificar) {
            if (formcred.cbestado.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Solo se puede dar de baja el crédito");
            }
            if (formcred.jtabono.getRowCount() >= 1) {
                JOptionPane.showMessageDialog(null, "Tiene que dar de baja los abonos antes de dar de baja el crédito");
            } else {
                modificar();
            }
        }
        if (e.getSource() == formcred.btnseleccionarcliente) {

            formcred.jTabbedPane1.setSelectedIndex(1);
            limpiartablaClie();
            ListarCliente(formcred.jtclientes);
        }
        if (e.getSource() == formcred.btnseleccionarv) {
            if (formcred.jtclientes.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un vendedor");
            } else {
                int fila = formcred.jtclientes.getSelectedRow();
                formcred.txtidcliente.setText(formcred.jtclientes.getValueAt(fila, 0).toString());
                String nombre = formcred.jtclientes.getValueAt(fila, 1).toString();
                String apellido = formcred.jtclientes.getValueAt(fila, 2).toString();
                formcred.txtnombrecliente.setText(nombre + " " + apellido);
                formcred.txtdireccion.setText(formcred.jtclientes.getValueAt(fila, 3).toString());
                formcred.jTabbedPane1.setSelectedIndex(0);
                limpiartablaClie();
                this.formcred.datecredito.requestFocus();
            }

        }
        if (e.getSource() == formcred.btncancelar) {
            limpiartablaClie();
            formcred.jTabbedPane1.setSelectedIndex(0);
        }
        if (e.getSource() == formcred.btnseleccionarprod) {

            formcred.jTabbedPane1.setSelectedIndex(2);
            limpiartablaprod();
            ListarProducto(formcred.jtproducto);
        }
        if (e.getSource() == formcred.btnseleccionarp) {
            if (formcred.jtproducto.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
            } else {
                int fila = formcred.jtproducto.getSelectedRow();
                formcred.txtidproducto.setText(formcred.jtproducto.getValueAt(fila, 0).toString());
                formcred.txtproducto.setText(formcred.jtproducto.getValueAt(fila, 1).toString());
                formcred.txtprecio.setText(formcred.jtproducto.getValueAt(fila, 3).toString());
                formcred.jTabbedPane1.setSelectedIndex(0);
                limpiartablaprod();
            }

        }
        if (e.getSource() == formcred.btncancelarp) {
            limpiartablaprod();
            formcred.jTabbedPane1.setSelectedIndex(0);
        }
        if (e.getSource() == formcred.btnseleccionarvendedor) {

            formcred.jTabbedPane1.setSelectedIndex(3);
            limpiartablavend();
            ListarVendedor(formcred.jtvendedor);
        }
        if (e.getSource() == formcred.btnseleccionarven) {
            if (formcred.jtvendedor.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un vendedor");
            } else {
                int fila = formcred.jtvendedor.getSelectedRow();
                formcred.txtidvendedor.setText(formcred.jtvendedor.getValueAt(fila, 0).toString());
                formcred.txtnombrevendedor.setText(formcred.jtvendedor.getValueAt(fila, 1).toString());
                formcred.txtapellidovendedor.setText(formcred.jtvendedor.getValueAt(fila, 2).toString());
                formcred.jTabbedPane1.setSelectedIndex(0);
                limpiartablavend();
            }

        }
        if (e.getSource() == formcred.btncancelarven) {
            limpiartablavend();
            formcred.jTabbedPane1.setSelectedIndex(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == formcred.jtcreditos) {
            int fila = formcred.jtcreditos.rowAtPoint(e.getPoint());
            formcred.txtidcredito.setText(formcred.jtcreditos.getValueAt(fila, 0).toString());
            int a = Integer.parseInt(formcred.txtidcredito.getText());
            limpiartablaAbono();
            ListaraltasAbono(formcred.jtabono, a);
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
        if (e.getSource() == formcred.txtidproducto) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!"".equals(formcred.txtidproducto.getText())) {
                    int cod = Integer.parseInt(formcred.txtidproducto.getText());
                    prod = credao.BuscarPro(cod);
                    if (prod.getNombre() != null) {
                        formcred.txtproducto.setText("" + prod.getNombre());
                        formcred.txtprecio.setText("" + prod.getPrecioventa());
                        formcred.txtcantidad.requestFocus();
                    } else {
                        formcred.txtproducto.setText("");
                        formcred.txtprecio.setText("");
                        formcred.txtidproducto.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
                    formcred.txtidproducto.requestFocus();
                }
            }
        }
//        if (e.getSource() == formcred.txtcliente) {
//            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                if (!"".equals(formcred.txtcliente.getText())) {
//                    int id = Integer.parseInt(formcred.txtcliente.getText());
//                    clien = credao.Buscaruser(id);
//                    if (clien.getNombres() != null) {
//                        formcred.txtnombrecliente.setText("" + clien.getNombres() + " " + clien.getApellidos());
//                        formcred.txtdireccion.setText(clien.getDireccion());
//                        formcred.btnguardar.requestFocus();
//                    } else {
//                        formcred.txtcliente.setText("");
//
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Ingrese el codigo del cliente");
//                    formcred.txtcliente.requestFocus();
//                }
//            }
//        }

        if (e.getSource() == formcred.txtcantidad) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!"".equals(formcred.txtcantidad.getText())) {
                    String cod = formcred.txtidproducto.getText();
                    String nombre = formcred.txtproducto.getText();
                    int cant = Integer.parseInt(formcred.txtcantidad.getText());
                    double precio = Double.parseDouble(formcred.txtprecio.getText());
                    double total = cant * precio;

                    if (formcred.txtcantidad != null) {
                        item = item + 1;
                        modelo = (DefaultTableModel) formcred.jtcreditodetalle.getModel();
                        ArrayList lista = new ArrayList();
                        lista.add(item);
                        lista.add(cod);
                        lista.add(nombre);
                        lista.add(cant);
                        lista.add(precio);
                        lista.add(total);
                        Object[] O = new Object[5];
                        O[0] = lista.get(1);
                        O[1] = lista.get(2);
                        O[2] = lista.get(3);
                        O[3] = lista.get(4);
                        O[4] = lista.get(5);
                        modelo.addRow(O);
                        formcred.jtcreditodetalle.setModel(modelo);
                        Limpiar();
                        formcred.txtidproducto.requestFocus();
                        Totalpagar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese la cantidad");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == formcred.txtbusqueda) {
            if (formcred.txtbusqueda.getText().isEmpty()) {
                String a = formcred.txtbusqueda.getText();
                limpiartablaClie();
                buscarcli(formcred.jtclientes, a);
            } else {
                String a = formcred.txtbusqueda.getText();
                limpiartablaClie();
                buscarcli(formcred.jtclientes, a);
            }
        }
    }

    private void Totalpagar() {
        Totalpagar = 0.00;
        int numFila = formcred.jtcreditodetalle.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(formcred.jtcreditodetalle.getModel().getValueAt(i, 4)));
            Totalpagar = Totalpagar + cal;
        }
        formcred.lbtotal.setText(String.format("%.2f", Totalpagar));
    }

    private void Limpiar() {
        formcred.txtidproducto.setText("");
        formcred.txtproducto.setText("");
        formcred.txtidcliente.setText("");
        formcred.txtnombrecliente.setText("");
        formcred.txtdireccion.setText("");
        formcred.txtcantidad.setText("");
        formcred.txtprecio.setText("");
        formcred.datecredito.setDate(null);
        formcred.txtnombrevendedor.setText("");
        formcred.txtapellidovendedor.setText("");
        formcred.txtidvendedor.setText("");
    }

    private void Eliminar() {
        if (formcred.jtcreditodetalle.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Para poder eliminar, debe seleccionar el registro en la tabla");
        } else {
            modelo.removeRow(formcred.jtcreditodetalle.getSelectedRow());
            Totalpagar();
            formcred.txtidproducto.requestFocus();
        }
    }

    private void Guardar() {
        try {
            int idcliente = Integer.parseInt(formcred.txtidcliente.getText());
            int idvendedor= Integer.parseInt(formcred.txtidvendedor.getText());
//            LocalDate date = LocalDate.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int dia, mes, ano;
            dia = formcred.datecredito.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes = formcred.datecredito.getCalendar().get(Calendar.MONTH) + 1;
            ano = formcred.datecredito.getCalendar().get(Calendar.YEAR);
            String fechain;
            fechain = ano + "-" + mes + "-" + dia;
            double total = Double.parseDouble(formcred.lbtotal.getText());
            String estado = "ALTA";
            String detalle = "DEBE";
            cred.setIdcliente(idcliente);
            cred.setIdvendedor(idvendedor);
            cred.setFecha(Date.valueOf(fechain));
            cred.setTotal(total);
            cred.setPendiente(total);
            cred.setEstado(estado);
            cred.setDetalle(detalle);
            if (credao.agregar(cred) == 1) {
                JOptionPane.showMessageDialog(null, "Crédito agregada");
            }
            int id = credao.Numerocredito();
            for (int i = 0; i < formcred.jtcreditodetalle.getRowCount(); i++) {
                int cod = Integer.parseInt(formcred.jtcreditodetalle.getValueAt(i, 0).toString());
                int cant = Integer.parseInt(formcred.jtcreditodetalle.getValueAt(i, 2).toString());
                double precio = Double.parseDouble(formcred.jtcreditodetalle.getValueAt(i, 4).toString());
                credetalle.setIdcredito(id);
                credetalle.setIdproducto(cod);
                credetalle.setCantidad(cant);
                credetalle.setSubtotal(precio);
                if (credao.agregardetalle(credetalle) == 1) {
                    limpiartablaCred();
                    ListaraltasCred(formcred.jtcreditos);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar, Error");
        }

    }

    private void modificar() {
        try {
            int id = Integer.parseInt(formcred.txtidcredito.getText());
            String estado = formcred.cbestado.getSelectedItem().toString();
            cred.setEstado(estado);
            cred.setId(id);
            if (credao.modificar(cred) == 1) {
                limpiartablaCred();
                ListaraltasCred(formcred.jtcreditos);
                formcred.cbestado.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Credito actualizado");

            } else {
                formcred.cbestado.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Error al actualizar el crédito");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }
    }

    private void ListaraltasCred(JTable tabla) {
        modeloC = (DefaultTableModel) tabla.getModel();
        List<Creditos> lista = credao.listarAltaCrédito();
        Object[] object = new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombrevendedor();
            object[2] = lista.get(i).getApellidovendedor();
            object[3] = lista.get(i).getNombres();
            object[4] = lista.get(i).getApellidos();
            object[5] = lista.get(i).getFecha();
            object[6] = lista.get(i).getTotal();
            object[7] = lista.get(i).getPendiente();
            object[8] = lista.get(i).getEstado();
            modeloC.addRow(object);
        }
        formcred.jtcreditos.setModel(modeloC);
    }

    private void ListaraltasAbono(JTable tabla, int a) {
        modeloA = (DefaultTableModel) tabla.getModel();
        List<Creditosabono> lista = credao.listarAltaAbonoDetalle(a);
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getIdcredito();
            object[2] = lista.get(i).getFecha();
            object[3] = lista.get(i).getAbono();
            modeloA.addRow(object);
        }
        formcred.jtabono.setModel(modeloA);
    }

    private void ListarCliente(JTable tabla) {
        modeloCli = (DefaultTableModel) tabla.getModel();
        List<Clientes> lista = credao.listarcliente();
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getDireccion();
            object[4] = lista.get(i).getTelefono();
            modeloCli.addRow(object);
        }
        formcred.jtclientes.setModel(modeloCli);
    }

    private void buscarcli(JTable tabla, String b) {
        modeloCli = (DefaultTableModel) tabla.getModel();
        List<Clientes> lista = credao.buscarcliente(b);
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getDireccion();
            object[4] = lista.get(i).getTelefono();
            modeloCli.addRow(object);
        }
        formcred.jtclientes.setModel(modeloCli);
    }

    private void ListarProducto(JTable tabla) {
        modelprod = (DefaultTableModel) tabla.getModel();
        List<Productos> lista = credao.listarproducto();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getDescripcion();
            object[3] = lista.get(i).getPrecioventa();
            modelprod.addRow(object);
        }
        formcred.jtproducto.setModel(modelprod);
    }
    
    private void ListarVendedor(JTable tabla) {
        modelovend = (DefaultTableModel) tabla.getModel();
        List<Usuario> lista = credao.listarvendedor();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getID();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getTipo_Usuario();
            modelovend.addRow(object);
        }
        formcred.jtvendedor.setModel(modelovend);
    }

    public void limpiartabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiartablaCred() {
        for (int i = 0; i < modeloC.getRowCount(); i++) {
            modeloC.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiartablaAbono() {
        for (int i = 0; i < modeloA.getRowCount(); i++) {
            modeloA.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiartablaClie() {
        for (int i = 0; i < modeloCli.getRowCount(); i++) {
            modeloCli.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiartablaprod() {
        for (int i = 0; i < modelprod.getRowCount(); i++) {
            modelprod.removeRow(i);
            i = i - 1;
        }
    }
    
    public void limpiartablavend() {
        for (int i = 0; i < modelovend.getRowCount(); i++) {
            modelovend.removeRow(i);
            i = i - 1;
        }
    }
}
