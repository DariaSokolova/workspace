package com.epam.mentoring.lambda.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.lambda.bean.Theatre;
import com.epam.mentoring.lambda.test.AbstractDBUnitTest;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class TheatreDefaultManagerTest extends AbstractDBUnitTest {

	@Autowired
	protected TheatreManager theatreManager;

	@Test
	@DatabaseSetup("/data/theatre-data.xml")
	public void testFindByTitle_whenTheatreExists() {
		String title = "Prince of Wales";

		Theatre theatre = theatreManager.findByTitle(title);

		assertEquals(title, theatre.getTitle());
	}

	@Test
	public void testFindByTitle_whenTheatreNotExists() {
		String title = "Prince of Wales";

		Theatre theatre = theatreManager.findByTitle(title);

		assertNull(theatre.getTitle());
	}

	@Test
	@DatabaseSetup("/data/theatre-data.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/data/theatre-after-update-data.xml")
	public void testUpdateTitle() {
		Map<Long, String> map = new HashMap<>();
		map.put(1l, "Apollo Victoria Theatre");
		map.put(2l, "Prince of Wales Theatre");
		map.put(3l, "Wyndhams Theatre");
		theatreManager.updateTitle(map);
	}
}
