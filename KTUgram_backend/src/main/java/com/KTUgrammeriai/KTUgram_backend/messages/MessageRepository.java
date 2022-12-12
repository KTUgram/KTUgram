package com.KTUgrammeriai.KTUgram_backend.messages;

import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {


}
