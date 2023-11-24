package com.study.studyproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id @GeneratedValue
	private Long id;
	private String username;
	private String password;

	private String email;
	private String role; // ROLE_USER, ROLE_ADMIN

	private String provider; // google
	private String providerId; // 12391203129401249

	public User(String username) {
		this.username = username;
	}

	@CreationTimestamp
	private Timestamp createDate;

	@Builder
	public User(String username, String password, String email, String role, String provider,
			String providerId, Timestamp createDate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
		this.createDate = createDate;
	}
}
