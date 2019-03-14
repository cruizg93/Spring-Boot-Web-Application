package com.cristianruizblog.springbootApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cristianruizblog.springbootApp.entity.User;
import com.cristianruizblog.springbootApp.repository.RoleRepository;
import com.cristianruizblog.springbootApp.repository.UserRepository;
import com.cristianruizblog.springbootApp.service.UserService;


@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired 
	UserService userService;
	
	@GetMapping("/userForm")
	public String getUserForm(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab","active");
		return "user-form/user-view";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
