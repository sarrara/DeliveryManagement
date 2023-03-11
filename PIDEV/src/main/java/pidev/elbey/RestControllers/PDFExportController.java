package pidev.elbey.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pidev.elbey.Entities.Delivery;
import pidev.elbey.Repositories.DeliveryRepo;
import pidev.elbey.Services.PDFGeneratorService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class PDFExportController {


   /*@GetMapping("/pdf/generate/{IdDelivery}")
    public void generatePDF(HttpServletResponse response, @PathVariable Long IdDelivery) throws IOException, TransformerException {
        response.setContentType("application/pdf");


        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=FACTURE_" + ".pdf";
        response.setHeader(headerKey, headerValue);
        Delivery delivery = this.pdfGeneratorService.export(response,IdDelivery);


    }
    */

    @Autowired
    private PDFGeneratorService pdfGeneratorService;


    public PDFExportController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate/{ref}")
    public void generatePDF(HttpServletResponse response, @PathVariable Long ref) throws IOException, TransformerException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=FACTURE_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);



    }
}







