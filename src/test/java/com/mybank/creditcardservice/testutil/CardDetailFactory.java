package com.mybank.creditcardservice.testutil;

import com.mybank.creditcardservice.model.CardDetail;
import com.mybank.creditcardservice.model.CardHolderName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CardDetailFactory {

    private CardDetailFactory(){

    }

    public static CardDetail getDefault(){
        return new CardDetail(new CardHolderName("john","doe"),
                TestConstants.VALID_CARD_NUMBER,
                MoneyFactory.getDefault(),
                MoneyFactory.getWithAmount(new BigDecimal("0")),
                TestConstants.CUSTOMER_ID
                );
    }

    public static CardDetail getWithCreditCardNumber(String creditCardNumber){
        return new CardDetail(new CardHolderName("john","doe"),
                creditCardNumber,
                MoneyFactory.getDefault(),
                MoneyFactory.getWithAmount(new BigDecimal("0")),
                TestConstants.CUSTOMER_ID
        );
    }

    public static List<CardDetail> getCardDetailList(int size){
        List<CardDetail> cards = new ArrayList<>(size);
        for (int i=0;i<size;i++){
            cards.add(getDefault());
        }
        return cards;
    }
}
