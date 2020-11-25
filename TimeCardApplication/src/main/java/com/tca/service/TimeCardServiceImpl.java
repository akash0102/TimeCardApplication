package com.tca.service;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.entity.TimeCard;
import com.tca.exception.ResourceNotFoundException;
import com.tca.repository.TimeCardRepository;


@Service
public class TimeCardServiceImpl implements TimeCardService {
	
	@Autowired
	TimeCardRepository daoCaller;
	
	Logger log=Logger.getLogger(getClass());
	
	@Override
	public TimeCard saveTimeEntry(TimeCard timeCard) {
		log.info("added timecard entry");
		return daoCaller.save(timeCard);  
	}

	@Override
	public boolean removeEntry(int timeCardId) throws ResourceNotFoundException {
		boolean check=false;
		TimeCard toDelete= daoCaller.findById(timeCardId).orElseThrow(() -> new ResourceNotFoundException("TimeCard not found for this id :: " + timeCardId));
		check=(toDelete!=null);
		daoCaller.deleteId(timeCardId);
		log.info("deleted entry");
		return check;
	}
	
	@Override
	public int updateEntries(int id,TimeCard tcard ) throws ResourceNotFoundException {
		
		TimeCard timecard=daoCaller.findById(id).orElseThrow(() -> new ResourceNotFoundException(" TimeCard not found for this id :: " + id));
		timecard.setDate(tcard.getDate());
		timecard.setTimeEntry(tcard.getTimeEntry());
		timecard.setTimeExit(tcard.getTimeExit());
		daoCaller.save(timecard);
		log.info("update entry");
		return timecard.getTimeCardId();
	}

	@Override
	public List<TimeCard> displayEntries(int empId) {
		log.info("entries by employee id");
		return daoCaller.findByEmpId(empId);
		
	}

	@Override
	public List<TimeCard> displayAll() {
		log.info("all entries");
		return daoCaller.findAll();
	}
}
