package com.tca.service;

import java.util.List;

import com.tca.repository.ManagerRepository;

public class ManagerServiceImpl implements ManagerService {

	ManagerRepository mDao=new ManagerRepository();
	
	@Override
	public List<Object> viewRequests() {
		return mDao.getRequests();
	}

	@Override
	public boolean resolveRequest() {
		return mDao.resolveReq(new Object());
	}

}
