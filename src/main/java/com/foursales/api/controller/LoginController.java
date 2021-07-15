package com.foursales.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public ModelAndView login(RedirectAttributes status) {
		ModelAndView view = new ModelAndView("login/login");
		
		return view;
	}
}
