package com.tapcell.mukesh.database.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tapcell.mukesh.database.entity.Users;
import com.tapcell.mukesh.database.service.UsersService;
import com.tapcell.mukesh.response.UserCreationResponse;

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	private String messageUserPresent = "User is Present in DataBase no need to create with email : ";
	

	@PostConstruct
	public void initDefaultUser() {
		usersService.initDefaultUser();
	}
	
	@PreAuthorize("hasAuthority('ROLE_Admin')")
	@PostMapping("/createNewAdmin")
	public ResponseEntity<UserCreationResponse> createNewAdminUsers(@Valid @RequestBody Users users) {
		if (usersService.isUserPersentWithEmail(users.getEmail())) {
			UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"User Created");

			return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.CONFLICT);
		}
		
		return usersService.createAdminUsers(users);
	}
	
	@PreAuthorize("hasAuthority('ROLE_Admin')"+ "|| hasAuthority('ROLE_Professor')")
	@PostMapping("/createNewStudent")
	public ResponseEntity<UserCreationResponse> createnewStudentUsers(@Valid @RequestBody Users users,@Valid @RequestParam String rollNo) {
		if(rollNo==null || rollNo == "") {
			UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"Enter Roll Number");

			return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.BAD_REQUEST);
		}
		if (usersService.isUserPersentWithEmail(users.getEmail())) {
			UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"User Exists");

			return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.CONFLICT);	
		}
		System.out.println(rollNo);
		users.setStudentRollNo(rollNo);
		return usersService.createStudentUsers(users);
	}
	
	@PreAuthorize("hasAuthority('ROLE_Admin')")
	@PostMapping("/createNewProfessor")
	public ResponseEntity<UserCreationResponse> createNewProfessorUsers(@Valid @RequestBody Users users) {
		if (usersService.isUserPersentWithEmail(users.getEmail())) {
			UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"User Created");

			return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.CONFLICT);
			}
		return usersService.createProfessorUsers(users);
	}
	
	@PreAuthorize("hasAuthority('ROLE_Admin')")
	@PostMapping("/createNewCordinator")
	public ResponseEntity<UserCreationResponse> createNewCordinatorUsers(@Valid @RequestBody Users users) {
		if (usersService.isUserPersentWithEmail(users.getEmail())) {
			UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"User exist");

			return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.CONFLICT);	
		}
		return usersService.createCordinatorUsers(users);
	}
	
	@PreAuthorize("hasAuthority('ROLE_Admin')")
	@PostMapping("createNewCompany")
	public ResponseEntity<UserCreationResponse> createNewCompanyUsers(@Valid @RequestBody Users users) {
		if (usersService.isUserPersentWithEmail(users.getEmail())) {
			UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"User Created");

			return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.CONFLICT);	
		}
		return usersService.createCompanyUsers(users);
	}
	
	
	@GetMapping("/users")
	public ResponseEntity<Iterable<Users>> getUsers() throws InterruptedException{
		Thread.sleep(1000);
		return usersService.getUsers();
	}
	
	

}
