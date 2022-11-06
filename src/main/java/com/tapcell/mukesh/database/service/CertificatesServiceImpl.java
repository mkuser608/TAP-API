package com.tapcell.mukesh.database.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tapcell.mukesh.database.entity.Certificates;
import com.tapcell.mukesh.database.repository.CertificatesRepository;
import com.tapcell.mukesh.helper.FileDeleteHealper;
import com.tapcell.mukesh.response.OnlyMessageString;
import com.tapcell.mukesh.uploadLinkBuilderHelper.UploadLinkbuilder;

@Service
public class CertificatesServiceImpl implements CertificatesService {

	@Autowired
	private CertificatesRepository certificatesRepository;

	@Autowired
	private StudentsService studentsService;

	@Autowired
	private FileDeleteHealper fileDeleteHealper;

	@Autowired
	private UploadLinkbuilder uploadLinkbuilder;

	@Override
	public ResponseEntity<OnlyMessageString> saveNewCertificate(Certificates certificates, String roll, String email,
			MultipartFile multipartFile) {
		OnlyMessageString onlyMessageString = new OnlyMessageString("Certificate Saved");

		String pdfLink = uploadLinkbuilder.certificateUploadLink(roll, multipartFile);
		if (pdfLink.equals("upload Failed")) {
			onlyMessageString.setMessage(pdfLink);
			return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (pdfLink.equals("not pdf")) {
			onlyMessageString.setMessage("Only Pdf is Acceptable");
			return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.NOT_ACCEPTABLE);
		} else {
			certificates.setCertificatePdf(pdfLink);
		}

		studentsService.saveCertificates(certificates, email);

		return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<OnlyMessageString> updateCertificates(Certificates certificates, String roll, String email,
			MultipartFile multipartFile) {
		OnlyMessageString onlyMessageString = new OnlyMessageString("Certificate not Updated");
		if (!isValidUpdate(roll, certificates.getId().toString())) {
			onlyMessageString.setMessage("you are trying to update other");
			return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.NOT_ACCEPTABLE);
		}

		if (certificates.getId() == 0) {
			return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.NOT_ACCEPTABLE);
		} else {
			String oldStringPdf = certificatesRepository.findById(certificates.getId()).get().getCertificatePdf();

			String newPdfLink = uploadLinkbuilder.certificateUploadLink(roll, multipartFile);

			if (newPdfLink.equals("not pdf")) {
				onlyMessageString.setMessage("not a pdf file");
				return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.BAD_REQUEST);
			} else if (newPdfLink.equals("upload Failed")) {
				onlyMessageString.setMessage("upload Failed");
				return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				certificates.setCertificatePdf(newPdfLink);
				fileDeleteHealper.deleteProductImage(oldStringPdf);
				certificatesRepository.save(certificates);
				onlyMessageString.setMessage("Certificate Updated or Created");
				return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.ACCEPTED);
			}

		}
	}

	@Override
	public ResponseEntity<Set<Certificates>> getAllCertificateByRoll(String roll) {
		return new ResponseEntity<Set<Certificates>>(studentsService.getCertificatesByRoll(roll), HttpStatus.OK);
	}



	@Override
	public ResponseEntity<OnlyMessageString> deleteCertificate(Long id, String email, String roll) {
		OnlyMessageString onlyMessageString = new OnlyMessageString("Certificate deleted");
		System.out.println(email);
		if (isValidUpdate(roll, id.toString())) {
			fileDeleteHealper.deleteNoticePdf(certificatesRepository.findById(id).get().getCertificatePdf());
			studentsService.deleteCertificate(id, email);
			certificatesRepository.deleteById(id);
			return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.ACCEPTED);
		} else {
			onlyMessageString.setMessage("Certificate not present or trying to update others");
			return new ResponseEntity<OnlyMessageString>(onlyMessageString, HttpStatus.BAD_REQUEST);
		}
	}
	private boolean isValidUpdate(String loggedRoll, String id) {
		if (!certificatesRepository.existsById(Long.parseLong(id))) {
			return false;
		}
		String presentRoll = certificatesRepository.findById(Long.parseLong(id)).get().getCertificatePdf();

		int rollIntAt = presentRoll.indexOf("static/image/students/") + 22;

		return presentRoll.substring(rollIntAt, rollIntAt + loggedRoll.length()).equals(loggedRoll);
	}

}
