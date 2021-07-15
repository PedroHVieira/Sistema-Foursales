package com.foursales.api.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.foursales.api.model.MyUserDetails;

@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView index(@AuthenticationPrincipal MyUserDetails user) {
		ModelAndView view = new ModelAndView("index");
		
		
		return view.addObject("user", user);
	}
}
