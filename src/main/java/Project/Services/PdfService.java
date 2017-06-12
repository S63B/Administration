package Project.Services;

import Project.DAO.OwnerDao;
import Project.DAO.PolDao;
import Project.DAO.RateDao;
import Project.Pdf.PdfGenerator;
import com.S63B.domain.Entities.*;
import com.S63B.domain.Ride;
import com.google.maps.model.LatLng;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Ellipse2D;
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
    private RateDao rateDao;

    @Autowired
    private PdfService(OwnerDao ownerDao, InvoiceService invoiceService, PolDao polDao, RateDao rateDao) {
        this.generator = new PdfGenerator();
        this.ownerDao = ownerDao;
        this.invoiceService = invoiceService;
        this.polDao = polDao;
        this.rateDao = rateDao;
    }

    /**
     * createPdf
     * Creates a Pdf file, later on will need a user id to get the correct data.
     */
    public String createPdf(int owner_id, long fromdate, long enddate) {
        Owner owner = ownerDao.findOne(owner_id);

        if (owner == null){
            return null;
        }

        DateTime fromDate = new DateTime(fromdate);
        DateTime endDate = new DateTime(enddate);

        double price = calculatePrice(owner, fromDate, endDate);

        Invoice invoice = new Invoice(owner, new DateTime(), price, fromDate, endDate, 0, "NL");

        return generator.GenerateInvoicePdf(invoice);
    }

    public void createPdfFromInvoiceId(int id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice != null) {
            generator.GenerateInvoicePdf(invoice);
        }
    }

    public double calculatePrice(Owner owner, DateTime fromDate, DateTime endDate){
        double price = 0;

        List<Rate> rates = (List<Rate>) rateDao.findAll();
        BidiMap<Integer, Ellipse2D> regions = new DualHashBidiMap<>();

        int a = 0;
        for (Rate rate : rates){
            regions.put(a++, new Ellipse2D.Double(rate.getLat(), rate.getLng(), rate.getRadius(), rate.getRadius()));
        }

        // Calculate the total price
        for (Car_Ownership carOwnership : owner.getOwnedCars()){
            Car car = carOwnership.getCar();
            String licensePlate = car.getLicensePlate().getLicense();

            List<Ride> rides = polDao.getRides(licensePlate, fromDate.getMillis(), endDate.getMillis());

            for (Ride ride : rides){
                List<Pol> pols = ride.getPols();

                for (int i = 0; i < pols.size() - 1; i++) {
                    Pol p1 = pols.get(i);
                    Pol p2 = pols.get(i + 1);

                    long distance = polDao.getDistance(new LatLng(p1.getLat(), p1.getLng()), new LatLng(p2.getLat(), p2.getLng()));

                    Ellipse2D r1 = null;
                    Ellipse2D r2 = null;

                    for (Ellipse2D ellipse2D : regions.values()){
                        if (r1 == null && ellipse2D.contains(p1.getLat(), p1.getLng())){
                            r1 = ellipse2D;
                        }

                        if (r2 == null && ellipse2D.contains(p2.getLat(), p2.getLng())){
                            r2 = ellipse2D;
                        }

                        if (r1 != null && r2 != null){
                            break;
                        }
                    }

                    double pr1 = -1;
                    double pr2 = -1;

                    if (r1 != null){
                        pr1 = rates.get(regions.getKey(r1)).getRate();
                    }

                    if (r2 != null){
                        pr2 = rates.get(regions.getKey(r2)).getRate();
                    }

                    if (pr1 == -1){
                        // TODO: Set pr1 to default value
                        pr1 = 0;
                    }

                    if (pr2 == -1){
                        // TODO: Set pr2 to default value
                        pr2 = 0;
                    }

                    double pr = Math.max(pr1, pr2);

                    // TODO: Verify if this actually makes sense
                    price += pr * distance;
                }
            }
        }

        return price;
    }
}
