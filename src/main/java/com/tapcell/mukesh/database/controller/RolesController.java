package com.tapcell.mukesh.database.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tapcell.mukesh.database.service.RolesService;

@RestController
public class RolesController {
	
	@Autowired
	private RolesService rolesService;
	
	@PostConstruct
	public void initDefaultRoles() {
		rolesService.initDefaultRoles();
	}
	


}
