package springbootprojecttwowheller.com.cagl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootprojecttwowheller.com.cagl.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer,String>{


	@Query("SELECT c.branchName,c.kendraName,c.status,count(c.customerID) FROM Customer c where c.branch_id=?1 and diff=2 group by c.kendra_id,c.status order by c.kendraName")
	 List<String> getbm(String branch_id);

	@Query("SELECT c.status,count(c.customerID) FROM Customer c where branch_id=?1 and diff=1 group by status")
	List<String> getid(String branch_id);

	@Query("SELECT c.branchName,c.kendraName,c.status,count(c.customerID) FROM Customer c where c.employee_id=?1 group by c.kendra_id,c.status order by c.kendraName")
	List<String> getkm(String employee_id);

	@Query("SELECT c FROM Customer c where c.employee_id=?1")
	List<Customer> getcustomers(String employee_id);

	@Query("SELECT c FROM Customer c where c.branch_id=?1 and diff=2")
	List<Customer> getcustomersByKendra(String branch_id);

	@Query("SELECT c FROM Customer c where c.branch_id=?1 and c.diff=1 ORDER BY c.createdDate DESC")
	List<Customer> getcustomersByBranch(String branch_id);

	@Query("SELECT c FROM Customer c where c.customerID=?1")
	List<Customer> getcustomerById(int customerID);

	//	@Query("SELECT c.status,count(c.customerID) FROM Customer c where branchId=?1 and diff=1 group by status")
	//	List<String> getid(String branch_id);
}
