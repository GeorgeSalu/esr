package com.algawors.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algawors.algafood.di.modelo.Cliente;
import com.algawors.algafood.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {

	private AtivacaoClienteService ativacaoClienteService;
	
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		Cliente cliente = new Cliente("joao", "joao@gmail.com", "121213232", false);
		ativacaoClienteService.ativar(cliente);
		return "Ola!";
	}
	
}
