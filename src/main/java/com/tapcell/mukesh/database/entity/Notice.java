package com.tapcell.mukesh.database.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Notice")
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Email
	private String pubEmail;
	
	@NotBlank(message = "Notice title Should not blank")
	private String title;
	
	private String noticeUrl;
	
	@CreationTimestamp
	@Column(name="Publish_At", nullable = false, updatable = false)
	private Date publishDate;
	
	@UpdateTimestamp
	private Date updatedDate;
}
