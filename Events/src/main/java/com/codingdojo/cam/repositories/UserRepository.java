package com.codingdojo.cam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.cam.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	//Consulta que me regrese un User en base a su email
		User findByEmail(String email);
}
