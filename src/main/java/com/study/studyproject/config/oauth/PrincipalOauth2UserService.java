package com.study.studyproject.config.oauth;

import com.study.studyproject.User;
import com.study.studyproject.UserRepository;
import com.study.studyproject.config.auth.PrincipalDetails;
import com.study.studyproject.config.oauth.provider.GithubUserInfo;
import com.study.studyproject.config.oauth.provider.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;

	// 깃허브로 받은 userRequest 데이터에 대한 후처리되는 함수
	// 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("userRequest = " + userRequest);
		System.out.println("getClientRegistration = " + userRequest.getClientRegistration()); // getClientRegistrationID 로 어떤 OAuth 로 로그인했는지 알수 있음
		System.out.println("getAccessToken = " + userRequest.getAccessToken());
		System.out.println("getTokenValue = " + userRequest.getAccessToken().getTokenValue());
		System.out.println("getAdditionalParameters" + userRequest.getAdditionalParameters());
		System.out.println(userRequest.getClientRegistration().getProviderDetails().getAuthorizationUri());
		System.out.println(super.loadUser(userRequest).getAttributes());

		OAuth2User oAuth2User = super.loadUser(userRequest);

		// 회원가입을 강제로 진행
		OAuth2UserInfo oAuth2UserInfo = null;

		if (userRequest.getClientRegistration().getRegistrationId().equals("github")) {
			System.out.println("깃허브 로그인 요청");
			oAuth2UserInfo = new GithubUserInfo(oAuth2User.getAttributes());

		} else {
			System.out.println("깃허브만 지원해요");
		}

		System.out.println(oAuth2User.getAttributes());

		String provider = oAuth2UserInfo.getProvider(); // google // 일반로그인과 구별하는방법은 Provider 가 있는지 없는지 체크하면된다.
		String providerId = oAuth2UserInfo.getProviderId(); // oAuth2User.getAttribute("sub");
		String username = provider + "_" + providerId; // google_1231289385
		String password = "비밀번호";
		String email = oAuth2UserInfo.getEmail(); // oAuth2User.getAttribute("email");
		String role = "ROLE_USER";

		User userEntity = userRepository.findByUsername(username);
		if (userEntity == null) {
			System.out.println("로그인이 최초입니다");
			userEntity = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.role(role)
					.provider(provider)
					.providerId(providerId)
					.build();
			userRepository.save(userEntity);
		} else {
			System.out.println("로그인을 이미 한적이 있습니다");
		}

		return new PrincipalDetails(userEntity, oAuth2User.getAttributes()); // authentication 객체안에 들어감
		// 일반 로그인이면 User 만들고 있겠지만, Oauth 로그인은 맵으로 user 와 attributes 를 들고 있음
		//return super.loadUser(userRequest);

		// 구글 로그인 버튼클릭 -> 로그인 창 -> 로그인완료 -> code 를 리턴(OAuth-client 라이브러리가 받아줌) -> AccessToken 요청
		// 요청 받은것 까지가 userRequest 정보 -> loadUser 함수 -> 구글로부터 회원프로필정보 받아줌
	}
}
