package com.palindrome.message.palindromemessageservice.controller;

import com.palindrome.message.palindromemessageservice.model.Message;
import com.palindrome.message.palindromemessageservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/messages/create")
    public void createMessage(@RequestBody String text) {
        messageService.createMessage(text);
    }

    @PostMapping("/messages/create-with-id")
    public void createMessage(@RequestBody String messageId, String text) {
        messageService.createMessage(messageId, text);
    }

    @GetMapping("/messages/{messageId}")
    public Message retrieveMessage(@PathVariable String messageId) {
        return messageService.retrieveMessage(messageId);
    }

    @PostMapping("/messages/update")
    public void updateMessage(@RequestBody String messageId, String text) {
        messageService.updateMessage(messageId, text);
    }

    @DeleteMapping("/messages/delete/{messageId}")
    public ResponseEntity<Long> deleteMessage(@PathVariable String messageId) {
        if (!messageService.deleteMessage(messageId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/messages/all")
    public List<Message> retrieveAllMessages() {
        return messageService.retrieveAllMessages();
    }

    @GetMapping("/messages/check-palindrome/{messageId}")
    public boolean isPalindrome(@PathVariable String messageId) {
        return messageService.isPalindrome(messageId);
    }
}
