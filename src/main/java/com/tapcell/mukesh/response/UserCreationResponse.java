package com.tapcell.mukesh.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreationResponse {

	private String email;
	
	private String name;
	
	private String message;
}
