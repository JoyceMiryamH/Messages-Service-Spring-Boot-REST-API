package com.palindrome.message.palindromemessageservice.controller;

import com.palindrome.message.palindromemessageservice.model.Message;
import com.palindrome.message.palindromemessageservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/messages/create")
    public @ResponseBody int createMessage(@RequestBody String text) {
        return messageService.createMessage(text);
    }

    @GetMapping("/messages/{messageId}")
    public Message retrieveMessage(@PathVariable int messageId) {
        return messageService.retrieveMessage(messageId);
    }

    @PostMapping("/messages/update/{messageId}")
    public void updateMessage(@PathVariable int messageId, @RequestBody String text) {
        messageService.updateMessage(messageId, text);
    }

    @DeleteMapping("/messages/delete/{messageId}")
    public void deleteMessage(@PathVariable int messageId) {
        messageService.deleteMessage(messageId);
    }

    @GetMapping("/messages/all")
    public Iterable<Message> retrieveAllMessages() {
        return messageService.retrieveAllMessages();
    }

    @GetMapping("/messages/check-palindrome/{messageId}")
    public boolean isPalindrome(@PathVariable int messageId) {
        return messageService.retrieveMessage(messageId).isPalindrome();
    }
}
