package com.algaworks.logapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.logapi.domain.model.Cliente;
import com.algaworks.logapi.domain.repository.ClienteRepository;
import com.algaworks.logapi.domain.services.CatalogoClienteService;

@RestController
@RequestMapping("/clientes") //tira a obrigatoriedade da chamada do GetMapping("/clientes")
public class ClienteController {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	
	public ClienteController(CatalogoClienteService catalogoClienteService) {
		super();
		this.catalogoClienteService = catalogoClienteService;
	}

	@GetMapping
	public List<Cliente> Listar(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
		/*alternativa ao codigo acima --->
		 * Depois do (@PathVariable Long clienteId) {
		 * return clienteRepository.findById(clienteId).map(Rcliente -> ResponseEntity.ok(cliente)).orElse(ResponseEntity.notFound().build());
		 * }*/ 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		//return clienteRepository.save(cliente); /***excluido e substituido pelo comando abaixo***/
		return catalogoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		//cliente = clienteRepository.save(cliente); ***excluido e substituido pelo comando abaixo***
		cliente = catalogoClienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	//void = corpo da resppsta nao vai existir
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		
		if(!clienteRepository.existsById(clienteId)) { //senao existir o id, retornar 404
			return ResponseEntity.notFound().build(); 
		}
		
		//clienteRepository.deleteById(clienteId); ***excluido e substituido pelo comando abaixo***
		catalogoClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build(); //como nao tem corpo de resposta, vai retornar 204 "sem conteudo"
	}
	
	
}
