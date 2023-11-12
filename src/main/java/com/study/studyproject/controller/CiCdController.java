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
		return "test6";
	}

	@GetMapping("/test2")
	public String test2() {
		User user = new User("user1");
		userRepository.save(user.getId());
		return "docker run 할 때 환경변수 안줬는데 DB에 저장된다고?";
	}

}
