package com.algaworks.logapi.domain.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.logapi.domain.model.Cliente;
import com.algaworks.logapi.domain.model.Entrega;
import com.algaworks.logapi.domain.model.StatusEntrega;
import com.algaworks.logapi.domain.repository.ClienteRepository;
import com.algaworks.logapi.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {
	
	private ClienteRepository clienteRepository;
	private EntregaRepository entregaRepository;
	private CatalogoClienteService catalogoClienteService;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
		
		return entregaRepository.save(entrega);
	}

	public SolicitacaoEntregaService(ClienteRepository clienteRepository, EntregaRepository entregaRepository, CatalogoClienteService catalogoClienteService) {
		this.clienteRepository = clienteRepository;
		this.entregaRepository = entregaRepository;
		this.catalogoClienteService = catalogoClienteService;
	}

	
}
