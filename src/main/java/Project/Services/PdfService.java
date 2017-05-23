package Project.Services;

import Project.DAO.OwnerDao;
import Project.Pdf.PdfGenerator;
import com.S63B.domain.Entities.Invoice;
import com.S63B.domain.Entities.Owner;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Created by Nekkyou on 18-4-2017.
 */
@Service("pdfService")
public class PdfService {

	private PdfGenerator generator;
	private OwnerService ownerService;
	private InvoiceService invoiceService;


	public PdfService() {
	}

	@Autowired
	private void PdfService(OwnerService ownerService, InvoiceService invoiceService) {
		this.generator = new PdfGenerator();
		this.ownerService = ownerService;
		this.invoiceService = invoiceService;
	}

	/**
	 * createPdf
	 * Creates a Pdf file, later on will need a user id to get the correct data.
	 */
	public String createPdf(int id, long fromdate, long enddate) {
		Owner owner = ownerService.getOwner(id);

		DateTime fromDate = new DateTime(fromdate);
		DateTime endDate = new DateTime(enddate);
		//TODO change when domain has changed.
		//Invoice invoice = new Invoice(1, owner, new DateTime(), 200.12, fromDate, endDate, 0, "NL");
		Invoice invoice = invoiceService.createInvoice(owner, 200.12, fromDate, endDate, "NL");
		return generator.GenerateInvoicePdf(invoice);
	}

	public void createPdfFromInvoiceId(int id) {
		Invoice invoice = invoiceService.getInvoiceById(id);
		if (invoice != null) {
			generator.GenerateInvoicePdf(invoice);
		}

	}


}
