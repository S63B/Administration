package Project.DAO;

import com.S63B.domain.Entities.Invoice;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nekkyou on 16-5-2017.
 */
public class InvoiceDao extends BaseDao<Invoice> {
	public List<Invoice> getInvoicesFromUser(int userId) {
		Query query = em.createQuery("SELECT i FROM Invoice i WHERE i.user.id = :ownerId")
				.setParameter("ownerId", userId);

		return  new ArrayList<Invoice>(query.getResultList());
	}
}
