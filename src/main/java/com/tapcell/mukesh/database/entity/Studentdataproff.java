package com.tapcell.mukesh.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Getter;

@Entity
@Immutable
@Getter
@Table(name = "studentdataproff")
public class Studentdataproff {

	@Id
	private String roll_No;
	
	private String branch;
	
	private String fullName;
	
	private String cgpa;
	
	private String passingYear;
	
	private String profilePhotoString;
}
