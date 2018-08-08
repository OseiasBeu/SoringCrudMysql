package com.spring.crud.basico.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.crud.basico.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	
}
