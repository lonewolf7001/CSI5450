package edu.oakland.csi5450.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DaoFailedException extends Exception
{
	private static final long serialVersionUID = -207478826571834587L;
	
	public DaoFailedException() {
		super();
	}
	public DaoFailedException(String msg) {
		super(msg);
	}
	public DaoFailedException(Throwable cause) {
		super(cause);
	}
	public DaoFailedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
