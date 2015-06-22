package com.rigil.fda.repository;

import com.rigil.fda.dao.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends
JpaRepository<User, Integer> {

	@Query("Select u from User u where u.email=:email")
	public List<User> findUserByEmail(
			@Param("email") String email);
	
	@Query("Select u from User u where u.phone=:phone")
	public List<User> findUserByPhone(
			@Param("phone") String phone);
}


