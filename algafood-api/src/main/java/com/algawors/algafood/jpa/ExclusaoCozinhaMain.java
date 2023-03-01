package com.algawors.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algawors.algafood.AlgafoodApiApplication;
import com.algawors.algafood.domain.model.Cozinha;
import com.algawors.algafood.domain.repository.CozinhaRepository;

public class ExclusaoCozinhaMain {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		
		CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1l);
		
		cadastroCozinha.remover(cozinha);
	}
}
