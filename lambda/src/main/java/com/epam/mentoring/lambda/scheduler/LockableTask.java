package com.epam.mentoring.lambda.scheduler;

import com.epam.mentoring.lambda.scheduler.lock.LockManager;

public class LockableTask implements Runnable {

	private final LockManager lockManager;
	private final Task task;

	public LockableTask(Task task, LockManager lockManager) {
		this.task = task;
		this.lockManager = lockManager;
	}

	@Override
	public void run() {
		lockManager.executeTask(task);
	}

}
