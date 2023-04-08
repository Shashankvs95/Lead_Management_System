package springbootprojecttwowheller.com.cagl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootprojecttwowheller.com.cagl.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
}
