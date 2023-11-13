package com.study.studyproject.controller;

import com.study.studyproject.User;
import com.study.studyproject.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CiCdController {

	private final UserRepository userRepository;

	@GetMapping("/test")
	public String test() {
		return "success";
	}

	@GetMapping("/test2")
	public String test2() {
		User user = new User("user1");
		userRepository.save(user);
		return "success2";
	}

}
