package com.xinpenghu.ebankportal;

import com.google.gson.Gson;
import com.xinpenghu.ebankportal.entity.ExchangeRate;
import com.xinpenghu.ebankportal.entity.User;
import com.xinpenghu.ebankportal.repository.UserRepository;
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

	/*
	 * @test :- testKafka
	 * @Description :- Add a user through send message to kafka
	 * @params:-
	 * */
	@Test
	void testKafka() {
		User user = new User("test@example.com", "123456");
		if(!userRepository.existsByEmail("test@example.com"))
			userKafkaProducer.sendMessage(user);
	}

	/*
	 * @test :- testMongo
	 * @Description :- Get  User Based on Email
	 * @params:-
	 * */
	@Autowired
	private UserRepository userRepository;
	@Test
	void testMongo() {
		String email = "figohxp@example.com";
		Optional<User> optionalUser = userRepository.findUserByEmail(email);
		System.out.println(optionalUser);
	}

	/*
	 * @test :- testExchangeAPI
	 * @Description :- Get  exchange rate based on HKD
	 * @params:-
	 * */
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
