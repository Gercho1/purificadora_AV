package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Productos;
import modelo.Usuario;
import modelo.Ventas;
import modelo.VentasDAO;
import modelo.VentasDetalle;
import vista.Jventasdetalle;

public class VentasControllers implements ActionListener, MouseListener, KeyListener {

    private Ventas ventas;
    private VentasDetalle ventasdetalle;
    private Usuario usuario;
    private Productos productos;
    private VentasDAO vendao;
    private Jventasdetalle formven;

    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelov = new DefaultTableModel();
    DefaultTableModel modelod = new DefaultTableModel();
    DefaultTableModel modeloVen = new DefaultTableModel();
    DefaultTableModel modeloprod = new DefaultTableModel();
    int item;
    double Totalpagar = 0.00;

    public VentasControllers(VentasDetalle ventasdetalle, Ventas ventas, Usuario usuario, Productos productos, VentasDAO vendao, Jventasdetalle formven) {
        this.ventasdetalle = ventasdetalle;
        this.ventas = ventas;
        this.usuario = usuario;
        this.productos = productos;
        this.vendao = vendao;
        this.formven = formven;

        /*Botones*/
        this.formven.btnguardar.addActionListener(this);
        this.formven.btnmodificar.addActionListener(this);
        this.formven.btnseleccionar.addActionListener(this);
        this.formven.btnseleccionarv.addActionListener(this);
        this.formven.btncancelar.addActionListener(this);
        this.formven.btneliminar.addActionListener(this);
        this.formven.btnseleccionarprod.addActionListener(this);
        this.formven.btnseleccionarp.addActionListener(this);
        this.formven.btncancelarp.addActionListener(this);
        this.formven.btnpdf.setVisible(false);
        /*Texfields*/
        this.formven.txtbusqueda.addKeyListener(this);
        this.formven.txtcantidad.addKeyListener(this);
        /*Texfields ocultos*/
        this.formven.txtidventa.setVisible(false);
        this.formven.txtidproducto.setVisible(false);
        this.formven.txtidvendedor.setVisible(false);
        /*Texfields bloqueados*/
        this.formven.txtproducto.setEditable(false);
        this.formven.txtprecio.setEditable(false);
        this.formven.txtnombrevendedor.setEditable(false);
        this.formven.txtapellidovendedor.setEditable(false);
        /*Tabla*/
        this.formven.jtventa.addMouseListener(this);
        /*Metodos*/
        ListaraltasV(formven.jtventa);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == formven.btneliminar) {
            Eliminar();
        }

        if (e.getSource() == formven.btnguardar) {
            if (formven.txtnombrevendedor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un vendedor antes de guardar");
            } 
            else {
                Guardar();
                Limpiar();
                limpiartabla();
            }
        }

        if (e.getSource() == formven.btnmodificar) {
            if (formven.cbestado.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Solo se puede dar de baja la venta");
            } else {
                modificar();
            }
        }

        if (e.getSource() == formven.btnseleccionar) {
            formven.jTabbedPane1.setSelectedIndex(1);
            limpiartablavend();
            ListarVendedor(formven.jtvendedor);
        }

        if (e.getSource() == formven.btnseleccionarv) {
            if (formven.jtvendedor.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un vendedor");
            } else {
                int fila = formven.jtvendedor.getSelectedRow();
                formven.txtidvendedor.setText(formven.jtvendedor.getValueAt(fila, 0).toString());
                formven.txtnombrevendedor.setText(formven.jtvendedor.getValueAt(fila, 1).toString());
                formven.txtapellidovendedor.setText(formven.jtvendedor.getValueAt(fila, 2).toString());
                formven.jTabbedPane1.setSelectedIndex(0);
                formven.btnguardar.requestFocus();
            }

        }

        if (e.getSource() == formven.btncancelar) {
            formven.jTabbedPane1.setSelectedIndex(0);
        }

        if (e.getSource() == formven.btnseleccionarprod) {
            formven.jTabbedPane1.setSelectedIndex(2);
            limpiartablaprod();
            ListarProducto(formven.jtproducto);
        }

        if (e.getSource() == formven.btnseleccionarp) {
            if (formven.jtproducto.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
            } else {
                int fila = formven.jtproducto.getSelectedRow();
                formven.txtidproducto.setText(formven.jtproducto.getValueAt(fila, 0).toString());
                formven.txtproducto.setText(formven.jtproducto.getValueAt(fila, 1).toString());
                formven.txtprecio.setText(formven.jtproducto.getValueAt(fila, 3).toString());
                formven.jTabbedPane1.setSelectedIndex(0);
                formven.txtcantidad.requestFocus();
            }

        }

