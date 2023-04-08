package springbootprojecttwowheller.com.cagl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springbootprojecttwowheller.com.cagl.entity.GruhaSudharLoan;
import springbootprojecttwowheller.com.cagl.repository.GruhaSudharLoanRepository;
import springbootprojecttwowheller.com.cagl.serviceimple.FileSystemStorageService;

@Controller
public class FileUploadController {
	
@Autowired
	private FileSystemStorageService service;

@Autowired
private GruhaSudharLoanRepository gruhaSudharLoanRepository;
	
	@GetMapping("/upload")
	public String index(HttpServletResponse response,HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		String branch_id=(String)session.getAttribute("branch");
		List<GruhaSudharLoan> loans=gruhaSudharLoanRepository.getLoans(branch_id);
		model.addAttribute("loans",loans);
		return "upload";
	}
	
	
	
	@PostMapping("/import-to-db")
	public String importTransactionsFromExcelToDb(@RequestParam("file") MultipartFile file,HttpServletResponse response,HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		String fileName=file.getName();
		String extension = "";

int index = fileName.lastIndexOf('.');
System.out.println(fileName);
if (index > 0) {
extension = fileName.substring(index + 1);
}
	String branch_id=(String)session.getAttribute("branch");
		List<GruhaSudharLoan> loans=gruhaSudharLoanRepository.getLoans(branch_id);
		if(file.isEmpty()) {
			return "AdminHomePage";
		}else {
			
			String gruha=service.importToDb(file,request,model);
			String string = gruha;
			String[] parts = string.split("_", 9);
			String part1 = parts[0];
			if(gruha.equals("Format")){
				
				model.addAttribute("loans",loans);
				model.addAttribute("logError", "logError");
				return "AdminHomePage";
			}	
			else if(part1.equals("Success")) {
				String part2 = parts[1];
				String part=part2+"  rows uploaded";
				model.addAttribute("loans",loans);
				model.addAttribute("part",part);
				model.addAttribute("successError", "successError");
				return "AdminHomePage";
			}
			return "AdminHomePage";
		}
	}

}
