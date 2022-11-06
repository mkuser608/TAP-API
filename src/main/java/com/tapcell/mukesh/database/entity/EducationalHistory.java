package com.tapcell.mukesh.database.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EducationalHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "enter Educational history type example 10th or 12th")
	private String type;
	
	@NotBlank(message = "enter name examination like secondary examination")
	private String nameOfExam;
	
	@NotBlank(message = "enter valid board name like JAC or CBSE")
	private String board;
	
	@NotBlank(message = "Enter your School Name")
	private String nameOfSchool;
	
	@NotBlank(message = "enter results type eg CGPA, PERCENTAGE")
	private String resultPublishType;
	
	@Column(nullable = false)
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2, message = "enter value like 00.00 or 100.00")
	private BigDecimal result;
	
	
	private String remark;
	
}
