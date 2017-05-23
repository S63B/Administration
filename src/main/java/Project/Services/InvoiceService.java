package Project.Services;

import Project.DAO.InvoiceDao;
import com.S63B.domain.Entities.Invoice;
import com.S63B.domain.Entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Nekkyou on 16-5-2017.
 */
@Service("invoiceService")
public class InvoiceService {

	private InvoiceDao invoiceDao;

	@Autowired
	private void InvoiceService(InvoiceDao invoiceDao) {
		this.invoiceDao = invoiceDao;
	}

	public List<Invoice> getInvoicesBetweenDate(String userId, long startDate, long endDate) {
		return null;
	}


	public List<Invoice> getInvoicesFromOwner(Owner owner) {
		return invoiceDao.getByOwner(owner);
	}
}
