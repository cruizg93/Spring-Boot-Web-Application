package com.cristianruizblog.springbootApp.service;

import java.util.Optional;

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

	@Override
	public User createUser(User formUser) throws Exception {
		if( !checkUsernameExists(formUser)
				&& checkPasswordMatch(formUser)){

			formUser.setPassword(formUser.getPassword());
			
			User createdUser = userRepository.save(formUser);
			return createdUser;
		}
		return formUser;
	}
	
	private boolean checkUsernameExists(User user) throws Exception{
		Optional<User> existentUser = userRepository.findByUsername(user.getUsername());
		if(existentUser.isPresent()) {
			throw new Exception("Username already exists");
		}
		return false;
	}

	private boolean checkPasswordMatch(User user)throws Exception{
		if( user.getPassword()!=null
				&& !user.getPassword().equals(user.getConfirmPassword()) ){
			
			throw new Exception("Passwords does not match");
		}
		return true;
	}
	
}
