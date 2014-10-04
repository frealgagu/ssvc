package co.edu.udistrital.domain.measure;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class Interval {

	private DateTime dateTime;
	private BigDecimal count;
	private BigDecimal sum;

	public Interval(DateTime dateTime, BigDecimal value) {
		this.dateTime = dateTime;
		this.count = BigDecimal.ONE;
		this.sum = value;
	}

	public DateTime getDateTime() {
		return dateTime;
	}
	
	public BigDecimal getValue() {
		return sum.divide(count, 1, BigDecimal.ROUND_HALF_UP);
	}
	
	public void appendValue(BigDecimal value) {
		count = count.add(BigDecimal.ONE);
		sum = sum.add(value);
	}
}
