package com.codingdojo.cam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.cam.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>{
	
	//SELECT * FROM events WHERE province = <Provincia Enviada>
	List<Event> findByProvince(String province); //lista de eventos en mi provincia
	
	//SELECT * FROM events WHERE province != <Provincia Enviada>
	List<Event> findByProvinceIsNot(String province);
}
