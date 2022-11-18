/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.transform.Rotate;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.CreditoreporteDAO;
import modelo.Creditos;

import modelo.Usuario;
import vista.Jcreditoreporte;

public class CreditosreporteControllers implements ActionListener, KeyListener, MouseListener {

    private Jcreditoreporte formreporte;
    private CreditoreporteDAO redao;
    private Creditos cred;

    public CreditosreporteControllers(Jcreditoreporte formreporte, CreditoreporteDAO redao, Creditos cred) {
        this.formreporte = formreporte;
        this.redao = redao;
        this.cred = cred;

        this.formreporte.jtvendedor.addMouseListener(this);
        this.formreporte.txtbusqueda.addKeyListener(this);
        this.formreporte.btnreporte.addActionListener(this);
        this.formreporte.txtid.setVisible(false);
        this.formreporte.txtvendedor.setVisible(false);
        ListarVendedor(formreporte.jtvendedor);

    }

    DefaultTableModel modeloven = new DefaultTableModel();
    DefaultTableModel modelocred = new DefaultTableModel();

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == formreporte.btnreporte) {
            if (formreporte.jtvendedor.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un vendedor para poder generar el reporte");
            } else {
                pdf();
                JOptionPane.showMessageDialog(null, "Reporte generado exitosamente en la carpeta Reportes");
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == formreporte.txtbusqueda) {
            if (formreporte.txtbusqueda.getText().isEmpty()) {
                String a = formreporte.txtbusqueda.getText();
                limpiartablavend();
                buscarvendedor(formreporte.jtvendedor, a);
            } else {
                String a = formreporte.txtbusqueda.getText();
                limpiartablavend();
                buscarvendedor(formreporte.jtvendedor, a);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == formreporte.jtvendedor) {
            int fila = formreporte.jtvendedor.rowAtPoint(e.getPoint());

            formreporte.txtid.setText(formreporte.jtvendedor.getValueAt(fila, 0).toString());
            int a = Integer.parseInt(formreporte.jtvendedor.getValueAt(fila, 0).toString());
            String nombre = formreporte.jtvendedor.getValueAt(fila, 1).toString();
            String apellido = formreporte.jtvendedor.getValueAt(fila, 2).toString();
            formreporte.txtvendedor.setText(nombre + " " + apellido);
            limpiartablacred();
            ListarCredito(formreporte.jtcredito, a);
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

    private void ListarVendedor(JTable tabla) {
        modeloven = (DefaultTableModel) tabla.getModel();
        List<Usuario> lista = redao.listarUsuario();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getID();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getTipo_Usuario();
            modeloven.addRow(object);
        }
        formreporte.jtvendedor.setModel(modeloven);
    }

    private void buscarvendedor(JTable tabla, String b) {
        modeloven = (DefaultTableModel) tabla.getModel();
        List<Usuario> lista = redao.buscarvendedor(b);
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getID();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getTipo_Usuario();
            modeloven.addRow(object);
        }
        formreporte.jtvendedor.setModel(modeloven);
    }

    private void ListarCredito(JTable tabla, int a) {
        modelocred = (DefaultTableModel) tabla.getModel();
        List<Creditos> lista = redao.listarCredito(a);
        Object[] object = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getFecha();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getDireccion();
            object[4] = lista.get(i).getTotal();
            object[5] = lista.get(i).getPendiente();
            modelocred.addRow(object);
        }
        formreporte.jtcredito.setModel(modelocred);
    }

    private void pdf() {
        try {

            FileOutputStream archivo;
////            String desktopPath = System.getProperty("user.home") + "/Desktop/Reportes/credito.pdf";
////            System.out.println(desktopPath.replace("\\", "/"));
////            FileOutputStream archivo = new FileOutputStream(desktopPath); 
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
            Date dates = new Date();
            String desktopPath = System.getProperty("user.home") + "/Desktop/Reportes/" + df.format(new Date()) + " " + formreporte.txtvendedor.getText() + ".pdf";
            File file = new File(desktopPath);
            archivo = new FileOutputStream(file);
            Document doc = new Document(PageSize.A4, 10f, 10f, 10f, 0f);
            PdfWriter.getInstance(doc, archivo);
            doc.open();
//            String desktopPathI = System.getProperty("user.dir") + "/src/Icono/logochiac2.png";
            String desktopPathI = System.getProperty("user.dir") + "/Icono/logochiac2.png";
            Image img = Image.getInstance(desktopPathI);
            Paragraph fech = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fech.add(Chunk.NEWLINE);
            Date date = new Date();
            fech.add("Vendedor : " + formreporte.txtvendedor.getText() + " \n" + "Fecha: " + new SimpleDateFormat("dd-MMM-yyyy").format(date) + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(img);

            String nom = "Purificadora Agua Viva Chiac";

            Encabezado.addCell("");
            Encabezado.addCell(nom);
            Encabezado.addCell(fech);
            doc.add(Encabezado);

            Paragraph credito = new Paragraph();
            credito.add(Chunk.NEWLINE);
            credito.add("Datos del crédito" + "\n\n");
            doc.add(credito);

            PdfPTable tablacredito = new PdfPTable(6);
            tablacredito.setWidthPercentage(100);
            tablacredito.getDefaultCell().setBorder(0);
            float[] Columnacredito = new float[]{60f, 60f, 60f, 60f, 60f, 60f};
            tablacredito.setWidths(Columnacredito);
            tablacredito.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cl1 = new PdfPCell(new Phrase("Fecha", negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("Nombre Cliente", negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("Apellido Cliente", negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("Dirección", negrita));
            PdfPCell cl5 = new PdfPCell(new Phrase("Total", negrita));
            PdfPCell cl6 = new PdfPCell(new Phrase("Pendiente", negrita));
            cl1.setBorder(0);
            cl2.setBorder(0);
            cl3.setBorder(0);
            cl4.setBorder(0);
            cl5.setBorder(0);
            cl6.setBorder(0);
            cl1.setBackgroundColor(BaseColor.CYAN);
            cl2.setBackgroundColor(BaseColor.CYAN);
            cl3.setBackgroundColor(BaseColor.CYAN);
            cl4.setBackgroundColor(BaseColor.CYAN);
            cl5.setBackgroundColor(BaseColor.CYAN);
            cl6.setBackgroundColor(BaseColor.CYAN);
            tablacredito.addCell(cl1);
            tablacredito.addCell(cl2);
            tablacredito.addCell(cl3);
            tablacredito.addCell(cl4);
            tablacredito.addCell(cl5);
            tablacredito.addCell(cl6);
            for (int i = 0; i < formreporte.jtcredito.getRowCount(); i++) {
                String fecha = formreporte.jtcredito.getValueAt(i, 0).toString();
                String nombre = formreporte.jtcredito.getValueAt(i, 1).toString();
                String apellido = formreporte.jtcredito.getValueAt(i, 2).toString();
                String direccion = formreporte.jtcredito.getValueAt(i, 3).toString();
                String total = formreporte.jtcredito.getValueAt(i, 4).toString();
                String pendiente = formreporte.jtcredito.getValueAt(i, 5).toString();
                tablacredito.addCell(fecha);
                tablacredito.addCell(nombre);
                tablacredito.addCell(apellido);
                tablacredito.addCell(direccion);
                tablacredito.addCell(total);
                tablacredito.addCell(pendiente);
            }

            doc.add(tablacredito);

            doc.close();
            archivo.close();
//writer.close();
//            Desktop.getDesktop().open(file);
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void limpiartablavend() {
        for (int i = 0; i < modeloven.getRowCount(); i++) {
            modeloven.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiartablacred() {
        for (int i = 0; i < modelocred.getRowCount(); i++) {
            modelocred.removeRow(i);
            i = i - 1;
        }
    }
}
