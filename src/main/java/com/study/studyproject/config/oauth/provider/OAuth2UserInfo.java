package com.study.studyproject.config.oauth.provider;

public interface OAuth2UserInfo {

	String getProviderId();
	String getProvider(); // github
	String getEmail();
	String getName();


}
