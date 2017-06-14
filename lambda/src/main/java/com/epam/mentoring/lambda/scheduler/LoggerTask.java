package com.epam.mentoring.lambda.scheduler;

import org.apache.log4j.Logger;

public class LoggerTask extends AbstractTask {
	private final Logger logger = Logger.getLogger(LoggerTask.class);

	@Override
	public void execute() {
		logger.info("Log something...");
	}

}
