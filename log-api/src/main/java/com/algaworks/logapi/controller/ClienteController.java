package com.algaworks.logapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.logapi.domain.model.Cliente;
import com.algaworks.logapi.domain.repository.ClienteRepository;

@RestController
public class ClienteController {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> Listar(){
		return clienteRepository.findAll();
	}
		
}
