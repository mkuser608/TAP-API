package com.tapcell.mukesh.database.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("deprecation")
@Getter
@Setter
@Entity
@Table(name = "Users")
public class Users {

	@Id
	@Email(message = "Enter a valid Email Address example@domain.com")
	@NotEmpty(message = "email should not empty")
	private String email;

	@NotBlank(message = "Enter  a valid name")
	@Size(min = 2, max = 75)
	private String name;

	@NotEmpty(message = "password should not empty")
	private String password;
	
	@JsonIgnore
	private String studentRollNo;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "User_Role", joinColumns = { @JoinColumn(name = "User_Email") }, inverseJoinColumns = {
			@JoinColumn(name = "Role_Id") })
	private Set<Roles> roles;
	

	

}
