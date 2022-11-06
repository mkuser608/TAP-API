package com.tapcell.mukesh.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tapcell.mukesh.database.entity.Roles;
import com.tapcell.mukesh.database.repository.RolesRepository;

@Service
public class RolesService {
	
	@Autowired
	private RolesRepository repository;

	
	public void initDefaultRoles() {
		Roles adminroles = new Roles();
		adminroles.setRoleName("Admin");
		adminroles.setRoleDescription("Have Admin rights");
		repository.save(adminroles);
		
		Roles studentRoles = new Roles();
		studentRoles.setRoleName("student");
		studentRoles.setRoleDescription("Have Student Rights");
		repository.save(studentRoles);
		
		Roles professorRoles = new Roles();
		professorRoles.setRoleName("Professor");
		professorRoles.setRoleDescription("Have all professor Rights");
		repository.save(professorRoles);
		
		Roles cordinatorRoles = new Roles();
		cordinatorRoles.setRoleName("Cordinator");
		cordinatorRoles.setRoleDescription("Have all Tap Cordinator role");
		repository.save(cordinatorRoles);
		
		Roles companyRoles = new Roles();
		companyRoles.setRoleName("Company");
		companyRoles.setRoleDescription("Have Company Rights");
		repository.save(companyRoles);
		
	}
}
