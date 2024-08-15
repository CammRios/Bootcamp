package com.codingdojo.cam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.cam.models.Event;
import com.codingdojo.cam.models.Message;
import com.codingdojo.cam.models.Province;
import com.codingdojo.cam.models.User;
import com.codingdojo.cam.services.AppService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller //trae info que el desarrollador desee (repo, serv, model)
public class EventController {

	@Autowired
	private AppService serv;

	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, @ModelAttribute("newEvent") Event newEvent, // obj vacio para el
																								// form:form
			Model model) {// envia info del metodo al jsp
		
		/* ===REVISAMOS SESION== */
		// Validación de que el usuario inició sesión
		User userTemp = (User) session.getAttribute("userInSession"); // Obj User o null
		if (userTemp == null) {
			return "redirect:/";
		}
		/* ===REVISAMOS SESION== */

		// enviar mi user
		User myUser = serv.getUser(userTemp.getId());
		model.addAttribute("user", myUser);

		// Obtener la lista de eventos en mi provincia
		List<Event> eventsNear = serv.getEventsNear(myUser.getProvince());
		model.addAttribute("eventsNear", eventsNear);

		// Obtener la lista de eventos en otras provincias
		List<Event> eventsNotNear = serv.getEventsNotNear(myUser.getProvince());
		model.addAttribute("eventsNotNear", eventsNotNear);

		// enviar list de provinces
		model.addAttribute("provinces", Province.Provinces);

		return "dashboard.jsp";
	}

	@PostMapping("/create_event")
	public String createEvent(HttpSession session, @Valid @ModelAttribute("newEvent") Event newEvent,
							  BindingResult result, // genera msj de error
							  Model model) {// info al jsp
		User userTemp = (User) session.getAttribute("userInSession"); // Obj User o null
		if (userTemp == null) {
			return "redirect:/";
		}
		if (result.hasErrors()) {

			// enviar mi user
			User myUser = serv.getUser(userTemp.getId());
			model.addAttribute("user", myUser);

			// Obtener la lista de eventos en mi provincia
			List<Event> eventsNear = serv.getEventsNear(myUser.getProvince());
			model.addAttribute("eventsNear", eventsNear);

			// Obtener la lista de eventos en otras provincias
			List<Event> eventsNotNear = serv.getEventsNotNear(myUser.getProvince());
			model.addAttribute("eventsNotNear", eventsNotNear);

			// enviar list de provinces
			model.addAttribute("provinces", Province.Provinces);

			return "dashboard.jsp";
		} else {
			serv.saveEvent(newEvent);
			return "redirect:/dashboard";
		}

	}

	@GetMapping("/join/{eventId}")
	public String join(@PathVariable("eventId") Long eventId, HttpSession session) {
		/* === REVISAMOS SESION === */
		User userTemp = (User) session.getAttribute("userInSession"); // Obj User o null
		if (userTemp == null) {
			return "redirect:/";
		}
		/* === REVISAMOS SESION === */

		serv.joinEvent(userTemp.getId(), eventId);
		return "redirect:/dashboard";
	}

	@GetMapping("/cancel/{eventId}")
	public String cancel(@PathVariable("eventId") Long eventId, HttpSession session) {
		/* === REVISAMOS SESION === */
		User userTemp = (User) session.getAttribute("userInSession"); // Obj User o null
		if (userTemp == null) {
			return "redirect:/";
		}
		/* === REVISAMOS SESION === */

		serv.cancelEvent(userTemp.getId(), eventId);
		return "redirect:/dashboard";
	}

	//funcion de entrar a la url / el user recibe info del controller
	@GetMapping("/event/{id}")
	public String event(@PathVariable("id") Long id, HttpSession session, Model model,
			@ModelAttribute("message") Message message) {
		/* === REVISAMOS SESION === */
		User userTemp = (User) session.getAttribute("userInSession"); // Obj User o null
		if (userTemp == null) {
			return "redirect:/";
		}
		/* === REVISAMOS SESION === */

		Event event = serv.getEvent(id);
		model.addAttribute("event", event);

		return "event.jsp";
	}

	//guarda info de un form/ el user brinda info 
	@PostMapping("/create_message")
	public String createMessage(@Valid @ModelAttribute("message") Message message, BindingResult result,
			HttpSession session, Model model) {
		
		/* === REVISAMOS SESION === */
		User userTemp = (User) session.getAttribute("userInSession"); // Obj User o null
		if (userTemp == null) {
			return "redirect:/";
		}
		/* === REVISAMOS SESION === */

		if (result.hasErrors()) {
			model.addAttribute("event", message.getEvent());
			return "event.jsp";
		} else {
			serv.saveMessage(message);
			return "redirect:/event/" + message.getEvent().getId();
		}
	}
	
	@GetMapping("/event/edit/{id}")
	public String editEvent(@PathVariable("id")Long id, //id del evento que quiero editar
							@ModelAttribute("event")Event event,
							HttpSession session, Model model) {
		//reviso session
		User userTemp = (User) session.getAttribute("userInSession"); // Obj User o null
		if (userTemp == null) {
			return "redirect:/";
		}
		
		model.addAttribute("event", serv.getEvent(id));
		
		model.addAttribute("provincesList", Province.Provinces); 
		
		User myUser = serv.getUser(userTemp.getId());
        model.addAttribute("user", myUser);
		
		return "editEvent.jsp";
	}
	
	//editado de info que hace el user/necesita del GetMapping
	@PutMapping("/update_event/{id}")
	public String udateEvent(HttpSession session,
							 @PathVariable("id")Long id,
							 @Valid @ModelAttribute("event")Event event,
							 Model model,
							 BindingResult result) {
		
		//reviso session
		User userTemp = (User) session.getAttribute("userInSession"); // Obj User o null
		if (userTemp == null) {
			return "redirect:/";
		}
		
		if(result.hasErrors()) {
			
			model.addAttribute("event", serv.getEvent(id));
			
			model.addAttribute("provincesList", Province.Provinces); 
			
			User myUser = serv.getUser(userTemp.getId());
	        model.addAttribute("user", myUser);
	        
	        return "editEvent.jsp";
			
		}else {
			
			serv.saveEvent(event);
		}
		
		return "redirect:/dashboard";
	}
	
	@DeleteMapping("/event/delete/{id}")
	public String deleteEvent(@PathVariable("id")Long id,
							  HttpSession session) {
		//reviso session
		User userTemp = (User) session.getAttribute("userInSession"); // Obj User o null
		if (userTemp == null) {
			
			return "redirect:/";
	}
		serv.deleteEvent(id);
		
		return "redirect:/dashboard";
		
	}
	
	

}
