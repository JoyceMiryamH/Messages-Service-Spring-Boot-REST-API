package com.palindrome.message.palindromemessageservice.service;

import com.palindrome.message.palindromemessageservice.model.Message;

public interface IMessageService {
    int createMessage(String text);
    Message retrieveMessage(int messageId);
    void updateMessage(int messageId, String text);
    void deleteMessage(int messageId);
    Iterable<Message> retrieveAllMessages();
    boolean isPalindrome(String messageText);
}
