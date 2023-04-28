package com.xinpenghu.ebankportal.service;

import com.xinpenghu.ebankportal.entity.Transaction;
import com.xinpenghu.ebankportal.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionKafkaConsumer.class);

    @Autowired
    TransactionRepository transactionRepository;

    /**
     * Add transaction to database
     * @param transaction
     */
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Transaction transaction){
        transactionRepository.save(transaction);
        LOGGER.info(String.format("Message received -> %s", transaction.toString()));
    }
}

