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
import com.foursales.api.repository.CandidatosRepository;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {
	
	@Autowired
	private CandidatosRepository candidatoRepo;
		
	@GetMapping
	public List<Candidato> listar(){	
		return candidatoRepo.findAll();
	}

	@PostMapping
	public ResponseEntity<Candidato> criar(@RequestBody Candidato candidato){	
		try {
			candidatoRepo.save(candidato);
			return new ResponseEntity<Candidato>(candidato, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Candidato>(candidato, HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@PutMapping
	public ResponseEntity<Candidato> atualizar(@RequestBody Candidato candidato){	
		try {
			if(candidato.getId() == null) {
				return new ResponseEntity<Candidato>(candidato, HttpStatus.NOT_ACCEPTABLE);
			}
			
			Candidato candidatoAux = candidatoRepo.findById(candidato.getId()).get();
			if(candidatoAux == null) {
				return new ResponseEntity<Candidato>(candidato, HttpStatus.NOT_ACCEPTABLE);
			}
			
			candidatoRepo.save(candidato);
			return new ResponseEntity<Candidato>(candidato, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Candidato>(candidato, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody Candidato candidato){	
		try {
			if(candidato.getId() == null) {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
			candidatoRepo.delete(candidato);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
