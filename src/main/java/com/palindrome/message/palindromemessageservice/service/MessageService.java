package com.palindrome.message.palindromemessageservice.service;

import com.palindrome.message.palindromemessageservice.error.MessageNotFoundException;
import com.palindrome.message.palindromemessageservice.model.Message;
import com.palindrome.message.palindromemessageservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public int createMessage(String text) {
        Message message = new Message(text);
        message.setPalindrome(isPalindrome(text));
        messageRepository.save(message);
        return message.getId();
    }

    @Override
    public Message retrieveMessage(int messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        if (!message.isPresent()) {
            throw new MessageNotFoundException();
        }
        return message.get();
    }

    @Override
    public void updateMessage(int messageId, String text) {
        Message message = retrieveMessage(messageId);
        if (message != null) {
            message.setText(text);
            message.setPalindrome(isPalindrome(text));
            messageRepository.save(message);
        }
    }

    @Override
    public void deleteMessage(int messageId) {
        messageRepository.deleteById(Integer.valueOf(messageId));
    }

    @Override
    public Iterable<Message> retrieveAllMessages() {
        return messageRepository.findAll();
    }

    public boolean isPalindrome(String messageText) {
        if (messageText.length() < 2) {
            return true;
        }
        char[] messageArray = messageText.toCharArray();
        int messageArrLength = messageArray.length;
        for (int i=0, j=messageArrLength-1; i<j; i++, j--) {
            if (messageArray[i] != messageArray[j]) {
                return false;
            }
        }
        return true;
    }
}
