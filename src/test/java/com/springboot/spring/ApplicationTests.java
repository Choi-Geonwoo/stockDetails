package com.springboot.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.main;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ApplicationTests {

	public static void main(String[] args) {
		log.info("??");
	}

	@Test
	void contextLoads() {
		System.out.println("??");
	}

}
