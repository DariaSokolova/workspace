package com.epam.mentoring.lambda.dao;

import com.epam.mentoring.lambda.bean.Perfomance;


public class PerfomanceDefaultDAO extends BeanDAO<Perfomance> implements PerfomanceDAO {

	@Override
	public Class<?> getBeanClass() {
		return Perfomance.class;
	}
}
