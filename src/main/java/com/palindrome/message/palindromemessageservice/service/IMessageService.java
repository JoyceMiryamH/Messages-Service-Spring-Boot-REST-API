package com.palindrome.message.palindromemessageservice.service;

import com.palindrome.message.palindromemessageservice.model.Message;

import java.util.List;

public interface IMessageService {
    void createMessage(String text);
    void createMessage(String messageId, String text);
    Message retrieveMessage(String messageId);
    void updateMessage(String messageId, String text);
    boolean deleteMessage(String messageId);
    List<Message> retrieveAllMessages();
    boolean isPalindrome(String messageId);
}
