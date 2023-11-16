package com.study.studyproject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CiCdControllerTest {

	@Test
	@DisplayName("aaa")
	public void test() {
		assertThat(1).isEqualTo(1);
	}


}