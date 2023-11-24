package com.study.studyproject.config.oauth.provider;

import java.util.Map;

public class GithubUserInfo implements OAuth2UserInfo {

	private Map<String, Object> attributes;

	public GithubUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getProviderId() {
		return String.valueOf(attributes.get("id"));
	}

	@Override
	public String getProvider() {
		return "github";
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("html_url");
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}
}
