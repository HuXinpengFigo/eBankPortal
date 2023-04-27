package com.xinpenghu.ebankportal.service;

import com.xinpenghu.ebankportal.entity.User;
import com.xinpenghu.ebankportal.mongorepo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserKafkaConsumer.class);

    @Autowired
    UserRepository userRepository;

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(User user){
        userRepository.save(user);
        LOGGER.info(String.format("Json message recieved -> %s", user.toString()));
    }
}
