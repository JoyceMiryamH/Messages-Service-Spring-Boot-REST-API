package com.palindrome.message.palindromemessageservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String text;

    public Message() {
    }

    public Message(String text) {
        super();
        SecureRandom random = new SecureRandom();
        String generatedId = new BigInteger(130, random).toString(32);
        this.id = generatedId;
        this.text = text;
    }

    public Message(String id, String text) {
        super();
        this.id = id;
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return String.format("Student [id=%s, text=%s]", id, text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
