package Project.Rest;

import Project.Services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by Nekkyou on 4-4-2017.
 */
@RestController
@RequestMapping("/pdf")
@Controller
public class PdfRest {

	@Autowired
	PdfService service;

	/**
	 * Create a pdf and downloads it.
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> downloadPDFFile()
			throws IOException {

		service.createPdf();

		FileSystemResource resource = new FileSystemResource("factuur.pdf");

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		header.set(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=factuur.pdf");

		return ResponseEntity
				.ok()
				.contentLength(resource.contentLength())
				.headers(header)
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(resource.getInputStream()));
	}
}
