package com.algawors.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawors.algafood.di.modelo.Cliente;
import com.algawors.algafood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	private Notificador notificador;
	
	@Autowired
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
	}
	
	public AtivacaoClienteService(String qualquer) {
		
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		notificador.notificar(cliente, "seu cadastro no sistema esta ativo");
	}
}
