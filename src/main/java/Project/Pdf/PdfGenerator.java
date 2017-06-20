package Project.Pdf;

import Project.DAO.PolDao;
import com.S63B.domain.Entities.*;
import com.S63B.domain.Ride;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static Project.Pdf.PdfFonts.*;

/**
 * Created by Nekkyou on 4-4-2017.
 */
public class PdfGenerator {
	private final Logger logger = LoggerFactory.getLogger(PdfGenerator.class);

	private PolDao polDao;

	private Document document;
	private String filename = "";
	private String title;
	private DateTime vandatum;
	private DateTime totdatum;
	private double prijs;
	private String imagePath;
	private Map<String, String> userData = new LinkedHashMap<>();

	private float imgWidth = 100f;
	private float imgHeight = 66f;

	public PdfGenerator() {
		polDao = new PolDao();
	}

	/**
	 * Generates a PDF from an Invoice.
	 * @param invoice The invoice which contains the data for the PDF
	 */
	public String GenerateInvoicePdf(Invoice invoice) {
		filename = String.valueOf(invoice.getId()) + ".pdf";


		title = "Factuur voor " + invoice.getOwner().getName();
		prijs = invoice.getTotalPrice();
		imagePath = invoice.getCountryOfOrigin() + ".png";

		vandatum = invoice.getStartDate();
		totdatum = invoice.getEndDate();

		userData.put("Adres", invoice.getOwner().getAddress());
		userData.put("Woonplaats", invoice.getOwner().getResidence());
		userData.put("Rol", invoice.getOwner().getRole());
		userData.put("Auto's", String.valueOf((invoice.getOwner().getOwnedCars().size())));

		document = new Document();

		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.open();
			setMetadata();

			createTitlePage();
			addContent(invoice.getOwner());

			//Close document
			document.close();
			writer.close();
		} catch (Exception ex) {
			logger.error("Error while writing document to file", ex);
		}

		return filename;
	}

	/**
	 * Create the title page for the PDF
	 * @throws DocumentException When there is a problem with the PdfDocument.
	 * @throws IOException When there is a problem with IO.
	 */
	private void createTitlePage() throws DocumentException, IOException {
		document.add(new Paragraph(title, catFont));
		document.add(new Paragraph("Created on: " + new DateTime().toString("dd/MM/yyyy"), smallBold));

		document.add(new Paragraph("Van: " + vandatum.toString("dd/MM/yyyy"), smallBold));
		document.add(new Paragraph("Tot: " + totdatum.toString("dd/MM/yyyy"), smallBold));
		createUserInfo();

		addPricing();

		//Add line separator.
		LineSeparator ls = new LineSeparator();
		Paragraph p = new Paragraph();
		p.add(new Chunk(ls));
		p.setSpacingAfter(30f);

		document.add(p);

		//Add country flag to corner
		File imageFile = new File(imagePath);
		if (imageFile.exists()) {
			Image img = Image.getInstance(imagePath);
			img.scaleAbsolute(imgWidth, imgHeight);
			img.setAbsolutePosition(PageSize.A4.getWidth() - (imgWidth + 10), PageSize.A4.getHeight() - (imgHeight + 10));
			document.add(img);
		}
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
		table.addCell(new Phrase("€" + String.valueOf(prijs)));
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
	private void addContent(Owner owner) throws DocumentException {
		document.newPage();
		document.add(new Paragraph("Ritten", subFont));
		document.add(createRideList(owner));

	}

	/**
	 * Creates a list of Rides and puts them in a table.
	 * @return A table with the rides, each ride has a from and to date and the total amount of km driven.
	 * @throws DocumentException
	 */
	private PdfPTable createRideList(Owner owner) throws DocumentException {
		//Create a table with 3 columns
		PdfPTable table = new PdfPTable(4);

		//Set the border of the table.
		table.getDefaultCell().setBorder(PdfPCell.BOTTOM);
		//Width of table
		table.setWidthPercentage(100);
		//split table columns width in 5, first part is 1/5, second part is 1/5 and third is 3/5.
		table.setWidths(new float[]{1, 2, 2, 1});

		//Add the titles with the smallBold.
		table.addCell(new Phrase("Auto", PdfFonts.smallBold));
		table.addCell(new Phrase("Van datum", PdfFonts.smallBold));
		table.addCell(new Phrase("Tot datum", PdfFonts.smallBold));
		table.addCell(new Phrase("Aantal Km", PdfFonts.smallBold));


		//Get each car from the owner
		List<Car_Ownership> car_ownerships = owner.getOwnedCars();
		ArrayList<Car> cars = new ArrayList<>();
		for(Car_Ownership co : car_ownerships) {
			cars.add(co.getCar());
		}

		//Go through each Car.
		for (Car c : cars) {
			LicensePlate lp = c.getLicensePlate();
			//Get rides with license plate and loop through it and add each entry to a list.
			long fromMillis = vandatum.getMillis();
			long endMillis = totdatum.getMillis();

			List<Ride> rides = polDao.getRides(lp.getLicense(), fromMillis, endMillis);
			for(Ride r: rides) {
				table.addCell(new Phrase(c.getLicensePlate().getLicense()));
				table.addCell(new Phrase(new DateTime(r.getStartDate()).toString("dd/MM/yyyy HH:mm:ss")));
				table.addCell(new Phrase(new DateTime(r.getEndDate()).toString("dd/MM/yyyy HH:mm:ss")));
				double kms = (double)r.getDistance() / 1000;
				table.addCell(new Phrase(String.valueOf(kms)));
			}
		}
		//Set space before the table to 30f.
		table.setSpacingBefore(30f);
		return table;
	}

	/**
	 * Set the meta data for the pdf document.
	 */
	private void setMetadata() {
		//Attributes
		document.addAuthor("Administration application");
		document.addCreationDate();
		document.addCreator("Administration application");
		document.addTitle("Factuur");
		document.addSubject("Factuur");
	}
}
