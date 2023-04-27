package com.xinpenghu.ebankportal.entity;

import lombok.Data;

@Data
public class Balance {
    private Float balance;
    private Float totalCredit;
    private Float totalDebit;

    public Balance(Float balance, Float totalCredit, Float totalDebit) {
        this.balance = balance;
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
    }
}
