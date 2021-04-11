package com.mybank.creditcardservice.model;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;

@Value
public class Money {

    String currency;

    BigDecimal amount;

    public Money(@NonNull String currency, BigDecimal amount){
        this.currency = currency;
        this.amount = amount;
    }


}
