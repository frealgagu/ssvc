package co.edu.udistrital.domain.plc;

public enum DataBits {
	
	SEVEN(7),
	EIGHT(8);
	
	private int value;
	
	DataBits(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static DataBits getDataBits(int value) {
		for(DataBits dataBits : values()) {
			if(dataBits.getValue() == value) {
				return dataBits;
			}
		}
		throw new IllegalArgumentException("Value " + value + " not match with any DataBits");
	}
}