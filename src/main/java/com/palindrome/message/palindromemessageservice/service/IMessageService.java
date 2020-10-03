package com.palindrome.message.palindromemessageservice.service;

import com.palindrome.message.palindromemessageservice.model.Message;

public interface IMessageService {
    long createMessage(String text);
    Message retrieveMessage(long messageId);
    Message updateMessage(long messageId, String text);
    String deleteMessage(long messageId);
    Iterable<Message> retrieveAllMessages();
    boolean isPalindrome(Message message);
}
