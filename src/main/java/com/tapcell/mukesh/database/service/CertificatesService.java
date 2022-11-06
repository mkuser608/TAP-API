package com.tapcell.mukesh.database.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.tapcell.mukesh.database.entity.Certificates;
import com.tapcell.mukesh.response.OnlyMessageString;

public interface CertificatesService {
	
	public ResponseEntity<OnlyMessageString> saveNewCertificate(Certificates certificates,String roll,
														String email,MultipartFile multipartFile);
	
	public ResponseEntity<OnlyMessageString> updateCertificates(Certificates certificates,String roll,
															String email, MultipartFile multipartFile);
	
	public ResponseEntity<OnlyMessageString> deleteCertificate(Long id, String email,String roll);
	
	public ResponseEntity<Set<Certificates>> getAllCertificateByRoll(String roll);
	

}
