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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Rate>> getAllRates(){
        List<Rate> allRates = rateService.getAllRates();
        HttpStatus status = HttpStatus.OK;

        if(allRates.isEmpty()){
            status = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(allRates, status);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Rate> create(@RequestParam("centerLat") double centerLat,
                                       @RequestParam("centerLon") double centerLon,
                                       @RequestParam("radius") double radius,
                                       @RequestParam("pricing") double pricing,
                                       @RequestParam("startDate") String startDateString,
                                       @RequestParam("endDate") String endDateString) {
        DateTime startDate = DateTime.parse(startDateString, DateTimeFormat.forPattern("YYYY-MM-DD"));
        DateTime endDate = DateTime.parse(endDateString, DateTimeFormat.forPattern("YYYY-MM-DD"));
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

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Rate> update(@RequestParam("rateId") int id,
                                       @RequestParam("centerLat") double centerLat,
                                       @RequestParam("centerLon") double centerLng,
                                       @RequestParam("radius") double radius,
                                       @RequestParam("pricing") double rate,
                                       @RequestParam("startDate") String startDateString,
                                       @RequestParam("endDate") String endDateString) {
        DateTime startDate = DateTime.parse(startDateString, DateTimeFormat.forPattern("YYYY-MM-DD"));
        DateTime endDate = DateTime.parse(endDateString, DateTimeFormat.forPattern("YYYY-MM-DD"));

        Rate existingRate = rateService.findById(id);
        existingRate.setLat(centerLat);
        existingRate.setLng(centerLng);
        existingRate.setRadius(radius);
        existingRate.setRate(rate);
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
}
