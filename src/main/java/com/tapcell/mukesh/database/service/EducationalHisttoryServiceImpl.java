package com.tapcell.mukesh.database.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tapcell.mukesh.database.entity.EducationalHistory;
import com.tapcell.mukesh.database.repository.EducationalHistoryRepository;
import com.tapcell.mukesh.response.OnlyMessageString;

@Service
public class EducationalHisttoryServiceImpl implements EducationalHistoryService{
	
	@Autowired
	private EducationalHistoryRepository educationalHistoryRepository;
	
	@Autowired
	private StudentsService studentsService;
	

	@Override
	public ResponseEntity<OnlyMessageString> saveStudentEducationalHistory(EducationalHistory educationalHistory,
			String email) {
		studentsService.saveStudentEducationalHistory(educationalHistory, email);
		
		return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Educational History Created"), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Set<EducationalHistory>> getEducationHistories(String roll) {
		
		return new ResponseEntity<Set<EducationalHistory>>(studentsService.getEducationalHistoriesByRoll(roll),HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<OnlyMessageString> updateEducationHistory(String roll, EducationalHistory educationalHistory) {
		
		if (educationalHistory.getId()==null) {
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("not exist"), HttpStatus.BAD_REQUEST);
		}
		if (  isValidUpdate(roll, educationalHistory.getId().toString())) {
			educationalHistoryRepository.save(educationalHistory);
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Educational History Updated"), HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("You are Trying to Update Other"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public ResponseEntity<OnlyMessageString> deleteEducationHistory(String roll, String email, Long id) {
		if (isValidUpdate(roll, id.toString())) {
			studentsService.deleteEducationHistory(id, email);
			educationalHistoryRepository.deleteById(id);
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Educational Histrory deleted"), HttpStatus.OK);
		}else {
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Educational Histrory not deleted"), HttpStatus.BAD_REQUEST);
		}
	}
	
	private boolean isValidUpdate(String loggedRoll, String id) {
		if (!educationalHistoryRepository.existsById(Long.parseLong(id))) {
			return false;
		}
		Set<EducationalHistory> educationalHistories = studentsService.getEducationalHistoriesByRoll(loggedRoll) ;
		
		for(EducationalHistory x : educationalHistories) {
			if(x.getId()==Long.parseLong(id)) {
				return true;
			}
		}
		return false;
	}

	

	

}
