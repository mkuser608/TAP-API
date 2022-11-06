package com.tapcell.mukesh.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tapcell.mukesh.database.entity.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long>{


	
}
