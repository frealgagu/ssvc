package co.edu.udistrital.exception;

public class PLCConnectionException extends Exception {

	private static final long serialVersionUID = 3791522700401333913L;

	public PLCConnectionException() {
		super();
	}

	public PLCConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PLCConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PLCConnectionException(String message) {
		super(message);
	}

	public PLCConnectionException(Throwable cause) {
		super(cause);
	}

}
