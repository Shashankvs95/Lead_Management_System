package springbootprojecttwowheller.com.cagl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springbootprojecttwowheller.com.cagl.entity.customer_eligible;

public interface Customer_eligibleRepository extends JpaRepository<customer_eligible,Integer>{
	

	@Query("select c from customer_eligible c")
	List<customer_eligible> getId();
	
	@Query("select c.customerID from customer_eligible c where c.kendra_id=?1")
	List<Integer> getCustomerIds(String kendra_id);
	
	@Query("select c from customer_eligible c where c.kendra_id=?1")
	List<customer_eligible> getCustomers(String kendra_id);

}
