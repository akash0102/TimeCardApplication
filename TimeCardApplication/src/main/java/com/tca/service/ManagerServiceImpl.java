package com.tca.service;

import java.util.List;

import com.tca.dao.ManagerDao;

public class ManagerServiceImpl implements ManagerService {

	ManagerDao mDao=new ManagerDao();
	
	@Override
	public List<Object> viewRequests() {
		return mDao.getRequests();
	}

	@Override
	public boolean resolveRequest() {
		return mDao.resolveReq(new Object());
	}

}
