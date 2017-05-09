package Project.Services;

import Project.DAO.UserDao;
import Project.Pdf.PdfGenerator;
import com.S63B.domain.Entities.Invoice;
import com.S63B.domain.Entities.Owner;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Nekkyou on 18-4-2017.
 */
@Service("pdfService")
public class PdfService {

	private PdfGenerator generator;
	private UserDao userDao;


	public PdfService() {
	}


	@PostConstruct
	private void init() {
		this.generator = new PdfGenerator();
		this.userDao = new UserDao();
	}

	/**
	 * createPdf
	 * Creates a Pdf file, later on will need a user id to get the correct data.
	 */
	public void createPdf() {
		//Dummy data
		//User user = new User(1, "Tim Daniëls", "Kerkstraat  qweqweasda", "Casteren", false, "Admin", true);

		Owner user = userDao.getUser("");
		Invoice invoice = new Invoice(1, user, new DateTime(), 200.12, new DateTime(), new DateTime(), 0, "NL");

		String fileName = "factuur.pdf";

		generator.GenerateInvoicePdf(invoice);
	}


}
