package com.algawors.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.algawors.algafood.di.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {
	
	@Override
	public void notificar(Cliente cliente,String mensagem) {
		System.out.printf("Notificando %s atraves do email %s: %s\n",
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
	
}
