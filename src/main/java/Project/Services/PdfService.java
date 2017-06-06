package Project.Services;

import Project.DAO.OwnerDao;
import Project.DAO.PolDao;
import Project.Pdf.PdfGenerator;
import com.S63B.domain.Entities.*;
import com.S63B.domain.Ride;
import com.google.maps.model.LatLng;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Nekkyou on 18-4-2017.
 */
@Service("pdfService")
public class PdfService {

    private PdfGenerator generator;
    private OwnerDao ownerDao;
    private InvoiceService invoiceService;
    private PolDao polDao;


    public PdfService() {
    }

    @Autowired
    private void PdfService(OwnerDao ownerDao, InvoiceService invoiceService, PolDao polDao) {
        this.generator = new PdfGenerator();
        this.ownerDao = ownerDao;
        this.invoiceService = invoiceService;
        this.polDao = polDao;
    }

    /**
     * createPdf
     * Creates a Pdf file, later on will need a user id to get the correct data.
     */
    public String createPdf(int id, long fromdate, long enddate) {
        Owner user = ownerDao.findOne(id);
        DateTime fromDate = new DateTime(fromdate);
        DateTime endDate = new DateTime(enddate);

        // Calculate the total price
        for (Car_Ownership carOwnership : user.getOwnedCars()){
            Car car = carOwnership.getCar();
            String licensePlate = car.getLicensePlate().getLicense();

            List<Ride> rides = polDao.getRides(licensePlate, fromDate.getMillis(), endDate.getMillis());

            for (Ride ride : rides){
                List<Pol> pols = ride.getPols();

                // TODO: Get regions

                for (int i = 0; i < pols.size() - 1; i++) {
                    long distance = polDao.getDistance(new LatLng(pols.get(i).getLat(), pols.get(i).getLng()), new LatLng(pols.get(i + 1).getLat(), pols.get(i + 1).getLng()));

                    // TODO: Multiply distance with region rate?
                }
            }
        }
        double price = 0;

        Invoice invoice = new Invoice(user, new DateTime(), price, fromDate, endDate, 0, "NL");

        String fileName = generator.GenerateInvoicePdf(invoice);
        return fileName;
    }

    public void createPdfFromInvoiceId(int id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice != null) {
            generator.GenerateInvoicePdf(invoice);
        }

    }
}
