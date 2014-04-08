package co.edu.udistrital.exception;

public class PLCCommunicationException extends Exception {

	private static final long serialVersionUID = -1693537030946019608L;

	public PLCCommunicationException() {
		super();
	}

	public PLCCommunicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PLCCommunicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PLCCommunicationException(String message) {
		super(message);
	}

	public PLCCommunicationException(Throwable cause) {
		super(cause);
	}
}
