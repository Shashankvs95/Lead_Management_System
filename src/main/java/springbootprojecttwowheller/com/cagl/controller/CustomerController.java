package springbootprojecttwowheller.com.cagl.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import springbootprojecttwowheller.com.cagl.dropdown.entity.KYC;
import springbootprojecttwowheller.com.cagl.dropdown.entity.Occupation;
import springbootprojecttwowheller.com.cagl.dropdown.entity.State;
import springbootprojecttwowheller.com.cagl.entity.Branch;
import springbootprojecttwowheller.com.cagl.entity.Customer;
import springbootprojecttwowheller.com.cagl.entity.Kendra;
import springbootprojecttwowheller.com.cagl.entity.Product;
import springbootprojecttwowheller.com.cagl.entity.customer_eligible;
import springbootprojecttwowheller.com.cagl.repository.BranchRepository;
import springbootprojecttwowheller.com.cagl.repository.CustomerRepository;
import springbootprojecttwowheller.com.cagl.repository.KendraRepository;
import springbootprojecttwowheller.com.cagl.repository.ProductRepository;

@Controller
public class CustomerController {
	
	@Autowired
	private ProductRepository productRepository;
																																		
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private KendraRepository kendraRepository;

	@GetMapping("/LMS/customers/add")
	public String createCustomer(HttpServletRequest request, Model model) {
		Customer customer = new Customer();
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		String userId=(String)session.getAttribute("userid");
		List<Kendra> kendras=kendraRepository.findAll();
		List<Kendra> kendrass=new ArrayList<Kendra>();
		for(Kendra kendra:kendras) {
			if(kendra.getEmployee_id().equals(userId)) {
				kendrass.add(kendra);
			}
		}
		if(role!=null) {
			if (role.equalsIgnoreCase("KM")) {
				Branch branch =(Branch)session.getAttribute("kendrabranch");
				if(branch!=null) {
					model.addAttribute("kendras", kendrass);
					model.addAttribute("customer", customer);
					model.addAttribute("branchName", branch.getBranchName());
					model.addAttribute("branchId", branch.getBranchId());
					List<Product> products=	productRepository.findAll();
					List<Occupation> occupations=productRepository.getOccupations();
					List<KYC> kycs=productRepository.getKYC();
					List<State> states=productRepository.getStates();
					model.addAttribute("states",states);
					model.addAttribute("kycs",kycs);
					model.addAttribute("occupations",occupations);
					model.addAttribute("products",products);
					return "create_customer";
				}
				else {
					model.addAttribute("sessionError", "sessionError");
					return "login";
				}
			}else {
				String branchName = (String) session.getAttribute("branchName");
				String branchId = (String)session.getAttribute("branch");
				Branch branch = branchRepository.findById(branchId).get();

				List<customer_eligible> customers=new ArrayList<customer_eligible>();
				List<Kendra> kendra=new  ArrayList<Kendra>();
				model.addAttribute("customers", customers);
				List<Kendra>kendrasss=kendraRepository.findAll();
				for(Kendra kendraa:kendrasss) {
					if(branch.getBranchId().equals(kendraa.getBranch_id())) {
						kendra.add(kendraa);
					}
				}
				model.addAttribute("kendras",kendra);
				model.addAttribute("customer", customer);
				model.addAttribute("branchId", branchId);
				model.addAttribute("branchName", branchName);
				List<Product> products=	productRepository.findAll();
				List<Occupation> occupations=productRepository.getOccupations();
				List<KYC> kycs=productRepository.getKYC();
				List<State> states=productRepository.getStates();
				model.addAttribute("states",states);
				model.addAttribute("kycs",kycs);
				model.addAttribute("occupations",occupations);
				model.addAttribute("products",products);
				return "/create_customer";
			}
		}
		else {
			model.addAttribute("sessionError", "sessionError");
			return "login";
		}
	}


	@PostMapping("/LMS/customers")
	public String saveCustomer(HttpServletRequest request, @ModelAttribute("customer") Customer customer, Model model) {
		HttpSession session = request.getSession();

		if((String)session.getAttribute("role")!=null) {
			String role = (String) session.getAttribute("role");
			if (role.equalsIgnoreCase("km")) {
				Kendra kendra = kendraRepository.getKendra(	customer.getKendraName());
				customer.setDiff(2);
				LocalDate date = LocalDate.now();
				customer.setCreatedDate(date);
				Branch branch=branchRepository.findById(kendra.getBranch_id()).get();	
				customer.setBranch_id(kendra.getBranch_id());
				customer.setKendra_id(kendra.getKendraId());
				customer.setCreated_By_id((String)session.getAttribute("userid"));
				customer.setCreated_By_name((String)session.getAttribute("username"));
				customer.setEmployee_id((String)session.getAttribute("userid"));
				customer.setBranchName(branch.getBranchName());
				customer.setStatus("PENDING");
				customerRepository.save(customer);

				return "redirect:/LMS/customers/add";

			} else 
			{
				String branchId = (String) session.getAttribute("branch");
				Branch branch = branchRepository.findById(branchId).get();
				customer.setDiff(1);
				LocalDate date = LocalDate.now();
				customer.setCreatedDate(date);
				customer.setCreated_By_id((String)session.getAttribute("userid"));
				customer.setCreated_By_name((String)session.getAttribute("username"));
				customer.setBranch_id(branchId);
				String kendraName=customer.getKendraName();
				List<Kendra> kendras=kendraRepository.findAll();
				for(Kendra kendra:kendras) {
					if(kendra.getKendraName().equals(kendraName)) {
						customer.setKendra_id(kendra.getKendraId());
						customer.setEmployee_id(kendra.getEmployee_id());
					}
				}
				customer.setBranchName(branch.getBranchName());
				customer.setStatus("PENDING");
				customerRepository.save(customer);
				return "redirect:/LMS/customers/add";
			}
		}
		else {
			model.addAttribute("sessionError", "sessionError");
			return "/login";
		}
	}


	@GetMapping("/LMS/customers/bm")
	public String getAllCustomers(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		if((String)session.getAttribute("role")!=null)
		{
			String branchId = (String) session.getAttribute("branch");
			//Branch branch = branchRepository.findById(branchId).get();
			List<Customer> customers=customerRepository.getcustomersByKendra(branchId);
			List<Customer> customerss =customerRepository.getcustomersByBranch(branchId);
			model.addAttribute("customers", customers);
			model.addAttribute("customerss", customerss);
			return "customerbm";
		}
		else
		    {
			model.addAttribute("sessionError", "sessionError");
			return "login";
		    }
	}


	@GetMapping("/LMS/customers/km")
	public String getAllCustomersKm(HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession();
		if((String)session.getAttribute("role")!=null)
		{
			String empid=(String)session.getAttribute("userid");
			List<Customer> customers=customerRepository.getcustomers(empid);
			model.addAttribute("customers", customers);
			return "customerkm";
		}
		else
		    {
			model.addAttribute("sessionError", "sessionError");
			return "login";
		    }
	}
	

}







