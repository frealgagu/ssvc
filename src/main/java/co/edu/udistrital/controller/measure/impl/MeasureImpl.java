package co.edu.udistrital.controller.measure.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.udistrital.controller.measure.Measure;
import org.joda.time.DateTime;

import co.edu.udistrital.controller.measure.MeasureIntervalType;
import co.edu.udistrital.domain.measure.Interval;
import co.edu.udistrital.exception.IntervalNotFoundException;

public class MeasureImpl implements Measure {
	
	private final MeasureIntervalType measureIntervalType;
	private final int recordsQuantity;
	private final List<Interval> intervals;
	
	public MeasureImpl(MeasureIntervalType measureIntervalType, int recordsQuantity) {
		this.measureIntervalType = measureIntervalType;
		this.recordsQuantity = recordsQuantity;
		this.intervals = new ArrayList<>(recordsQuantity);
	}

	public Interval retrieveLastInterval() throws IntervalNotFoundException {
		if(intervals.size() > 0) {
			return intervals.get(0);
		} else {
			throw new IntervalNotFoundException("Interval list is empty");			
		}
	}
	
	public List<Interval> retrieveLastIntervals(int intervalQuantity) {
		if(intervals.size() >= intervalQuantity) {
			return intervals.subList(0, intervalQuantity);
		} else {
			return intervals.subList(0, intervals.size());
		}
	}
	
	public Interval retrieveInterval(DateTime dateTime) throws IntervalNotFoundException {
		DateTime fixedDateTime = fixDateTime(dateTime);
		for(Interval interval : intervals) {
			if(interval.getDateTime().equals(fixedDateTime)) {
				return interval;
			}
		}
		throw new IntervalNotFoundException("Interval for date time " + fixedDateTime + " not found");
	}
	
	public void appendValue(int value) {
		appendValue(new DateTime(), value);
	}
	
	public void appendValue(DateTime dateTime, int value) {
		DateTime fixedDateTime = fixDateTime(dateTime);
		try {
			retrieveInterval(fixedDateTime).appendValue(value);
		} catch(IntervalNotFoundException ignore) {
			if(intervals.size() > recordsQuantity) {
				intervals.remove(intervals.size() - 1);
			}
			intervals.add(0, new Interval(fixedDateTime, value));
		}
	}
	
	private DateTime fixDateTime(DateTime dateTime) {
		DateTime fixedDateTime = dateTime;
		switch(measureIntervalType) {
			case YEAR:
				fixedDateTime = fixedDateTime.withMonthOfYear(1);
			case MONTH:
				fixedDateTime = fixedDateTime.withDayOfMonth(1);
			case DAY:
				fixedDateTime = fixedDateTime.withTimeAtStartOfDay();
				break;
			case HOUR:
				fixedDateTime = fixedDateTime.withMinuteOfHour(0);
			case MINUTE:
				fixedDateTime = fixedDateTime.withSecondOfMinute(0);
			case SECOND:
				fixedDateTime = fixedDateTime.withMillisOfSecond(0);
				break;
			default:
				throw new IllegalArgumentException("Unknown Measure Interval");
		}
		return fixedDateTime;
	}	
}
