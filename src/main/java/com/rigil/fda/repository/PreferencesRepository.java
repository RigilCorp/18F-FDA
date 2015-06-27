package com.rigil.fda.repository;

import com.rigil.fda.dao.entity.PreferenceEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PreferencesRepository extends
JpaRepository<PreferenceEntity, Integer> {

    @Query("Select p from PreferenceEntity p where p.preferenceUserEntity.email=:email")
    List<PreferenceEntity> findPreferencesByEmail(
            @Param("email") String email);

    @Query("Select p from PreferenceEntity p where p.preferenceUserEntity.email=:email and p.fdaDataEntity.dataName=:dataName")
    List<PreferenceEntity> findPreferencesByEmailAndDataName(
            @Param("email") String email, @Param("dataName") String dataName);

    @Query("Select p from PreferenceEntity p where p.preferenceUserEntity.phone=:phone")
    List<PreferenceEntity> findPreferencesByPhone(
            @Param("phone") String phone);
}

