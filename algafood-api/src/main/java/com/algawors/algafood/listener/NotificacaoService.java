package com.algawors.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algawors.algafood.di.notificacao.NivelUrgencia;
import com.algawors.algafood.di.notificacao.Notificador;
import com.algawors.algafood.di.notificacao.TipoDoNotificador;
import com.algawors.algafood.di.service.ClienteAtivadoEvent;

@Component
public class NotificacaoService {

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "seu cadastro no sistema esta ativo");
	}
	
}
