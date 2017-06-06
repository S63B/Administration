package Project.Rest;

import Project.Services.RateService;
import com.S63B.domain.Entities.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kevin.
 */
@RestController
@RequestMapping("/rate")
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Rate> create(@RequestParam("centerLat") double centerLat,
                                       @RequestParam("centerLon") double centerLon,
                                       @RequestParam("radius") double radius,
                                       @RequestParam("pricing") double pricing) {
        Rate newRate = new Rate();
        Rate insertedRate = rateService.create(newRate);
        HttpStatus status;

        if (insertedRate == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(insertedRate, status);
    }
}
