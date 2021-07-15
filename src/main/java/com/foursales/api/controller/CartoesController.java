package com.foursales.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.foursales.api.model.Candidato;
import com.foursales.api.model.CartaoCredito;
import com.foursales.api.model.MyUserDetails;
import com.foursales.api.repository.CandidatosRepository;
import com.foursales.api.repository.CartaoCreditoRepository;

@RestController
@RequestMapping("/cartoes")
public class CartoesController {
	
	@Autowired 
	private CartaoCreditoRepository cartoesRepo;
	@Autowired
	private CandidatosRepository candidatoRepo;

	@GetMapping
	public ModelAndView abrir(@AuthenticationPrincipal MyUserDetails user) {
		ModelAndView view = new ModelAndView("cartoes/cartoes");
		
		return view.addObject("user", user);
	}
	
	@GetMapping("/buscar")
	public ModelAndView buscar() {
		ModelAndView view = new ModelAndView("cartoes/tabela_cartoes");
		
		List<CartaoCredito> lista = cartoesRepo.findAll();		
		
		return view.addObject("lista", lista);
	}
	
	@GetMapping(value = {"/modal", "/modal/{pesquisa}"})
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView modal(@PathVariable Optional<String> pesquisa) {
		ModelAndView view = new ModelAndView("cartoes/modal_cartoes");	
		
		if(pesquisa.isPresent()) {
			
			CartaoCredito card = cartoesRepo.findById(Long.parseLong(pesquisa.get())).get();
			
			return view.addObject("card", card);
		}else {
			return view.addObject("card", new CartaoCredito());
		}
	}
	
	@GetMapping(value = { "/filtrar/{nome}", "/filtrar" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List> autoComplete(@PathVariable Optional<String> nome,@AuthenticationPrincipal MyUserDetails user) {
		List lista = null;
		String pesquisa = "";
		if (nome.isPresent()) {
			pesquisa = nome.get();
		}
		lista = candidatoRepo.findByNomeAutoComplete(pesquisa);
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
}
