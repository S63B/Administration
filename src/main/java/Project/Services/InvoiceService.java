package Project.Services;

import Project.DAO.InvoiceDao;
import Project.DAO.PolDao;
import Project.DAO.RateDao;
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
 * Created by Nekkyou on 16-5-2017.
 */
@Service("invoiceService")
public class InvoiceService {

	private double DEFAULT_RATE = 1;

	private InvoiceDao invoiceDao;
	private RateDao rateDao;
	private PolDao polDao;

	@Autowired
	public InvoiceService(InvoiceDao invoiceDao, RateDao rateDao, PolDao polDao) {
		this.invoiceDao = invoiceDao;
		this.rateDao = rateDao;
		this.polDao = polDao;
	}

	public List<Invoice> getInvoicesFromOwner(Owner owner) {
		return invoiceDao.getByOwner(owner);
	}

	public Invoice createInvoice(Owner owner, DateTime startDate, DateTime endDate, String countryOfOrigin) {
		double price = calculatePrice(owner, startDate, endDate);

		Invoice invoice = new Invoice(owner, new DateTime(), price, startDate, endDate, 0, countryOfOrigin);
		invoiceDao.save(invoice);

		return invoice;
	}

	public Invoice getInvoiceById(int id) {
		return invoiceDao.findOne(id);
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

					double distance = polDao.getDistance(new LatLng(p1.getLat(), p1.getLng()), new LatLng(p2.getLat(), p2.getLng()));

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
						pr1 = DEFAULT_RATE;
					}

					if (pr2 == -1){
						pr2 = DEFAULT_RATE;
					}

					double pr = Math.max(pr1, pr2);

					price += pr * (distance / 1000);
				}
			}
		}

		return (double) Math.round(price * 100) / 100;
	}

}
