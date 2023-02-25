package com.algawors.algafood.di.notificacao;

import com.algawors.algafood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}