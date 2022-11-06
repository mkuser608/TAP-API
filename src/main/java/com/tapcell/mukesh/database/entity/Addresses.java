package com.tapcell.mukesh.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Addresses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, max = 45, message = "Enter address type")
	@Column(nullable = false)
	private String addressType;

	@Size(min = 2, max = 255, message = "address text should not blank or greater than 255 char")
	@Column(nullable = false)
	private String addressText;

	@Size(min = 2, max = 45, message = "enter City name")
	@Column(nullable = false)
	private String city;

	@Size(min = 2, max = 45, message = "enter valid district")
	@Column(nullable = false)
	private String district;

	@Size(min = 3, max = 45, message = "enter your valid state")
	@Column(nullable = false)
	private String state;

	@Size(min = 3, max = 45, message = "enter valid country name")
	@Column(nullable = false)
	private String country;

	@Column(nullable = false)
	private int pinCode;

}