        if (e.getSource() == formven.btncancelarp) {
            formven.jTabbedPane1.setSelectedIndex(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == formven.jtventa) {
            int fila = formven.jtventa.rowAtPoint(e.getPoint());
            formven.txtidventa.setText(formven.jtventa.getValueAt(fila, 0).toString());
            int a = Integer.parseInt(formven.txtidventa.getText());
            limpiartablad();
            ListaraltasD(formven.jtdetalevista, a);
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
        if (e.getSource() == formven.txtidproducto) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!"".equals(formven.txtidproducto.getText())) {
                    int cod = Integer.parseInt(formven.txtidproducto.getText());
                    productos = vendao.BuscarPro(cod);
                    if (productos.getNombre() != null) {
                        formven.txtproducto.setText("" + productos.getNombre());
                        formven.txtprecio.setText("" + productos.getPrecioventa());
                        formven.txtcantidad.requestFocus();
                    } else {
                        formven.txtproducto.setText("");
                        formven.txtprecio.setText("");
                        formven.txtidproducto.requestFocus();
                        JOptionPane.showMessageDialog(null, "El ID del producto no existe");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
                    formven.txtidproducto.requestFocus();
                }
            }
        }
//        if (e.getSource() == formven.txtvendedor) {
//            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                if (!"".equals(formven.txtvendedor.getText())) {
//                    int id = Integer.parseInt(formven.txtvendedor.getText());
//                    usuario = vendao.Buscaruser(id);
//                    if (usuario.getNombres() != null) {
//                        formven.txtnombrevendedor.setText("" + usuario.getNombres());
//                        formven.txtapellidovendedor.setText(usuario.getApellidos());
//                        formven.btnguardar.requestFocus();
//                    } else {
//                        formven.txtvendedor.setText("");
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Ingrese el codigo del vendedor");
//                    formven.txtvendedor.requestFocus();
//                }
//            }
//        }

