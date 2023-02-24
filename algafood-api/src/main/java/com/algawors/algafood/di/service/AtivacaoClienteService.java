package com.algawors.algafood.di.service;

import org.springframework.stereotype.Component;

import com.algawors.algafood.di.modelo.Cliente;
import com.algawors.algafood.di.notificacao.NotificadorEmail;

@Component
public class AtivacaoClienteService {

	private NotificadorEmail notificador;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		notificador.notificar(cliente, "seu cadastro no sistema esta ativo");
	}
}
