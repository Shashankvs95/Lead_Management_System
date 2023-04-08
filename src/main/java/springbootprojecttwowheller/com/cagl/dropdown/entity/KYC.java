package springbootprojecttwowheller.com.cagl.dropdown.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KYC {
	
	@Id
	private String kyc_id;
	private String kyc_name;
	
	public String getKyc_id() {
		return kyc_id;
	}
	public void setKyc_id(String kyc_id) {
		this.kyc_id = kyc_id;
	}
	public String getKyc_name() {
		return kyc_name;
	}
	public void setKyc_name(String kyc_name) {
		this.kyc_name = kyc_name;
	}
	
	 private void getbm(String branch_id){
		 
	 }
	

}
