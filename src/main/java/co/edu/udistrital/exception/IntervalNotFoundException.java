package co.edu.udistrital.exception;

public class IntervalNotFoundException extends Exception {

	private static final long serialVersionUID = -7104571608235959838L;

	public IntervalNotFoundException() {
	}

	public IntervalNotFoundException(String message) {
		super(message);
	}

	public IntervalNotFoundException(Throwable cause) {
		super(cause);
	}

	public IntervalNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public IntervalNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
