package springbootprojecttwowheller.com.cagl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootprojecttwowheller.com.cagl.entity.GruhaSudharLoan;

public interface GruhaSudharLoanRepository extends JpaRepository<GruhaSudharLoan,String>{
	
	@Query("SELECT c FROM GruhaSudharLoan c where c.branch_id=?1 ORDER BY c.upload_date DESC")
	List<GruhaSudharLoan> getLoans(String branch_id);

}
