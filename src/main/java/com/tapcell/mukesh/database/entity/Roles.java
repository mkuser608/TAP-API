package com.tapcell.mukesh.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Roles")
public class Roles {
	
	@Id
	private String roleName;
	
	private String roleDescription;

}
