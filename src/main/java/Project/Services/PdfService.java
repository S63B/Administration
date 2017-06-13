package Project.Services;

import Project.DAO.OwnerDao;
import Project.DAO.PolDao;
import Project.DAO.RateDao;
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
    private PolDao polDao;
    private RateDao rateDao;

    @Autowired
    private PdfService(OwnerDao ownerDao, InvoiceService invoiceService, PolDao polDao, RateDao rateDao) {
        this.generator = new PdfGenerator();
        this.ownerDao = ownerDao;
        this.invoiceService = invoiceService;
        this.polDao = polDao;
        this.rateDao = rateDao;
    }

    /**
     * createPdf
     * Creates a Pdf file, later on will need a user id to get the correct data.
     */
    public String createPdf(int owner_id, long fromdate, long enddate) {
        Owner owner = ownerDao.findOne(owner_id);

        if (owner == null){
            return null;
        }

        DateTime fromDate = new DateTime(fromdate);
        DateTime endDate = new DateTime(enddate);

        Invoice invoice = invoiceService.createInvoice(owner, fromDate, endDate, "NETHERLANDS");

        return generator.GenerateInvoicePdf(invoice);
    }

    public void createPdfFromInvoiceId(int id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice != null) {
            generator.GenerateInvoicePdf(invoice);
        }
    }

}
