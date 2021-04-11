package com.mybank.creditcardservice.testutil;

import com.mybank.creditcardservice.model.Money;

import java.math.BigDecimal;

public class MoneyFactory {

    private MoneyFactory(){

    }

    public static Money getDefault(){
        return new Money("GBP", new BigDecimal("100"));
    }

    public static Money getWithAmount(BigDecimal amount){
        return new Money("GBP", amount);
    }
}
