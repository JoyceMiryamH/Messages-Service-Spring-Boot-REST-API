package com.palindrome.message.palindromemessageservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Message {
    @Id
    @GeneratedValue()
    private Long id;
    private Date created;
    private Date lastModified;
    private String text;
    private boolean isPalindrome;


    public Message() {
    }

    public Message(String text) {
        super();
        this.text = text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getLastModified() {
        return lastModified;
    }
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isPalindrome() {
        return isPalindrome;
    }

    public void setPalindrome(boolean palindrome) {
        isPalindrome = palindrome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message that = (Message) o;
        return isPalindrome == that.isPalindrome &&
                Objects.equals(id, that.id) &&
                Objects.equals(created, that.created) &&
                Objects.equals(lastModified, that.lastModified) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, lastModified, text, isPalindrome);
    }

    @Override
    public String toString() {
        return "PalindromeMessage{" +
                "id=" + id +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", text='" + text + '\'' +
                ", isPalindrome=" + isPalindrome +
                '}';
    }
}
