package com.epam.mentoring.lambda.manager;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.lambda.bean.Perfomance;
import com.epam.mentoring.lambda.dao.PerfomanceDAO;
import com.epam.mentoring.lambda.dao.TheatreDAO;
import com.epam.mentoring.lambda.main.TriConsumer;

public class PerfomanceDefaultManager implements PerfomanceManager {

	private final static Lock lock = new ReentrantLock();

	private final Logger logger = Logger
			.getLogger(PerfomanceDefaultManager.class);

	@Autowired
	protected PerfomanceDAO perfomanceDAO;
	@Autowired
	protected TheatreDAO theatreDAO;

	@Override
	public String getBasicInfo() {
		List<Perfomance> list = perfomanceDAO.getAll();

		String info = list
				.stream()
				.sorted((a, b) -> a.getStartTime().compareTo(b.getStartTime()))
				.map(x -> x.getShow().getTitle() + ", "
						+ x.getTheatre().getTitle())
				.reduce((s1, s2) -> s1 + "\n" + s2).orElse("Empty");

		return info;
	}

	@Override
	public void updateDates(long id, Date startDate, Date endDate) {
		TriConsumer<Long, Date, Date> consumer = (i, d1, d2) -> {
			Perfomance perfomance = perfomanceDAO.get(i);
			perfomance.setStartTime(startDate);
			perfomance.setEndTime(endDate);
			perfomanceDAO.save(perfomance);
		};

		consumer.accept(id, startDate, endDate);
	}

	@Override
	public void updateStartTime(Date startTime) {
		try {
			lock.lock();
			List<Perfomance> list = perfomanceDAO.getAll();
			list.stream()
					.peek(p -> p.setStartTime(startTime))
					.peek(p -> logger.info("Updated start time for "
							+ p.getID())).forEach(p -> perfomanceDAO.save(p));
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void updateEndTime(Date endTime) {
		try {
			lock.lock();
			List<Perfomance> list = perfomanceDAO.getAll();
			list.stream()
					.filter(p -> p.getStartTime().before(endTime))
					.peek(p -> p.setEndTime(endTime))
					.peek(p -> logger.info("Updated end time for " + p.getID()))
					.forEach(p -> perfomanceDAO.save(p));
		} finally {
			lock.unlock();
		}
	}
}
