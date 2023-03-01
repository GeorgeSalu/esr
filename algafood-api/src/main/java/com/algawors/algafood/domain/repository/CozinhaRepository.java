package com.algawors.algafood.domain.repository;

import java.util.List;

import com.algawors.algafood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> todos();
	Cozinha porId(Long id);
	Cozinha adicionar(Cozinha cozinha);
	void remover(Cozinha cozinha);
	
}
