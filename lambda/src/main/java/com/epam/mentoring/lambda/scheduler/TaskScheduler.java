package com.epam.mentoring.lambda.scheduler;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

public interface TaskScheduler {

	public ScheduledFuture<?> schedule(Task task, Date startTime);

	public ScheduledFuture<?> scheduleAtFixedRate(Task task, Date startTime,
			long period);
}
