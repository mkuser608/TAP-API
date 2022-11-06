package com.tapcell.mukesh.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;


@Getter
@Entity
@Table(name = "User_Role")
public class User_Role {
	
	@Id
	@Column(name = "User_Email")
	private String user_Email;
	
	@Column(name = "Role_Id")
	private String role_Id;

}
