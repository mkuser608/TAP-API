package com.tapcell.mukesh.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tapcell.mukesh.database.entity.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, String>{
	
	@Query
	public Users findByStudentRollNo(String studentRollNo);
	
	

}
