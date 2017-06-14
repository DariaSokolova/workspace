package com.epam.mentoring.lambda.scheduler.lock;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.epam.mentoring.lambda.scheduler.Task;

public class DefaultLockManager implements LockManager {
	//it should be a table in the database, but just for the sake of simplicity
	private static final Map<String, Date> registry = new ConcurrentHashMap<>();
	private final static Date lockUntil;

	private final Logger logger = Logger.getLogger(DefaultLockManager.class);

	static {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2999, 0, 0);
		lockUntil = calendar.getTime();
	}

	@Override
	public void executeTask(Task task) {
		String name = task.getClass().getName();
		boolean isLocked = lock(name);
		if (!isLocked) {
			try {
				logger.info("Locked " + name);
				task.execute();
			} finally {
				unlock(name);
				logger.info("Unlocked " + name);
			}
		} else {
			logger.info("The task " + name + " is already executing");
		}
	}

	private boolean lock(String name) {
		Date now = new Date();
		Date value = registry.put(name, lockUntil);
		return value == null || value.before(now) || value.equals(now) ? false
				: true;
	}

	private void unlock(String name) {
		Date now = new Date();
		registry.put(name, now);
	}
}
