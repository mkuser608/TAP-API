package com.tapcell.mukesh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tapcell.mukesh.database.repository.UsersRepository;

import lombok.Getter;

@Component
public class forUserEmail {
	
	@Autowired
	private UsersRepository usersRepository;
	
	private  String userEmail;
	
	@Getter
	private String userRollNo;
	
	@Getter
	private String userEmailOtherthanStudent;


	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		if(usersRepository.findById(userEmail).get().getStudentRollNo()==null) {
			this.userEmailOtherthanStudent =userEmail;
			return;
		}
		
		this.userEmail=userEmail;
		setUserRollNo();
	}
	 private void setUserRollNo() {
		 this.userRollNo = usersRepository.findById(userEmail).get().getStudentRollNo();
	 }

	

}
