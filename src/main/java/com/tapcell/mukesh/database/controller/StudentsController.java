package com.tapcell.mukesh.database.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.tapcell.mukesh.forUserEmail;
import com.tapcell.mukesh.database.entity.Students;
import com.tapcell.mukesh.database.service.StudentsService;

import com.tapcell.mukesh.helper.FileUploadHealper;
import com.tapcell.mukesh.response.OnlyMessageString;

@RestController
public class StudentsController {

	@Autowired
	private forUserEmail forUserEmail;

	@Autowired
	private StudentsService studentsService;

	@PostMapping("/saveStudent")
	@PreAuthorize("hasAuthority('ROLE_student')")
	public ResponseEntity<Students> saveStudent(@Valid @RequestBody Students students) {
		students.setRoll_No(forUserEmail.getUserRollNo());
		students.setEmail(forUserEmail.getUserEmail());
		return studentsService.saveStudentsEntity(students);
	}

	@GetMapping("/getStudent")
	@PreAuthorize("hasAuthority('ROLE_student')")
	public ResponseEntity<Students> getStudent() {
		System.out.println(forUserEmail.getUserEmail());
		return studentsService.getStudent(forUserEmail.getUserEmail());
	}

	@PostMapping("/uploadProfile")
	@PreAuthorize("hasAuthority('ROLE_student')")
	public ResponseEntity<OnlyMessageString> uploadProfile(@RequestParam("file") MultipartFile multipartFile) {

		if (multipartFile.isEmpty()) {
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Insert File"), HttpStatus.BAD_REQUEST);
		}

		return studentsService.saveProfilePhoto(forUserEmail.getUserRollNo(), forUserEmail.getUserEmail(),
				multipartFile);

	}

}
