package com.tapcell.mukesh.professor.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tapcell.mukesh.database.entity.Studentdataproff;

@RestController
public class ProfessorDashboardController {
	
	@Autowired
	private ProfessorDashboardService professorDashboardService;
	
	@GetMapping("/getAllStudentsByBranchAndPassingYear")
	public List<Studentdataproff> getStudentsByBranchAndPassingYear( @RequestParam String branch, @RequestParam String passingYear) {
	
		return professorDashboardService.getAllStudentListByListBranchAndBatch(branch, passingYear);
	}

}
