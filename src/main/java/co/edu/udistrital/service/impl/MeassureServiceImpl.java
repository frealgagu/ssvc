package co.edu.udistrital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.edu.udistrital.controller.meassure.Meassure;
import co.edu.udistrital.domain.meassure.Interval;
import co.edu.udistrital.exception.IntervalNotFoundException;
import co.edu.udistrital.service.MeassureService;

@Service
public class MeassureServiceImpl implements MeassureService {

	@Autowired
	@Qualifier("pressureSecondsMeassure")
	protected Meassure pressureSecondsMeassure;
	@Autowired
	@Qualifier("pressureMinutesMeassure")
	protected Meassure pressureMinutesMeassure;
	@Autowired
	@Qualifier("pressureHoursMeassure")
	protected Meassure pressureHoursMeassure;
	
	@Autowired
	@Qualifier("temperatureSecondsMeassure")
	protected Meassure temperatureSecondsMeassure;
	@Autowired
	@Qualifier("temperatureMinutesMeassure")
	protected Meassure temperatureMinutesMeassure;
	@Autowired
	@Qualifier("temperatureHoursMeassure")
	protected Meassure temperatureHoursMeassure;

	//Pressure Intervals
	
	@Override
	public Interval retrieveLastPressureSecondInterval() throws IntervalNotFoundException {
		return pressureSecondsMeassure.retrieveLastInterval();
	}

	@Override
	public Interval retrieveLastPressureMinuteInterval() throws IntervalNotFoundException {
		return pressureMinutesMeassure.retrieveLastInterval();
	}

	@Override
	public Interval retrieveLastPressureHourInterval() throws IntervalNotFoundException {
		return pressureHoursMeassure.retrieveLastInterval();
	}

	@Override
	public List<Interval> retrieveLastPressureSecondIntervals(int secondsQuantity) {
		return pressureSecondsMeassure.retrieveLastIntervals(secondsQuantity);
	}

	@Override
	public List<Interval> retrieveLastPressureMinuteIntervals(int minutesQuantity) {
		return pressureMinutesMeassure.retrieveLastIntervals(minutesQuantity);
	}

	@Override
	public List<Interval> retrieveLastPressureHourIntervals(int hoursQuantity) {
		return pressureHoursMeassure.retrieveLastIntervals(hoursQuantity);
	}

	//Temperature Intervals
	
	@Override
	public Interval retrieveLastTemperatureSecondInterval() throws IntervalNotFoundException {
		return temperatureSecondsMeassure.retrieveLastInterval();
	}

	@Override
	public Interval retrieveLastTemperatureMinuteInterval() throws IntervalNotFoundException {
		return temperatureMinutesMeassure.retrieveLastInterval();
	}

	@Override
	public Interval retrieveLastTemperatureHourInterval() throws IntervalNotFoundException {
		return temperatureHoursMeassure.retrieveLastInterval();
	}

	@Override
	public List<Interval> retrieveLastTemperatureSecondIntervals(int secondsQuantity) {
		return temperatureSecondsMeassure.retrieveLastIntervals(secondsQuantity);
	}

	@Override
	public List<Interval> retrieveLastTemperatureMinuteIntervals(int minutesQuantity) {
		return temperatureMinutesMeassure.retrieveLastIntervals(minutesQuantity);
	}

	@Override
	public List<Interval> retrieveLastTemperatureHourIntervals(int hoursQuantity) {
		return temperatureHoursMeassure.retrieveLastIntervals(hoursQuantity);
	}
}
