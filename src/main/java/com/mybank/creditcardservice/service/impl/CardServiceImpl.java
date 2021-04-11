package com.mybank.creditcardservice.service.impl;

import com.mybank.creditcardservice.entity.CreditCardEntity;
import com.mybank.creditcardservice.exception.ServiceException;
import com.mybank.creditcardservice.model.CardDetail;
import com.mybank.creditcardservice.model.CardHolderName;
import com.mybank.creditcardservice.model.CreditCardNumber;
import com.mybank.creditcardservice.model.CustomerID;
import com.mybank.creditcardservice.model.Money;
import com.mybank.creditcardservice.repository.CardRepository;
import com.mybank.creditcardservice.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @Override
    public void addCard(CardDetail cardDetail) throws ServiceException{
           final CreditCardEntity cardEntity =  CreditCardEntity
                    .builder()
                    .cardNumber(cardDetail.getCardNumber())
                    .cardHolderFirstName(cardDetail.getCardHolderName().getFirstName())
                    .cardHolderLastName(cardDetail.getCardHolderName().getLastName())
                    .customerId(cardDetail.getCustomerID())
                    .balance(BigDecimal.valueOf(0))
                    .cardlimit(cardDetail.getLimit().getAmount())
                    .currency(cardDetail.getLimit().getCurrency())
                    .build();
           cardRepository.save(cardEntity);

    }

    @Override
    public List<CardDetail> getCards(CustomerID customerID) throws ServiceException{
            final List<CreditCardEntity> creditCardEntities = cardRepository.findByCustomerId(customerID.getId());
            if(creditCardEntities.size()==0) throw new ServiceException("No cards found");
            final List<CardDetail> cards = new ArrayList<>();
            creditCardEntities.forEach(creditCardEntity -> {
                CardDetail card = new CardDetail(new CardHolderName(creditCardEntity.getCardHolderFirstName(),creditCardEntity.getCardHolderLastName()),
                        creditCardEntity.getCardNumber(),
                        new Money(creditCardEntity.getCurrency(), creditCardEntity.getCardlimit()),
                        new Money(creditCardEntity.getCurrency(), creditCardEntity.getBalance()),
                        creditCardEntity.getCustomerId()
                );

                cards.add(card);
            });

            return cards;

    }
}
