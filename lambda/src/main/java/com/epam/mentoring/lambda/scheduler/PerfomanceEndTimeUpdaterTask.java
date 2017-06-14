package com.epam.mentoring.lambda.scheduler;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.lambda.manager.PerfomanceManager;

public class PerfomanceEndTimeUpdaterTask extends AbstractTask {

	@Autowired
	protected PerfomanceManager perfomanceManager;

	@Override
	public void execute() {
		Date endTime = (Date) context.get("endTime");
		perfomanceManager.updateEndTime(endTime);
	}
}
