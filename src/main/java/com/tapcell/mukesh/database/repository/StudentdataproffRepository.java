package com.tapcell.mukesh.database.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tapcell.mukesh.database.entity.Studentdataproff;

public interface StudentdataproffRepository extends PagingAndSortingRepository<Studentdataproff, String>{

	
	public List<Studentdataproff> findByBranchAndPassingYear(String branch,String passingYear );
}
