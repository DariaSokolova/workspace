package com.epam.mentoring.lambda.test;

import org.junit.Ignore;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
@Ignore
public class AbstractDBUnitTest extends AbstractDBTest {

}
