package com.tca.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.tca.entity.TimeCard;
import com.tca.exception.ResourceNotFoundException;

public interface TimeCardService {

	TimeCard saveTimeEntry(TimeCard man);

	boolean removeEntry(int timeCardId) throws ResourceNotFoundException;

	int updateEntries(int id,LocalDate date,LocalTime inTime, LocalTime outTime) throws ResourceNotFoundException;

	List<TimeCard> displayEntries(int employeeId);
	
}
