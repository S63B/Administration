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


    public PdfService() {
    }

    @Autowired
    private void PdfService(OwnerDao ownerDao) {
        this.generator = new PdfGenerator();
        this.ownerDao = ownerDao;
    }

    /**
     * createPdf
     * Creates a Pdf file, later on will need a user id to get the correct data.
     */
    public String createPdf(int id, long fromdate, long endDate) {
        //Dummy data
        //User user = new User(1, "Tim Daniëls", "Kerkstraat  qweqweasda", "Casteren", false, "Admin", true);

        Owner user = ownerDao.findOne(1);
        DateTime fromDate = new DateTime();
        fromDate.minusDays(2);
        Invoice invoice = new Invoice(user, new DateTime(), 200.12, fromDate, new DateTime(), 0, "NL");

        String fileName = "factuur.pdf";

        generator.GenerateInvoicePdf(invoice);
        return fileName;
    }
}
