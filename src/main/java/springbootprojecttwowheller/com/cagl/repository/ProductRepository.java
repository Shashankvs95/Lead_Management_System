package springbootprojecttwowheller.com.cagl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootprojecttwowheller.com.cagl.dropdown.entity.KYC;
import springbootprojecttwowheller.com.cagl.dropdown.entity.Occupation;
import springbootprojecttwowheller.com.cagl.dropdown.entity.State;
import springbootprojecttwowheller.com.cagl.entity.Product;

public interface ProductRepository extends JpaRepository<Product,String>{
	
	@Query("SELECT c FROM Occupation c")
	List<Occupation> getOccupations();
	
	@Query("SELECT c FROM KYC c")
	List<KYC> getKYC();
	
	@Query("SELECT c FROM State c")
	List<State> getStates();
	
	
}
