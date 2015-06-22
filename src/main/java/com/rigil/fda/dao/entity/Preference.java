package com.rigil.fda.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "preferences")
public class Preference {	
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "PREFERENCES_ID", unique = true, nullable = false, precision = 15, scale = 0)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="PREFERENCES_FDA_DATA_ID")
	private FDAData fdaData;
	
	@ManyToOne
	@JoinColumn(name="PREFERENCES_USER_ID")
	private User preferenceUser;
		
	//SETTERS and GETTERS
	
	public Long getId() {
		return id;
	}

	public FDAData getFdaData() {
		return fdaData;
	}
	
	public void setFdaData(FDAData fdaData) {
		this.fdaData = fdaData;
	}
	public User getPreferenceUser() {
		return preferenceUser;
	}

	public void setPreferenceUser(User preferenceUser) {
		this.preferenceUser = preferenceUser;
	}

}
