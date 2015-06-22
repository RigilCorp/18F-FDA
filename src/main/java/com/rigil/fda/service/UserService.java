package com.rigil.fda.service;

import com.rigil.fda.dao.entity.User;

public interface UserService {
	
	public User findByEmail(String email);
	public User findByPhone(String phone);
	public User save(User user);
	
}