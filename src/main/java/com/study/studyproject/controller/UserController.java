package com.study.studyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/callback")
	public String callback(
			Authentication authentication,
			@AuthenticationPrincipal OAuth2User oauth2User) { // PrincipalDetails 타입으로 캐스팅 할수 없다고 뜸
		System.out.println("testOauthLogin==================");

		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal(); // UserDetails 를 OAuth2User 로 다운캐스팅
		System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());

		System.out.println("user = " + oauth2User.getAttributes());


		return "callback";
	}

}
