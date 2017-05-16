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
	public String createPdf(int userId, long startDate, long endDate) {
		Owner user = userDao.getUserById(userId);

		if (user == null) {
			throw new NullPointerException("There does not exist a user with id: " + userId);
		}

		Invoice invoice = new Invoice(1, user, new DateTime(), 200.12, new DateTime(startDate), new DateTime(endDate) , 0, "NL");
		String filename = generator.GenerateInvoicePdf(invoice);

		//Return the file name
		return filename;
	}


}
