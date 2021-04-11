package com.mybank.creditcardservice.model;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CardNumber {

    String lcNUmber;


    public CardNumber(@NonNull String lcNUmber){
        this.lcNUmber = lcNUmber;
    }


}
