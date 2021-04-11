package com.mybank.creditcardservice.model;

import lombok.Value;

@Value
public class Currency {

    String isoCode;

    /**
    * @Desc This field represents the number of part per whole of a currency.
     * For example: 100 pence (parts) in 1 pound (whole) or 100 cents (parts) in 1 dollar (whole)
    * */
    int partsPerWhole;
}
