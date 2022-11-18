
package modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;


public class Plantilla {
    
    
    String rutaImagen;
    List<Creditos> creditos;
    
    Document documento;
    FileOutputStream archivo;
    Paragraph titulo;
    
    public Plantilla(String rutaImagen, List<Creditos> credito) {
        
        this.rutaImagen = rutaImagen;
        this.creditos = credito;
        
        documento = new Document();
        titulo  = new Paragraph("Plantilla Personalizada");  
    }
    
    public void crearPlantilla(){
        try {
            archivo = new FileOutputStream("prueba" + ".pdf");
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            titulo.setAlignment(1);

            Image image = null;
            try {
                image =  Image.getInstance(rutaImagen);//carga imagen
                image.scaleAbsolute(150, 100);//cambia tamaño
                image.setAbsolutePosition(415, 750);//coloca imagen en la posicion
                
            } catch (Exception e) {
            }
            
            documento.add(image);//agrega la imagen al documento
            
            documento.add(titulo);
            
            documento.add(new Paragraph("Director: "));
            
            documento.add(Chunk.NEWLINE);
            
            Paragraph texto = new Paragraph("It is a long established fact that a reader will be distracted by the readable content of "
                    + "a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution"
                    + " of letters, as opposed to using 'Content here, content here', making it look like readable English. "
                    + "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for "
                    + "'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, "
                    + "sometimes on purpose (injected humour and the like).");
            texto.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(texto);
            
            documento.add(Chunk.NEWLINE);
            
            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);
            PdfPCell fecha = new PdfPCell(new Phrase("Fecha"));
            fecha.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell nombre = new PdfPCell(new Phrase("Nombre Cliente"));
            nombre.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell apellido = new PdfPCell(new Phrase("Apellido Cliente"));
            apellido.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell direccion = new PdfPCell(new Phrase("Dirección"));
            direccion.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell total = new PdfPCell(new Phrase("Total"));
            total.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell pendiente = new PdfPCell(new Phrase("Pendiente"));
            pendiente.setBackgroundColor(BaseColor.ORANGE);
            
            tabla.addCell(fecha);
            tabla.addCell(nombre);
            tabla.addCell(apellido);
            tabla.addCell(direccion);            
            tabla.addCell(total);
            tabla.addCell(pendiente);            
            
            for(Creditos credito: this.creditos){
                tabla.addCell(credito.getFecha().toString());
                tabla.addCell(credito.getNombres());
                tabla.addCell(credito.getApellidos());
                tabla.addCell(credito.getDireccion());
                tabla.addCell(String.valueOf(credito.getTotal()));
                tabla.addCell(String.valueOf(credito.getPendiente()));
                
            }
            documento.add(tabla);          
            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Fecha: "));
            
            documento.close();
            JOptionPane.showMessageDialog(null, "El archivo PDF se a creado correctamente!");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch(DocumentException e){
            System.err.println(e.getMessage());
        }
        
    }
    
    
    
    
}
