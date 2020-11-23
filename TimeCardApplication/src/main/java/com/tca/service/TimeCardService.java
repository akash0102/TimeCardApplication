package com.tca.service;


import java.util.List;

import com.tca.entity.TimeCard;
import com.tca.exception.ResourceNotFoundException;

public interface TimeCardService {

	TimeCard saveTimeEntry(TimeCard man);

	boolean removeEntry(int timeCardId) throws ResourceNotFoundException;

	int updateEntries(int id,TimeCard tca) throws ResourceNotFoundException;

	List<TimeCard> displayEntries(int employeeId);

}
