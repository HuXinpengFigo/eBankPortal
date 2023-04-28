package com.xinpenghu.ebankportal.service;

import com.xinpenghu.ebankportal.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class TransactionKafkaProducer {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionKafkaProducer.class);

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public TransactionKafkaProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Send message to transaction kafka
     * @param transaction
     */
    public void sendMessage(Transaction transaction){

        LOGGER.info(String.format("Message sent -> %s", transaction.toString()));

        Message<Transaction> message = MessageBuilder
                .withPayload(transaction)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(message);
    }
}
