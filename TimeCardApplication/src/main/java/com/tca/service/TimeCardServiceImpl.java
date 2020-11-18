package com.tca.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.dao.TimeCardDao;
import com.tca.entity.TimeCard;


@Service
public class TimeCardServiceImpl implements TimeCardService {
	
	@Autowired
	TimeCardDao daoCaller;
	
	@Override
	public TimeCard saveTimeEntry(TimeCard timeCard) {
		return daoCaller.save(timeCard);
	}

	@Override
	public void removeEntry(int timeCardId) {
		Optional<TimeCard> toDelete= daoCaller.findById(timeCardId);
		if(toDelete.isPresent()) {
			daoCaller.delete(toDelete.get());	
		}
	}
	
	@Override
	public int updateEntries(int id,TimeCard tca) {
		
		Optional<TimeCard> timecard=daoCaller.findById(id);
		TimeCard toEdit=(!timecard.isEmpty())?timecard.get():null;
		
		if(toEdit==null) {
			toEdit=new TimeCard();
			toEdit.setTimeCardID(tca.getTimeCardID());
		}
		toEdit.setEmployee(tca.getEmployee());
		toEdit.setDate(tca.getDate());
		toEdit.setTimeEntry(tca.getTimeEntry());
		toEdit.setTimeExit(tca.getTimeExit());
		daoCaller.save(toEdit);
		return toEdit.getTimeCardID();
	}

	@Override
	public List<TimeCard> displayEntries(int empId) {
		
		return daoCaller.findByEmpId(empId);
		
	}

}
