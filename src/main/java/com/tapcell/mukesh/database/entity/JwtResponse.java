package com.tapcell.mukesh.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {

	

	
	private String jwtToken;
	
	private String userRole;
}