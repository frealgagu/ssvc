package co.edu.udistrital.controller.temperature;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import co.edu.udistrital.controller.MeassureController;
import co.edu.udistrital.controller.meassure.Meassure;
import co.edu.udistrital.exception.PLCCommunicationException;
import co.edu.udistrital.service.ConfigurationService;
import co.edu.udistrital.service.PLCService;

import com.spinn3r.log5j.Logger;

@Controller("temperatureController")
public class TemperatureControllerImpl implements MeassureController {
	
	protected static final Logger logger = Logger.getLogger();
	
	protected static final int UNIT_ID = 1;
	
	@Autowired
	protected ConfigurationService configurationService;
	@Autowired
	protected PLCService plcService;
	@Autowired
	@Qualifier("temperatureSecondsMeassure")
	protected Meassure secondsMeassure;
	@Autowired
	@Qualifier("temperatureMinutesMeassure")
	protected Meassure minutesMeassure;
	@Autowired
	@Qualifier("temperatureHoursMeassure")
	protected Meassure hoursMeassure;
	
	protected boolean monitoring;
	protected boolean communicationError;
	
	@Scheduled(fixedRate = 500)
	public void process() {
		if(monitoring) {
			int registerNumber = configurationService.getTemperatureRegisterNumber();
			try {
				int register = plcService.readRegister(registerNumber, UNIT_ID);
				secondsMeassure.appendValue(register);
				minutesMeassure.appendValue(register);
				hoursMeassure.appendValue(register);
				logCommunicationStablished();
			} catch (PLCCommunicationException ex) {
				logCommunicationMissed();
			}
		}
	}
	
	public void startMonitoring() {
		monitoring = true;
	}
	
	public void stopMonitoring() {
		monitoring = false;
	}
	
	protected void logCommunicationStablished() {
		if(communicationError) {
			communicationError = false;
			logger.error("Communication with PLC stablished at " + new DateTime());
		}
	}
	
	protected void logCommunicationMissed() {
		if(!communicationError) {
			communicationError = true;
			logger.error("Communication with PLC missed at " + new DateTime());
		}
	}
}
