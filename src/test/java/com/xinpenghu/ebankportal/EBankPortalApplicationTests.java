package com.xinpenghu.ebankportal;

import com.xinpenghu.ebankportal.entity.User;
import com.xinpenghu.ebankportal.interfaces.UserRepository;
import com.xinpenghu.ebankportal.service.TransactionKafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class EBankPortalApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TransactionKafkaProducer transactionKafkaProducer;
	@Test
	void testKafka() {
		String message = "OHHHHHHHHHHHHHHHH";
//		transactionKafkaProducer.sendMessage(message);
	}




	/*
	 * @test :- testMongo
	 * @Description :- Get  User Based on Email or Id
	 * @params:-
	 * */
	@Autowired
	private UserRepository userRepository;
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
