package co.edu.udistrital.controller.meassure.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.udistrital.controller.meassure.Meassure;
import co.edu.udistrital.controller.meassure.MeassureIntervalType;

@Configuration
public class MeassureFactory {

	@Bean
	public Meassure pressureSecondsMeassure() {
		return new MeassureImpl(MeassureIntervalType.SECOND, 20);
	}
	
	@Bean
	public Meassure pressureMinutesMeassure() {
		return new MeassureImpl(MeassureIntervalType.MINUTE, 20);
	}
	
	@Bean
	public Meassure pressureHoursMeassure() {
		return new MeassureImpl(MeassureIntervalType.HOUR, 20);
	}
	
	@Bean
	public Meassure temperatureSecondsMeassure() {
		return new MeassureImpl(MeassureIntervalType.SECOND, 20);
	}
	
	@Bean
	public Meassure temperatureMinutesMeassure() {
		return new MeassureImpl(MeassureIntervalType.MINUTE, 20);
	}
	
	@Bean
	public Meassure temperatureHoursMeassure() {
		return new MeassureImpl(MeassureIntervalType.HOUR, 20);
	}	
}
