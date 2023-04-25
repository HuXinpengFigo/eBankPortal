package com.xinpenghu.ebankportal.service;

import com.xinpenghu.ebankportal.entity.Transaction;
import com.xinpenghu.ebankportal.interfaces.TransactionRepository;
import com.xinpenghu.ebankportal.model.AddTransactionRequest;
import com.xinpenghu.ebankportal.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionKafkaProducer transactionKafkaProducer;

    public String add(AddTransactionRequest request) {
        Transaction transactionToAdd = new Transaction(request.email, request.amount, request.type,request.description, request.currency, LocalDate.now());
        transactionKafkaProducer.sendMessage(transactionToAdd);
        return "Add Transaction Message sent to Kafka";
    }

    public TransactionResponse getById(String id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if(optionalTransaction.isEmpty()) {
            System.out.println("Empty Transaction");
            return null;
        }
        System.out.println("Get Transaction");
        return new TransactionResponse(optionalTransaction.get());
    }

    public List<Transaction> getAllByEmail(String email) {
        return transactionRepository.findAllByEmail(email);
    }

    public List<Transaction> getAllBetweenDate(LocalDate start, LocalDate end) {
        return transactionRepository.findAllByDateBetween(start, end);
    }
}
