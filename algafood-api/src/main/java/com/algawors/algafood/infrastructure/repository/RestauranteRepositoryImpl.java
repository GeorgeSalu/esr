package com.algawors.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algawors.algafood.domain.model.Restaurante;
import com.algawors.algafood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> todos() {
		TypedQuery<Restaurante> query = manager.createQuery("from Restaurante", Restaurante.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}

	@Override
	public Restaurante porId(Long id) {
		return manager.find(Restaurante.class, id);
	}

	@Override
	@Transactional
	public void remover(Restaurante restaurante) {
		restaurante = porId(restaurante.getId());
		manager.remove(restaurante);
	}

}
