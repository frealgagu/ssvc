package co.edu.udistrital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.edu.udistrital.controller.measure.Measure;
import co.edu.udistrital.domain.measure.Interval;
import co.edu.udistrital.exception.IntervalNotFoundException;
import co.edu.udistrital.service.MeasureService;

@Service
public class MeasureServiceImpl implements MeasureService {

	@Autowired
	@Qualifier("pressureSecondsMeasure")
	protected Measure pressureSecondsMeasure;
	@Autowired
	@Qualifier("pressureMinutesMeasure")
	protected Measure pressureMinutesMeasure;
	@Autowired
	@Qualifier("pressureHoursMeasure")
	protected Measure pressureHoursMeasure;
	
	@Autowired
	@Qualifier("temperatureSecondsMeasure")
	protected Measure temperatureSecondsMeasure;
	@Autowired
	@Qualifier("temperatureMinutesMeasure")
	protected Measure temperatureMinutesMeasure;
	@Autowired
	@Qualifier("temperatureHoursMeasure")
	protected Measure temperatureHoursMeasure;

	//Pressure Intervals
	
	@Override
	public Interval retrieveLastPressureSecondInterval() throws IntervalNotFoundException {
		return pressureSecondsMeasure.retrieveLastInterval();
	}

	@Override
	public Interval retrieveLastPressureMinuteInterval() throws IntervalNotFoundException {
		return pressureMinutesMeasure.retrieveLastInterval();
	}

	@Override
	public Interval retrieveLastPressureHourInterval() throws IntervalNotFoundException {
		return pressureHoursMeasure.retrieveLastInterval();
	}

	@Override
	public List<Interval> retrieveLastPressureSecondIntervals(int secondsQuantity) {
		return pressureSecondsMeasure.retrieveLastIntervals(secondsQuantity);
	}

	@Override
	public List<Interval> retrieveLastPressureMinuteIntervals(int minutesQuantity) {
		return pressureMinutesMeasure.retrieveLastIntervals(minutesQuantity);
	}

	@Override
	public List<Interval> retrieveLastPressureHourIntervals(int hoursQuantity) {
		return pressureHoursMeasure.retrieveLastIntervals(hoursQuantity);
	}

	//Temperature Intervals
	
	@Override
	public Interval retrieveLastTemperatureSecondInterval() throws IntervalNotFoundException {
		return temperatureSecondsMeasure.retrieveLastInterval();
	}

	@Override
	public Interval retrieveLastTemperatureMinuteInterval() throws IntervalNotFoundException {
		return temperatureMinutesMeasure.retrieveLastInterval();
	}

	@Override
	public Interval retrieveLastTemperatureHourInterval() throws IntervalNotFoundException {
		return temperatureHoursMeasure.retrieveLastInterval();
	}

	@Override
	public List<Interval> retrieveLastTemperatureSecondIntervals(int secondsQuantity) {
		return temperatureSecondsMeasure.retrieveLastIntervals(secondsQuantity);
	}

	@Override
	public List<Interval> retrieveLastTemperatureMinuteIntervals(int minutesQuantity) {
		return temperatureMinutesMeasure.retrieveLastIntervals(minutesQuantity);
	}

	@Override
	public List<Interval> retrieveLastTemperatureHourIntervals(int hoursQuantity) {
		return temperatureHoursMeasure.retrieveLastIntervals(hoursQuantity);
	}
}
