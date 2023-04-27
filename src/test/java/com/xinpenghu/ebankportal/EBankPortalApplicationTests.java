package com.xinpenghu.ebankportal;

import com.google.gson.Gson;
import com.xinpenghu.ebankportal.entity.ExchangeRate;
import com.xinpenghu.ebankportal.entity.User;
import com.xinpenghu.ebankportal.mongorepo.UserRepository;
import com.xinpenghu.ebankportal.service.TransactionKafkaProducer;
import com.xinpenghu.ebankportal.service.UserKafkaProducer;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@SpringBootTest
class EBankPortalApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserKafkaProducer userKafkaProducer;

	@Test
	void testKafka() {
		User user = new User("figohxp@example.com", "123456");
		userKafkaProducer.sendMessage(user);
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


	@Test
	void testExchangeAPI() throws JSONException {
		String url = "https://v6.exchangerate-api.com/v6/1af5924cda5c9b070747a158/latest/HKD";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);

		JSONObject jsonObject = new JSONObject(result);

		String exchangeRateString = jsonObject.getString("conversion_rates");
		Gson gson = new Gson();
		ExchangeRate exchangeRate = gson.fromJson(exchangeRateString, ExchangeRate.class);

		System.out.println(exchangeRate.toString());
	}

}
