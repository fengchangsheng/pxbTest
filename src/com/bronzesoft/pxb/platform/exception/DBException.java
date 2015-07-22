package com.bronzesoft.pxb.platform.exception;

import org.springframework.dao.DataAccessException;

@SuppressWarnings("serial")
public class DBException extends DataAccessException {
	
	public DBException(String arg0) {
		super(arg0);
	}

	public DBException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
