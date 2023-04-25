package com.xinpenghu.ebankportal.entity;

import com.xinpenghu.ebankportal.model.AddTransactionRequest;
import com.xinpenghu.ebankportal.model.TransactionResponse;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@Document
public class Transaction {
    @Id
    private String id;
    private String email;
    private Float amount;
    private String type;
    private String currency;
    private String description;
    private LocalDate date;

//    public Transaction(AddTransactionRequest request) {
//        this.user_id = request.user_id;
//        this.amount = request.amount;
//        this.currency = request.currency;
//        this.date = request.date;
//    }
    @Tolerate
    public Transaction() {
    }

    public Transaction(AddTransactionRequest request) {
        this.email = request.email;
        this.amount = request.amount;
        this.type = request.type;
        this.currency = request.currency;
        this.description = request.description;
    }

    @PersistenceCreator
    public Transaction(String email,Float amount, String type,String currency, String description, LocalDate date) {
        this.email = email;
        this.amount = amount;
        this.type = type;
        this.currency = currency;
        this.description = description;
        this.date = date;
    }
}
