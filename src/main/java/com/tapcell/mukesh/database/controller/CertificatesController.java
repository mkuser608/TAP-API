package com.tapcell.mukesh.database.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tapcell.mukesh.forUserEmail;
import com.tapcell.mukesh.database.entity.Certificates;
import com.tapcell.mukesh.database.service.CertificatesService;
import com.tapcell.mukesh.helper.FileUploadHealper;
import com.tapcell.mukesh.response.OnlyMessageString;

@RestController
@PreAuthorize("hasAuthority('ROLE_student')")
public class CertificatesController {

	@Autowired
	private CertificatesService certificatesService;

	@Autowired
	private forUserEmail forUserEmail;

	

	@GetMapping("/findAllCertificate")
	public ResponseEntity<Set<Certificates>> findCertificates() {
		return certificatesService.getAllCertificateByRoll(forUserEmail.getUserRollNo());
	}

	@DeleteMapping("/deleteCertificate")
	public ResponseEntity<OnlyMessageString> deleteCertificateByid(@RequestParam Long id) {

		return certificatesService.deleteCertificate(id, forUserEmail.getUserEmail(), forUserEmail.getUserRollNo());

	}

	@PostMapping("/createNewCertificate")
	public ResponseEntity<OnlyMessageString> saveNewCertificate(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("type") String type, @RequestParam("organizationName") String organizationName,
			@RequestParam("projectName") String projectName, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws ParseException {

		

		if (!type.trim().equals("") && !organizationName.trim().equals("") && !projectName.trim().equals("")
				&& !startDate.trim().equals("") && !endDate.trim().equals("")) {
			Certificates certificates = new Certificates();
			certificates.setType(type);
			certificates.setOrganizationName(organizationName);
			certificates.setProjectName(projectName);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			certificates.setStartDate(formatter.parse(endDate));
			certificates.setEndDate(formatter.parse(endDate));

			return certificatesService.saveNewCertificate(certificates, forUserEmail.getUserRollNo(),
					forUserEmail.getUserEmail(), multipartFile);

		} else {
			OnlyMessageString onlyMessageString = new OnlyMessageString("few details are missing");
			return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/updateCertificate")
	public ResponseEntity<OnlyMessageString> updateCertificate(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("id") String id, @RequestParam("type") String type,
			@RequestParam("organizationName") String organizationName, @RequestParam("projectName") String projectName,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate)
			throws ParseException {

		

		if (!type.trim().equals("") && !organizationName.trim().equals("") && !projectName.trim().equals("")
				&& !startDate.trim().equals("") && !endDate.trim().equals("")) {
			Certificates certificates = new Certificates();
			certificates.setId(Long.parseLong(id));
			certificates.setType(type);
			certificates.setOrganizationName(organizationName);
			certificates.setProjectName(projectName);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			certificates.setStartDate(formatter.parse(endDate));

			certificates.setEndDate(formatter.parse(endDate));

			return certificatesService.updateCertificates(certificates, forUserEmail.getUserRollNo(),forUserEmail.getUserEmail(), multipartFile);
		} else {
			OnlyMessageString onlyMessageString = new OnlyMessageString("Enter Other Details");
			return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.BAD_REQUEST);
		}
	}

}
