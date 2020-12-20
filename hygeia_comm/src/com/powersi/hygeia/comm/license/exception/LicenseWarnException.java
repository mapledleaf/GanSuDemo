package com.powersi.hygeia.comm.license.exception;

/**
 * 许可证警告异常
 * 使用本异常传递警告信息(即将过期)，这并不是错误，而是提示信息的传递方式
 * @author 李志钢
 *
 */
public class LicenseWarnException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LicenseWarnException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LicenseWarnException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public LicenseWarnException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public LicenseWarnException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LicenseWarnException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
