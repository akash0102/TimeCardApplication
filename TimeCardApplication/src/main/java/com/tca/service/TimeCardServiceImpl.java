package com.tca.service;


import java.util.List;

import com.tca.dao.TimeCardDao;
import com.tca.entity.TimeCard;

public class TimeCardServiceImpl implements TimeCardService {

	TimeCardDao daoCaller=new TimeCardDao();
	
	@Override
	public int saveTimeEntry(TimeCard timeCard) {
		return daoCaller.addTimeCardEntry(timeCard);
	}

	@Override
	public int removeEntry(int timeCardId) {
		return daoCaller.removeTimeCard(timeCardId);
	}
	
	@Override
	public int updateEntries(TimeCard tca) {
		return daoCaller.addTimeCardEntry(tca);
		
	}

	@Override
	public List<TimeCard> displayEntries(TimeCard employee) {
		return daoCaller.displayAllEntry(employee);
		
	}

}
