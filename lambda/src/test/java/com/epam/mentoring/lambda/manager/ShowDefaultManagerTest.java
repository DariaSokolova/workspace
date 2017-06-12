package com.epam.mentoring.lambda.manager;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.lambda.test.AbstractDBUnitTest;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class ShowDefaultManagerTest extends AbstractDBUnitTest {

	@Autowired
	protected ShowManager manager;

	@Test
	@DatabaseSetup({ "/data/show-data.xml", "/data/theatre-data.xml",
			"/data/perfomance-data.xml" })
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/data/perfomance-after-update-data.xml")
	public void testUpdatePerfomancesStartTimeOfShow() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2017, 2, 15);
		Date dateTime = calendar.getTime();
		long showID = 2;

		manager.updatePerfomancesStartTimeOfShow(dateTime, showID);
	}
}
