package com.tapcell.mukesh.database.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.tapcell.mukesh.database.entity.Addresses;
import com.tapcell.mukesh.database.entity.Certificates;
import com.tapcell.mukesh.database.entity.EducationalHistory;
import com.tapcell.mukesh.database.entity.SemesterResults;
import com.tapcell.mukesh.database.entity.Students;
import com.tapcell.mukesh.response.OnlyMessageString;

public interface StudentsService {

	public ResponseEntity<Students> saveStudentsEntity(Students students);

	public ResponseEntity<Students> getStudent(String email);

	public ResponseEntity<OnlyMessageString> saveProfilePhoto(String roll, String userEmail,
			MultipartFile multipartFile);

	public Addresses saveStudentAddress(Addresses addresses, String email);
	
	public Set<Addresses> getAddressesByRoll(String roll);
	
	public void deleteAddress(Long id, String roll);

	public SemesterResults saveStuentSemesterResult(SemesterResults semesterResults, String email);
	
	public Set<SemesterResults> getSemesterResultsByRoll(String roll);
	
	public void deleteSemesterResult(Long id, String roll);

	public EducationalHistory saveStudentEducationalHistory(EducationalHistory educationalHistory, String email);
	
	public Set<EducationalHistory> getEducationalHistoriesByRoll(String roll);
	
	public void deleteEducationHistory(Long id, String email);
 
	public Certificates saveCertificates(Certificates certificates, String email);

	public Set<Certificates> getCertificatesByRoll(String roll);

	public void deleteCertificate(Long id, String email);

}
