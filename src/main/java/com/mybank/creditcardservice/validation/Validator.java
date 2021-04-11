package com.mybank.creditcardservice.validation;


import com.mybank.creditcardservice.exception.BadRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Validator {

	abstract void validate();
	
	protected List<String> messages;
	
	protected void addMessage(String message){
		if(messages==null) messages = new ArrayList<>();
		messages.add(message);
	}
	
	protected void checkMessage(List<String> messages){
		if(messages!=null)
			throw new BadRequestException(messages.stream().collect(Collectors.joining(".")));
	}
	
}
