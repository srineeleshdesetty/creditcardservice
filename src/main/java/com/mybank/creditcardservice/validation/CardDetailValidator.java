package com.mybank.creditcardservice.validation;

import com.mybank.creditcardservice.model.CardDetail;
import org.apache.commons.lang3.StringUtils;

public class CardDetailValidator extends Validator {

    private final CardDetail cardDetail;

    public CardDetailValidator(CardDetail cardDetail){
        this.cardDetail = cardDetail;
    }

    @Override
    public void validate() {
        if(StringUtils.isBlank(cardDetail.getCardNumber()) || !isLuhnTenValid(cardDetail.getCardNumber().trim())){
            addMessage("Invalid card number");
        }
        checkMessage(messages);
    }

    private boolean isLuhnTenValid(String cardNumber){
        int sum = 0;
        boolean alternate = false;
        for (int i = 0; i < cardNumber.length(); i++)
        {
            int n = Integer.parseInt(cardNumber.substring(i,i+1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
