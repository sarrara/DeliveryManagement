package pidev.elbey.Services;




import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pidev.elbey.Entities.Delivery;
import pidev.elbey.Repositories.BillToSeenRepo;
import pidev.elbey.Repositories.DeliveryRepo;


import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.*;
import java.util.Optional;

@Service

@AllArgsConstructor
public class PDFGeneratorService {


    BillToSeenRepo billToSeenRepo;
    DeliveryRepo deliveryRepo;


    public void export(HttpServletResponse response, Long idDelivery) throws IOException, TransformerException {
        Optional<Delivery> d = deliveryRepo.findById(idDelivery);
        Delivery delivery = d.get();
        Double DeliveryPrice = 7.0;

        Document document = new Document(PageSize.A4);

        // Set up the PDF writer to write directly to the HTTP response
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Sarra\\Downloads\\Central-d-achat-main (1)\\Central-d-achat-main\\PIDEV\\Facture.pdf"));
        Font f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 22, Color.RED);
        Font f2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 22, Color.BLACK);
        Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15.0f, Color.BLACK);

        Image image = Image.getInstance("C:\\Users\\Sarra\\Downloads\\logo.png");
        image.scaleAbsoluteWidth(160);
        image.scaleAbsoluteHeight(92);
        Paragraph p7 = new Paragraph("\n");
        Paragraph paragraph = new Paragraph("FACTURE", f2);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        Paragraph p1 = new Paragraph("Information du Client    :", f);
        Paragraph p10 = new Paragraph("Information du Commande  :", f);
        Paragraph p = new Paragraph("Name of the Client                    :      " + delivery.getUser().getName(), f1);
        Paragraph p9 = new Paragraph("Phone of the Client                  :      " + d.get().getUser().getPhoneNumber(), f1);
        Paragraph p2 = new Paragraph("Creation Delivery date                :      " + d.get().getDateDelivery(), f1);
        Paragraph p3 = new Paragraph("Price of the delivery                 :      " + DeliveryPrice + "    DT", f1);
        // Paragraph p4 = new Paragraph("Total Price of the product            :      " + bill.get().getTotalTTC() + "    DT", f1);

        document.open();
        image.setAlignment(Image.ALIGN_RIGHT);
        document.add(paragraph);
        document.add(image);
        document.add(new Paragraph(" "));
        document.add(p1);
        document.add(new Paragraph(" "));
        document.add(p7);
        document.add(p);
        document.add(p9);
        document.add(p2);
        document.add(p3);
        //  document.add(p4);
        document.add(new Paragraph(" "));
        document.add(p7);
        document.add(p10);
        document.add(new Paragraph(" "));
        document.add(p7);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);


        document.add(table);


        document.close();

// Write the PDF contents to the HTTP response
        response.setHeader("Content-Disposition", "attachment; filename=FACTURE.pdf");
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        OutputStream out = response.getOutputStream();
        baos.writeTo(out);
        out.flush();
        out.close();
    }


}