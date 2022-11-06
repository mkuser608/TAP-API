package com.tapcell.mukesh.database.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tapcell.mukesh.database.entity.Roles;
import com.tapcell.mukesh.database.entity.Users;
import com.tapcell.mukesh.database.repository.RolesRepository;
import com.tapcell.mukesh.database.repository.UsersRepository;
import com.tapcell.mukesh.response.UserCreationResponse;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	private String userCreationMessage = "User is created with your entered password, email :";

	public ResponseEntity<UserCreationResponse> createAdminUsers(Users users) {
		Users adminUsers = new Users();
		adminUsers.setEmail(users.getEmail());
		adminUsers.setName(users.getName());
		adminUsers.setPassword(getEncodedPassword(users.getPassword()));

		Roles adminRoles = rolesRepository.findById("Admin").get();

		Set<Roles> adminRolesSet = new HashSet<>();
		adminRolesSet.add(adminRoles);
		adminUsers.setRoles(adminRolesSet);
		usersRepository.save(adminUsers);
		
		UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"User Created");

		return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.CREATED);
	}

	public ResponseEntity<UserCreationResponse> createStudentUsers(Users users) {

		if (usersRepository.findByStudentRollNo(users.getStudentRollNo())== null) {

			Users studentusers = new Users();
			studentusers.setEmail(users.getEmail());
			studentusers.setName(users.getName());
			studentusers.setPassword(getEncodedPassword(users.getPassword()));
			studentusers.setStudentRollNo(users.getStudentRollNo());

			Roles studentRoles = rolesRepository.findById("Student").get();

			Set<Roles> studentRolesSet = new HashSet<>();
			studentRolesSet.add(studentRoles);
			studentusers.setRoles(studentRolesSet);
			usersRepository.save(studentusers);

			UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"User Created");

			return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.CREATED);
		}
		UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"User Exist");

		return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.CONFLICT);		

	}

	public ResponseEntity<UserCreationResponse> createProfessorUsers(Users users) {
		Users professorUsers = new Users();
		professorUsers.setEmail(users.getEmail());
		professorUsers.setName(users.getName());
		professorUsers.setPassword(getEncodedPassword(users.getPassword()));

		Roles professorRoles = rolesRepository.findById("Professor").get();

		Set<Roles> professoRolesSet = new HashSet<>();
		professoRolesSet.add(professorRoles);
		professorUsers.setRoles(professoRolesSet);
		usersRepository.save(professorUsers);

		UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"User Created");

		return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.CREATED);
	}

	public ResponseEntity<UserCreationResponse> createCordinatorUsers(Users users) {
		Users cordinatorUsers = new Users();
		cordinatorUsers.setEmail(users.getEmail());
		cordinatorUsers.setName(users.getName());
		cordinatorUsers.setPassword(getEncodedPassword(users.getPassword()));

		Roles cordinatorRoles = rolesRepository.findById("Cordinator").get();

		Set<Roles> cordinatorRolesSet = new HashSet<>();
		cordinatorRolesSet.add(cordinatorRoles);
		cordinatorUsers.setRoles(cordinatorRolesSet);
		usersRepository.save(cordinatorUsers);

		UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"User Created");

		return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.CREATED);
	}

	public ResponseEntity<UserCreationResponse> createCompanyUsers(Users users) {
		Users companyUsers = new Users();
		companyUsers.setEmail(users.getEmail());
		companyUsers.setName(users.getName());
		companyUsers.setPassword(getEncodedPassword(users.getPassword()));

		Roles companyRoles = rolesRepository.findById("Company").get();

		Set<Roles> companyRolesSet = new HashSet<>();
		companyRolesSet.add(companyRoles);
		companyUsers.setRoles(companyRolesSet);
		usersRepository.save(companyUsers);

		UserCreationResponse userCreationResponse = new UserCreationResponse(users.getEmail(),users.getName(),"User Created");

		return  new ResponseEntity<UserCreationResponse>(userCreationResponse,HttpStatus.CREATED);
	}

	public void initDefaultUser() {
		Users adminUsers = new Users();
		adminUsers.setEmail("admin@gmail.com");
		adminUsers.setName("admin");
		adminUsers.setPassword("admin@123");
		createAdminUsers(adminUsers);

	}

	@Override
	public ResponseEntity<Iterable<Users>> getUsers() {
		return new ResponseEntity<Iterable<Users>>(usersRepository.findAll(),HttpStatus.OK);
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public Users getUserByEmail(String email) {
		return (Users)usersRepository.findById(email).get();
	}

	@Override
	public boolean isUserPersentWithEmail(String email) {
		return usersRepository.findById(email).isPresent();
	}

}
