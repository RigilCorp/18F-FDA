package com.rigil.fda.repository;

import com.rigil.fda.dao.entity.Preference;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PreferencesRepository extends
JpaRepository<Preference, Integer> {

	@Query("Select p from Preference p where p.preferenceUser.email=:email")
	public List<Preference> findPreferencesByEmail(
			@Param("email") String email);
	
	@Query("Select p from Preference p where p.preferenceUser.email=:email and p.fdaData.dataName=:dataName")
	public List<Preference> findPreferencesByEmailAndDataName(
			@Param("email") String email, @Param("dataName") String dataName);
	
	@Query("Select p from Preference p where p.preferenceUser.phone=:phone")
	public List<Preference> findPreferencesByPhone(
			@Param("phone") String phone);
}

