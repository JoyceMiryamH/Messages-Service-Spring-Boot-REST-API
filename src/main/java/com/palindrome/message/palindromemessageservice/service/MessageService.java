package com.palindrome.message.palindromemessageservice.service;

import com.palindrome.message.palindromemessageservice.error.MessageNotFoundException;
import com.palindrome.message.palindromemessageservice.model.Message;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MessageService implements IMessageService {

    private SecureRandom random = new SecureRandom();
    private static List<Message> messages = new ArrayList<>();

    @Override
    public void createMessage(String text) {
        String generatedMessageId = new BigInteger(130, random).toString(32);
        Message message = new Message(generatedMessageId, text);
    }

    @Override
    public void createMessage(String messageId, String text) {
        Message message = new Message(messageId, text);
    }

    @Override
    public Message retrieveMessage(String messageId) {
        Optional<Message> message = messages.stream().filter(msg -> msg.getId().equals(messageId)).findFirst();
        if (!message.isPresent()) {
            throw new MessageNotFoundException();
        }
        return message.get();
    }

    @Override
    public void updateMessage(String messageId, String text) {
        Message message = retrieveMessage(messageId);
        if (message != null) {
            message.setText(text);
        } else {
            createMessage(messageId, text);
        }
    }

    @Override
    public boolean deleteMessage(String messageId) {
        return messages.removeIf(message -> message.getId().equals(messageId));
    }

    @Override
    public List<Message> retrieveAllMessages() {
        return messages.stream().sorted(Comparator.comparing(Message::getId)).collect(Collectors.toList());
    }

//    public boolean isPalindrome(String messageId) {
//        boolean isPalindrome = false;
//        Message message = retrieveMessage(messageId);
//        String messageText = message.getText();
//        if (messageText.length() < 2) {
//            isPalindrome = true;
//        }
//        char[] messageArray = messageText.toCharArray();
//        int messageArrLength = messageArray.length;
//        for (int i=0, j=messageArrLength-1; i<j; i++, j--) {
//            if (messageArray[i] != messageArray[j]) {
//                isPalindrome = false;
//                break;
//            }
//
//        }
//        return isPalindrome;
//    }

    public boolean isPalindrome(String messageText) {
        boolean isPalindrome = false;
        if (messageText.length() < 2) {
            isPalindrome = true;
        }
        char[] messageArray = messageText.toCharArray();
        int messageArrLength = messageArray.length;
        for (int i=0, j=messageArrLength-1; i<j; i++, j--) {
            if (messageArray[i] != messageArray[j]) {
                isPalindrome = false;
                break;
            }

        }
        return isPalindrome;
    }
}
