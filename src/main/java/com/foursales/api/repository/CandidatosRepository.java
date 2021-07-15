package com.foursales.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foursales.api.model.Candidato;

public interface CandidatosRepository extends JpaRepository<Candidato, Long>{

	@Query(value="SELECT * FROM candidato WHERE cand_nome LIKE %?1%",nativeQuery = true)
	List<Candidato> findByNomeAutoComplete(String nome);
}
