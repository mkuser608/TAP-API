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

public class SemesterResults {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "enter your univercity registration Number")
	private String registrationNo;
	
	@Column(nullable = false)
	@NotNull
    private int semNo;
	
    @Column(nullable = false)
    @NotNull
	@DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=1, fraction=2, message = "enter value like 0.00 or 9.99")
	private BigDecimal sgpa;
	
    @Column(nullable = false)
    @NotNull
	@DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=1, fraction=2, message = "enter value like 0.00 or 9.99")
	private BigDecimal cgpa;
	
    
	private String remarks;
	
}
