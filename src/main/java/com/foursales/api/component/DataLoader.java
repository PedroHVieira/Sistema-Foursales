package com.foursales.api.component;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.foursales.api.model.Pessoa;
import com.foursales.api.model.Roles;
import com.foursales.api.model.User;
import com.foursales.api.repository.PessoaRepository;
import com.foursales.api.repository.RolesRepository;
import com.foursales.api.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	private PessoaRepository pessoaRepo;
	@Autowired
	private RolesRepository rolesRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		
		Pessoa objPessoa = pessoaRepo.findByEmail("admin@admin.com");
		if(objPessoa == null) {
			objPessoa = new Pessoa("Pedro Henrique Vieira Silva", "admin@admin.com",ts,"Usuário Admin Teste");
			pessoaRepo.save(objPessoa);	
		}
		
		Roles objRoles = rolesRepo.findByNome("ROLE_ADMIN");
		if(objRoles == null){
			objRoles = new Roles("ROLE_ADMIN",ts);
			rolesRepo.save(objRoles);
		}
		
		User objUser = userRepo.findByUserName("admin@admin.com");
		if(objUser == null) {
			objUser = new User("$2a$10$c7aAs0aMEAdi5YisSGjuF.V1g7EVpxjI/amo7YeKmbfRdNQDkxIGy", ts, objPessoa, objRoles);
			userRepo.save(objUser);
		}
	}
}