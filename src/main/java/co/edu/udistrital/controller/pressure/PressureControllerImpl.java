package co.edu.udistrital.controller.pressure;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.spinn3r.log5j.Logger;

import co.edu.udistrital.controller.MeassureController;
import co.edu.udistrital.controller.meassure.Meassure;
import co.edu.udistrital.exception.PLCCommunicationException;
import co.edu.udistrital.service.ConfigurationService;
import co.edu.udistrital.service.PLCService;

@Controller("pressionController")
public class PressureControllerImpl implements MeassureController {

	protected static final Logger logger = Logger.getLogger();
	
	protected static final int UNIT_ID = 1;
	
	@Autowired
	protected ConfigurationService configurationService;
	@Autowired
	protected PLCService plcService;
	@Autowired
	@Qualifier("pressureSecondsMeassure")
	protected Meassure secondsMeassure;
	@Autowired
	@Qualifier("pressureMinutesMeassure")
	protected Meassure minutesMeassure;
	@Autowired
	@Qualifier("pressureHoursMeassure")
	protected Meassure hoursMeassure;
	
	protected boolean monitoring;
	protected boolean communicationError;
	
	@Scheduled(fixedRate = 500)
	public void process() {
		if(monitoring) {
			int registerNumber = configurationService.getPressureRegisterNumber();
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
