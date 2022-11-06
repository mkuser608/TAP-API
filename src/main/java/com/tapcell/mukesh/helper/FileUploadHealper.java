package com.tapcell.mukesh.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.swing.plaf.basic.BasicMenuBarUI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tapcell.mukesh.forUserEmail;

import lombok.Getter;
import lombok.Setter;

@Component
public class FileUploadHealper {
	
	@Getter
	@Setter
	private long currDatefile;
	
	@Value("${project.image}")
	private String UPLOAD_DIR;
	
//String UPLOAD_DIR = null;
//try {
//	UPLOAD_DIR = new ClassPathResource("/").getFile().getAbsolutePath();
//} catch (Exception e1) {
//	// TODO Auto-generated catch block
//	e1.printStackTrace();
//}	
	
	
	
	public Boolean uploadStudentData(MultipartFile multipartFile,String roll) {
		System.out.println(System.getProperty("user.dir"));
		
		createStaticUploadRepo();
		createStudentRollDirectory(roll);
		Date date = new Date();
		long currDate = date.getTime();
		boolean f=false;
	
		try {
			
			String originalFilename = multipartFile.getOriginalFilename();
			Path path =Paths.get(UPLOAD_DIR+File.separator+"static"+File.separator
				+"image"+File.separator+"students"+File.separator+roll+File.separator+String.valueOf(currDate)
					+originalFilename.substring(originalFilename.length()-6,originalFilename.length()-0));
//			Files.copy(multipartFile.getInputStream(), path,
//					StandardCopyOption.REPLACE_EXISTING);
			multipartFile.transferTo(path);
		    f=true;
		    setCurrDatefile(currDate);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return f;
		
	}
	public Boolean uploadNotice(MultipartFile multipartFile) {
		createStaticUploadRepo();
		Date date = new Date();
		long currDate = date.getTime();
		boolean f=false;
	
		try {
			String originalFilename = multipartFile.getOriginalFilename();
			
			Path path = Paths.get(UPLOAD_DIR+File.separator+"static"+File.separator
					+"image"+File.separator+"notice"+File.separator+String.valueOf(currDate)+
					originalFilename.substring(originalFilename.length()-6,originalFilename.length()-0));
//			Files.copy(multipartFile.getInputStream(), path, 
//					StandardCopyOption.REPLACE_EXISTING);
			multipartFile.transferTo(path);
		    f=true;
		    setCurrDatefile(currDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return f;
		
	}
	
	private void createStudentRollDirectory(String roll) {
		String path = UPLOAD_DIR+File.separator+"static"+File.separator
				+"image"+File.separator+"students"+File.separator+roll;
		File pathAsFile = new File(path);

		if (!Files.exists(Paths.get(path))) {
			
			pathAsFile.mkdir();
			System.out.println("success");
		}
	}
	
	private void createStaticUploadRepo() {
		String path[] = {UPLOAD_DIR,UPLOAD_DIR+File.separator+"static",
				UPLOAD_DIR+File.separator+"static"+File.separator+"image",
				UPLOAD_DIR+File.separator+"static"+File.separator+"image"+File.separator+"students",
				UPLOAD_DIR+File.separator+"static"+File.separator+"image"+File.separator+"notice"};
		for(String x: path) {
		File pathAsFile = new File(x);

		if (!Files.exists(Paths.get(x))) {
			
			pathAsFile.mkdir();
			System.out.println(x);
		}
		}
	}
	
	
	
	
	
}
