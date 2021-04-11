package com.mybank.creditcardservice.service;

import com.mybank.creditcardservice.model.CardDetail;
import com.mybank.creditcardservice.model.CustomerID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {

    public void addCard(CardDetail cardDetail) throws Exception;

    public List<CardDetail> getCards(CustomerID customerID) throws Exception;
}
