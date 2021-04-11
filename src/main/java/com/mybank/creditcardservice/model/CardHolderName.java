package com.mybank.creditcardservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mybank.creditcardservice.exception.BadRequestException;
import lombok.NonNull;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value
public class CardHolderName {

    String firstName;
    String lastName;

    public CardHolderName(@NonNull String firstName, @NonNull String lastName){
        isValid(firstName, lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private void isValid(String firstName, String lastName){
        if(StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)){
            throw new BadRequestException("Invalid card holder name");
        }
    }

    @JsonIgnore
    public String getFullName(){
        return String.format("{} {}", firstName, lastName);
    }

    /***
     * @Desc: returns the name in the following format. <i>Initial from firstName <space> last name</i>
     *
     */
    @JsonIgnore
    public String getNameOnCard(){
        return String.format("{} {}", firstName.trim().charAt(0), lastName);
    }
}
