package com.foursales.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foursales.api.model.Candidato;
import com.foursales.api.model.CartaoCredito;
import com.foursales.api.repository.CandidatosRepository;
import com.foursales.api.repository.CartaoCreditoRepository;

@RestController
@RequestMapping("/cartao_credito")
public class CartaoCreditoController {

	@Autowired
	private CartaoCreditoRepository cartaoRepo;
	@Autowired
	private CandidatosRepository candidatoRepo;
		
	@GetMapping
	public List<CartaoCredito> listar(){	
		return cartaoRepo.findAll();
	}

	@PostMapping
	public ResponseEntity<CartaoCredito> criar(@RequestBody CartaoCredito card){	
		try {
			Candidato candidato = candidatoRepo.findById(card.getCandidato().getId()).get();
			if(candidato != null) {
				card.setCandidato(candidato);
				cartaoRepo.save(card);
				
				return new ResponseEntity<CartaoCredito>(card, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<CartaoCredito>(card, HttpStatus.NOT_ACCEPTABLE);
			}
		}catch (Exception e) {
			return new ResponseEntity<CartaoCredito>(card, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping
	public ResponseEntity<CartaoCredito> atualizar(@RequestBody CartaoCredito card){	
		try {
			if(card.getId() == null) {
				return new ResponseEntity<CartaoCredito>(card, HttpStatus.NOT_ACCEPTABLE);
			}
			Candidato candidato = candidatoRepo.findById(card.getCandidato().getId()).get();
			
			if(candidato == null) {
				return new ResponseEntity<CartaoCredito>(card, HttpStatus.NOT_ACCEPTABLE);				
			}
			
			card.setCandidato(candidato);
			cartaoRepo.save(card);				
			return new ResponseEntity<CartaoCredito>(card, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<CartaoCredito>(card, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody CartaoCredito card){			
		try {
			if(card.getId() == null) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}			
			cartaoRepo.delete(card);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
	}
}
