package com.epam.orm.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.orm.dao.GenericDao;

public abstract class GenericDaoImpl<T, I extends Serializable> implements GenericDao<T, I> {

	@Autowired
	private EntityManager entityManager;

	public void setSessionFactory(EntityManager em) {
		this.entityManager = em;
	}

	protected EntityManager getEntityManager() {
		if (entityManager == null)
			throw new IllegalStateException("SessionFactory has not been set on DAO before usage");
		return entityManager;
	}

	public abstract Class<T> getType();

	@Transactional
	@Override
	public T find(I id) {
		return (T) getEntityManager().find(getType(), id);
	}

	@Transactional
	@Override
	public void delete(T obj) {
		getEntityManager().remove(obj);
	}

	@Transactional
	@Override
	public void save(T obj) {
		getEntityManager().persist(obj);
	}

	@Transactional
	@Override
	public List<T> getAll() {
		return ((SessionFactory) getEntityManager().getDelegate()).getCurrentSession().createCriteria(getType()).list();
	}

	@Transactional
	@Override
	public void update(T obj) {
		getEntityManager().merge(obj);

	}

}