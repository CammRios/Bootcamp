package com.codingdojo.cam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.cam.models.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{

}
