package Project.Pdf;

import Project.Domain.Invoice;
import com.itextpdf.text.*;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.joda.time.DateTime;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

import static Project.Pdf.PdfFonts.catFont;
import static Project.Pdf.PdfFonts.smallBold;
import static Project.Pdf.PdfFonts.subFont;

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

	public void GenerateInvoicePdf() {
		vandatum = DateTime.parse("01.01.2017");
		totdatum = DateTime.parse("31.01.2017");

		userData.put("CarTracker", "123");
		userData.put("Hardware", "123456");
		userData.put("Identification Method", "GpsV01");
		userData.put("Car category", "Old-Timer");

		title += username;

		document = new Document();
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream( filename + ".pdf"));
			document.open();
			setMetadata();

			createTitlePage();
			//addContent();

			document.close();
			writer.close();
		} catch (DocumentException e)
		{
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void GenerateInvoicePdf(Invoice invoice) {
		title += invoice.getUser().getName();
		//filename = "factuur" + invoice.getUser().getName();
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

			//Close document
			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void createTitlePage() throws DocumentException, IOException {
		setPdfHeader();
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

	private void setPdfHeader() {

	}

	private void addPricing() throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.setWidthPercentage(100);
		table.addCell(new Phrase("Totaalprijs"));
		table.addCell(new Phrase(String.valueOf(prijs)));
		table.setSpacingBefore(30f);
		document.add(table);
	}

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

	private void addContent() throws DocumentException {
		document.add(new Paragraph("Ritten", subFont));
		document.add(createList());

	}

	private List createList() {
		List unorderedList = new List(List.UNORDERED);
		for (int i = 0; i < 10; i++) {
			unorderedList.add(new ListItem("Item " + i));
		}
		return unorderedList;
	}

	private void setMetadata() {
		//Attributes
		document.addAuthor(author);
		document.addCreationDate();
		document.addCreator(creator);
		document.addTitle(title);
		document.addSubject(subject);
	}
}
