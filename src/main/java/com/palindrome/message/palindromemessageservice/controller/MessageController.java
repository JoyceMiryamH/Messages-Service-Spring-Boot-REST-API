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
    public @ResponseBody long createMessage(@RequestBody String text) {
        return messageService.createMessage(text);
    }

    @GetMapping("/messages/{messageId}")
    public @ResponseBody Message retrieveMessage(@PathVariable long messageId) {
        return messageService.retrieveMessage(messageId);
    }

    @PutMapping("/messages/update/{messageId}")
    public @ResponseBody Message updateMessage(@PathVariable long messageId, @RequestBody String text) {
        return messageService.updateMessage(messageId, text);
    }

    @DeleteMapping("/messages/delete/{messageId}")
    public @ResponseBody String deleteMessage(@PathVariable long messageId) {
        return messageService.deleteMessage(messageId);
    }

    @GetMapping("/messages/all")
    public @ResponseBody Iterable<Message> retrieveAllMessages() {
        return messageService.retrieveAllMessages();
    }

    @GetMapping("/messages/check-palindrome/{messageId}")
    public @ResponseBody boolean isPalindrome(@PathVariable long messageId) {
        return messageService.retrieveMessage(messageId).isPalindrome();
    }
}
