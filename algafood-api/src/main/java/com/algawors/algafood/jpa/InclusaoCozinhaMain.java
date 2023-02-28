package com.algawors.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algawors.algafood.AlgafoodApiApplication;
import com.algawors.algafood.domain.model.Cozinha;

public class InclusaoCozinhaMain {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("brasileira");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("japonesa");
		
		
		cadastroCozinha.adicionar(cozinha1);
		cadastroCozinha.adicionar(cozinha2);
		
	}
}
