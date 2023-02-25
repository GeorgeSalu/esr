package com.algawors.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.algawors.algafood.di.modelo.Cliente;

@Component
public class NotificadorSMS implements Notificador {
	
	@Override
	public void notificar(Cliente cliente,String mensagem) {
		System.out.printf("Notificando %s atraves do telefone %s: %s\n",
				cliente.getNome(), cliente.getTelefone(), mensagem);
	}
	
}
