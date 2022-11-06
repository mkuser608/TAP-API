package com.tapcell.mukesh.professor.dashboard;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tapcell.mukesh.database.entity.Studentdataproff;
import com.tapcell.mukesh.database.repository.StudentdataproffRepository;

@Service
public class ProfessorDashboardServiceImpl implements ProfessorDashboardService{
	
	@Autowired
	private StudentdataproffRepository studentdataproffRepository;

	


	@Override
	public List<Studentdataproff> getAllStudentListByListBranchAndBatch(String branch, String passingYear) {
		return studentdataproffRepository.findByBranchAndPassingYear(branch, passingYear);
	}

	

}
