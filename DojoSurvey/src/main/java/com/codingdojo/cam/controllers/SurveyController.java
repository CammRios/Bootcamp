package com.codingdojo.cam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class SurveyController {
	
	@GetMapping("/")
	public String index() {
		return "index.jsp";
	}
	
	@PostMapping("/result")
	public String result(@RequestParam (value="username")String name,
						 @RequestParam (value="location") String location,
						 @RequestParam (value="language")String language,
						 @RequestParam (value="comment")String comment,
						 HttpSession session) {
	
		session.setAttribute("name", name);
		session.setAttribute("location", location);
		session.setAttribute("language", language);
		session.setAttribute("comment", comment);
		
		System.out.println(name+location+language+comment);
		
		return"redirect:/answers";
	}
	@GetMapping("/answers")
	public String answers() {
		return "answers.jsp";
	}
}
