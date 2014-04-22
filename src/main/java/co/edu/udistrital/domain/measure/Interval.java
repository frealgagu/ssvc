package co.edu.udistrital.domain.measure;

import org.joda.time.DateTime;

public class Interval {

	private DateTime dateTime;
	private int count;
	private int sum;

	public Interval(DateTime dateTime, int value) {
		this.dateTime = dateTime;
		this.count = 1;
		this.sum = value;
	}

	public DateTime getDateTime() {
		return dateTime;
	}
	
	public int getValue() {
		return sum / count; 
	}
	
	public void appendValue(int value) {
		count++;
		sum += value;
	}
}
