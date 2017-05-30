package Project.DAO;

import com.S63B.domain.Entities.Invoice;
import com.S63B.domain.Entities.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Kevin.
 */
public interface InvoiceDao extends CrudRepository<Invoice, Integer> {
    List<Invoice> getByOwner(Owner owner);
}
