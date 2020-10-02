package com.palindrome.message.palindromemessageservice.service;

import com.palindrome.message.palindromemessageservice.model.Message;

public interface IMessageService {
    Long createMessage(String text);
    Message retrieveMessage(Long messageId);
    Message updateMessage(Long messageId, String text);
    String deleteMessage(Long messageId);
    Iterable<Message> retrieveAllMessages();
    boolean isPalindrome(Message message);
    boolean isPalindrome(String messageText);
}
