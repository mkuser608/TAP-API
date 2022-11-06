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
import com.tapcell.mukesh.database.entity.SemesterResults;
import com.tapcell.mukesh.database.service.SemesterResultsService;
import com.tapcell.mukesh.response.OnlyMessageString;

@RestController
@PreAuthorize("hasAuthority('ROLE_student')")
public class SemesterResultsController {
	
	@Autowired
	private SemesterResultsService semesterResultsService;
	
	@Autowired
	private forUserEmail forUserEmail;
	
	@PostMapping("/saveStudentSemesterResult")
	public ResponseEntity<OnlyMessageString> saveStudentAddress(@Valid @RequestBody SemesterResults semesterResults){
		return semesterResultsService.saveStuentSemesterResult(semesterResults, forUserEmail.getUserEmail());
	}
	
	@PostMapping("/updateSemeterResult")
	public ResponseEntity<OnlyMessageString> updateSemResults(@Valid @RequestBody SemesterResults semesterResults){
		return semesterResultsService.updateSemesterResults(forUserEmail.getUserRollNo(), semesterResults);
	}
	
	@GetMapping("/getSemesterResults")
	public Set<SemesterResults> getSemesterResults(){
		return semesterResultsService.getSemResultsByRoll(forUserEmail.getUserRollNo());
	}
	
	@DeleteMapping("/deleteSemesterResult")
	public ResponseEntity<OnlyMessageString> deleteSemesterResults(@RequestParam Long id){
		return semesterResultsService.deleteSemesterResult(forUserEmail.getUserRollNo(), id);
	}

}
