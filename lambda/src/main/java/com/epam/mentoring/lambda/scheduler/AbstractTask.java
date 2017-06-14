package com.epam.mentoring.lambda.scheduler;

import java.util.Map;

public abstract class AbstractTask implements Task {

	protected Map<String, Object> context;

	@Override
	public void setContext(Map<String, Object> context) {
		this.context = context;
	}

}
