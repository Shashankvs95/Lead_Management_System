package springbootprojecttwowheller.com.cagl.entity;
import javax.persistence.Entity;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Kendra 
{
	@Id
	private String kendraId;
	private String kendraName;
	private String branch_id;
	private String employee_id;
	
	

	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public Kendra() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getKendraId() {
		return kendraId;
	}

	public void setKendraId(String kendraId) {
		this.kendraId = kendraId;
	}

	public String getKendraName() {
		return kendraName;
	}

	public void setKendraName(String kendraName) {
		this.kendraName = kendraName;
	}

}