        if (e.getSource() == formven.txtcantidad) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!"".equals(formven.txtcantidad.getText())) {
                    String cod = formven.txtidproducto.getText();
                    String nombre = formven.txtproducto.getText();
                    int cant = Integer.parseInt(formven.txtcantidad.getText());
                    double precio = Double.parseDouble(formven.txtprecio.getText());
                    double total = cant * precio;

                    if (formven.txtcantidad != null) {
                        item = item + 1;
                        modelo = (DefaultTableModel) formven.jtdetalleventa.getModel();
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
                        formven.jtdetalleventa.setModel(modelo);
                        Limpiar();
                        formven.txtidproducto.requestFocus();
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
        if (e.getSource() == formven.txtbusqueda) {
            if (formven.txtbusqueda.getText().isEmpty()) {
                String a = formven.txtbusqueda.getText();
                limpiartablavend();
                buscar(formven.jtvendedor, a);
            } else {
                String a = formven.txtbusqueda.getText();
                limpiartablavend();
                buscar(formven.jtvendedor, a);
            }
        }

    }

    private void Totalpagar() {
        Totalpagar = 0.00;
        int numFila = formven.jtdetalleventa.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(formven.jtdetalleventa.getModel().getValueAt(i, 4)));
            Totalpagar = Totalpagar + cal;
        }
        formven.jltotalventa.setText(String.format("%.2f", Totalpagar));
    }

    private void Limpiar() {
        formven.txtidproducto.setText("");
        formven.jltotalventa.setText("");
        formven.txtproducto.setText("");
        formven.txtidvendedor.setText("");
        formven.txtnombrevendedor.setText("");
        formven.txtapellidovendedor.setText("");
        formven.txtcantidad.setText("");
        formven.txtprecio.setText("");
        formven.dateventa.setDate(null);
    }

    private void Eliminar() {
        if (formven.jtdetalleventa.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Para poder eliminar, debe seleccionar el registro en la tabla");
        } else {
            modelo.removeRow(formven.jtdetalleventa.getSelectedRow());
            Totalpagar();
            formven.txtidproducto.requestFocus();
        }
    }

    private void Guardar() {
        try {
            int idvendedor = Integer.parseInt(formven.txtidvendedor.getText());
//            LocalDate date = LocalDate.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int dia, mes, ano;
            dia = formven.dateventa.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes = formven.dateventa.getCalendar().get(Calendar.MONTH) + 1;
            ano = formven.dateventa.getCalendar().get(Calendar.YEAR);
            String fechain;
            fechain = ano + "-" + mes + "-" + dia;
            double total = Double.parseDouble(formven.jltotalventa.getText());
            String estado = "ALTA";
            ventas.setIdvendedor(idvendedor);
            ventas.setFecha(Date.valueOf(fechain));
            ventas.setTotal(total);
            ventas.setEstado(estado);
            if (vendao.agregar(ventas) == 1) {

                limpiartablav();
                ListaraltasV(formven.jtventa);
                JOptionPane.showMessageDialog(null, "Venta agregada");
            }
            int id = vendao.Numeroventa();
            for (int i = 0; i < formven.jtdetalleventa.getRowCount(); i++) {
                int cod = Integer.parseInt(formven.jtdetalleventa.getValueAt(i, 0).toString());
                int cant = Integer.parseInt(formven.jtdetalleventa.getValueAt(i, 2).toString());
                double precio = Double.parseDouble(formven.jtdetalleventa.getValueAt(i, 4).toString());
                ventasdetalle.setIdventa(id);
                ventasdetalle.setIdproducto(cod);
                ventasdetalle.setCantidad(cant);
                ventasdetalle.setSubtotal(precio);
                if (vendao.agregardetalle(ventasdetalle) == 1) {
//                    JOptionPane.showMessageDialog(null, "Detalle de la venta agregada");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar, Error");
        }
    }

    private void modificar() {
        try {
            int id = Integer.parseInt(formven.txtidventa.getText());
            String estado = formven.cbestado.getSelectedItem().toString();
            ventas.setEstado(estado);
            ventas.setId(id);
            if (vendao.modificar(ventas) == 1) {
                limpiartablav();
                ListaraltasV(formven.jtventa);
                formven.cbestado.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Venta actualizada");

            } else {
                formven.cbestado.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Error al actualizar la venta");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos ");
        }

    }

    private void ListaraltasV(JTable tabla) {
        modelov = (DefaultTableModel) tabla.getModel();
        List<Ventas> lista = vendao.listarAltaVenta();
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getIdvendedor();
            object[2] = lista.get(i).getNombre();
            object[3] = lista.get(i).getApellidos();
            object[4] = lista.get(i).getFecha();
            object[5] = lista.get(i).getTotal();
            object[6] = lista.get(i).getEstado();
            modelov.addRow(object);
        }
        formven.jtventa.setModel(modelov);
    }

    private void ListaraltasD(JTable tabla, int a) {
        modelod = (DefaultTableModel) tabla.getModel();
        List<VentasDetalle> lista = vendao.listarAltaVentaDetalle(a);
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getIdventa();
            object[1] = lista.get(i).getProducto();
            object[2] = lista.get(i).getCantidad();
            object[3] = lista.get(i).getSubtotal();
            modelod.addRow(object);
        }
        formven.jtdetalevista.setModel(modelod);
    }

    private void ListarVendedor(JTable tabla) {
        modeloVen = (DefaultTableModel) tabla.getModel();
        List<Usuario> lista = vendao.listarvendedor();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getID();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getTipo_Usuario();
            modeloVen.addRow(object);
        }
        formven.jtvendedor.setModel(modeloVen);
    }

    private void buscar(JTable tabla, String b) {
        modeloVen = (DefaultTableModel) tabla.getModel();
        List<Usuario> lista = vendao.buscarvendedor(b);
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getID();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getTipo_Usuario();
            modeloVen.addRow(object);
        }
        formven.jtvendedor.setModel(modeloVen);
    }

    private void ListarProducto(JTable tabla) {
        modeloprod = (DefaultTableModel) tabla.getModel();
        List<Productos> lista = vendao.listarproducto();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getDescripcion();
            object[3] = lista.get(i).getPrecioventa();
            modeloprod.addRow(object);
        }
        formven.jtproducto.setModel(modeloprod);
    }

    public void limpiartabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiartablav() {
        for (int i = 0; i < modelov.getRowCount(); i++) {
            modelov.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiartablad() {
        for (int i = 0; i < modelod.getRowCount(); i++) {
            modelod.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiartablavend() {
        for (int i = 0; i < modeloVen.getRowCount(); i++) {
            modeloVen.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiartablaprod() {
        for (int i = 0; i < modeloprod.getRowCount(); i++) {
            modeloprod.removeRow(i);
            i = i - 1;
        }
    }
}
