package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.BalanceDAO;
import modelo.Egresos;
import modelo.Ingresos;
import vista.Jbalance;

public class BalanceControllers implements ActionListener {

    private Ingresos in;
    private Egresos en;
    private Jbalance formb;
    private BalanceDAO bandao;

    DefaultTableModel modeloi = new DefaultTableModel();
    DefaultTableModel modeloe = new DefaultTableModel();
    double Totalpagari = 0.00;
    double Totalpagare = 0.00;

    public BalanceControllers(Ingresos in, Egresos en, Jbalance formb, BalanceDAO bandao) {
        this.in = in;
        this.en = en;
        this.formb = formb;
        this.bandao = bandao;
        this.formb.btnver.addActionListener(this);
        this.formb.btnexit.addActionListener(this);
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == formb.btnver){
            if(formb.dateinicio.getDateFormatString().isEmpty() ||
                    formb.datefin.getDateFormatString().isEmpty() || formb.txtinversion.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos");
            }
            else{
                mostrar();
                Totalingreso();
                Totalegreso();
                double a = Double.parseDouble(formb.lbingreso.getText());
                double b = Double.parseDouble(formb.lbegreso.getText());
                double c = Double.parseDouble(formb.txtinversion.getText());
                double util = a - b;
                int rent = (int) (util / c * 100);
                formb.lbutilidad.setText(String.valueOf(util));
                formb.lbrentabilidad.setText(String.valueOf(rent) + " %");
                
            }
        }
    }

    private void Totalingreso() {
        Totalpagari = 0.00;
        int numFila = formb.jtingreso.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(formb.jtingreso.getModel().getValueAt(i, 2)));
            Totalpagari = Totalpagari + cal;
        }
        formb.lbingreso.setText(String.format("%.2f", Totalpagari));
    }

    private void Totalegreso() {
        Totalpagare = 0.00;
        int numFila = formb.jtegreso.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(formb.jtegreso.getModel().getValueAt(i, 2)));
            Totalpagare = Totalpagare + cal;
        }
        formb.lbegreso.setText(String.format("%.2f", Totalpagare));
    }

    private void Listaraltasingreso(JTable tabla, String a, String b) {
        modeloi = (DefaultTableModel) tabla.getModel();
        List<Ingresos> lista = bandao.listarIngreso(a, b);
        Object[] object = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getFecha();
            object[1] = lista.get(i).getTipo();
            object[2] = lista.get(i).getTotal();
            modeloi.addRow(object);
        }
        formb.jtingreso.setModel(modeloi);
    }

    private void Listaraltasegreso(JTable tabla, String a, String b) {
        modeloe = (DefaultTableModel) tabla.getModel();
        List<Egresos> lista = bandao.listarEgreso(a, b);
        Object[] object = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getFecha();
            object[1] = lista.get(i).getTipo();
            object[2] = lista.get(i).getTotal();
            modeloe.addRow(object);
        }
        formb.jtegreso.setModel(modeloe);
    }
    
    private void mostrar(){
        int dia,mes,ano;
        dia = formb.dateinicio.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = formb.dateinicio.getCalendar().get(Calendar.MONTH)+1;
        ano = formb.dateinicio.getCalendar().get(Calendar.YEAR);
        String fechain;
        fechain = ano+"-"+mes+"-"+dia;
        int dia1,mes1,ano1;
        dia1 = formb.datefin.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes1 = formb.datefin.getCalendar().get(Calendar.MONTH)+1;
        ano1 = formb.datefin.getCalendar().get(Calendar.YEAR);
        String fechaen;
        fechaen = ano1+"-"+mes1+"-"+dia1;
        limpiartablai();
        Listaraltasingreso(formb.jtingreso, fechain, fechaen);
        limpiartablae();
        Listaraltasegreso(formb.jtegreso, fechain, fechaen);
    }
    
    public void limpiartablai() {
        for (int i = 0; i < modeloi.getRowCount(); i++) {
            modeloi.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiartablae() {
        for (int i = 0; i < modeloe.getRowCount(); i++) {
            modeloe.removeRow(i);
            i = i - 1;
        }
    }
}
