package com.palindrome.message.palindromemessageservice.service;

import com.palindrome.message.palindromemessageservice.model.Message;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageService.class)
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void testCreateMessage() {
        Message message = new Message("message1", "message text");
        messageService.createMessage("message1", "message text");
        Assert.assertEquals(message, messageService.retrieveMessage("message1"));
    }

    @Test
    public void testUpdateMessage() {
        messageService.createMessage("message1", "message text");
        messageService.updateMessage("message1", "new message");
        Assert.assertEquals("new message", messageService.retrieveMessage("message1").getText());
    }

    @Test
    public void testIsPalindrome_emptyString() {
        messageService.createMessage("message1", "");
        Assert.assertTrue(messageService.isPalindrome("message1"));
    }

    @Test
    public void testIsPalindrome_singleCharacterString() {
        messageService.createMessage("message1", "a");
        Assert.assertTrue(messageService.isPalindrome("message1"));
    }

    @Test
    public void testIsPalindrome_evenLengthString() {
        messageService.createMessage("message1", "abba");
        Assert.assertTrue(messageService.isPalindrome("message1"));
    }

    @Test
    public void testIsPalindrome_oddLengthString() {
        messageService.createMessage("message1", "abcba");
        Assert.assertTrue(messageService.isPalindrome("message1"));
    }

    @Test
    public void testIsPalindrome_caseSensitive() {
        messageService.createMessage("message1", "abcbA"); // TODO
        Assert.assertTrue(messageService.isPalindrome("message1"));
    }

    @Test
    public void testIsPalindrome_falseCase() {
        messageService.createMessage("message1", "abc");
        Assert.assertTrue(!messageService.isPalindrome("message1"));
    }
}
