package com.palindrome.message.palindromemessageservice.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException() {
        super();
    }

    public MessageNotFoundException(Long id){
        super(String.format("No message was found for ID: %s", id));
    }
}
