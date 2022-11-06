package com.tapcell.mukesh.database.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tapcell.mukesh.database.entity.EducationalHistory;
import com.tapcell.mukesh.response.OnlyMessageString;

public interface EducationalHistoryService {

	ResponseEntity<OnlyMessageString> saveStudentEducationalHistory(EducationalHistory educationalHistory, String email);
	
	ResponseEntity<Set<EducationalHistory>> getEducationHistories(String roll);
	
	ResponseEntity<OnlyMessageString> updateEducationHistory(String roll , EducationalHistory educationalHistory);
	
	ResponseEntity<OnlyMessageString> deleteEducationHistory(String roll, String email, Long id);
}
