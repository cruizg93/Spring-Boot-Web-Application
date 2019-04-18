package com.cristianruizblog.springbootApp.service;


import com.cristianruizblog.springbootApp.entity.User;

public interface UserService {

	public Iterable<User> getAllUsers();
	
	public User createUser(User formUser) throws Exception;
	
	public User getUserById(Long id) throws Exception;
	
	public User updateUser(User formUser) throws Exception;
	
	public void deleteUser(Long id) throws Exception;
		
}
