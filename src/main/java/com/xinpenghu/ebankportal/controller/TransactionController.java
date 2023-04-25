package com.xinpenghu.ebankportal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xinpenghu.ebankportal.entity.Transaction;
import com.xinpenghu.ebankportal.entity.User;
import com.xinpenghu.ebankportal.model.AddTransactionRequest;
import com.xinpenghu.ebankportal.model.TransactionResponse;
import com.xinpenghu.ebankportal.model.UserResponse;
import com.xinpenghu.ebankportal.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ranges.Range;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trans")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping
    public String addTransaction(@RequestBody AddTransactionRequest request) {
        return transactionService.add(request);
    }

    @GetMapping
    public TransactionResponse getTransactionById(@RequestParam String trans_id) {
        return transactionService.getById(trans_id);
    }

    @GetMapping(value = "/email")
    public String getTransctionsByEmail(@RequestParam String email) throws JsonProcessingException {
        List<Transaction> transactions = transactionService.getAllByEmail(email);

        // For jsonify the list of objects
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.writeValueAsString(transactions);
    }

    @GetMapping(value = "/period")
    public String getTransctionsByPeriod(@RequestParam int days,@RequestParam int months, @RequestParam int years) throws JsonProcessingException {
        LocalDate start = LocalDate.now().minusDays(days+1).minusMonths(months).minusYears(years);
        LocalDate end = LocalDate.now().plusDays(1);
        List<Transaction> transactions = transactionService.getAllBetweenDate(start, end);

        // For jsonify the list of objects
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.writeValueAsString(transactions);
    }

}
