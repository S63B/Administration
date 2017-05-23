package Project.Services;

import Project.DAO.OwnerDao;
import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Owner;
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
public class OwnerService {
    private OwnerDao ownerDao;
    private CarOwnerService carOwnerService;

    @Autowired
    public OwnerService(OwnerDao ownerDao, CarOwnerService carOwnerService){
        this.ownerDao = ownerDao;
        this.carOwnerService = carOwnerService;
    }

    public List<Owner> getAllOwners(){
        return Lists.newArrayList(ownerDao.findAll());
    }

    public List<Car> getOwnersCars(Owner owner){
        return carOwnerService.getCarsByOwner(owner);
    }

    public Owner getOwner(int ownerId) {
        return ownerDao.findOne(ownerId);
    }
}
