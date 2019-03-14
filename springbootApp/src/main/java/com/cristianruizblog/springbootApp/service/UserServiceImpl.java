package com.cristianruizblog.springbootApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cristianruizblog.springbootApp.entity.User;
import com.cristianruizblog.springbootApp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	public Iterable<User> getAllUsers(){
		return userRepository.findAll();
	}
	
}
