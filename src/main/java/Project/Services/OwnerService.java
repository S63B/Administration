package Project.Services;

import Project.DAO.OwnerDao;
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

    @Autowired
    public OwnerService(OwnerDao ownerDao){
        this.ownerDao = ownerDao;
    }

    public List<Owner> getAllOwners(){
        return Lists.newArrayList(ownerDao.findAll());
    }
}
