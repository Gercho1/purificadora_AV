package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Movimiento;
import modelo.MovimientoDAO;
import vista.Jmovimiento;

public class MovimientoControllers implements ActionListener {

    private Movimiento mov;
    private MovimientoDAO movdao;
    private Jmovimiento formov;

    public MovimientoControllers(Movimiento mov, MovimientoDAO movdao, Jmovimiento formov) {
        this.mov = mov;
        this.movdao = movdao;
        this.formov = formov;
        this.formov.btnactualizar.addActionListener(this);
        this.formov.lbsaldo.setText(String.valueOf(movdao.buscar()));
        Listar(formov.jtmovimiento);
    }

    DefaultTableModel modelo = new DefaultTableModel();

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == formov.btnactualizar){
            limpiartabla();
            Listar(formov.jtmovimiento);
            this.formov.lbsaldo.setText(String.valueOf(movdao.buscar()));
        }
    }

    private void Listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Movimiento> lista = movdao.listar();
        Object[] object = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            
            object[0] = lista.get(i).getFecha();
            object[1] = lista.get(i).getTipo();
            object[2] = lista.get(i).getEntrada();
            object[3] = lista.get(i).getSalida();
            object[4] = lista.get(i).getEstado();
            object[5] = lista.get(i).getSaldo();
            modelo.addRow(object);
        }
        formov.jtmovimiento.setModel(modelo);
    }
    
    public void limpiartabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
