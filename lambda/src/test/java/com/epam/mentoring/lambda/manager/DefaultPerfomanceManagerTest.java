package com.epam.mentoring.lambda.manager;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.lambda.test.AbstractDBUnitTest;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class DefaultPerfomanceManagerTest extends AbstractDBUnitTest {

	@Autowired
	protected PerfomanceManager perfomanceManager;

	@Test
	@DatabaseSetup({ "/data/show-data.xml", "/data/theatre-data.xml",
			"/data/perfomance-data.xml" })
	public void testGetBasicInfo_whenPerfomancesExist() {
		String expected = "The Book Of Mormon, Prince of Wales\nDon Juan in Soho, Wyndhams\nWicked, Apollo Victoria";

		String info = perfomanceManager.getBasicInfo();

		assertEquals(expected, info);
	}

	@Test
	public void testGetBasicInfo_whenPerfomancesNotExist() {
		String expected = "Empty";

		String info = perfomanceManager.getBasicInfo();

		assertEquals(expected, info);
	}

	@Test
	@DatabaseSetup({ "/data/show-data.xml", "/data/theatre-data.xml",
			"/data/perfomance-data.xml" })
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/data/perfomance-after-date-update-data.xml")
	public void testUpdateDates() {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2017, 1, 15);

		Date startDate = calendar.getTime();

		calendar.clear();
		calendar.set(2017, 1, 16);
		Date endDate = calendar.getTime();
		perfomanceManager.updateDates(1, startDate, endDate);
	}
}
