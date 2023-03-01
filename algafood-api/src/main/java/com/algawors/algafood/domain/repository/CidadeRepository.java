package com.algawors.algafood.domain.repository;

import java.util.List;

import com.algawors.algafood.domain.model.Cidade;

public interface CidadeRepository {

	List<Cidade> listar();
	Cidade buscar(Long id);
	Cidade salvar(Cidade cidade);
	void remover(Cidade cidade);
	
}