package com.rigil.fda.repository;

import java.util.List;

import com.rigil.fda.dao.entity.FDADataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FDADataRepository extends
JpaRepository<FDADataEntity, Integer> {

    @Query("Select f from FDADataEntity f where f.dataCode=:dataCode order by f.dataName")
    List<FDADataEntity> findFDADataByCode(
            @Param("dataCode") String dataCode);

    @Query("Select f from FDADataEntity f where f.dataName=:dataName")
    List<FDADataEntity> findFDADataByName(
            @Param("dataName") String dataName);

    @Query("Select f from FDADataEntity f where f.dataName=:dataName and f.dataCode=:dataCode")
    FDADataEntity findFDADataByNameAndCode(
            @Param("dataName") String dataName, @Param("dataCode") String dataCode);

}

