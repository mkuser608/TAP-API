package com.tapcell.mukesh.database.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tapcell.mukesh.database.entity.SemesterResults;
import com.tapcell.mukesh.response.OnlyMessageString;

public interface SemesterResultsService {

	
	public ResponseEntity<OnlyMessageString> saveStuentSemesterResult(SemesterResults semesterResults, String email);
	
	public Set<SemesterResults> getSemResultsByRoll(String roll);
	
	public ResponseEntity<OnlyMessageString> updateSemesterResults(String roll, SemesterResults semesterResults);
	
	public ResponseEntity<OnlyMessageString> deleteSemesterResult(String roll, Long id);
}
