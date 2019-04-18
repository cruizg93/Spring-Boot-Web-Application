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

	@Override
	public User getUserById(Long id) throws Exception {
		User user = userRepository.findById(id).orElseThrow(() -> new Exception("User does not exist"));
		return user;
	}
	
	public User updateUser(User formUser) throws Exception {
		
		User storedUser = userRepository.findById(formUser.getId())
				.orElseThrow(() -> new Exception("UsernotFound in updateUser -"+this.getClass().getName()));
		
		mapUser(formUser,storedUser);
		userRepository.save(storedUser);
		return storedUser;
	}
	
	protected void mapUser(User from,User to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
		to.setPassword(from.getPassword());
	}
	
	public void deleteUser(Long id) throws Exception {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new Exception("UsernotFound in deleteUser -"+this.getClass().getName()));

		userRepository.delete(user);
	}
}
