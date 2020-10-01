package com.palindrome.message.palindromemessageservice.controller;

import com.palindrome.message.palindromemessageservice.error.MessageNotFoundException;
import com.palindrome.message.palindromemessageservice.model.Message;
import com.palindrome.message.palindromemessageservice.service.MessageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MessageController.class)
@AutoConfigureMockMvc(addFilters=false)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    public void testRetrieveMessage() throws Exception {
        Message mockMessage = new Message("message1", "this is a message");
        Mockito.when(
                messageService.retrieveMessage(Mockito.anyString())).thenReturn(mockMessage);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/messages/message1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{id:message1}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test(expected = NestedServletException.class)
    public void TestRetrieveMessageFailed() throws Exception {
        Message mockMessage = new Message("message2", "this is a message");
        Mockito.when(
                messageService.retrieveMessage(Mockito.anyString())).thenThrow(new MessageNotFoundException());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/messages/message1").accept(
                MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    public void testDeleteMessage() throws Exception {
        Mockito.when(
                messageService.deleteMessage(Mockito.anyString())).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/messages/delete/message1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
    }

    @Test
    public void testRetrieveAllMessages() throws Exception {
        Message mockMessage1 = new Message("message1", "messageText");
        Message mockMessage2 = new Message("message2", "messageText");
        List<Message> mockMessages = new ArrayList<>();
        mockMessages.add(mockMessage1);
        mockMessages.add(mockMessage2);

        Mockito.when(
                messageService.retrieveAllMessages()).thenReturn(mockMessages);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/messages/all").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":\"message1\",\"text\":\"messageText\"},{\"id\":\"message2\",\"text\":\"messageText\"}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}
