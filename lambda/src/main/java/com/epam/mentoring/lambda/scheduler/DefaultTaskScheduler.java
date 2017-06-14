package com.epam.mentoring.lambda.scheduler;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.lambda.scheduler.lock.LockManager;

public class DefaultTaskScheduler implements TaskScheduler {

	private int poolSize = 1;
	private ScheduledExecutorService scheduledThreadPool;

	@Autowired
	private LockManager lockManager;

	public DefaultTaskScheduler() {
		scheduledThreadPool = Executors.newScheduledThreadPool(poolSize);
	}

	public DefaultTaskScheduler(int poolSize) {
		this.poolSize = poolSize;
		scheduledThreadPool = Executors.newScheduledThreadPool(poolSize);
	}

	@Override
	public ScheduledFuture<?> schedule(Task task, Date startTime) {
		long initialDelay = startTime.getTime() - System.currentTimeMillis();
		return scheduledThreadPool.schedule(wrap(task), initialDelay,
				TimeUnit.MILLISECONDS);
	}

	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(Task task, Date startTime,
			long period) {
		long initialDelay = startTime.getTime() - System.currentTimeMillis();
		return scheduledThreadPool.scheduleAtFixedRate(wrap(task),
				initialDelay, period, TimeUnit.MILLISECONDS);
	}

	private Runnable wrap(Task task) {
		return new LockableTask(task, lockManager);
	}

}
