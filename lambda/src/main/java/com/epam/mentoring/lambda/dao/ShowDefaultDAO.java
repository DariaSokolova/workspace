package com.epam.mentoring.lambda.dao;

import com.epam.mentoring.lambda.bean.Show;


public class ShowDefaultDAO extends BeanDAO<Show> implements ShowDAO {

	@Override
	public Class<?> getBeanClass() {
		return Show.class;
	}
}
