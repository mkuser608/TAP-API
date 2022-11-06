package com.tapcell.mukesh.database.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tapcell.mukesh.forUserEmail;
import com.tapcell.mukesh.database.entity.Addresses;
import com.tapcell.mukesh.database.service.AddressesService;
import com.tapcell.mukesh.response.OnlyMessageString;

@RestController
@PreAuthorize("hasAuthority('ROLE_student')")
public class AddressesController {
	
	@Autowired
	private AddressesService addressesService;
	
	@Autowired
	private forUserEmail forUserEmail;

	
	@PostMapping("/saveStudentAddress")
	public ResponseEntity<OnlyMessageString> saveStudentAddress(@Valid @RequestBody Addresses addresses){
		return addressesService.saveStudentAddress(addresses,forUserEmail.getUserEmail());
	}
	
	@PostMapping("/updateStudentAddress")
	public ResponseEntity<OnlyMessageString> updateStudentAddress(@Valid @RequestBody Addresses addresses){
		return addressesService.updateStudentAddress(forUserEmail.getUserRollNo(), addresses);
	}
	
	@GetMapping("/getStudentAddress")
	public Set<Addresses> getStudentAddress(){
		return addressesService.getAddressesByRoll(forUserEmail.getUserRollNo());
	}
	
	@DeleteMapping("/deleteStudentAddress")
	public ResponseEntity<OnlyMessageString> deleteStudentAddressById(@RequestParam Long id){
		return addressesService.deleteAddressById(forUserEmail.getUserRollNo(), id);
	}
}
