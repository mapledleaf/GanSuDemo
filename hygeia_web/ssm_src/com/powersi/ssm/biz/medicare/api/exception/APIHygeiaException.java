package com.powersi.ssm.biz.medicare.api.exception;

import com.powersi.comm.exception.ApusException;

/**
 * 
 * @author 刘勇
 *
 */
public class APIHygeiaException extends ApusException {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public APIHygeiaException() {
		super();
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public APIHygeiaException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public APIHygeiaException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public APIHygeiaException(Throwable cause) {
		super(cause);
	}

}
