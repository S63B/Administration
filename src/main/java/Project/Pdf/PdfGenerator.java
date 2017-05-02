package Project.Pdf;

import com.S63B.domain.Entities.Invoice;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.joda.time.DateTime;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static Project.Pdf.PdfFonts.*;

/**
 * Created by Nekkyou on 4-4-2017.
 */
public class PdfGenerator {
	private Document document;
	private String filename = "factuur";
	private String author = "Overheid";
	private String creator = "FBI";
	private String title = "Factuur voor ";
	private String subject = "Set the subject here";
	private DateTime vandatum;
	private DateTime totdatum;
	private String land = "Nederland";
	private String username = "Tim Daniëls";
	private double prijs = 50.12;
	private String imagePath = "vlag.png";
	private Map<String, String> userData = new LinkedHashMap<>();

	private float imgWidth = 100f;
	private float imgHeight = 66f;

	/**
	 * Generates a PDF from an Invoice.
	 * @param invoice The invoice which contains the data for the PDF
	 */
	public void GenerateInvoicePdf(Invoice invoice) {
		title += invoice.getUser().getName();
		prijs = invoice.getTotalPrice();

		vandatum = invoice.getStartDate();
		totdatum = invoice.getEndDate();

		userData.put("Address", invoice.getUser().getAddress());
		userData.put("Residence", invoice.getUser().getResidence());
		userData.put("Role", invoice.getUser().getRole());
		userData.put("Owned cars", String.valueOf((invoice.getUser().getOwnedCars().size())));

		document = new Document();
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream( filename + ".pdf"));
			document.open();
			setMetadata();

			createTitlePage();
			addContent();

			//Close document
			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the title page for the PDF
	 * @throws DocumentException When there is a problem with the PdfDocument.
	 * @throws IOException When there is a problem with IO.
	 */
	private void createTitlePage() throws DocumentException, IOException {
		document.add(new Paragraph(title, catFont));
		document.add(new Paragraph("Created on: " + new Date().toString(), smallBold));

		document.add(new Paragraph("Van: " + vandatum.toString(), smallBold));
		document.add(new Paragraph("Tot: " + totdatum.toString(), smallBold));
		createUserInfo();

		addPricing();

		//Add line separator.
		LineSeparator ls = new LineSeparator();
		Paragraph p = new Paragraph();
		p.add(new Chunk(ls));
		p.setSpacingAfter(30f);

		document.add(p);

		//Add country flag to corner
		Image img = Image.getInstance(imagePath);
		img.scaleAbsolute(imgWidth, imgHeight);
		img.setAbsolutePosition(PageSize.A4.getWidth() - (imgWidth + 10), PageSize.A4.getHeight() - (imgHeight + 10));
		document.add(img);
	}

	/**
	 * Add the pricing information to the pdf.
	 * @throws DocumentException
	 */
	private void addPricing() throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.setWidthPercentage(100);
		table.addCell(new Phrase("Totaalprijs"));
		table.addCell(new Phrase(String.valueOf(prijs)));
		table.setSpacingBefore(30f);
		document.add(table);
	}

	/**
	 * Add the information from the user to the pdf.
	 * @throws DocumentException
	 */
	private void createUserInfo() throws DocumentException {
		Paragraph userInfo = new Paragraph("User info", smallBold);
		userInfo.setSpacingBefore(30f);
		document.add(userInfo);

		PdfPTable table = new PdfPTable(2);
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.setWidthPercentage(100);

		Iterator it = userData.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			table.addCell(new Phrase(pair.getKey().toString()));
			table.addCell(new Phrase(pair.getValue().toString()));
			it.remove(); // avoids a ConcurrentModificationException
		}
		document.add(table);

		LineSeparator ls = new LineSeparator();
		document.add(new Chunk(ls));
	}

	/**
	 * Add content to the pdf after the title page.
	 * @throws DocumentException
	 */
	private void addContent() throws DocumentException {
		document.newPage();
		document.add(new Paragraph("Ritten", subFont));
		document.add(createRideList());

	}

	/**
	 * Creates a list of Rides and puts them in a table.
	 * @return A table with the rides, each ride has a from and to date and the total amount of km driven.
	 * @throws DocumentException
	 */
	private PdfPTable createRideList() throws DocumentException {
		//Create a table with 3 columns
		PdfPTable table = new PdfPTable(3);

		//Set the border of the table.
		table.getDefaultCell().setBorder(PdfPCell.BOTTOM);
		//Width of table
		table.setWidthPercentage(100);
		//split table columns width in 5, first part is 1/5, second part is 1/5 and third is 3/5.
		table.setWidths(new float[]{1, 1, 3});

		//Add the titles with the smallBold.
		table.addCell(new Phrase("Van datum", PdfFonts.smallBold));
		table.addCell(new Phrase("Tot datum", PdfFonts.smallBold));
		table.addCell(new Phrase("Aantal Km", PdfFonts.smallBold));

		//Loop through the rides and add them to the table.
		for (int i = 0; i < 10; i++) {
			table.addCell(new Phrase(generateRandomdate().toString()));
			table.addCell(new Phrase(generateRandomdate().toString()));
			table.addCell(new Phrase(String.valueOf(generateRandomInt())));
		}


		table.addCell(new Phrase("Totaalprijs"));
		table.addCell(new Phrase(String.valueOf(prijs)));
		//Set space before the table to 30f.
		table.setSpacingBefore(30f);
		return table;
	}

	/**
	 * Generate a random date for test data.
	 * @return A Date between 1 January 1900 and today.
	 */
	private LocalDate generateRandomdate() {
		Random random = new Random();
		int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.now().toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);

		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

		return randomDate;
	}

	/**
	 * Generate a random int between 0 and 100 for test data usage
	 * @return A random int between 0 and 100
	 */
	private int generateRandomInt() {
		Random random = new Random();
		return random.nextInt(100);
	}

	/**
	 * Set the meta data for the pdf document.
	 */
	private void setMetadata() {
		//Attributes
		document.addAuthor(author);
		document.addCreationDate();
		document.addCreator(creator);
		document.addTitle(title);
		document.addSubject(subject);
	}
}
