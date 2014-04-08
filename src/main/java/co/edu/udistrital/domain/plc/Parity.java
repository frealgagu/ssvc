package co.edu.udistrital.domain.plc;

public enum Parity {

	NONE("None"),
	EVEN("Even"),
	ODD("Odd");
	
	private String value;
	
	Parity(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static Parity getParity(String value) {
		for(Parity parity : values()) {
			if(parity.getValue().equalsIgnoreCase(value)) {
				return parity;
			}
		}
		throw new IllegalArgumentException("Value " + value + " not match with any Parity");
	}	
}