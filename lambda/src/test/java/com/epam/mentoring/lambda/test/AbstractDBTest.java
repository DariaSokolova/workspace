package com.epam.mentoring.lambda.test;

import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:springMainTest.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Ignore
public class AbstractDBTest {

	@Autowired
	protected SessionFactory sessionFactory;

}
