package com.study.studyproject.config.auth;


import com.study.studyproject.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {
	// UserDetails, OAuth2User 두 가지 타입을 PrincipalDetails 타입으로 묶어서 시큐리티 세션에 저장, 각각 메서드들은 오버라이드해서 사용

	private User user;
	private Map<String, Object> attributes;

	// 일반 로그인 생성자
	public PrincipalDetails(User user) {
		this.user = user;
	}

	// Oauth 로그인 생성자
	public PrincipalDetails(User user, Map<String, Object> attributes) { //attributes 를 토대로 user 객체를 만들어야함
		this.user = user;
		this.attributes = attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>(); // GrantedAuthority 타입으로 맞춰줘
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getRole(); // 이게 권한임
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호 유효기간 만료되었는지
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 사이트에서 1년동안 로그인을 안하면 휴먼유저로 분류하기로 함 이런경우 사용됨
		// 현재시간 - 마지막으로 로그인한 시간 > 1년 -> false
		return true;
	}

	// Oauth 메서드
	// - {sub=113676134780663010177,
	// - name=이상훈, given_name=상훈, family_name=이,
	// - picture=https://lh3.googleusercontent.com/a/AAcHTtfc9NCakKqJIH5R1j1obxOWqNZ25twNNE7lGe-HTchd=s96-c,
	// - [email=tkdtkd975@gmail.com](mailto:email=tkdtkd975@gmail.com),
	// - email_verified=true, locale=ko}
	@Override
	public String getName() {
		String sub = (String) attributes.get("sub");
		return sub;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
}
