package com.tapcell.mukesh.database.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tapcell.mukesh.forUserEmail;
import com.tapcell.mukesh.database.entity.Addresses;
import com.tapcell.mukesh.database.entity.Certificates;
import com.tapcell.mukesh.database.entity.EducationalHistory;
import com.tapcell.mukesh.database.entity.SemesterResults;
import com.tapcell.mukesh.database.entity.Students;
import com.tapcell.mukesh.database.repository.StudentsRepository;
import com.tapcell.mukesh.helper.FileDeleteHealper;
import com.tapcell.mukesh.response.OnlyMessageString;
import com.tapcell.mukesh.uploadLinkBuilderHelper.UploadLinkbuilder;

@Service
public class StudentsServiceImpl implements StudentsService {

	@Autowired
	private StudentsRepository studentsRepository;

	@Autowired
	private FileDeleteHealper fileDeleteHealper;

	@Autowired
	private UploadLinkbuilder uploadLinkbuilder;

	@Override
	public ResponseEntity<Students> saveStudentsEntity(Students students) {

		Students oldStudents = studentsRepository.findByEmail(students.getEmail());
		if (oldStudents == null) {
			return new ResponseEntity<Students>(studentsRepository.save(students), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Students>(students, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Students> getStudent(String email) {
		return new ResponseEntity<Students>(studentsRepository.findByEmail(email), HttpStatus.OK);
	}

	@Override
	public Addresses saveStudentAddress(Addresses addresses, String email) {
		Students students = getStudentByEmail(email);
		Set<Addresses> addressSet = new HashSet<>();
		addressSet.add(addresses);
		addressSet.addAll(students.getAddresses());
		students.setAddresses(addressSet);
		studentsRepository.save(students);
		return addresses;
	}

	@Override
	public Set<Addresses> getAddressesByRoll(String roll) {
		return studentsRepository.findById(roll).get().getAddresses();
	}

	@Override
	public void deleteAddress(Long id, String roll) {
		Students students = studentsRepository.findById(roll).get();
		Set<Addresses> addresses = students.getAddresses();
		Set<Addresses> filteredAddress = new HashSet<>();

		for (Addresses x : addresses) {
			if (x.getId() != id) {
				filteredAddress.add(x);
			}
		}
		students.setAddresses(filteredAddress);
		studentsRepository.save(students);
		
	}
	
	@Override
	public SemesterResults saveStuentSemesterResult(SemesterResults semesterResults, String email) {
		Students students = getStudentByEmail(email);
		Set<SemesterResults> semesteRresultsSet = new HashSet<>();
		semesteRresultsSet.add(semesterResults);
		semesteRresultsSet.addAll(students.getSemesterResults());
		students.setSemesterResults(semesteRresultsSet);
		studentsRepository.save(students);

		return semesterResults;
	}

	@Override
	public Set<SemesterResults> getSemesterResultsByRoll(String roll) {
		return studentsRepository.findById(roll).get().getSemesterResults();
	}

	@Override
	public void deleteSemesterResult(Long id, String roll) {
		Students students = studentsRepository.findById(roll).get();
		Set<SemesterResults> oldSemesterResults = students.getSemesterResults();
		Set<SemesterResults> filteredSemesterResults  = new HashSet<>();

		for (SemesterResults x : oldSemesterResults) {
			if (x.getId() != id) {
				filteredSemesterResults.add(x);
			}
		}
		students.setSemesterResults(filteredSemesterResults);
		studentsRepository.save(students);
		
	}

	@Override
	public EducationalHistory saveStudentEducationalHistory(EducationalHistory educationalHistory, String email) {
		Students students = getStudentByEmail(email);
		Set<EducationalHistory> educationalHistoriesSet = new HashSet<>();
		educationalHistoriesSet.add(educationalHistory);
		educationalHistoriesSet.addAll(students.getEducationalHistories());
		students.setEducationalHistories(educationalHistoriesSet);
		studentsRepository.save(students);
		return educationalHistory;
	}

	@Override
	public Set<EducationalHistory> getEducationalHistoriesByRoll(String roll) {
		return studentsRepository.findById(roll).get().getEducationalHistories();
	}

	@Override
	public void deleteEducationHistory(Long id, String email) {
		Students students = getStudentByEmail(email);
		Set<EducationalHistory> educationalHistories = students.getEducationalHistories();
		Set<EducationalHistory> filteredEducationalHistories = new HashSet<>();

		for (EducationalHistory x : educationalHistories) {
			if (x.getId() != id) {
				filteredEducationalHistories.add(x);
			}
		}
		students.setEducationalHistories(filteredEducationalHistories);
		studentsRepository.save(students);
	}

	@Override
	public ResponseEntity<OnlyMessageString> saveProfilePhoto(String roll, String userEmail,
			MultipartFile multipartFile) {
		String newProfileLink = uploadLinkbuilder.profileUploadLink(roll, multipartFile);
		OnlyMessageString onlyMessageString = new OnlyMessageString("Profile Uploaded");
		if (newProfileLink.equals("upload Failed")) {
			onlyMessageString.setMessage(newProfileLink);
			return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (newProfileLink.equals("Required Formate")) {
			onlyMessageString.setMessage("only jpg, jpeg, png allowed");
			return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.INTERNAL_SERVER_ERROR);

		} else {
			Students students = getStudentByEmail(userEmail);
			String oldProfile = students.getProfilePhotoString();
			System.out.println(oldProfile);

			students.setProfilePhotoString(newProfileLink);
			studentsRepository.save(students);
			if (oldProfile != null) {
				fileDeleteHealper.deleteProductImage(oldProfile);
			}
			return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.OK);
		}

	}


	@Override
	public Certificates saveCertificates(Certificates certificates, String email) {
		Students students = getStudentByEmail(email);
		Set<Certificates> certificatesSet = new HashSet<>();
		certificatesSet.addAll(students.getCertificates());
		certificatesSet.add(certificates);
		students.setCertificates(certificatesSet);
		studentsRepository.save(students);
		return certificates;
	}

	@Override
	public Set<Certificates> getCertificatesByRoll(String roll) {
		return studentsRepository.findById(roll).get().getCertificates();
	}

	@Override
	public void deleteCertificate(Long id, String email) {
		Students students = getStudentByEmail(email);
		Set<Certificates> certificates = students.getCertificates();
		Set<Certificates> filteredCertificates = new HashSet<>();

		for (Certificates x : certificates) {
			if (x.getId() != id) {
				filteredCertificates.add(x);
			}
		}
		students.setCertificates(filteredCertificates);
		studentsRepository.save(students);
	}
	
	/**
	 * @param email
	 * @return
	 */
	private Students getStudentByEmail(String email) {
		Students students = studentsRepository.findByEmail(email);
		return students;
	}

	

}
