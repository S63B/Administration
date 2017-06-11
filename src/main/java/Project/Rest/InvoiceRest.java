package Project.Rest;

import Project.Services.InvoiceService;
import Project.Services.OwnerService;
import com.S63B.domain.Entities.Invoice;
import com.S63B.domain.Entities.Owner;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Nekkyou on 16-5-2017.
 */
@RestController
@Controller
@CrossOrigin(origins = "*")
public class InvoiceRest {

	private OwnerService ownerService;
	private final InvoiceService invoiceService;

	@Autowired
	public InvoiceRest(InvoiceService invoiceService, OwnerService ownerService) {
		this.invoiceService = invoiceService;
		this.ownerService = ownerService;
	}


	@RequestMapping(value = "/invoice", method = RequestMethod.GET)
	public Response getInvoicesBetweenDates(@RequestParam(value = "owner") String userId,
											@RequestParam(value = "start_date") long startdate,
											@RequestParam(value = "end_date") long enddate) {
		List<Invoice> invoices = invoiceService.getInvoicesBetweenDate(userId, startdate, enddate);

		return Response.ok().entity(invoices).build();
	}

	@RequestMapping(value = "/invoices", method = RequestMethod.GET)
	public Response getInvoicesFromOwner(@RequestParam(value = "owner") int userId) {

		Owner foundOwner = ownerService.getOwner(userId);
		List<Invoice> invoices = invoiceService.getInvoicesFromOwner(foundOwner);

		return Response.ok().entity(invoices).build();
	}

	@RequestMapping(value = "/invoice/generate", method = RequestMethod.GET)
	public Response generateInvoice(@RequestParam(value = "ownerId") int id,
									@RequestParam(value = "start_date") long startdate,
									@RequestParam(value = "end_date") long enddate) {
		Owner owner = ownerService.getOwner(id);
		DateTime fromDate = new DateTime(startdate);
		DateTime endDate = new DateTime(enddate);

		//TODO generate price
		double price = 50;
		Invoice invoice = invoiceService.createInvoice(owner, price, fromDate, endDate, "NETHERLANDS");

		return Response.ok().entity(invoice).build();
	}

}
