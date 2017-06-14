package com.epam.mentoring.lambda.scheduler.lock;

import com.epam.mentoring.lambda.scheduler.Task;

public interface LockManager {
	public void executeTask(Task task);
}
