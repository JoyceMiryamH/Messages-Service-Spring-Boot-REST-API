package com.palindrome.message.palindromemessageservice.service;

import com.palindrome.message.palindromemessageservice.model.Message;

public interface IMessageService {
    Long createMessage(String text);
    Message retrieveMessage(Long messageId);
    void updateMessage(Long messageId, String text);
    void deleteMessage(Long messageId);
    Iterable<Message> retrieveAllMessages();
    boolean isPalindrome(Message message);
    boolean isPalindrome(String messageText);
}
