package Project.Services;

import Project.DAO.InvoiceDao;
import com.S63B.domain.Entities.Invoice;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Nekkyou on 16-5-2017.
 */
@Service("invoiceService")
public class InvoiceService {

	private InvoiceDao invoiceDao;

	@PostConstruct
	private void init() {
		invoiceDao = new InvoiceDao();
	}

	public List<Invoice> getInvoicesBetweenDate(String userId, long startDate, long endDate) {
		return null;
	}


	public List<Invoice> getInvoicesFromUser(int userId) {
		return invoiceDao.getInvoicesFromUser(userId);
	}
}
