package com.tapcell.mukesh.database.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("deprecation")
@Getter
@Setter
@Entity
@Table(name = "Students")
public class Students {
	
	
	@Id
	@NotBlank(message = "enter your valid roll no")
	private String roll_No;
	

	private String profilePhotoString;
	
	@NotBlank(message = "enter you valid registration no if not then enter NA")
	private String registrationString_No;
	
	@NotBlank(message = "enter your first name")
	private String first_Name;
	
	@NotBlank(message = "enter last name")
	private String last_Name;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date date_of_birth;
	
	@NotBlank(message = "enter your branch")
	private String branch;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date date_of_college_enrolls;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date date_of_passing;
	
	
    
	@DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=1, fraction=2, message = "enter value like 0.00 or 9.99")
	private BigDecimal cgpa;
	
	
	@Email
	private String email;
	
	@NotBlank(message = "enter your valid mobile no")
	@Size(min = 10, max = 10)
	private String mobile_No;
	
	@NotBlank(message = "enter your valid Whatsappmobile no")
	@Size(min = 10, max = 10)
	private String whatsApp_No;
	
	@NotBlank(message = "Enter your Father Name")
	private  String father_Name;
	
	@NotBlank(message = "Enter your Mother Name")
	private String mother_Name;
	
	@NotBlank(message = "Enter your father Occupation")
	private String father_Occupation;
	
	@NotBlank(message = "enter your mother occupation")
	private String mother_Occupation;
	
	private String collegeName="BIT SINDRI";
	

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "StudentSemResult", joinColumns = { @JoinColumn(name = "StudentRollNo") }, inverseJoinColumns = {
			@JoinColumn(name = "SemesterResultId") })
	private Set<SemesterResults> SemesterResults;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "StudentEducationHistory", joinColumns = { @JoinColumn(name = "StudentRollNo") }, inverseJoinColumns = {
			@JoinColumn(name = "Eh_Id") })
	private Set<EducationalHistory> educationalHistories;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "StudentAddress", joinColumns = { @JoinColumn(name = "StudentRollNo") }, inverseJoinColumns = {
			@JoinColumn(name = "AddressId") })
	private Set<Addresses> addresses;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "StudentCertificates", joinColumns = { @JoinColumn(name = "StudentRollNo") }, inverseJoinColumns = {
			@JoinColumn(name = "CertificateId") })
	private Set<Certificates> certificates;
	
	
	
}
