package com.tca.service;


import java.util.List;

import com.tca.entity.TimeCard;

public interface TimeCardService {

	TimeCard saveTimeEntry(TimeCard man);

	void removeEntry(int timeCardId);

	int updateEntries(int id,TimeCard tca);

	List<TimeCard> displayEntries(int employeeId);

}
