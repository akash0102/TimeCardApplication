package com.tca.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.tca.entity.TimeCard;
import com.tca.util.JPaUtil;

public class TimeCardDao {
	
	int ret=0;
	Logger log=Logger.getLogger(getClass());

	public int addTimeCardEntry(TimeCard timeCard) {
		JPaUtil.createEM(); 
		try {
			JPaUtil.entMan.getTransaction().begin();  //begin 
			JPaUtil.entMan.merge(timeCard);      //managed stated  ... pushed database ,insert 
			JPaUtil.entMan.getTransaction().commit();//end
			ret=timeCard.getTimeCardID();
		} catch (Exception e) {
			JPaUtil.entMan.getTransaction().rollback(); 
			log.debug(e);
			ret=2;
		}
		JPaUtil.entMan.close();
		return ret;
		
	}

	public int removeTimeCard(int timeCardId) {
		JPaUtil.createEM();
		
		TimeCard timeCard = JPaUtil.entMan.find(TimeCard.class, timeCardId);
		
		try {
			JPaUtil.entMan.getTransaction().begin();
			JPaUtil.entMan.remove(timeCard);   //delete 
			JPaUtil.entMan.getTransaction().commit();
			log.info(JPaUtil.entMan);
			ret=1;
		} 
		catch (Exception e) {
			JPaUtil.entMan.getTransaction().rollback();
			log.fatal(e);
		}
		
		JPaUtil.closeEM();
		return ret;
	}

	public List<TimeCard> displayAllEntry(TimeCard employee) {
		JPaUtil.createEM();
		List<TimeCard> entries = JPaUtil.entMan
				 .createNamedQuery("TimeCard.getAllById", TimeCard.class)
				 .setParameter("emp", employee.getEmployee())
				 .getResultList();
		JPaUtil.closeEM();
		log.info(JPaUtil.entMan);
		return entries;
	}


}
