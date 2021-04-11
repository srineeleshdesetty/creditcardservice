package com.mybank.creditcardservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybank.creditcardservice.model.CardDetail;
import com.mybank.creditcardservice.model.CustomerID;
import com.mybank.creditcardservice.service.CardService;
import com.mybank.creditcardservice.testutil.CardDetailFactory;
import com.mybank.creditcardservice.testutil.TestConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.mybank.creditcardservice.testutil.TestConstants.CUSTOMER_ID;
import static com.mybank.creditcardservice.testutil.TestConstants.INVALID_CARD_NUMBER;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Test
    public void testAddCardSuccess() throws Exception{

       doNothing().when(cardService).addCard(any(CardDetail.class));
        this.mockMvc
                .perform(post("/card/add").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CardDetailFactory.getDefault())))
                .andDo(print())
                .andExpect(status().is(204));

    }

    @Test
    public void testAddCardFailWhenInvalidCardNumberSupplied() throws Exception{

        doNothing().when(cardService).addCard(any(CardDetail.class));
        this.mockMvc
                .perform(post("/card/add").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CardDetailFactory.getWithCreditCardNumber(INVALID_CARD_NUMBER))))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors", is("Invalid card number")));

    }

    @Test
    public void testGetCardsWhenCardsExists() throws Exception{

       when(cardService.getCards(new CustomerID(CUSTOMER_ID))).thenReturn(CardDetailFactory.getCardDetailList(1));
        this.mockMvc
                .perform(get("/card/{id}", CUSTOMER_ID).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk());

    }
}


