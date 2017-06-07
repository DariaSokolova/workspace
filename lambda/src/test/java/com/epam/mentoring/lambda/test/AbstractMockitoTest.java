package com.epam.mentoring.lambda.test;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class AbstractMockitoTest {
	
	@Before
	public void setup() {
	    MockitoAnnotations.initMocks(this);
	}
}
