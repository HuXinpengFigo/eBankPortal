package com.xinpenghu.ebankportal.service;

import com.xinpenghu.ebankportal.entity.Balance;
import com.xinpenghu.ebankportal.entity.ExchangeRate;
import com.xinpenghu.ebankportal.entity.Transaction;
import com.xinpenghu.ebankportal.model.ExchangeRateAPIResponse;
import com.xinpenghu.ebankportal.repository.TransactionRepository;
import com.xinpenghu.ebankportal.model.AddTransactionRequest;
import com.xinpenghu.ebankportal.model.TransactionResponse;
import com.xinpenghu.ebankportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionKafkaProducer transactionKafkaProducer;

    @Autowired
    private UserRepository userRepository;

    /**
     * Add transaction
     * @param request
     * @return
     */
    public String add(AddTransactionRequest request) {
        if(!userRepository.existsByEmail(request.email)) {
            return "User doesn't exist!";
        }

        List<Transaction> transactions = transactionRepository.findAllByEmailAndCurrency(request.email, request.currency);
        Float balance = getBalance(transactions).getBalance();
        if(Objects.equals(request.type, "Debit") && balance < request.amount) {
            return "Invalid amount of transaction, Insufficient Balance";
        }

        Transaction transactionToAdd = new Transaction(request.email, request.amount, request.type,request.currency, request.description, LocalDate.now());
        transactionKafkaProducer.sendMessage(transactionToAdd);
        return "Add Transaction Message sent to Kafka";
    }

    /**
     * Get transaction by Id
     * @param id
     * @return
     */
    public TransactionResponse getById(String id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if(optionalTransaction.isEmpty()) {
            System.out.println("Empty Transaction");
            return null;
        }
        System.out.println("Get Transaction");
        return new TransactionResponse(optionalTransaction.get());
    }

    /**
     * Get all transactions by email
     * @param email
     * @return
     */
    public List<Transaction> getAllByEmail(String email) {
        return transactionRepository.findAllByEmail(email);
    }

    /**
     * Get all transactions between two Date
     * @param start
     * @param end
     * @param email
     * @return
     */
    public List<Transaction> getAllBetweenDate(LocalDate start, LocalDate end, String email) {
        return transactionRepository.findAllByDateBetweenAndEmail(start, end, email);
    }

    /**
     * Get all transactions by email and currency type
     * @param email
     * @param currency
     * @return
     */
    public List<Transaction> getAllByEmailAndCurrency(String email, String currency) {
        return transactionRepository.findAllByEmailAndCurrency(email, currency);
    }

    /**
     * Get balance by email
     * @param transactions
     * @return
     */
    public Balance getBalance(List<Transaction> transactions) {
        Float balance = (float) 0;
        Float credit = (float) 0;
        Float debit = (float) 0;
        for( Transaction transaction : transactions) {
            if(Objects.equals(transaction.getType(), "Credit")) {
                balance += transaction.getAmount();
                credit += transaction.getAmount();
            } else if (Objects.equals(transaction.getType(), "Debit")) {
                balance -= transaction.getAmount();
                debit += transaction.getAmount();
            }
        }

        return new Balance(balance, credit, debit);
    }

    /**
     * Get exchange rate by call API
     * @return
     */
    public ExchangeRate getExchangeRate() {
        String url = "https://v6.exchangerate-api.com/v6/1af5924cda5c9b070747a158/latest/HKD";
        RestTemplate restTemplate = new RestTemplate();
        ExchangeRateAPIResponse exchangeRateAPIResponse = restTemplate.getForObject(url, ExchangeRateAPIResponse.class);

//        Gson gson = new Gson();
//        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

        assert exchangeRateAPIResponse != null;
        return exchangeRateAPIResponse.getConversion_rates();
    }
}
