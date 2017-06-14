package com.epam.mentoring.lambda.manager;

import java.util.Date;

public interface PerfomanceManager {

	public String getBasicInfo();

	public void updateDates(long id, Date startDate, Date endDate);

	public void updateStartTime(Date startTime);

	public void updateEndTime(Date endTime);
}
