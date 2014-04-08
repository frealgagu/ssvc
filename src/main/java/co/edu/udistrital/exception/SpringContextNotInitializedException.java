package co.edu.udistrital.exception;

public class SpringContextNotInitializedException extends RuntimeException {

    private static final long serialVersionUID = 860375149486586685L;

	public SpringContextNotInitializedException() {
	}

	public SpringContextNotInitializedException(String message) {
		super(message);
	}

	public SpringContextNotInitializedException(Throwable cause) {
		super(cause);
	}

	public SpringContextNotInitializedException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpringContextNotInitializedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
