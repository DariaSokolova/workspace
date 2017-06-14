package com.epam.mentoring.lambda.scheduler;

import java.util.Map;

public interface Task {
	public void execute();

	public void setContext(Map<String, Object> context);
}
