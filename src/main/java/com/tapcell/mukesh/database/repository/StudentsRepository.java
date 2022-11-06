package com.tapcell.mukesh.database.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tapcell.mukesh.database.entity.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, String>{
	
	
	public Students findByEmail(String email);
	

}
