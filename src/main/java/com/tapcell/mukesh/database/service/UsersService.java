package com.tapcell.mukesh.database.service;

import org.springframework.http.ResponseEntity;

import com.tapcell.mukesh.database.entity.Users;
import com.tapcell.mukesh.response.UserCreationResponse;

public interface UsersService {

	
	public ResponseEntity<UserCreationResponse> createAdminUsers(Users users);
	
	public ResponseEntity<UserCreationResponse> createStudentUsers(Users users);
	
	public ResponseEntity<UserCreationResponse> createProfessorUsers(Users users);
	
	public ResponseEntity<UserCreationResponse> createCordinatorUsers(Users users);
	
	public ResponseEntity<UserCreationResponse> createCompanyUsers(Users users);
	
	public void initDefaultUser();
	
	public ResponseEntity<Iterable<Users>> getUsers();
	
	public String getEncodedPassword(String password);
	
	public Users getUserByEmail(String email);
	
	public boolean isUserPersentWithEmail(String email);
}
