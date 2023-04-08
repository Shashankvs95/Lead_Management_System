package springbootprojecttwowheller.com.cagl.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import springbootprojecttwowheller.com.cagl.entity.GruhaSudharLoan;

public interface IFileSytemStorage {
	void init();

	String saveFile(MultipartFile file);

	Resource loadFile(String fileName);
	
	String importToDb(MultipartFile multipartfiles,HttpServletRequest request,Model model);
}
