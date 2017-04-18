package Project.Rest;

import Project.Services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;

@RestController
@RequestMapping("/invoice")
@Controller
public class InvoiceRest {

    @Autowired
    private InvoiceService service;

    @RequestMapping(method = RequestMethod.GET)
    public Response getInvoice(@RequestParam(value = "id") int id) {
        return Response.status(OK).entity("Test rest method: " + id).build();
    }
}
