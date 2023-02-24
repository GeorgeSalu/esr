package com.algawors.algafood.di.notificacao;

import com.algawors.algafood.di.modelo.Cliente;

public class NotificacadorEmail {
	
	public void notificar(Cliente cliente,String mensagem) {
		System.out.printf("Notificando %s atraves do email %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
