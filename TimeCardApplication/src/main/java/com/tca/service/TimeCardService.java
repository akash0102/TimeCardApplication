package com.tca.service;


import java.util.List;

import com.tca.entity.TimeCard;

public interface TimeCardService {

	int saveTimeEntry(TimeCard man);

	int removeEntry(int timeCardId);

	int updateEntries(TimeCard tca);

	List<TimeCard> displayEntries(TimeCard employee);

}
