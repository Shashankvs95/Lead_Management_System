package springbootprojecttwowheller.com.cagl.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GruhaSudharLoan {
	
	@Id
	private long customer_id;
	private String member_name;
	private String branch_id;
	private String branch_name;
	private String area_name;
	private String region_name;
	private long kendra_id;
	private String kendra_name;
	private String kendra_manager_id;
	private String kendra_manager_name;
	private double indicative_eligibile_amount;
	private String meeting_day;
	private String Upload_by_id;
	private LocalDate upload_date;
	
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public long getKendra_id() {
		return kendra_id;
	}
	public void setKendra_id(long kendra_id) {
		this.kendra_id = kendra_id;
	}
	public String getKendra_manager_id() {
		return kendra_manager_id;
	}
	public void setKendra_manager_id(String kendra_manager_id) {
		this.kendra_manager_id = kendra_manager_id;
	}
	public String getKendra_manager_name() {
		return kendra_manager_name;
	}
	public void setKendra_manager_name(String kendra_manager_name) {
		this.kendra_manager_name = kendra_manager_name;
	}
	public String getKendra_name() {
		return kendra_name;
	}
	public void setKendra_name(String kendra_name) {
		this.kendra_name = kendra_name;
	}
	public String getMeeting_day() {
		return meeting_day;
	}
	public void setMeeting_day(String meeting_day) {
		this.meeting_day = meeting_day;
	}
	public String getUpload_by_id() {
		return Upload_by_id;
	}
	public void setUpload_by_id(String upload_by_id) {
		Upload_by_id = upload_by_id;
	}
	public LocalDate getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(LocalDate upload_date) {
		this.upload_date = upload_date;
	}
	public double getIndicative_eligibile_amount() {
		return indicative_eligibile_amount;
	}
	public void setIndicative_eligibile_amount(double indicative_eligibile_amount) {
		this.indicative_eligibile_amount = indicative_eligibile_amount;
	}
	
	
	

}
