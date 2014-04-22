package co.edu.udistrital.controller.measure.impl;

import co.edu.udistrital.controller.measure.Measure;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.udistrital.controller.measure.MeasureIntervalType;

@Configuration
public class MeasureFactory {

	@Bean
	public Measure pressureSecondsMeasure() {
		return new MeasureImpl(MeasureIntervalType.SECOND, 20);
	}
	
	@Bean
	public Measure pressureMinutesMeasure() {
		return new MeasureImpl(MeasureIntervalType.MINUTE, 20);
	}
	
	@Bean
	public Measure pressureHoursMeasure() {
		return new MeasureImpl(MeasureIntervalType.HOUR, 20);
	}
	
	@Bean
	public Measure temperatureSecondsMeasure() {
		return new MeasureImpl(MeasureIntervalType.SECOND, 20);
	}
	
	@Bean
	public Measure temperatureMinutesMeasure() {
		return new MeasureImpl(MeasureIntervalType.MINUTE, 20);
	}
	
	@Bean
	public Measure temperatureHoursMeasure() {
		return new MeasureImpl(MeasureIntervalType.HOUR, 20);
	}	
}
