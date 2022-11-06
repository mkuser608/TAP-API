package com.tapcell.mukesh.database.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Certificates {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Certificate type should not blank")
	private String type;
	
	@NotBlank(message = "Enter valid Organization Name")
	private String organizationName;
	
	@NotBlank(message = "Enter project name if not then fill NA")
	private String projectName;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date startDate;
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date endDate;
	
	private String certificatePdf;
	


}
