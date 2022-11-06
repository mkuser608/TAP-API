package com.tapcell.mukesh.database.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tapcell.mukesh.database.entity.SemesterResults;
import com.tapcell.mukesh.database.repository.SemesterResultsRepository;
import com.tapcell.mukesh.response.OnlyMessageString;


@Service
public class SemesterResultsServiceImpl implements SemesterResultsService{
	
	@Autowired
	private SemesterResultsRepository semesterResultsRepository;
	
	@Autowired
	private StudentsService studentsService;

	@Override
	public ResponseEntity<OnlyMessageString> saveStuentSemesterResult(SemesterResults semesterResults, String email) {
		 studentsService.saveStuentSemesterResult(semesterResults, email);
		
		return new  ResponseEntity<OnlyMessageString>(new OnlyMessageString("Semester Result saved"),HttpStatus.OK);
	}

	@Override
	public Set<SemesterResults> getSemResultsByRoll(String roll) {
		return studentsService.getSemesterResultsByRoll(roll);
	}

	@Override
	public ResponseEntity<OnlyMessageString> updateSemesterResults(String roll, SemesterResults semesterResults) {
		if (isValidUpdate(roll, semesterResults.getId().toString())) {
			semesterResultsRepository.save(semesterResults);
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Semester Result Updated"), HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Semester Result Not Updated"), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<OnlyMessageString> deleteSemesterResult(String roll, Long id) {
		if (isValidUpdate(roll, id.toString())) {
			studentsService.deleteSemesterResult(id, roll);
			semesterResultsRepository.deleteById(id);
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Sem Result Deleted"), HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Sem Result not Deleted"), HttpStatus.BAD_REQUEST);
		}
	}
	
	private boolean isValidUpdate(String roll, String id) {
		Set<SemesterResults> semesterResults = studentsService.getSemesterResultsByRoll(roll);
		
		for(SemesterResults xResults : semesterResults) {
			if (xResults.getId()==Long.parseLong(id)) {
				return true;
			}
		}
		return false;
		
	}
	
}
