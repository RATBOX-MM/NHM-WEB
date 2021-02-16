package com.rbx.nhm.web.repositories;


import java.util.Map;
import java.util.List;
import java.util.Map.Entry;
import java.io.Serializable;

import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author kaungsithu
 * @since 07-02-2021
 *
 */

public abstract class MainRepository<T extends Serializable> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<T> type;

	public MainRepository(Class<T> type) {
		super();
		this.type = type;
	}
	
	 public void persist(T entity) {
		entityManager.persist(entity);
	}
	
	public void merge(T entity) {
		entityManager.merge(entity);
	}
	
	public void delete(T entity) {
		entityManager.remove(entity);
	}
	
	public T findByIdWithErase(String id) {
		String sql = String.format("select t from %s t where t.erase = false and t.id = '"+id+"'", type.getSimpleName());
		TypedQuery<T> query = entityManager.createQuery(sql, type);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public T findByIdWithoutErase(String id) {
		String sql = String.format("select t from %s t where t.id = '"+id+"'", type.getSimpleName());
		TypedQuery<T> query = entityManager.createQuery(sql, type);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public long findAllCountWithErase() {
		String sql = String.format("select count(t) from %s t where t.erase = false", type.getSimpleName());
		TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
		return query.getSingleResult().longValue();
	}
	
	public long findAllCountWithoutErase() {
		String sql = String.format("select count(t) from %s t", type.getSimpleName());
		TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
		return query.getSingleResult().longValue();
	}
	
	public List<T> findAllWithErase() {
		String sql = String.format("select t from %s t where t.erase = false", type.getSimpleName());
		TypedQuery<T> query = entityManager.createQuery(sql, type);
		return query.getResultList();
	}
	
	public List<T> findAllWithoutErase() {
		String sql = String.format("select t from %s t", type.getSimpleName());
		TypedQuery<T> query = entityManager.createQuery(sql, type);
		return query.getResultList();
	}
	
	public List<T> findAllWithEraseByLimit(int number) {
		String sql = String.format("select t from %s t where t.erase = false", type.getSimpleName());
		TypedQuery<T> query = entityManager.createQuery(sql, type);
		return query.setMaxResults(number).getResultList();
	}
	
	public List<T> findAllWithoutEraseByLimit(int number) {
		String sql = String.format("select t from %s t", type.getSimpleName());
		TypedQuery<T> query = entityManager.createQuery(sql, type);
		return query.setMaxResults(number).getResultList();
	}
	
	public List<T> findByNamedQuery(String sql, Map<String, Object> params) {
		TypedQuery<T> query  = entityManager.createNamedQuery(sql, type);
		return find(query, params, type);
	}
	
	public T findSingleByNamedQuery (String sql, Map<String, Object> params) {
		TypedQuery<T> query  = entityManager.createNamedQuery(sql, type);
		return findSingle(query, params, type);
	}
	
	public long findCountByNamedQuery(String sql, Map<String, Object> params) {
		TypedQuery<Long> query  = entityManager.createNamedQuery(sql, Long.class);
		for (String key : params.keySet())
			query.setParameter(key, params.get(key));
		return query.getSingleResult().longValue();
	}
	
	private<E> List<E> find(TypedQuery<E> query, Map<String, Object> params, Class<E> type) {
		if (params != null)
			for(Entry<String, Object> entry : params.entrySet())
				query.setParameter(entry.getKey(), entry.getValue());
		return query.getResultList();
	}
	
	private<E> E findSingle(TypedQuery<E> query, Map<String, Object> params, Class<E> type) {
		if (params != null)
			for(Entry<String, Object> entry : params.entrySet())
				query.setParameter(entry.getKey(), entry.getValue());
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
