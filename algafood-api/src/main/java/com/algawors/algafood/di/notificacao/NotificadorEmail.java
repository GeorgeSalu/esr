package com.algawors.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algawors.algafood.di.modelo.Cliente;

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {
	
	public NotificadorEmail() {
		System.out.println("NotificadorEmail Real");
	}
	
	@Override
	public void notificar(Cliente cliente,String mensagem) {
		System.out.printf("Notificando %s atraves do email %s: %s\n",
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
	
}
