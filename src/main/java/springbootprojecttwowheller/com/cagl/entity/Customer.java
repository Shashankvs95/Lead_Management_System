package springbootprojecttwowheller.com.cagl.entity;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Customer{
	@Id
	@GenericGenerator(name="cust_id",strategy = "springbootprojecttwowheller.com.cagl.entity.CustomID")
	@GeneratedValue(generator ="cust_id")
	private String id;
	private int customerID;
	private String gender;
	private String customerName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate DOB;
	private long primaryMobileNumber;
	private long secondaryMobileNumber;
	private String occupation;
	private String KYCtype1;
	private String KYCnumber1;
	private String addressLine1;
	private String addressLine2;
	private long pincode;
	private String district;
	private String state;
	private String typeOfLoan;
	private String typeOfCustomer;
	private long loanAmount;
	private String remarks;
	private LocalDate createdDate;
	private String kendra_id;
	private String kendraName;
	private String branch_id;
	private String branchName;
	private String employee_id;
	private String status;
	private String created_By_id;
	private String created_By_name;
	@Column(name = "BM_KM")
	private int diff;
	private String newgen_ref_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public LocalDate getDOB() {
		return DOB;
	}
	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}
	public long getPrimaryMobileNumber() {
		return primaryMobileNumber;
	}
	public void setPrimaryMobileNumber(long primaryMobileNumber) {
		this.primaryMobileNumber = primaryMobileNumber;
	}
	public long getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}
	public void setSecondaryMobileNumber(long secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getKYCtype1() {
		return KYCtype1;
	}
	public void setKYCtype1(String kYCtype1) {
		KYCtype1 = kYCtype1;
	}
	public String getKYCnumber1() {
		return KYCnumber1;
	}
	public void setKYCnumber1(String kYCnumber1) {
		KYCnumber1 = kYCnumber1;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTypeOfLoan() {
		return typeOfLoan;
	}
	public void setTypeOfLoan(String typeOfLoan) {
		this.typeOfLoan = typeOfLoan;
	}
	public String getTypeOfCustomer() {
		return typeOfCustomer;
	}
	public void setTypeOfCustomer(String typeOfCustomer) {
		this.typeOfCustomer = typeOfCustomer;
	}
	public long getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public String getKendra_id() {
		return kendra_id;
	}
	public void setKendra_id(String kendra_id) {
		this.kendra_id = kendra_id;
	}
	public String getKendraName() {
		return kendraName;
	}
	public void setKendraName(String kendraName) {
		this.kendraName = kendraName;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreated_By_id() {
		return created_By_id;
	}
	public void setCreated_By_id(String created_By_id) {
		this.created_By_id = created_By_id;
	}
	public String getCreated_By_name() {
		return created_By_name;
	}
	public void setCreated_By_name(String created_By_name) {
		this.created_By_name = created_By_name;
	}
	public int getDiff() {
		return diff;
	}
	public void setDiff(int diff) {
		this.diff = diff;
	}
	public String getNewgen_ref_id() {
		return newgen_ref_id;
	}
	public void setNewgen_ref_id(String newgen_ref_id) {
		this.newgen_ref_id = newgen_ref_id;
	}
	public Customer(String id, int customerID, String gender, String customerName, LocalDate dOB,
			long primaryMobileNumber, long secondaryMobileNumber, String occupation, String kYCtype1, String kYCnumber1,
			String addressLine1, String addressLine2, long pincode, String district, String state, String typeOfLoan,
			String typeOfCustomer, long loanAmount, String remarks, LocalDate createdDate, String kendra_id,
			String kendraName, String branch_id, String branchName, String employee_id, String status,
			String created_By_id, String created_By_name, int diff, String newgen_ref_id) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.gender = gender;
		this.customerName = customerName;
		DOB = dOB;
		this.primaryMobileNumber = primaryMobileNumber;
		this.secondaryMobileNumber = secondaryMobileNumber;
		this.occupation = occupation;
		KYCtype1 = kYCtype1;
		KYCnumber1 = kYCnumber1;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pincode = pincode;
		this.district = district;
		this.state = state;
		this.typeOfLoan = typeOfLoan;
		this.typeOfCustomer = typeOfCustomer;
		this.loanAmount = loanAmount;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.kendra_id = kendra_id;
		this.kendraName = kendraName;
		this.branch_id = branch_id;
		this.branchName = branchName;
		this.employee_id = employee_id;
		this.status = status;
		this.created_By_id = created_By_id;
		this.created_By_name = created_By_name;
		this.diff = diff;
		this.newgen_ref_id = newgen_ref_id;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
