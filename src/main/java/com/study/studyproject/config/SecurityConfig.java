package com.study.studyproject.config;

import com.study.studyproject.config.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	private final PrincipalOauth2UserService principalOauth2UserService;

	// 비밀번호 암호화 빈등록
//	@Bean
//	public BCryptPasswordEncoder encodePwd() {
//		return new BCryptPasswordEncoder();
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.authorizeHttpRequests(authorize -> authorize
						.anyRequest().authenticated()
				)
				.oauth2Login(oauth2 -> oauth2
						.userInfoEndpoint(userInfo -> userInfo
								.userService(principalOauth2UserService)
						)
				)
				.oauth2Client(Customizer.withDefaults());
		return httpSecurity.build();
	}

}