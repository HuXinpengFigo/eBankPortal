package com.xinpenghu.ebankportal.entity;

import lombok.Data;

@Data
public class BalanceAndExchangeRate {
    public Balance balance;
    public ExchangeRate exchangeRate;

    public BalanceAndExchangeRate(Balance balance, ExchangeRate exchangeRate) {
        this.balance = balance;
        this.exchangeRate = exchangeRate;
    }
}
