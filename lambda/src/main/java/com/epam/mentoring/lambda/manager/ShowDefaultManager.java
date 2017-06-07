package com.epam.mentoring.lambda.manager;

import java.util.Date;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.lambda.bean.Perfomance;
import com.epam.mentoring.lambda.bean.Show;
import com.epam.mentoring.lambda.dao.PerfomanceDAO;
import com.epam.mentoring.lambda.dao.ShowDAO;

public class ShowDefaultManager implements ShowManager {

	@Autowired
	protected ShowDAO dao;
	@Autowired
	protected PerfomanceDAO perfomanceDAO;

	@Override
	public void updatePerfomancesStartTimeOfShow(Date dateTime, long showID) {
		Show show = dao.get(showID);

		Consumer<Perfomance> consumer1 = p -> p.setStartTime(dateTime);
		Consumer<Perfomance> consumer2 = p -> perfomanceDAO.save(p);

		show.getPerfomances().stream().peek(consumer1).forEach(consumer2);
	}
}
