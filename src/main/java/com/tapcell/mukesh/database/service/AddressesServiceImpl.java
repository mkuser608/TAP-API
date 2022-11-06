package com.tapcell.mukesh.database.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tapcell.mukesh.database.entity.Addresses;
import com.tapcell.mukesh.database.repository.AddressesRepository;
import com.tapcell.mukesh.response.OnlyMessageString;

@Service
public class AddressesServiceImpl implements AddressesService {

	@Autowired
	private AddressesRepository addressesRepository;

	@Autowired
	private StudentsService studentsService;

	@Override
	public ResponseEntity<OnlyMessageString> saveStudentAddress(Addresses addresses, String email) {
		studentsService.saveStudentAddress(addresses, email);

		return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Address Created And saved"),
				HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<OnlyMessageString> updateStudentAddress(String roll, Addresses addresses) {
		if (isValidUpdate(roll, addresses.getId().toString())) {
			addressesRepository.save(addresses);
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Address Updated"), HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Address not Updated or not a valid Update"), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public Set<Addresses> getAddressesByRoll(String roll) {
		return studentsService.getAddressesByRoll(roll);
	}
	
	@Override
	public ResponseEntity<OnlyMessageString> deleteAddressById(String roll, Long id) {
		if (isValidUpdate(roll, id.toString())) {
			studentsService.deleteAddress(id, roll);
			addressesRepository.deleteById(id);
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Address Deleted"),HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<OnlyMessageString>(new OnlyMessageString("Address not Deleted"),HttpStatus.BAD_REQUEST);
		}
	}
	
	private boolean isValidUpdate(String roll, String id) {
		Set<Addresses> savedAddresses = studentsService.getAddressesByRoll(roll);

		for (Addresses x : savedAddresses) {
			if (x.getId() == Long.parseLong(id)) {
				return true;
			}
		}

		return false;
	}

	

}
