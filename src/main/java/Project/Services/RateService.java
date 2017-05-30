package Project.Services;

import Project.DAO.RateDao;
import com.S63B.domain.Entities.Rate;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Kevin.
 */

@Service
@Transactional
public class RateService {
    private RateDao rateDao;

    @Autowired
    public RateService(RateDao rateDao){
        this.rateDao= rateDao;
    }

    public List<Rate> getAllRates(){
        return Lists.newArrayList(rateDao.findAll());
    }

    private Rate updateRate(Rate updatedRate){
        return rateDao.save(updatedRate);
    }
}
