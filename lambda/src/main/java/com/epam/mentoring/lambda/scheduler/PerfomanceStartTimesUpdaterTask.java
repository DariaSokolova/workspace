package com.epam.mentoring.lambda.scheduler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.lambda.manager.PerfomanceManager;

public class PerfomanceStartTimesUpdaterTask extends AbstractTask {

	@Autowired
	protected PerfomanceManager perfomanceManager;

	@Override
	public void execute() {
		Date startTime = (Date) context.get("startTime");
		perfomanceManager.updateStartTime(startTime);
	}

}
