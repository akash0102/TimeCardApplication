package com.tca.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.entity.TimeCard;
import com.tca.exception.ResourceNotFoundException;
import com.tca.repository.TimeCardRepository;


@Service
public class TimeCardServiceImpl implements TimeCardService {
	
	@Autowired
	TimeCardRepository daoCaller;
	
	@Override
	public TimeCard saveTimeEntry(TimeCard timeCard) {
		return daoCaller.save(timeCard); 
	}

	@Override
	public boolean removeEntry(int timeCardId) throws ResourceNotFoundException {
		boolean check=false;
		TimeCard toDelete= daoCaller.findById(timeCardId).orElseThrow(() -> new ResourceNotFoundException("TimeCard not found for this id :: " + timeCardId));
		daoCaller.delete(toDelete);
		if(daoCaller.findById(timeCardId).isEmpty()) {
			check=true;
		}
		return check;
	}
	
	@Override
	public int updateEntries(int id,LocalDate date,LocalTime intime, LocalTime outtime ) throws ResourceNotFoundException {
		
		TimeCard timecard=daoCaller.findById(id).orElseThrow(() -> new ResourceNotFoundException(" TimeCard not found for this id :: " + id));
		timecard.setDate(date);
		timecard.setTimeEntry(intime);
		timecard.setTimeExit(outtime);
		daoCaller.save(timecard);
		return timecard.getTimeCardId();
	}

	@Override
	public List<TimeCard> displayEntries(int empId) {
		
		return daoCaller.findByEmpId(empId);
		
	}
}
