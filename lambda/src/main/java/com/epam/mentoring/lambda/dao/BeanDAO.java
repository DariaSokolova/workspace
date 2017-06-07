package com.epam.mentoring.lambda.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.epam.mentoring.lambda.bean.AbstractBean;


public abstract class BeanDAO<T extends AbstractBean> implements IBeanDAO<T> {

	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public T get(long beanID) {
		Session session = sessionFactory.getCurrentSession();
		T bean = (T) session.get(getBeanClass(), beanID);
		session.flush();
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<T> list = session.createCriteria(getBeanClass()).list();
		session.flush();
		return list;
	}

	@Override
	@Transactional
	public void save(T bean) {
		Session session = sessionFactory.getCurrentSession();		
		session.saveOrUpdate(bean);
		session.flush();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(long beanID) {
		Session session = sessionFactory.getCurrentSession();
		T bean = (T) session.load(getBeanClass(), beanID);
		if (bean.getID() != 0) {
			session.delete(bean);
			session.flush();
		}
	}
}
