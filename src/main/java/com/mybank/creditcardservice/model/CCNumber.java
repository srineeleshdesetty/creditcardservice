package com.mybank.creditcardservice.model;

import lombok.Value;

@Value
public class CCNumber {

    String cNumber;

    public CCNumber(String cNumber){
        this.cNumber = cNumber;
    }


}
