package com.mybank.creditcardservice.service;

import com.google.common.collect.ImmutableList;
import com.mybank.creditcardservice.entity.CreditCardEntity;
import com.mybank.creditcardservice.model.CardDetail;
import com.mybank.creditcardservice.model.CustomerID;
import com.mybank.creditcardservice.repository.CardRepository;
import com.mybank.creditcardservice.service.impl.CardServiceImpl;
import com.mybank.creditcardservice.testutil.CardDetailFactory;
import com.mybank.creditcardservice.testutil.TestConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static com.mybank.creditcardservice.testutil.TestConstants.CUSTOMER_ID;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardServiceTest {

    @MockBean
    private CardRepository cardRepository;

    private CardService cardService;

    @Before
    public void setUp() {
        this.cardService = new CardServiceImpl(cardRepository);
    }

    @Test
    public void testAddCard() throws Exception{
        cardService.addCard(CardDetailFactory.getDefault());
        ArgumentCaptor<CreditCardEntity> creditCardEntityArgumentCaptor = ArgumentCaptor.forClass(CreditCardEntity.class);
        verify(cardRepository).save(creditCardEntityArgumentCaptor.capture());
        CreditCardEntity entity = creditCardEntityArgumentCaptor.getValue();
        assertTrue(entity.getCardNumber().equals(CardDetailFactory.getDefault().getCardNumber()));
        assertTrue(entity.getCardHolderFirstName().equals(CardDetailFactory.getDefault().getCardHolderName().getFirstName()));
        assertTrue(entity.getCardHolderLastName().equals(CardDetailFactory.getDefault().getCardHolderName().getLastName()));
    }

    @Test
    public void testGetCard() throws Exception{
        CardDetail cardDetail = CardDetailFactory.getDefault();
        CreditCardEntity entity = CreditCardEntity
                .builder()
                .cardNumber(cardDetail.getCardNumber())
                .cardHolderFirstName(cardDetail.getCardHolderName().getFirstName())
                .cardHolderLastName(cardDetail.getCardHolderName().getLastName())
                .customerId(cardDetail.getCustomerID())
                .balance(BigDecimal.valueOf(0))
                .cardlimit(cardDetail.getLimit().getAmount())
                .currency(cardDetail.getLimit().getCurrency())
                .build();

        when(cardRepository.findByCustomerId(CUSTOMER_ID)).thenReturn(ImmutableList.of(entity));
        List<CardDetail> cards = cardService.getCards(new CustomerID(CUSTOMER_ID));

        assertTrue(cards.get(0).getCardNumber().equals(cardDetail.getCardNumber()));
    }
}
