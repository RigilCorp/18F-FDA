package com.rigil.fda.repository;

import com.rigil.fda.dao.entity.UserEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends
JpaRepository<UserEntity, Integer> {

    @Query("Select u from UserEntity u where u.email=:email")
    List<UserEntity> findUserByEmail(
            @Param("email") String email);

    @Query("Select u from UserEntity u where u.phone=:phone")
    List<UserEntity> findUserByPhone(
            @Param("phone") String phone);
}


