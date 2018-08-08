package com.spring.crud.basico.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.spring.crud.basico.model.Compra;
import com.spring.crud.basico.repository.ClienteRepository;
import com.spring.crud.basico.repository.CompraRepository;

@RestController
public class CompraController {
	
	@Autowired
	CompraRepository compraRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@RequestMapping(method=RequestMethod.POST, path="/compras")
	public Compra criarCompra(@Valid @RequestBody Compra compra) {
		compraRepository.save(compra);
		return compra;	
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/listadecompras")
	public Iterable<Compra> listaDeCompras(){
		return compraRepository.findAll();
	}
	
}
