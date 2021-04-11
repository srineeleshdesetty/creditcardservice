package com.mybank.creditcardservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class CreditCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    long customerId;

    String cardHolderFirstName;

    String cardHolderLastName;

    //TODO: make this info encrypted
    String cardNumber;

    BigDecimal cardlimit;

    BigDecimal balance;

    String currency;
}
