package springbootprojecttwowheller.com.cagl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee{

	@Id
	private String employeeId;
	private String name;
	private String role;
	private String branch_id;

	
	public String getBranch_id() {
		return branch_id;
	}


	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getRole() {
		return role;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
