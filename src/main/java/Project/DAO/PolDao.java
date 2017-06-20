package Project.DAO;

import com.S63B.domain.Entities.Pol;
import com.S63B.domain.Ride;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nekkyou on 9-5-2017.
 */
@Repository
public class PolDao extends BaseDao<Pol> {

	private final Logger logger = LoggerFactory.getLogger(PolDao.class);

	public boolean deletePols(String licensePlate){
		try{
			em.createNamedQuery("Pol.deletePolls", Pol.class).setParameter("licensePlate", licensePlate);
			return true;
		}catch (Exception ex){
			logger.error("Error while executing named query", ex);
			return false;
		}
	}

	public List<Pol> getPols(String licensePlate){
		try{
			return em.createNamedQuery("Pol.getPolls", Pol.class).setParameter("licensePlate", licensePlate).getResultList();
		}catch (Exception ex){
			logger.error("Error while executing named query", ex);
			return null;
		}
	}

	public List<Pol> getPolsBetween(String licensePlate, long startDate, long endDate){
		try{
			return em.createNamedQuery("Pol.getPollsBetween", Pol.class).setParameter("licensePlate", licensePlate).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
		}catch (Exception ex){
			logger.error("Error while executing named query", ex);
			return null;
		}
	}

	public List<Ride> getRides(String licensePlate, long startDate, long endDate){
		try{
			List<Pol> pols = getPolsBetween(licensePlate, startDate, endDate);

			Collections.sort(pols);

			List<Ride> rides = new ArrayList<>();
			Pol lastPol = null;
			Ride currentRide = new Ride();

			for (Pol pol : pols){
				if (!(lastPol == null || pol.getTimestampMillis() - lastPol.getTimestampMillis() < 300000)){
					currentRide = updateRide(currentRide, licensePlate);
					if (currentRide.getPols().size() > 1) {
						rides.add(currentRide);
					}

					currentRide = new Ride();
				}

				currentRide.addPol(pol);
				lastPol = pol;
			}

			currentRide = updateRide(currentRide, licensePlate);
			if (currentRide.getPols().size() > 1) {
				rides.add(currentRide);
			}
			return rides;
		}catch (Exception ex){
			logger.error("Error while getting rides", ex);
			return null;
		}
	}

	public long getDrivenDistance(String licensePlate, long startDate, long endDate) {
		List<Pol> pols = getPolsBetween(licensePlate, startDate, endDate);
		if(pols != null){
			long meters = 0;

			for (int i = 0; i < pols.size() - 1; i++) {
				meters += getDistance(new LatLng(pols.get(i).getLat(), pols.get(i).getLng()), new LatLng(pols.get(i + 1).getLat(), pols.get(i + 1).getLng()));
			}
			return meters;
		}
		return 0;
	}

	public long getDistance(LatLng from, LatLng to){
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDNtmOxKdE2VfxAHO6wTdiqRZMoGN_20cc").setQueryRateLimit(100);
		try {
			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
			DistanceMatrix trix = req
					.origins(from)
					.destinations(to)
					.mode(TravelMode.DRIVING)
					.language("en-EN")
					.await();
			return trix.rows[0].elements[0].distance.inMeters;
		} catch (Exception ex) {
			logger.error("Error while calculating distance", ex);
			return 0;
		}
	}

	public Ride updateRide(Ride ride, String licensePlate){
		if (ride.getPols().size() >= 1){
			ride.setStartDate(ride.getPols().get(0).getTimestampMillis());
			ride.setEndDate(ride.getPols().get(ride.getPols().size() - 1).getTimestampMillis());
		}

		ride.setDistance(this.getDrivenDistance(licensePlate, ride.getStartDate() - 1, ride.getEndDate() + 1));
		return ride;
	}
}
