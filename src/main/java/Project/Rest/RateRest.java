package Project.Rest;

import Project.Services.RateService;
import com.S63B.domain.Entities.Rate;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kevin.
 */
@RestController
@RequestMapping("/rate")
@CrossOrigin(origins = "*")
public class RateRest {
    RateService rateService;

    @Autowired
    public RateRest(RateService rateService){
        this.rateService = rateService;
    }

    /**
     * Gets all rate regions from the database.
     * @return A response entity containing a list of rates.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Rate>> getAllRates(){
        List<Rate> allRates = rateService.getAllRates();
        HttpStatus status = HttpStatus.OK;

        if(allRates.isEmpty()){
            status = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(allRates, status);
    }

    /**
     * Creates a new rate regions in the database.
     * @param centerLat The latitude of the center point of the new rate region.
     * @param centerLon The longitude of the center point of the new rate region.
     * @param radius The radius of the rate region.
     * @param pricing The rate / price per kilometer in the region.
     * @param startDateString The start date of the rate region.
     * @param endDateString The end date of the rate region.
     * @return A response entity containing the new rate region.
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Rate> create(@RequestParam("centerLat") double centerLat,
                                       @RequestParam("centerLon") double centerLon,
                                       @RequestParam("radius") double radius,
                                       @RequestParam("pricing") double pricing,
                                       @RequestParam("startDate") String startDateString,
                                       @RequestParam("endDate") String endDateString) {
        DateTime startDate = DateTime.parse(startDateString, DateTimeFormat.forPattern("yyyy-MM-dd"));
        DateTime endDate = DateTime.parse(endDateString, DateTimeFormat.forPattern("yyyy-MM-dd"));
        Rate newRate = new Rate(pricing, centerLat, centerLon, radius, startDate, endDate);

        HttpStatus status;
        Rate insertedRate = rateService.create(newRate);
        if (insertedRate == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(insertedRate, status);
    }

    /**
     * Updates an existing rate regions in the database.
     * @param centerLat The updated latitude of the center point of the rate region.
     * @param centerLng The updated longitude of the center point of the rate region.
     * @param radius The updated radius of the rate region.
     * @param pricing The updated rate / pricing per kilometer in the rate region.
     * @param startDateString The updated start date of the rate region.
     * @param endDateString The updated end date of the rate region.
     * @return A response entity containing the updated rate region.
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Rate> update(@RequestParam("rateId") int id,
                                       @RequestParam("centerLat") double centerLat,
                                       @RequestParam("centerLon") double centerLng,
                                       @RequestParam("radius") double radius,
                                       @RequestParam("pricing") double pricing,
                                       @RequestParam("startDate") String startDateString,
                                       @RequestParam("endDate") String endDateString) {
        DateTime startDate = DateTime.parse(startDateString, DateTimeFormat.forPattern("yyyy-MM-dd"));
        DateTime endDate = DateTime.parse(endDateString, DateTimeFormat.forPattern("yyyy-MM-dd"));

        Rate existingRate = rateService.findById(id);
        existingRate.setLat(centerLat);
        existingRate.setLng(centerLng);
        existingRate.setRadius(radius);
        existingRate.setRate(pricing);
        existingRate.setStartDate(startDate);
        existingRate.setEndDate(endDate);

        HttpStatus status;
        Rate updatedRate = rateService.update(existingRate);
        if (updatedRate == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(updatedRate, status);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@RequestParam("rateRegionId") int rateId) {
        Rate rateToDelete = this.rateService.findById(rateId);

        HttpStatus status;
        boolean deleted = this.rateService.delete(rateToDelete);
        if (deleted) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(deleted, status);
    }
}
