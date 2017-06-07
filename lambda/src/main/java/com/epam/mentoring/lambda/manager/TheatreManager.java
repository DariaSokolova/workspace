package com.epam.mentoring.lambda.manager;

import java.util.Map;

import com.epam.mentoring.lambda.bean.Theatre;

public interface TheatreManager {
	public Theatre findByTitle(String title);
	public void updateTitle(Map<Long, String> map);
}
