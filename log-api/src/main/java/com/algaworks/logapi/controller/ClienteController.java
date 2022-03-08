package com.algaworks.logapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.logapi.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Misa");
		cliente1.setTelefone("11 99999-0001");
		cliente1.setEmail("misa@email.com");
		
		var cliente2 = new Cliente(); //var = novo metodo para declarar variavel
		cliente2.setId(2L);
		cliente2.setNome("Kyra");
		cliente2.setTelefone("11 99999-0002");
		cliente2.setEmail("kyra@email.com");
		
		return Arrays.asList(cliente1, cliente2);
	}

}
