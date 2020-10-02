package com.palindrome.message.palindromemessageservice.repository;

import com.palindrome.message.palindromemessageservice.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
