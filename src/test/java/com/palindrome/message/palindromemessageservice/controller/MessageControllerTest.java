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
    public void testCreateMessage() throws Exception {
        Message mockMessage = new Message("this is a message");
        Mockito.when(
                messageService.createMessage(Mockito.anyString())).thenReturn(mockMessage.getId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/messages/create").accept(
                MediaType.APPLICATION_JSON).content("this is a message").contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void testRetrieveMessage_Success() throws Exception {
        Message mockMessage = new Message("this is a message");
        Mockito.when(
                messageService.retrieveMessage(Mockito.anyInt())).thenReturn(mockMessage);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/messages/"+mockMessage.getId()).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{id:"+mockMessage.getId()+"}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test(expected = NestedServletException.class)
    public void testRetrieveMessage_Failed() throws Exception {
        Message mockMessage = new Message("this is a message");
        Mockito.when(
                messageService.retrieveMessage(Mockito.anyInt())).thenThrow(new MessageNotFoundException());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/messages/"+mockMessage.getId()).accept(
                MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    public void testUpdateMessage() throws Exception {
        Message mockMessage = new Message("text");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/messages/update/"+mockMessage.getId()).accept(
                MediaType.APPLICATION_JSON).content("updated text").contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void testDeleteMessage() throws Exception {
        Message mockMessage = new Message("this is a message");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/messages/delete/"+mockMessage.getId()).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
    }

    @Test
    public void testRetrieveAllMessages() throws Exception {
        Message mockMessage1 = new Message("messageText1");
        Message mockMessage2 = new Message("messageText2");
        List<Message> mockMessages = new ArrayList<>();
        mockMessages.add(mockMessage1);
        mockMessages.add(mockMessage2);

        Mockito.when(
                messageService.retrieveAllMessages()).thenReturn(mockMessages);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/messages/all").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":0,\"text\":\"messageText1\",\"palindrome\":false},{\"id\":0,\"text\":\"messageText2\",\"palindrome\":false}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}
