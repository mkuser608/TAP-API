package com.tapcell.mukesh.database.service;

import org.springframework.http.ResponseEntity;

import com.tapcell.mukesh.database.entity.Notice;
import com.tapcell.mukesh.response.OnlyMessageString;

public interface NoticeService {
	
	public ResponseEntity<Notice> createNewNoticeNotice(Notice notice);
	
	public ResponseEntity<Iterable<Notice>> viewAllNotice();
	
	public ResponseEntity<OnlyMessageString> deleteNotice(Long id);
	
	public ResponseEntity<Notice> updateNotice(Notice notice);
	
}
