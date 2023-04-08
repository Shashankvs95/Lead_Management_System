package springbootprojecttwowheller.com.cagl.serviceimple;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;


import springbootprojecttwowheller.com.cagl.entity.GruhaSudharLoan;
import springbootprojecttwowheller.com.cagl.exception.FileNotFoundException;
import springbootprojecttwowheller.com.cagl.exception.FileStorageException;
import springbootprojecttwowheller.com.cagl.fileuploadproperties.FileUploadProperties;

import springbootprojecttwowheller.com.cagl.repository.GruhaSudharLoanRepository;


@Service
public class FileSystemStorageService implements springbootprojecttwowheller.com.cagl.service.IFileSytemStorage {
	private final Path dirLocation;

	@Autowired
	GruhaSudharLoanRepository gruhaRepository;


	@Autowired
	public FileSystemStorageService(FileUploadProperties fileUploadProperties) {
		this.dirLocation = Paths.get(fileUploadProperties.getLocation()).toAbsolutePath().normalize();
	}

	@Override
	@PostConstruct
	public void init() {
		try {
			Files.createDirectories(this.dirLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create upload dir!");
		}
	}

	@Override
	public String saveFile(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			String fileName = file.getOriginalFilename();
			Path dfile = this.dirLocation.resolve(fileName);
			Files.copy(file.getInputStream(), dfile, StandardCopyOption.REPLACE_EXISTING);
			return fileName;

		} catch (Exception e) {
			throw new FileStorageException("Could not upload file");
		}
	}

	@Override
	public Resource loadFile(String fileName) {
		// TODO Auto-generated method stub
		try {
			Path file = this.dirLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new FileNotFoundException("Could not find file");
			}
		} catch (MalformedURLException e) {
			throw new FileNotFoundException("Could not download file");
		}

	}
//	public List<Customer> getall(LocalDate fromdate,LocalDate todate,String status){
//		List<Customer> customers=	customerRepository.getcustomers(fromdate,todate,status);
//		return	customers;
//
//	}

	@Override
	public String importToDb(MultipartFile multipartfiles,HttpServletRequest request,Model model) {
		int count=0;
		HttpSession session = request.getSession();
		String userId=(String)session.getAttribute("userid");
		List<GruhaSudharLoan> gruhadata = new ArrayList<>();

		if (!multipartfiles.isEmpty()) {
			try {
				XSSFWorkbook workBook = new XSSFWorkbook(multipartfiles.getInputStream());

				XSSFSheet sheet = workBook.getSheetAt(0);
				for (int rowIndex = 0; rowIndex <= getNumberOfNonEmptyCells(sheet, 0) - 1; rowIndex++) {
					XSSFRow row = sheet.getRow(rowIndex);
					 if (rowIndex == 0) {
						if((String.valueOf(row.getCell(0)).equalsIgnoreCase("customer_id"))&&
								(String.valueOf(row.getCell(1)).equalsIgnoreCase("member_name"))&&
								(String.valueOf(row.getCell(2)).equalsIgnoreCase("co_code"))&&
								(String.valueOf(row.getCell(3)).equalsIgnoreCase("branch_name"))&&
								(String.valueOf(row.getCell(4)).equalsIgnoreCase("areaname"))&&
								(String.valueOf(row.getCell(5)).equalsIgnoreCase("region_name"))&&
								(String.valueOf(row.getCell(6)).equalsIgnoreCase("kendra_id"))&&
								(String.valueOf(row.getCell(7)).equalsIgnoreCase("kendra_name"))&&
								(String.valueOf(row.getCell(8)).equalsIgnoreCase("kendra_manager ID"))&&
								(String.valueOf(row.getCell(9)).equalsIgnoreCase("kendra_manager_Name"))&&
								(String.valueOf(row.getCell(10)).equalsIgnoreCase("Meeting_Day"))&&
								(String.valueOf(row.getCell(11)).equalsIgnoreCase("Indicative Eligibile Amount"))){
						}
						else {
							return "Format";
						}
					}
					else if(rowIndex!=0) {
						Long customerId = Long.parseLong(getValue(row.getCell(0)).toString());

						
							GruhaSudharLoan g1=new GruhaSudharLoan();
							String member_name=String.valueOf(row.getCell(1));
							String branch_id=String.valueOf(row.getCell(2));
							String branch_name=String.valueOf(row.getCell(3));
							String areaname=String.valueOf(row.getCell(4));
							String region_name=String.valueOf(row.getCell(5));
							//String kendra_id=String.valueOf(row.getCell(6));
							Long kendra_id= Long.parseLong(getValue(row.getCell(6)).toString());
							String kendra_name=String.valueOf(row.getCell(7));
							String kendra_manager_id=String.valueOf(row.getCell(8));
							String kendra_manager_name=String.valueOf(row.getCell(9));
							String meeting_day=String.valueOf(row.getCell(10));
							Double Indicative_eligible_amount= Double.parseDouble(getValue(row.getCell(11)).toString());
							g1.setBranch_name(branch_name);
							g1.setCustomer_id(customerId);
							g1.setKendra_manager_id(kendra_manager_id);
							g1.setKendra_manager_name(kendra_manager_name);
							g1.setBranch_id(branch_id);
							g1.setArea_name(areaname);
							g1.setKendra_name(kendra_name);
							g1.setKendra_id(kendra_id);
							g1.setMember_name(member_name);
							g1.setMeeting_day(meeting_day);
							g1.setIndicative_eligibile_amount(Indicative_eligible_amount);
							g1.setRegion_name(region_name);
							g1.setUpload_by_id(userId);
							g1.setUpload_date(LocalDate.now());
							gruhadata.add(g1);
							count++;
					}
				}
				
			}
	catch (IOException e) {
				e.printStackTrace();
			}
		}
			if (!gruhadata.isEmpty()) {
				gruhaRepository.saveAll(gruhadata);
				System.out.println(count+"*************************");
				return "Success_"+count;
			}
			return null;
			}

	private Object getValue(Cell cell) {
		switch (cell.getCellType()) {
		case NUMERIC:
			return String.valueOf((int) cell.getNumericCellValue());
		case STRING:
			return cell.getStringCellValue();
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case ERROR:
			return cell.getErrorCellValue();
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
			return null;
		case _NONE:
			return null;	
		default:
			break;
		}
		return null;
	}

	public static int getNumberOfNonEmptyCells(XSSFSheet sheet, int columnIndex) {
		int numOfNonEmptyCells = 0;
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			if (row != null) {
				XSSFCell cell = row.getCell(columnIndex);
				if (cell != null && cell.getCellType() != CellType.BLANK) {
					numOfNonEmptyCells++;
				}
			}
		}
		return numOfNonEmptyCells;
	}
}
