package com.foursales.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.foursales.api.model.Candidato;
import com.foursales.api.model.MyUserDetails;
import com.foursales.api.repository.CandidatosRepository;

@RestController
@RequestMapping("/candidatos")
public class CandidatosController {

	@Autowired
	private CandidatosRepository candidatoRepo;
	
	@GetMapping
	public ModelAndView abrir(@AuthenticationPrincipal MyUserDetails user) {
		ModelAndView view = new ModelAndView("candidatos/candidatos");
		
		return view.addObject("user", user);
	}
	
	@GetMapping("/buscar")
	public ModelAndView buscar() {
		ModelAndView view = new ModelAndView("candidatos/tabela_candidatos");
		
		List<Candidato> lista = candidatoRepo.findAll();		
		
		return view.addObject("lista", lista);
	}
	
	@GetMapping(value = {"/modal", "/modal/{pesquisa}"})
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView modal(@PathVariable Optional<String> pesquisa) {
		ModelAndView view = new ModelAndView("candidatos/modal_candidato");	
		
		if(pesquisa.isPresent()) {
			
			Candidato candidato = candidatoRepo.findById(Long.parseLong(pesquisa.get())).get();
			
			return view.addObject("candidato", candidato);
		}else {
			return view.addObject("candidato", new Candidato());
		}
	}
}
