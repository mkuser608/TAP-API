package com.tapcell.mukesh.uploadLinkBuilderHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tapcell.mukesh.helper.FileUploadHealper;

@Component
public class UploadLinkbuilder {

	@Autowired
	private FileUploadHealper fileUploadHealper;

	public String certificateUploadLink(String roll, MultipartFile multipartFile) {
		if (multipartFile.getOriginalFilename().endsWith(".pdf")) {
			boolean fileUploaded = fileUploadHealper.uploadStudentData(multipartFile, roll);
			if (fileUploaded == true) {
				String originalFilename = multipartFile.getOriginalFilename();
				String string = ServletUriComponentsBuilder.fromCurrentContextPath().path("getPdf/").toUriString();
				string = string.concat("?");
				string = string.concat("filepath=static/image" + "/students/" + roll + "/"
						+ String.valueOf(fileUploadHealper.getCurrDatefile())
						+ originalFilename.substring(originalFilename.length() - 6, originalFilename.length() - 0));
				System.out.println(string);
				return string;
			} else {
				return "upload Failed";
			}

		}else {
			return "not pdf";
		}
	}
	
	public String profileUploadLink(String roll,MultipartFile multipartFile) {
		if(multipartFile.getOriginalFilename().endsWith(".jpg")||multipartFile.getOriginalFilename().endsWith(".jpeg")|| multipartFile.getOriginalFilename().endsWith(".png") ) {
			boolean fileUploaded = fileUploadHealper.uploadStudentData(multipartFile,roll);
			if(fileUploaded==true) {
				String originalFilename = multipartFile.getOriginalFilename();
				String string = ServletUriComponentsBuilder.fromCurrentContextPath().path("upload-files/").toUriString();
				string =string.concat("?");
				string=string.concat("filepath=static/image"+"/students/"+roll+"/"+String.valueOf(fileUploadHealper.getCurrDatefile())+originalFilename.substring(originalFilename.length()-6,originalFilename.length()-0));
				System.out.println(string);
				return string;
			}else {
				return "upload Failed";
			}
		}else {
			return "Required Formate";
		}
	}

}
