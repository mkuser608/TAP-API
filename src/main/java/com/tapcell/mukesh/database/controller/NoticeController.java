package com.tapcell.mukesh.database.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tapcell.mukesh.forUserEmail;
import com.tapcell.mukesh.database.entity.Notice;
import com.tapcell.mukesh.database.service.NoticeService;
import com.tapcell.mukesh.helper.FileUploadHealper;
import com.tapcell.mukesh.response.OnlyMessageString;

@RestController
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired 
	private FileUploadHealper fileUploadHealper;
	
	@Autowired 
	private forUserEmail forUserEmail;

	
	@PostMapping("/createNewNotice")
	public ResponseEntity<Notice> CreateNewNotice(@RequestParam("title") String title, @RequestParam("file") MultipartFile multipartFile){
		Notice notice = new Notice();
		
		if(multipartFile.getOriginalFilename().endsWith(".pdf")) {
			try {
				System.out.println(multipartFile.getBytes().length < 1000000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (multipartFile.isEmpty()) {
			return new  ResponseEntity<Notice>(notice, HttpStatus.BAD_REQUEST);
		}else
		{
			
			if(fileUploadHealper.uploadNotice(multipartFile)) {
				String originalFilename = multipartFile.getOriginalFilename();
				String string = ServletUriComponentsBuilder.fromCurrentContextPath().path("getPdf/").toUriString();
				string =string.concat("?");
				string=string.concat("filepath=static/image/notice/"+String.valueOf(fileUploadHealper.getCurrDatefile())+originalFilename.substring(originalFilename.length()-6,originalFilename.length()-0));
				System.out.println(string);
				notice.setNoticeUrl(string);
				notice.setTitle(title);
				notice.setPubEmail(forUserEmail.getUserEmailOtherthanStudent());
				return noticeService.createNewNoticeNotice(notice);
			}
		}}
		
		
		return new ResponseEntity<Notice>(notice, HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/viewAllNotice")
	public ResponseEntity<Iterable<Notice>> viewAllNotice(){
		return noticeService.viewAllNotice();
	}
	
	@DeleteMapping("/deleteNotice")
	public ResponseEntity<OnlyMessageString> deleteNotice(@RequestParam Long id){
		return noticeService.deleteNotice(id);
	}
}
