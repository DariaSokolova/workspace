package com.epam.mentoring.lambda.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.lambda.bean.Perfomance;
import com.epam.mentoring.lambda.dao.PerfomanceDAO;
import com.epam.mentoring.lambda.main.TriConsumer;

public class PerfomanceDefaultManager implements PerfomanceManager {

	@Autowired
	protected PerfomanceDAO dao;

	@Override
	public String getBasicInfo() {
		List<Perfomance> list = dao.getAll();

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
			Perfomance perfomance = dao.get(i);
			perfomance.setStartTime(startDate);
			perfomance.setEndTime(endDate);
			dao.save(perfomance);
		};

		consumer.accept(id, startDate, endDate);
	}
}
