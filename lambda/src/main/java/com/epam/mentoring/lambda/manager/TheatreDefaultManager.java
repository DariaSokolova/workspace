package com.epam.mentoring.lambda.manager;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.lambda.bean.Theatre;
import com.epam.mentoring.lambda.dao.TheatreDAO;

public class TheatreDefaultManager implements TheatreManager {

	@Autowired
	protected TheatreDAO dao;

	@Override
	public Theatre findByTitle(String title) {
		List<Theatre> list = dao.getAll();

		Predicate<Theatre> predicate = s -> s.getTitle().contains(title);
		Supplier<Theatre> supplier = Theatre::new;

		Theatre theatre = list.stream().filter(predicate).findFirst()
				.orElseGet(supplier);
		return theatre;
	}

	@Override
	public void updateTitle(Map<Long, String> map) {
		BiConsumer<Long, String> consumer = (k, v) -> {
			Theatre theatre = dao.get(k);
			
			theatre.setTitle(v);
			dao.save(theatre);
		};

		map.forEach(consumer);
	}
}
