package Project.Rest;

import Project.Services.InvoiceService;
import com.S63B.domain.Entities.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Nekkyou on 16-5-2017.
 */
@RestController
@Controller
public class InvoiceRest {

	@Autowired
	private InvoiceService invoiceService;

	@RequestMapping(value = "/invoice", method = RequestMethod.GET)
	public Response getInvoicesBetweenDates(@RequestParam(value = "user") String userId,
											@RequestParam(value = "start_date") long startDate,
											@RequestParam(value = "end_date") long endDate) {
		List<Invoice> invoices = invoiceService.getInvoicesBetweenDate(userId, startDate, endDate);

		return null;
	}

	@RequestMapping(value = "/invoices", method = RequestMethod.GET)
	public Response getInvoicesBetweenDates(@RequestParam(value = "user") int userId) {
		List<Invoice> invoices = invoiceService.getInvoicesFromUser(userId);

		return Response.ok().entity(invoices).build();
	}

}
