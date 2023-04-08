package springbootprojecttwowheller.com.cagl.dropdown.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Occupation {
	
	@Id
	private String occupation_id;
	private String occupation_name;
	
	public String getOccupation_id() {
		return occupation_id;
	}
	public void setOccupation_id(String occupation_id) {
		this.occupation_id = occupation_id;
	}
	public String getOccupation_name() {
		return occupation_name;
	}
	public void setOccupation_name(String occupation_name) {
		this.occupation_name = occupation_name;
	}
	
	
	
	

}
