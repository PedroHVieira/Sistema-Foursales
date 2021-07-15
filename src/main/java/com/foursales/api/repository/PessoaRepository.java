package com.foursales.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foursales.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query(value="SELECT * FROM pessoa WHERE pess_email = ?1",nativeQuery = true)
	Pessoa findByEmail(String email);
}
