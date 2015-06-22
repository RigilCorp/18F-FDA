package com.rigil.fda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rigil.fda.dao.entity.FDAData;

public interface FDADataRepository extends
JpaRepository<FDAData, Integer> {

	@Query("Select f from FDAData f where f.dataCode=:dataCode")
	public List<FDAData> findFDADataByCode(
			@Param("dataCode") String dataCode);
	
	@Query("Select f from FDAData f where f.dataName=:dataName")
	public List<FDAData> findFDADataByName(
			@Param("dataName") String dataName);
}

