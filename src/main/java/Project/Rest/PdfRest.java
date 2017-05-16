package Project.Rest;

import Project.Services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Nekkyou on 4-4-2017.
 */
@RestController
@Controller
@CrossOrigin(origins = "*")
public class PdfRest {

	@Autowired
	PdfService service;

	/**
	 * Create a pdf and downloads it.
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/pdf", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> downloadPDFFile(@RequestParam("user") int id,
															   @RequestParam("start_date") long startDate,
															   @RequestParam("end_date") long endDate)
			throws IOException {

		String filename = service.createPdf(id, startDate, endDate);

		FileSystemResource resource = new FileSystemResource(filename);

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		header.set(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=" + filename);

		return ResponseEntity
				.ok()
				.contentLength(resource.contentLength())
				.headers(header)
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(resource.getInputStream()));
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> downloadPDFFileById(@RequestParam("file") String fileName)
			throws IOException {
		fileName += ".pdf";

		FileSystemResource resource = new FileSystemResource(fileName);

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		header.set(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=" + fileName);

		return ResponseEntity
				.ok()
				.contentLength(resource.contentLength())
				.headers(header)
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(resource.getInputStream()));
	}
}
