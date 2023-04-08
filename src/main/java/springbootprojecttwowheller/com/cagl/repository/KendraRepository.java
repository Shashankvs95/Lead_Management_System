package springbootprojecttwowheller.com.cagl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springbootprojecttwowheller.com.cagl.entity.Kendra;

public interface KendraRepository extends JpaRepository<Kendra, String>{

	
	@Query("SELECT c.branch_id FROM Kendra c where c.employee_id=?1")
	String getBranchId(String employee_id);
	
	@Query("SELECT c FROM Kendra c where c.kendraName=?1")
	Kendra getKendra(String kendraName);
}
