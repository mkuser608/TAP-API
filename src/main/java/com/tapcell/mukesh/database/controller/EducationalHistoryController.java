package com.tapcell.mukesh.database.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tapcell.mukesh.forUserEmail;
import com.tapcell.mukesh.database.entity.EducationalHistory;
import com.tapcell.mukesh.database.service.EducationalHistoryService;
import com.tapcell.mukesh.response.OnlyMessageString;

@RestController
@PreAuthorize("hasAuthority('ROLE_student')")
public class EducationalHistoryController {
	
	@Autowired
	private EducationalHistoryService educationalHistoryService;

	@Autowired
	private forUserEmail forUserEmail;

	@PostMapping("/saveStudentEducationalHistory")
	public ResponseEntity<OnlyMessageString> saveStudentEducationalHistory(@Valid @RequestBody EducationalHistory educationalHistory) {
		return educationalHistoryService.saveStudentEducationalHistory(educationalHistory, forUserEmail.getUserEmail());
	}
	
	@GetMapping("/getStudentEducationalHistory")
	public ResponseEntity<Set<EducationalHistory>> getStudentEducationalHistories(){
		return educationalHistoryService.getEducationHistories(forUserEmail.getUserRollNo());
	}
	
	@PostMapping("/updateEducationHistory")
	public ResponseEntity<OnlyMessageString> updateEducationHistory(@Valid @RequestBody EducationalHistory educationalHistory){
		return educationalHistoryService.updateEducationHistory(forUserEmail.getUserRollNo(), educationalHistory);
	}
	
	@DeleteMapping("/deleteEducationHistory")
	public ResponseEntity<OnlyMessageString> deleteEducationHistory(@RequestParam Long id){
		return educationalHistoryService.deleteEducationHistory(forUserEmail.getUserRollNo(), forUserEmail.getUserEmail(), id);
	}
}
