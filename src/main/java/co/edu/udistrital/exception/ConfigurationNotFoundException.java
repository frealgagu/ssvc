package co.edu.udistrital.exception;

public class ConfigurationNotFoundException extends Exception {

	private static final long serialVersionUID = 1223965271938692323L;

	public ConfigurationNotFoundException() {
		super();
	}

	public ConfigurationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConfigurationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigurationNotFoundException(String message) {
		super(message);
	}

	public ConfigurationNotFoundException(Throwable cause) {
		super(cause);
	}
}
