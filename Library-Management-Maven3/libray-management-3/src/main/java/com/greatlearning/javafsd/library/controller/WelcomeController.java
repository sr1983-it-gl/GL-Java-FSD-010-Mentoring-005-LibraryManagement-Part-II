package com.greatlearning.javafsd.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String doWelcomePage() {
		return "welcome";
	}
}
