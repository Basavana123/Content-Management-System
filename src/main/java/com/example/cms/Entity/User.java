 package com.example.cms.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "users")
@EntityListeners(value =  AuditingEntityListener.class )
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String userName;
	private String email;
	private String password;
	@CreatedDate
	@Column(updatable = false)
	private LocalDate createdAt;
	@LastModifiedDate
	private LocalDate lastmodifiedAt;
	
	private boolean deleted;
	
	@OneToMany(mappedBy = "user")
	private List<Blog> blogs=new ArrayList<Blog>();
	
	
}
