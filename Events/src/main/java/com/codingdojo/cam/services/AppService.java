package com.codingdojo.cam.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.cam.models.Event;
import com.codingdojo.cam.models.Message;
import com.codingdojo.cam.models.User;
import com.codingdojo.cam.repositories.EventRepository;
import com.codingdojo.cam.repositories.MessageRepository;
import com.codingdojo.cam.repositories.UserRepository;

@Service
public class AppService {

	@Autowired
	private UserRepository ur;

	@Autowired
	private EventRepository er;

	@Autowired
	private MessageRepository mr;

	/* Método que registre a un nuevo usuario */
	public User register(User newUser, BindingResult result) {

		// Comparar las contraseñas
		String password = newUser.getPassword();
		String confirm = newUser.getConfirm();
		if (!password.equals(confirm)) {
			// SI no son iguales
			// path, clave, mensaje
			result.rejectValue("confirm", "Matches", "Password and confirmation don't match");
		}

		// Revisar que el email no esté registrado
		String email = newUser.getEmail();
		User userExist = ur.findByEmail(email); // Objeto de User o null
		if (userExist != null) {
			// El correo ya está registrado
			result.rejectValue("email", "Unique", "E-mail already exists");
		}

		// Si existe error, regreso null
		if (result.hasErrors()) {
			return null;
		} else {
			// NO HAY ERRORES
			// Hashear contraseña
			String passHash = BCrypt.hashpw(password, BCrypt.gensalt());
			newUser.setPassword(passHash); // Establecemos el password hasheado
			return ur.save(newUser);
		}

	}

	/* Método que revisa que los datos sean correctos para Iniciar Sesión */
	public User login(String email, String password) {
		// Revisamos que el correo exista en BD
		User userTryingLogin = ur.findByEmail(email); // Objeto User o NULL

		if (userTryingLogin == null) {
			return null;
		}

		// Comparar las contraseñas
		// BCrypt.checkpw(Contra NO hash, Contra SI hash) -> True o False
		if (BCrypt.checkpw(password, userTryingLogin.getPassword())) {
			return userTryingLogin;
		} else {
			return null;
		}

	}
	
	//funcion que me regrese un evento
	public Event saveEvent(Event newEvent) {
		return er.save(newEvent);
	}
	
	//metodo qeue en base a un id nos regresa un obj de user
	public User getUser(Long id) {
		return ur.findById(id).orElse(null);
	}
	
	
	/*Metodo que regresa lista de eventos de una provincia*/
	public List<Event> getEventsNear(String province){
		return er.findByProvince(province);
	}
	
	/*Metodo publico regresa una lista de event de otras provincias*/
	public List<Event> getEventsNotNear(String province){
		return er.findByProvinceIsNot(province);
	}
	public Event getEvent(Long id) {
		return er.findById(id).orElse(null);
	}
	
	/*Método que une un usuario a un evento*/
	public void joinEvent(Long userId, Long eventId) {
		User myUser = getUser(userId);
		Event myEvent = getEvent(eventId);
		
		myEvent.getJoinedUsers().add(myUser);
		er.save(myEvent);
	}
	
	/*Método que cancela asistencia de un usuario a un evento*/
	public void cancelEvent(Long userId, Long eventId) {
		User myUser = getUser(userId);
		Event myEvent = getEvent(eventId);
		
		myEvent.getJoinedUsers().remove(myUser);
		er.save(myEvent);
	}
	
	/*Método que guarde mensajes*/
	public Message saveMessage(Message newMessage) {
		return mr.save(newMessage);
	}
	
	// metodo que no regrese nada para borrar en cascada
	public void deleteEvent(Long id) {
		er.deleteById(id);
	}
	
}
