package com.tapcell.mukesh.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tapcell.mukesh.database.entity.Notice;
import com.tapcell.mukesh.database.repository.NoticeRepository;
import com.tapcell.mukesh.helper.FileDeleteHealper;
import com.tapcell.mukesh.response.OnlyMessageString;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private FileDeleteHealper fileDeleteHealper;

	@Override
	public ResponseEntity<Notice> createNewNoticeNotice(Notice notice) {
		if(notice.getId()==0L) {
		Notice notice2 = noticeRepository.save(notice);
		return new ResponseEntity<Notice>(notice2 , HttpStatus.CREATED);
		}
		return new ResponseEntity<Notice>(notice,HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<OnlyMessageString> deleteNotice(Long id) {
		OnlyMessageString onlyMessageString = new OnlyMessageString("Notice Deleted!");
		if(noticeRepository.existsById(id)) {
			fileDeleteHealper.deleteNoticePdf(noticeRepository.findById(id).get().getNoticeUrl());
			noticeRepository.deleteById(id);
		}else {
			onlyMessageString.setMessage("Notice can not be deleted!");
		}
		return new ResponseEntity<OnlyMessageString>(onlyMessageString,HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Notice> updateNotice(Notice notice) {
		if(notice.getId()!=0L) {
			
			return new ResponseEntity<Notice>(noticeRepository.save(notice),HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<Notice>(notice,HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Iterable<Notice>> viewAllNotice() {
		return new ResponseEntity<Iterable<Notice>>(noticeRepository.findAll(),HttpStatus.OK);
	}

}
