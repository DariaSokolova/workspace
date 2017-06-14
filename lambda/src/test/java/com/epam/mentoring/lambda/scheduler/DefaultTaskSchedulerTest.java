package com.epam.mentoring.lambda.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.lambda.test.AbstractDBUnitTest;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class DefaultTaskSchedulerTest extends AbstractDBUnitTest {

	@Autowired
	private DefaultTaskScheduler scheduler;
	@Autowired
	private LoggerTask loggerTask;
	@Autowired
	private PerfomanceEndTimeUpdaterTask perfomanceEndTimeUpdaterTask;
	@Autowired
	private PerfomanceStartTimesUpdaterTask perfomanceStartTimesUpdaterTask;

	@Test
	public void testSchedule() throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 10);
		Date date = calendar.getTime();

		scheduler.schedule(loggerTask, date);
		scheduler.schedule(loggerTask, date);

		Thread.sleep(20000);
	}

	@Test
	@DatabaseSetup({ "/data/show-data.xml", "/data/theatre-data.xml",
			"/data/perfomance-data.xml" })
	public void testScheduleAtFixedRate() throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();

		Map<String, Object> context = new HashMap<>();
		context.put("startTime", date);
		context.put("endTime", date);
		perfomanceEndTimeUpdaterTask.setContext(context);
		perfomanceStartTimesUpdaterTask.setContext(context);

		scheduler.scheduleAtFixedRate(perfomanceEndTimeUpdaterTask, date, 1000);
		scheduler.scheduleAtFixedRate(perfomanceStartTimesUpdaterTask, date,
				1000);

		Thread.sleep(10000);
	}
}
