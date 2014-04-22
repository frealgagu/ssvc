package co.edu.udistrital.controller.measure;

import java.util.List;

import org.joda.time.DateTime;

import co.edu.udistrital.domain.measure.Interval;
import co.edu.udistrital.exception.IntervalNotFoundException;

public interface Measure {

	Interval retrieveLastInterval() throws IntervalNotFoundException;
	
	List<Interval> retrieveLastIntervals(int intervalQuantity);
	
	Interval retrieveInterval(DateTime dateTime) throws IntervalNotFoundException;
	
	void appendValue(int value);
	
	void appendValue(DateTime dateTime, int value);
}
