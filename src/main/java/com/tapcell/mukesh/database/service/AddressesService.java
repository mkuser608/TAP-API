package com.tapcell.mukesh.database.service;


import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tapcell.mukesh.database.entity.Addresses;
import com.tapcell.mukesh.response.OnlyMessageString;

public interface AddressesService {
	
	public ResponseEntity<OnlyMessageString> saveStudentAddress(Addresses addresses,String email);
	
	public ResponseEntity<OnlyMessageString> updateStudentAddress(String roll,Addresses addresses);
	
	public Set<Addresses> getAddressesByRoll(String roll);
	
	public ResponseEntity<OnlyMessageString> deleteAddressById(String roll, Long id);

}
