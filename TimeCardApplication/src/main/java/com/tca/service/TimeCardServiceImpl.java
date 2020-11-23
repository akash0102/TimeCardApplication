package com.tca.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.entity.TimeCard;
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
	public boolean removeEntry(int timeCardId) {
		boolean check=false;
		TimeCard toDelete= daoCaller.findById(timeCardId).isPresent()?
				daoCaller.findById(timeCardId).get():null;
		if(toDelete!=null) {
			daoCaller.delete(toDelete);
		}
		if(daoCaller.findById(timeCardId).isEmpty()) {
			check=true;
		}
		return check;
	}
	
	@Override
	public int updateEntries(int id,TimeCard tca) {
		
		Optional<TimeCard> timecard=daoCaller.findById(id);
		TimeCard toEdit=(!timecard.isEmpty())?timecard.get():null;
		
		if(toEdit==null) {
			toEdit=new TimeCard();
			toEdit.setTimeCardId(tca.getTimeCardId());
		}
		toEdit.setEmployee(tca.getEmployee());
		toEdit.setDate(tca.getDate());
		toEdit.setTimeEntry(tca.getTimeEntry());
		toEdit.setTimeExit(tca.getTimeExit());
		daoCaller.save(toEdit);
		return toEdit.getTimeCardId();
	}

	@Override
	public List<TimeCard> displayEntries(int empId) {
		
		return daoCaller.findByEmpId(empId);
		
	}

}
