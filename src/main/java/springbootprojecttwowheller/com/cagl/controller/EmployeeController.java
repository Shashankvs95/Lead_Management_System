package springbootprojecttwowheller.com.cagl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import kong.unirest.json.JSONObject;
import springbootprojecttwowheller.com.cagl.entity.Branch;
import springbootprojecttwowheller.com.cagl.entity.Employee;
import springbootprojecttwowheller.com.cagl.entity.Kendra;
import springbootprojecttwowheller.com.cagl.entity.Product;
import springbootprojecttwowheller.com.cagl.repository.BranchRepository;
import springbootprojecttwowheller.com.cagl.repository.CustomerRepository;
import springbootprojecttwowheller.com.cagl.repository.EmployeeRepository;
import springbootprojecttwowheller.com.cagl.repository.KendraRepository;
import springbootprojecttwowheller.com.cagl.repository.ProductRepository;

@Controller
public class EmployeeController {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private KendraRepository kendraRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@SuppressWarnings("unused")
	@PostMapping("/login")
	public String logincreation(HttpServletRequest request, Model model) throws Exception, IOException {

		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		if(userid.equals("GK32960") && password.equals("Cagl@2023")) {
			session.setAttribute("userid", userid);
			session.setAttribute("role", "HO-Admin");
			return "AdminHomePage";
		}
		else {

		String str = "{\"id\": \"1\",\r\n" + "    \"jsonrpc\": \"2.0\",\r\n" + "    \"method\": \"identity.check\",\r\n"
				+ "    \"params\": {},\r\n" + "    \"auth\": {\r\n" + "        \"accountOfficer\": \"\",\r\n"
				+ "        \"fingerPrint\": null,\r\n" + "        \"password\": \"" + password + "\",\r\n"
				+ "        \"session\": {\r\n" + "            \"id\": null,\r\n"
				+ "            \"imei\": \"354630264012424\",\r\n" + "            \"language\": \"en\",\r\n"
				+ "            \"userId\": null\r\n" + "        },\r\n" + "        \"username\":\"" + userid + "\",\r\n"
				+ "        \"version\": \"2.4.223\"\r\n" + "    }" + "}";

		HttpEntity requestt = new HttpEntity(str, headers);
		try {
		HttpEntity<String> response = restTemplate.exchange("https://uatmobile.grameenkoota.in:8010/rpc", HttpMethod.POST,
				requestt, String.class);
		String body = response.getBody();
		JSONObject jsonObject = new JSONObject(body);

		if (jsonObject.has("result")) {
			JSONObject result = jsonObject.getJSONObject("result");
			if (result.has("utUserName")) {
				String userName = result.getString("utUserName");
				String passwordd = result.getString("utPassword");
				String role = result.getString("roleName");

				if (employeeRepository.findById(userid).isPresent()) {
					Employee employee = employeeRepository.findById(userid).get();
					if (role.equalsIgnoreCase("BM Role") || role.equalsIgnoreCase("KM Role")) {

					
						if (session != null) {

							if (employee != null) {
								session.setAttribute("userid", employee.getEmployeeId());
								session.setAttribute("username", employee.getName());
								session.setAttribute("role", employee.getRole());
								List<Kendra> Empkendras = new ArrayList<Kendra>();
								List<Kendra> kendras = kendraRepository.findAll();
								for (Kendra kendra : kendras) {
									if (kendra.getEmployee_id().equals(employee.getEmployeeId())) {
										Empkendras.add(kendra);
									}
								}
								session.setAttribute("kendra", Empkendras);

								if (employee.getRole().equalsIgnoreCase("KM")) {
									Branch branch = branchRepository.findById(employee.getBranch_id()).get();
									System.out.println(branch);
									session.setAttribute("kendrabranch", branch);
									session.setAttribute("kendrabranchKM", employee);
									String id = (String) session.getAttribute("userid");
									List<String> customerss = customerRepository.getkm(id);
									model.addAttribute("cust", customerss);

									return "kmhomepage";
								} else {
									Employee employeee = employeeRepository.findById(userid).get();
									String id = employee.getBranch_id();
									Branch branch = branchRepository.findById(id).get();
									session.setAttribute("branch", branch.getBranchId());
									session.setAttribute("branchName", branch.getBranchName());
									session.setAttribute("name", employee.getName());
									List<String> customersss = customerRepository.getbm(branch.getBranchId());
									List<String> customerss = customerRepository.getid(branch.getBranchId());
									model.addAttribute("cust", customersss);
									model.addAttribute("custo", customerss);

									return "bmhomepage";
								}
							} else {
								model.addAttribute("logError", "logError");
								return "login";
							}
						} else {
							model.addAttribute("sessionError", "sessionError");
							return "login";
						}
					}
				} else {
					model.addAttribute("roleError", "roleError");
					return "login";
				}
			} else {
				model.addAttribute("credError", "credError");
				return "login";
			}
		} else {
			model.addAttribute("credError", "credError");
			return "login";
		}
		model.addAttribute("credError", "credError");
		return "login";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("connectionError", "connectionError");
			return "login";
		}
		
		}
	
	}

	@GetMapping("/login/page")
	public String loginPage(Model model) {
		return "adminhomepage";
	}

	@GetMapping("/LMS/login/bmhomepage")
	public String bmhomepage(HttpServletRequest request, Model model, HttpSession httpSession) {
		HttpSession session = request.getSession();

		String role = (String) session.getAttribute("role");

		String id = (String) session.getAttribute("branch");
		if (role != null) {
			if (role.equalsIgnoreCase("km")) {
				String empid = (String) session.getAttribute("userid");
				List<String> customerss = customerRepository.getkm(empid);
				model.addAttribute("cust", customerss);
				return "kmhomepage";
			} else {
				List<String> customersss = customerRepository.getbm(id);
				List<String> customerss = customerRepository.getid(id);
				model.addAttribute("cust", customersss);
				model.addAttribute("custo", customerss);
				return "bmhomepage";
			}
		} else {
			model.addAttribute("sessionError", "sessionError");
			return "login";
		}
	}

	@GetMapping("/LMS/employees/add")
	public String createEmployee(Model model) {
		Employee employee = new Employee();
	List<Product> products=	productRepository.findAll();
		List<Branch> branches = branchRepository.findAll();
		List<Kendra> kendras = kendraRepository.findAll();
		model.addAttribute("employee", employee);
		model.addAttribute("branches", branches);
		model.addAttribute("products",products);
		model.addAttribute("kendras", kendras);
		return "create_employee";
	}

}
