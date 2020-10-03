package com.palindrome.message.palindromemessageservice.service;

import com.palindrome.message.palindromemessageservice.error.MessageNotFoundException;
import com.palindrome.message.palindromemessageservice.model.Message;
import com.palindrome.message.palindromemessageservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public long createMessage(String text) {
        Message message = new Message(text);
        message.setPalindrome(isPalindrome(message));
        message.setCreated(new Date());
        message.setLastModified(message.getCreated());
        messageRepository.save(message);
        return message.getId();
    }

    @Override
    public Message retrieveMessage(long messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        if (!message.isPresent()) {
            throw new MessageNotFoundException(messageId);
        }
        return message.get();
    }

    @Override
    public Message updateMessage(long messageId, String text) {
        Message message = messageRepository.findById(messageId).orElse(null);
        if (message == null) {
            throw new MessageNotFoundException(messageId);
        }
        message.setText(text);
        message.setPalindrome(isPalindrome(message));
        message.setLastModified(new Date());
        messageRepository.save(message);
        return message;
    }

    @Override
    public String deleteMessage(long messageId) {
        messageRepository.deleteById(messageId);
        return String.format("Message %s was successfully deleted.", messageId);
    }

    @Override
    public Iterable<Message> retrieveAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public boolean isPalindrome(Message message) {
        if (message == null) {
            throw new MessageNotFoundException(message.getId());
        }
        boolean isPalindrome = isPalindrome(message.getText());
        message.setPalindrome(isPalindrome);
        messageRepository.save(message);
        return isPalindrome;
    }

    public boolean isPalindrome(String messageText) {
        messageText = messageText.toLowerCase(); // this is important to make the algo not case sensitive - abcbA is a palindrome
        if (messageText.length() < 2) { // base cases - empty or 1 character string
            return true;
        }
        char[] messageArray = messageText.toCharArray();
        int messageArrLength = messageArray.length;
        for (int i=0, j=messageArrLength-1; i<j; i++, j--) {
            if (messageArray[i] != messageArray[j]) {
                return false;
            }
        }
        System.out.println("this is a palindrome");
        return true;
    }
}
