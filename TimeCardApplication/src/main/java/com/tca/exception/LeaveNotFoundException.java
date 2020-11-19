package com.tca.exception;

public class LeaveNotFoundException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeaveNotFoundException(String message) {
		super(message);
	}

}
