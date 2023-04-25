package com.xinpenghu.ebankportal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xinpenghu.ebankportal.entity.Transaction;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class TransactionResponse {
    @JsonProperty
    @Id
    private String id;
    @JsonProperty
    private String email;
    @JsonProperty
    private Float amount;
    @JsonProperty
    private String type;
    @JsonProperty
    private String currency;
    @JsonProperty
    private LocalDate date;
    @JsonProperty
    private String description;

    public TransactionResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.email = transaction.getEmail();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.currency = transaction.getCurrency();
        this.date = transaction.getDate();
        this.description = transaction.getDescription();
    }
}
