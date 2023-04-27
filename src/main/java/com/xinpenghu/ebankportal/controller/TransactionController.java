package com.xinpenghu.ebankportal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xinpenghu.ebankportal.entity.Balance;
import com.xinpenghu.ebankportal.entity.BalanceAndExchangeRate;
import com.xinpenghu.ebankportal.entity.ExchangeRate;
import com.xinpenghu.ebankportal.entity.Transaction;
import com.xinpenghu.ebankportal.model.AddTransactionRequest;
import com.xinpenghu.ebankportal.model.TransactionResponse;
import com.xinpenghu.ebankportal.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String getTransctionsByPeriod(@RequestParam int days,@RequestParam int months, @RequestParam int years, @RequestParam String email) throws JsonProcessingException {
        LocalDate start = LocalDate.now().minusDays(days+1).minusMonths(months).minusYears(years);
        LocalDate end = LocalDate.now().plusDays(1);
        List<Transaction> transactions = transactionService.getAllBetweenDate(start, end, email);

        // For jsonify the list of objects
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.writeValueAsString(transactions);
    }

    @GetMapping(value = "/currency")
    public String getTransactionByEmailAndCurrency(@RequestParam String email, @RequestParam String currency) throws JsonProcessingException {
        List<Transaction> transactions = transactionService.getAllByEmailAndCurrency(email,currency);

        // For jsonify the list of objects
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.writeValueAsString(transactions);
    }

    @GetMapping(value = "/balance_and_exchange_rate")
    public BalanceAndExchangeRate getBalanceAndExchangeRate(@RequestParam String email) {
        List<Transaction> transactions = transactionService.getAllByEmail(email);
        Balance balance = transactionService.getBalance(transactions);

        ExchangeRate exchangeRate = transactionService.getExchangeRate();

        return new BalanceAndExchangeRate(balance, exchangeRate);
    }

}
