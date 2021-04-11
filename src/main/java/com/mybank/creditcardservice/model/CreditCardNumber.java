package com.mybank.creditcardservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mybank.creditcardservice.exception.BadRequestException;
import lombok.NonNull;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value
public class CreditCardNumber {

    String longCardNumber;

    public CreditCardNumber( String longCardNumber){
        //isValid(longCardNumber);
        this.longCardNumber = longCardNumber;
    }

//    private void isValid(final String longCardNumber) {
//
//        if(StringUtils.isEmpty(longCardNumber) || !isValidluhnTen(longCardNumber)){
//            throw new BadRequestException("Invalid credit card number.");
//        }
//    }
//
//    @JsonIgnore
//    private boolean isValidluhnTen(final String longCardNumber) {
//        final int length = longCardNumber.length();
//
//        //do luhn logic here
//        return false;
//    }
}
