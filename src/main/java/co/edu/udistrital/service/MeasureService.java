package co.edu.udistrital.service;

import java.util.List;

import co.edu.udistrital.domain.measure.Interval;
import co.edu.udistrital.exception.IntervalNotFoundException;

public interface MeasureService {

	//Pressure intervals
	
	Interval retrieveLastPressureSecondInterval() throws IntervalNotFoundException;

	Interval retrieveLastPressureMinuteInterval() throws IntervalNotFoundException;

	Interval retrieveLastPressureHourInterval() throws IntervalNotFoundException;

	List<Interval> retrieveLastPressureSecondIntervals(int secondsQuantity);

	List<Interval> retrieveLastPressureMinuteIntervals(int minutesQuantity);

	List<Interval> retrieveLastPressureHourIntervals(int hoursQuantity);

	//Temperature intervals
	
	Interval retrieveLastTemperatureSecondInterval() throws IntervalNotFoundException;

	Interval retrieveLastTemperatureMinuteInterval() throws IntervalNotFoundException;

	Interval retrieveLastTemperatureHourInterval() throws IntervalNotFoundException;

	List<Interval> retrieveLastTemperatureSecondIntervals(int secondsQuantity);

	List<Interval> retrieveLastTemperatureMinuteIntervals(int minutesQuantity);
	
	List<Interval> retrieveLastTemperatureHourIntervals(int hoursQuantity);
}
