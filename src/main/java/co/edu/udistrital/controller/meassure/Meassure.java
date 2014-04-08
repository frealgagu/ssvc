package co.edu.udistrital.controller.meassure;

import java.util.List;

import org.joda.time.DateTime;

import co.edu.udistrital.domain.meassure.Interval;
import co.edu.udistrital.exception.IntervalNotFoundException;

public interface Meassure {

	Interval retrieveLastInterval() throws IntervalNotFoundException;
	
	List<Interval> retrieveLastIntervals(int intervalQuantity);
	
	Interval retrieveInterval(DateTime dateTime) throws IntervalNotFoundException;
	
	void appendValue(int value);
	
	void appendValue(DateTime dateTime, int value);
}
