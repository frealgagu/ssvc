package co.edu.udistrital.domain.plc;

public enum StopBits {
	
	ONE(1),
	TWO(2);
	
	private int value;
	
	StopBits(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static StopBits getStopBits(int value) {
		for(StopBits stopBits : values()) {
			if(stopBits.getValue() == value) {
				return stopBits;
			}
		}
		throw new IllegalArgumentException("Value " + value + " not match with any StopBits");
	}
}