package com.palindrome.message.palindromemessageservice.service;

import com.palindrome.message.palindromemessageservice.repository.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageService.class)
public class IsPalindromeTest {

    @MockBean
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @Test
    public void testIsPalindrome_emptyString() {
        Assert.assertTrue(messageService.isPalindrome(""));
    }

    @Test
    public void testIsPalindrome_singleCharacterString() {
        Assert.assertTrue(messageService.isPalindrome("a"));
    }

    @Test
    public void testIsPalindrome_specialCharacters() {
        Assert.assertTrue(messageService.isPalindrome("!$!"));
    }

    @Test
    public void testIsPalindrome_evenLengthString() {
        Assert.assertTrue(messageService.isPalindrome("abba"));
    }

    @Test
    public void testIsPalindrome_oddLengthString() {
        Assert.assertTrue(messageService.isPalindrome("abcba"));
    }

    @Test
    public void testIsPalindrome_caseSensitive() {
        Assert.assertTrue(messageService.isPalindrome("abcbA"));
    }

    @Test
    public void testIsPalindrome_longString() {
        Assert.assertTrue(messageService.isPalindrome("abcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcba"));
    }

    @Test
    public void testIsPalindrome_falseCase() {
        Assert.assertFalse(messageService.isPalindrome("palindrome"));
    }
}
