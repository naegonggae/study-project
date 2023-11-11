package com.study.studyproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CiCdController {

	@GetMapping("/test")
	public String test() {
		return "test1";
	}

}
