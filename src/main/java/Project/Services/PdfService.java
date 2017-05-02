package Project.Services;

import Project.Domain.Invoice;
import Project.Domain.User;
import Project.Pdf.PdfGenerator;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Nekkyou on 18-4-2017.
 */
@Service("pdfService")
public class PdfService {

	private PdfGenerator generator;

	public PdfService() {
	}


	@PostConstruct
	private void init() {
		this.generator = new PdfGenerator();
	}

	/**
	 * createPdf
	 * Creates a Pdf file, later on will need a user id to get the correct data.
	 */
	public void createPdf() {
		//Dummy data
		User user = new User(1, "Tim Daniëls", "Kerkstraat  qweqweasda", "Casteren", false, "Admin", true);
		Invoice invoice = new Invoice(1, user, new DateTime(), 200.12, new DateTime(), new DateTime(), 0, "NL");

		String fileName = "factuur.pdf";

		generator.GenerateInvoicePdf(invoice);
	}


}
