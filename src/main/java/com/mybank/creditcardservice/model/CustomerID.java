package com.mybank.creditcardservice.model;

import lombok.Value;

@Value
public class CustomerID {

    long id;

    public CustomerID(long id){
        this.id = id;
    }

}
