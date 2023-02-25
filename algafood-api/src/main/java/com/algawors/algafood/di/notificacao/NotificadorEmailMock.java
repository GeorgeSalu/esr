package com.algawors.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algawors.algafood.di.modelo.Cliente;

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmailMock implements Notificador {
	
	public NotificadorEmailMock() {
		System.out.println("NotificadorEmail Mock");
	}
	
	@Override
	public void notificar(Cliente cliente,String mensagem) {
		System.out.printf("Mock: Notificando seria enviada para %s atraves do email %s: %s\n",
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
	
}
