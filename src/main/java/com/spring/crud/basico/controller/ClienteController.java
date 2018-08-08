package com.spring.crud.basico.controller;

import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.crud.basico.model.Cliente;
import com.spring.crud.basico.repository.ClienteRepository;

@RestController
public class ClienteController {
	
	@Autowired
	ClienteRepository clienterepository;
	
	@RequestMapping(method=RequestMethod.POST, path="/clientes")
	public Cliente criarCLiente(@Valid @RequestBody Cliente cliente) {	
	
		clienterepository.save(cliente);
		
		return cliente;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/clientes")
	public Iterable<Cliente> listarClientes(){
		
		return clienterepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/clientes/{id}")
	public ResponseEntity<?> buscarCliente(@PathVariable long id){
		Optional<Cliente> optionalCliente = clienterepository.findById(id);
		
		if(!optionalCliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(optionalCliente.get());
		
	}
	@RequestMapping(method=RequestMethod.PUT, path="/clientes/{id}")
	public ResponseEntity<?> alterarCliente(@PathVariable long id, @Valid @RequestBody Cliente cliente){
		Optional<Cliente> optionalCliente = clienterepository.findById(id);
		
		if(!optionalCliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente clienteAtualizado = optionalCliente.get();
		
		clienteAtualizado.setNome(cliente.getNome());
		clienteAtualizado.setCpf(cliente.getCpf());
		clienteAtualizado.setEndereco(cliente.getEndereco());		
		
		Cliente clienteSalvo = clienterepository.save(clienteAtualizado);
		
		return ResponseEntity.ok().body(clienteSalvo);
		
		
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, path="/clientes/{id}")
	public ResponseEntity<?> deletarCliente(@PathVariable long id){
		Optional<Cliente> optionalCliente = clienterepository.findById(id);
		
		if(!optionalCliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		clienterepository.deleteById(id);
		
		return ResponseEntity.ok().build();
	
	}
	
	
}
