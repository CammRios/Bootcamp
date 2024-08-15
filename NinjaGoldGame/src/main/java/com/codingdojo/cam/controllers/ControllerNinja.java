package com.codingdojo.cam.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControllerNinja {
	
	@GetMapping("/Gold")
	public String gold() {
		return "index.jsp"; //ante la ruta /gold muestre index.jsp
	}
	
	@GetMapping("/")
	public String index() {
		return "redirect:/Gold";
	}
	
	@PostMapping("/find")
	public String find (@RequestParam("place") String place,
						HttpSession session) {
		int gold = 0;
		ArrayList<String> activities = new ArrayList<String>();
		
		if(session.getAttribute("gold") == null) {
			//NO hay sesion todavia
			session.setAttribute("gold", 0);
			session.setAttribute("activities", activities);
		}else {
			gold = (int) session.getAttribute("gold");
			activities = (ArrayList<String>) session.getAttribute("activities");
		}
		int min = 0;
		int max = 0;
		// compara el lugar con distintos valores
		switch(place) {
			case "farm":
				min = 10;
				max = 20;
				break;
			case "cave":
				min = 5;
				max = 10;
				break;
			case "house":
				min = 2;
				max = 5;
				break;
			case "casino":
				min = -50;
				max = 50;
				break;
		}
		
		int randomNum = min + (int)(Math.random() * ((max - min) + 1));
		gold += randomNum;
		session.setAttribute("gold", gold); //actualizo la puntuación
		
		Date date= new Date();
		SimpleDateFormat format = new  SimpleDateFormat("MMMM d Y h:mm a");
		String formatDate = format.format(date);
		
		String message = "";
		if(randomNum < 0) {
			message = "You entered the "+place+" and lost "+randomNum+" gold, Ouch! " +formatDate;
		}else {
			message = "You entered the "+place+" and earned "+randomNum+" gold, Yeyy!! "+formatDate;
		}
		
		activities.add(message);
		session.setAttribute("activities", activities);
		
		return"redirect:/Gold";
	}

}
