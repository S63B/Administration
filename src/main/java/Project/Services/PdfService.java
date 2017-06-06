package Project.Services;

import Project.DAO.OwnerDao;
import Project.Pdf.PdfGenerator;
import com.S63B.domain.Entities.Invoice;
import com.S63B.domain.Entities.Owner;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Nekkyou on 18-4-2017.
 */
@Service("pdfService")
public class PdfService {

    private PdfGenerator generator;
    private OwnerDao ownerDao;
    private InvoiceService invoiceService;


    public PdfService() {
    }

    @Autowired
    private void PdfService(OwnerDao ownerDao, InvoiceService invoiceService) {
        this.generator = new PdfGenerator();
        this.ownerDao = ownerDao;
        this.invoiceService = invoiceService;
    }

    /**
     * createPdf
     * Creates a Pdf file, later on will need a user id to get the correct data.
     */
    public String createPdf(int id, long fromdate, long enddate) {
        Owner user = ownerDao.findOne(id);
        DateTime fromDate = new DateTime(fromdate);
        DateTime endDate = new DateTime(enddate);

        //TODO generate price here
        double price = 0;

        Invoice invoice = new Invoice(user, new DateTime(), price, fromDate, endDate, 0, "NL");

        String fileName = generator.GenerateInvoicePdf(invoice);
        return fileName;
    }

    public void createPdfFromInvoiceId(int id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice != null) {
            generator.GenerateInvoicePdf(invoice);
        }

    }
}
