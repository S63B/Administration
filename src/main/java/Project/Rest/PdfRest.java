package Project.Rest;

import Project.Domain.Invoice;
import Project.Domain.User;
import Project.Pdf.PdfGenerator;
import org.joda.time.DateTime;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

import java.io.IOException;

import static javax.ws.rs.core.Response.Status.OK;

/**
 * Created by Nekkyou on 4-4-2017.
 */
@RestController
@RequestMapping("/pdf")
@Controller
public class PdfRest {


	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> downloadPDFFile()
			throws IOException {
		User user = new User(1, "Tim Daniëls", "Kerkstraat 5", "Casteren", false, "Admin", true);
		Invoice invoice = new Invoice(1, user, new DateTime(), 200.12, new DateTime(), new DateTime(), 0, "NL");



		String fileName = "factuur.pdf";

		PdfGenerator generator = new PdfGenerator();
		generator.GenerateInvoicePdf(invoice);

		FileSystemResource fileSystemResource = new FileSystemResource("E:\\School\\S63B\\RoadPricing\\RoadPricing\\factuur.pdf");

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		header.set(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=" + fileName.replace(" ", "_"));

		return ResponseEntity
				.ok()
				.contentLength(fileSystemResource.contentLength())
				.headers(header)
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(fileSystemResource.getInputStream()));
	}
}
