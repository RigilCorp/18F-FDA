package com.rigil.fda.service;

import java.util.List;

import com.rigil.fda.dao.entity.User;
import com.rigil.fda.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Autowired
	UserRepository repository;


	@Override
	public User findByEmail(String email) {
		logger.debug("Query User by [Email: {}]", email);
		List<User> userList = repository.findUserByEmail(email);
		if(userList != null && userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	@Override
	public User findByPhone(String phone) {
		logger.debug("Query User by [Phone: {}]", phone);
		List<User> userList = repository.findUserByEmail(phone);
		if(userList != null && userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}
	
	@Override
	@Transactional(readOnly=false)
	public User save(User user) {
		logger.debug("Saving user object [{}]", user);
		return repository.save(user);
		
	}
	
}
	