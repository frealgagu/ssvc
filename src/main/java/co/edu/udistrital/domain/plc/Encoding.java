package co.edu.udistrital.domain.plc;

public enum Encoding {

	ASCII("ASCII"),
	RTU("RTU"),
	BIN("BIN");
	
	private String value;
	
	Encoding(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static Encoding getEncoding(String value) {
		for(Encoding encoding : values()) {
			if(encoding.getValue().equalsIgnoreCase(value)) {
				return encoding;
			}
		}
		throw new IllegalArgumentException("Value " + value + " not match with any Encoding");
	}	
}