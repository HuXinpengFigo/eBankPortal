package com.xinpenghu.ebankportal;

import com.xinpenghu.ebankportal.entity.User;
import com.xinpenghu.ebankportal.interfaces.UserRepository;
import com.xinpenghu.ebankportal.model.UserResponse;
import com.xinpenghu.ebankportal.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class EBankPortalApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	/*
	 * @test :- testMongo
	 * @Description :- Get  User Based on Email or Id
	 * @params:-
	 * */
	@Test
	void testMongo() {
		String email = "figohxp@example.com";
		String id = "6441f088c5b64a73c723dcd1";
		Optional<User> optionalUser = userRepository.findUserByEmail(email);
		System.out.println(optionalUser);
		optionalUser = userRepository.findById(id);
		System.out.println(optionalUser);
	}

}
