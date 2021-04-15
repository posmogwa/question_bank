package com.ezen.question_bank.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.question_bank.entity.UserEntity;
import com.ezen.question_bank.repository.UserRepository;
import com.ezen.question_bank.viewmodel.UserForm;

@Controller
@RequestMapping("/user")
public class Usercontroller {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/register")
	public String register(
			UserForm userForm
			) {
		
		return"user/register";
	}
	
	@PostMapping("/register")
	public String register(
			@Valid UserForm userForm,
			BindingResult bindingResult
			) {
		
		if(bindingResult.hasErrors()) {
			return "user/register";
		}
		
		UserEntity userEntity= new UserEntity();
		userEntity.setUser_id(userForm.getUser_id());
		userEntity.setPassword(userForm.getPassword());
		userEntity.setTel(userForm.getTel());
		
		Date current_date= Calendar.getInstance().getTime();
		java.sql.Timestamp ts= new Timestamp(current_date.getTime());
		
		userEntity.setRegistered_date(ts);

		
		userRepository.save(userEntity);

		
		return "user/register_result";
	}
	

	
	@GetMapping("/login")
	public String login(
			UserForm userForm
			) {
		
		return "user/login";
	}
	

	@PostMapping("/login")
	public String login(
			@Valid UserForm userForm, 
			BindingResult bindingResult,
			Model model
			) {
		
		if(bindingResult.hasErrors()) {
			return "user/login";
		}
		
		
		UserEntity userEntity = new UserEntity();
		
		if (userForm.getUser_id() != null) {
			userEntity = userRepository.getOne(userEntity.getUser_id());
		}
		
		model.addAttribute("userForm", userForm);
 
		return "user/login_result";

	}

}
