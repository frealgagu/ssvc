package co.edu.udistrital.exception;

public class PropertyNotFoundException extends Exception {

	private static final long serialVersionUID = 2525287212644833802L;
	
	private final String property;

	public PropertyNotFoundException(String property) {
		super();
		this.property = property;
	}

	public PropertyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String property) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.property = property;
	}

	public PropertyNotFoundException(String message, Throwable cause, String property) {
		super(message, cause);
		this.property = property;
	}

	public PropertyNotFoundException(String message, String property) {
		super(message);
		this.property = property;
	}

	public PropertyNotFoundException(Throwable cause, String property) {
		super(cause);
		this.property = property;
	}
	
	public String getProperty() {
		return property;
	}
}
