package Project.Services;

import Project.Dao.IInvoiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by arjan on 28-3-2017.
 */
@Service("invoiceService")
public class InvoiceService {
    @Autowired
    private IInvoiceDao invoiceDao;

    public InvoiceService() {
    }
}
