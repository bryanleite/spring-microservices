package br.com.furb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<T> {

	public T save(T entity) {
		return getRepository().save(entity);
	}
	
	public void deleteById(Long id) {
		getRepository().deleteById(id);
	}
	
	public Optional<T> findById(Long id) {
		return getRepository().findById(id);
	}
	
	public List<T> findAll() {
		return getRepository().findAll();
	}
	
	protected abstract JpaRepository<T, Long> getRepository();
}
