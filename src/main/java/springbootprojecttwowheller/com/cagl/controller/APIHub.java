package springbootprojecttwowheller.com.cagl.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springbootprojecttwowheller.com.cagl.entity.Customer;
import springbootprojecttwowheller.com.cagl.entity.Kendra;
import springbootprojecttwowheller.com.cagl.entity.customer_eligible;
import springbootprojecttwowheller.com.cagl.repository.CustomerRepository;
import springbootprojecttwowheller.com.cagl.repository.Customer_eligibleRepository;
import springbootprojecttwowheller.com.cagl.repository.KendraRepository;

@RestController
//@RequestMapping("/LMS")
public class APIHub {

	@Autowired
	private Customer_eligibleRepository customer_eligibleRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private KendraRepository  kendraRepository;

	@PostMapping(path = "/LMS/APIHub")
	public  String getCustomerByID(@RequestParam(value = "id")int id,@RequestParam(value = "ltyp")String ltyp) {

		JSONObject jsonpObject=new JSONObject();
		if(customerRepository.getcustomerById(id)!=null) {
		List<Customer> customers=customerRepository.getcustomerById(id);
		customer_eligible customer_eligible=customer_eligibleRepository.findById(id).get();
		if(customers != null) {
			for(Customer customer:customers) {
				if(ltyp.equals(customer.getTypeOfLoan()) && (customer.getStatus().equals("WIP") ||customer.getStatus().equals("PENDING"))) {
					jsonpObject.put("error ","User Already Has Submitted Loan for this Product");
					return jsonpObject.toString();
				}
			}
		}

		jsonpObject.put("customerID", customer_eligible.getCustomerID());
		jsonpObject.put("gender",customer_eligible.getGender());
		jsonpObject.put("customerName",customer_eligible.getCustomerName());
		jsonpObject.put("DOB",customer_eligible.getDOB());
		jsonpObject.put("primaryMobileNumber",customer_eligible.getPrimaryMobileNumber());
		jsonpObject.put("secondaryMobileNumber",customer_eligible.getSecondaryMobileNumber());
		jsonpObject.put("occupation",customer_eligible.getOccupation());
		jsonpObject.put("KYCtype1",customer_eligible.getKYCtype1());
		jsonpObject.put("KYCnumber1",customer_eligible.getKYCnumber1());
		jsonpObject.put("addressLine1",customer_eligible.getAddressLine1());
		jsonpObject.put("addressLine2",customer_eligible.getAddressLine2());
		jsonpObject.put("pincode",customer_eligible.getPincode());
		jsonpObject.put("district",customer_eligible.getDistrict());
		jsonpObject.put("state",customer_eligible.getState());
		jsonpObject.put("typeOfLoan",ltyp);
		jsonpObject.put("typeOfCustomer",customer_eligible.getTypeOfCustomer());
		jsonpObject.put("loanAmount",customer_eligible.getLoanAmount());
		jsonpObject.put("remarks",customer_eligible.getRemarks());
		//jsonpObject.put("createdDate",customer_eligible.getCreatedDate());
		jsonpObject.put("status",customer_eligible.getStatus());
		jsonpObject.put("diff",customer_eligible.getDiff());
		jsonpObject.put("err","NA");
		return jsonpObject.toString();
		}
		else {
		jsonpObject.put("error ","User does not have valid fields");
		return jsonpObject.toString();
		}
	}




		@PostMapping(path = "/LMS/APIHub/kendra")
		public  String getCustomerByKendra(@RequestParam(value = "kendraname")String kendraname) {
			List<customer_eligible> cust=new ArrayList<customer_eligible>();
			List<Integer> ids=new ArrayList<Integer>();
			List<Kendra> kendras=	kendraRepository.findAll();
			for(Kendra kendra:kendras) {
				if(kendra.getKendraName().equals(kendraname)) {
					ids=customer_eligibleRepository.getCustomerIds(kendra.getKendraId());
				}
			}
			for(customer_eligible custo:cust) {
				ids.add(custo.getCustomerID());
			}
			String listString = ids.toString();
			return listString;
		}


	}
