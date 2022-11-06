package com.tapcell.mukesh.helper;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class fileServeController {
	
	@Autowired
	private FileServeHealper fileServeHealper;
	

	@GetMapping(value = "/upload-files")
	public void serveFile(@RequestParam("filepath") 
					String filepath,HttpServletResponse response) throws IOException {
		System.out.println("hello");
		System.out.println(filepath);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE );
		response.setContentType( MediaType.IMAGE_PNG_VALUE);
		InputStream resource = this.fileServeHealper.getResource(filepath);
		
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	@GetMapping("/getPdf")
	public void servePdfFile(@RequestParam("filepath") 
	String filepath,HttpServletResponse response) throws IOException {
System.out.println("hello");
System.out.println(filepath);
response.setContentType(MediaType.APPLICATION_PDF_VALUE );
InputStream resource = this.fileServeHealper.getResource(filepath);

StreamUtils.copy(resource, response.getOutputStream());
}
}
