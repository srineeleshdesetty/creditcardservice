package com.mybank.creditcardservice.controller;

import com.mybank.creditcardservice.exception.BadRequestException;
import com.mybank.creditcardservice.exception.ServiceException;
import com.mybank.creditcardservice.model.CardDetail;
import com.mybank.creditcardservice.model.CardHolderName;
import com.mybank.creditcardservice.model.CustomerID;
import com.mybank.creditcardservice.service.CardService;
import com.mybank.creditcardservice.validation.CardDetailValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/card")
@Api("Api for credit card related services")

public class CardController {

    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    //TODO: Add security
    //TODO: this can be paginated
    @GetMapping("/{id}")
    @ApiOperation("get all credit cards for a given customer")
    public ResponseEntity<List<CardDetail>> getCards(@PathVariable("id") long id) throws Exception {
        try{
            final CustomerID customerID = new CustomerID(id);
            log.info("invoked getCards for customer {}" , customerID.getId());
            return ResponseEntity.ok(cardService.getCards(customerID));
        } catch(Exception e){
            log.error(String.format("Unable to get card details for %d",id),e);
            throw new BadRequestException(String.format("Unable to get card details for %d",id));

        }

    }

    //TODO: Add security
    @PostMapping("/add")
    @ApiOperation("add a card for a customer")
    public ResponseEntity<Void> addCard(@RequestBody CardDetail cardDetail) throws Exception {
        new CardDetailValidator(cardDetail).validate();
        try {
            log.info("attempting to add card for customer {}", cardDetail.getCustomerID());
            cardService.addCard(cardDetail);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            log.error(String.format("Unable to save card details for %d",cardDetail.getCustomerID()),e);
            throw new BadRequestException(String.format("Unable to save card details for %d",cardDetail.getCustomerID()));

        }

}


}
