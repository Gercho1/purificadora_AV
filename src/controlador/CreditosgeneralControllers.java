package controlador;

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
import com.itextpdf.text.pdf.PdfWriter;
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
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.CreditoreporteDAO;
import modelo.Creditos;

import vista.Jcontrolcredito;

public class CreditosgeneralControllers implements ActionListener, KeyListener, MouseListener {

    private Jcontrolcredito formgeneral;
    private CreditoreporteDAO redao;
    private Creditos cred;

    public CreditosgeneralControllers(Jcontrolcredito formgeneral, CreditoreporteDAO redao, Creditos cred) {
        this.formgeneral = formgeneral;
        this.redao = redao;
        this.cred = cred;

        this.formgeneral.btnlistar.addActionListener(this);
        this.formgeneral.btnreporte.addActionListener(this);

    }

    DefaultTableModel modelocred = new DefaultTableModel();

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == formgeneral.btnlistar) {
            if (formgeneral.cbclientes.getSelectedIndex() == 0) {
                limpiartablacred();
                JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para poder listar");
            }
            if (formgeneral.cbclientes.getSelectedIndex() == 1) {
                limpiartablacred();
                ListarCredito(formgeneral.jtcredito);
            }
            if (formgeneral.cbclientes.getSelectedIndex() == 2) {
                limpiartablacred();
                ListarMorosos(formgeneral.jtcredito);
            }

        }

        if (e.getSource() == formgeneral.btnreporte) {
            if (formgeneral.jtcredito.getRowCount() == 0) {
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
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    private void ListarCredito(JTable tabla) {
        modelocred = (DefaultTableModel) tabla.getModel();
        List<Creditos> lista = redao.listarCreditoGeneral();
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getNombres();
            object[1] = lista.get(i).getNombrevendedor();
            object[2] = lista.get(i).getDireccion();
            object[3] = lista.get(i).getTelefono();
            object[4] = lista.get(i).getFecha();
            object[5] = lista.get(i).getTotal();
            object[6] = lista.get(i).getPendiente();
            modelocred.addRow(object);
        }
        formgeneral.jtcredito.setModel(modelocred);
    }

    private void ListarMorosos(JTable tabla) {
        modelocred = (DefaultTableModel) tabla.getModel();
        List<Creditos> lista = redao.listarCreditoMorosos();
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getNombres();
            object[1] = lista.get(i).getNombrevendedor();
            object[2] = lista.get(i).getDireccion();
            object[3] = lista.get(i).getTelefono();
            object[4] = lista.get(i).getFecha();
            object[5] = lista.get(i).getTotal();
            object[6] = lista.get(i).getPendiente();
            modelocred.addRow(object);
        }
        formgeneral.jtcredito.setModel(modelocred);
    }

    private void pdf() {
        try {

            FileOutputStream archivo;
////            String desktopPath = System.getProperty("user.home") + "/Desktop/Reportes/credito.pdf";
////            System.out.println(desktopPath.replace("\\", "/"));
////            FileOutputStream archivo = new FileOutputStream(desktopPath); 
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
            Date dates = new Date();
            String desktopPath = System.getProperty("user.home") + "/Desktop/Reportes/General/" + df.format(new Date()) + formgeneral.cbclientes.getSelectedItem().toString() + ".pdf";
            File file = new File(desktopPath);
            archivo = new FileOutputStream(file);
            Document doc = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 0f);
            PdfWriter.getInstance(doc, archivo);
            doc.open();
//            String desktopPathI = System.getProperty("user.dir") + "/src/Icono/logochiac2.png";
            String desktopPathI = System.getProperty("user.dir") + "/Icono/logochiac2.png";
            Image img = Image.getInstance(desktopPathI);
            Paragraph fech = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fech.add(Chunk.NEWLINE);
            Date date = new Date();
            fech.add(formgeneral.cbclientes.getSelectedItem().toString() + " \n" + "Fecha: " + new SimpleDateFormat("dd-MMM-yyyy").format(date) + "\n\n");

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

            PdfPTable tablacredito = new PdfPTable(7);
            tablacredito.setWidthPercentage(100);
            tablacredito.getDefaultCell().setBorder(0);
            float[] Columnacredito = new float[]{60f, 60f, 60f, 40f, 40f, 40f, 40f};
            tablacredito.setWidths(Columnacredito);
            tablacredito.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cl1 = new PdfPCell(new Phrase("Cliente", negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("Vendedor", negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("Dirección", negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("Teléfono", negrita));
            PdfPCell cl5 = new PdfPCell(new Phrase("Fecha", negrita));
            PdfPCell cl6 = new PdfPCell(new Phrase("Total", negrita));
            PdfPCell cl7 = new PdfPCell(new Phrase("Pendiente", negrita));
            cl1.setBorder(0);
            cl2.setBorder(0);
            cl3.setBorder(0);
            cl4.setBorder(0);
            cl5.setBorder(0);
            cl6.setBorder(0);
            cl7.setBorder(0);
            cl1.setBackgroundColor(BaseColor.CYAN);
            cl2.setBackgroundColor(BaseColor.CYAN);
            cl3.setBackgroundColor(BaseColor.CYAN);
            cl4.setBackgroundColor(BaseColor.CYAN);
            cl5.setBackgroundColor(BaseColor.CYAN);
            cl6.setBackgroundColor(BaseColor.CYAN);
            cl7.setBackgroundColor(BaseColor.CYAN);
            tablacredito.addCell(cl1);
            tablacredito.addCell(cl2);
            tablacredito.addCell(cl3);
            tablacredito.addCell(cl4);
            tablacredito.addCell(cl5);
            tablacredito.addCell(cl6);
            tablacredito.addCell(cl7);
            for (int i = 0; i < formgeneral.jtcredito.getRowCount(); i++) {
                String nombrecliente = formgeneral.jtcredito.getValueAt(i, 0).toString();
                String nombrevendedor = formgeneral.jtcredito.getValueAt(i, 1).toString();
                String direccion = formgeneral.jtcredito.getValueAt(i, 2).toString();
                String telefono = formgeneral.jtcredito.getValueAt(i, 3).toString();
                String fecha = formgeneral.jtcredito.getValueAt(i, 4).toString();
                String total = formgeneral.jtcredito.getValueAt(i, 5).toString();
                String pendiente = formgeneral.jtcredito.getValueAt(i, 6).toString();
                tablacredito.addCell(nombrecliente);
                tablacredito.addCell(nombrevendedor);
                tablacredito.addCell(direccion);
                tablacredito.addCell(telefono);
                tablacredito.addCell(fecha);
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

    public void limpiartablacred() {
        for (int i = 0; i < modelocred.getRowCount(); i++) {
            modelocred.removeRow(i);
            i = i - 1;
        }
    }
}
