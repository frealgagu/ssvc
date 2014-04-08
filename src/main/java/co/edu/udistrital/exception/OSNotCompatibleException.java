package co.edu.udistrital.exception;

public class OSNotCompatibleException extends Exception {

	private static final long serialVersionUID = 4020857064543259479L;
	
	private final String osName;
	
	public OSNotCompatibleException(String osName) {
		super();
		this.osName = osName;
	}

	public OSNotCompatibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String osName) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.osName = osName;
	}

	public OSNotCompatibleException(String message, Throwable cause, String osName) {
		super(message, cause);
		this.osName = osName;
	}

	public OSNotCompatibleException(String message, String osName) {
		super(message);
		this.osName = osName;
	}

	public OSNotCompatibleException(Throwable cause, String osName) {
		super(cause);
		this.osName = osName;
	}
	
	public String getOSName() {
		return osName;
	}
}
