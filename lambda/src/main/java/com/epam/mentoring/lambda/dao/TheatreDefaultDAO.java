package com.epam.mentoring.lambda.dao;

import com.epam.mentoring.lambda.bean.Theatre;

public class TheatreDefaultDAO extends BeanDAO<Theatre> implements TheatreDAO {

	@Override
	public Class<?> getBeanClass() {
		return Theatre.class;
	}
}
