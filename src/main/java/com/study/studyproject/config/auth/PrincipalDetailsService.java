package com.study.studyproject.config.auth;

import com.study.studyproject.User;
import com.study.studyproject.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username = " + username);
		User userEntity = userRepository.findByUsername(username);
		if (userEntity != null) {
			return new PrincipalDetails(userEntity); // 이름이 있으면 이렇게 호출
		}
		return null; // 없으면 Null
	}
}
