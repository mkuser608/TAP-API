package com.tapcell.mukesh.professor.dashboard;

import java.util.List;

import com.tapcell.mukesh.database.entity.Studentdataproff;

public interface ProfessorDashboardService {
	
	public List<Studentdataproff> getAllStudentListByListBranchAndBatch( String branch, String passingYear );

}
