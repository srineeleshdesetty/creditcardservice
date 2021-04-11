package com.mybank.creditcardservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotBlank;

//TODO: add more details like expiry date, cvv etc
@Value
public class CardDetail {

    @NonNull
    CardHolderName cardHolderName;

    @NonNull
    String cardNumber;

    @NonNull
    Money limit;

    @NonNull
    Money balance;

    @NonNull
    long customerID;

}
