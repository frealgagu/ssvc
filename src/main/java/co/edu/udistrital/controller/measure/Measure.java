package co.edu.udistrital.controller.measure;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;

import co.edu.udistrital.domain.measure.Interval;
import co.edu.udistrital.exception.IntervalNotFoundException;

public interface Measure {

	Interval retrieveLastInterval() throws IntervalNotFoundException;
	
	List<Interval> retrieveLastIntervals(int intervalQuantity);
	
	Interval retrieveInterval(DateTime dateTime) throws IntervalNotFoundException;
	
	void appendValue(BigDecimal value);
	
	void appendValue(DateTime dateTime, BigDecimal value);
}
