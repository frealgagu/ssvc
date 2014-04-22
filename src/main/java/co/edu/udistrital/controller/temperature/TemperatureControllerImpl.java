package co.edu.udistrital.controller.temperature;

import co.edu.udistrital.controller.MeasureController;
import co.edu.udistrital.controller.measure.Measure;
import co.edu.udistrital.notification.NotificationSender;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import co.edu.udistrital.exception.PLCCommunicationException;
import co.edu.udistrital.service.ConfigurationService;
import co.edu.udistrital.service.PLCService;

import com.spinn3r.log5j.Logger;

@Controller("temperatureController")
public class TemperatureControllerImpl implements MeasureController {
	
	protected static final Logger logger = Logger.getLogger();
	
	protected static final int UNIT_ID = 1;
	
	@Autowired
	protected ConfigurationService configurationService;
	@Autowired
	protected PLCService plcService;
	@Autowired
	@Qualifier("temperatureSecondsMeasure")
	protected Measure secondsMeasure;
	@Autowired
	@Qualifier("temperatureMinutesMeasure")
	protected Measure minutesMeasure;
	@Autowired
	@Qualifier("temperatureHoursMeasure")
	protected Measure hoursMeasure;

    @Autowired
    @Qualifier("emailNotificationSender")
    protected NotificationSender emailNotificationSender;

	protected boolean monitoring;
	protected boolean communicationError;

    protected DateTime startAdviceThresholdExceeded = null;
    protected DateTime lastAdviceSend = null;

    protected DateTime startAlarmThresholdExceeded = null;
    protected DateTime lastAlarmSend = null;

	@Scheduled(fixedRate = 500)
	public void process() {
		if(monitoring) {
			int registerNumber = configurationService.getTemperatureRegisterNumber();
			try {
				int register = plcService.readRegister(registerNumber, UNIT_ID);
				secondsMeasure.appendValue(register);
				minutesMeasure.appendValue(register);
				hoursMeasure.appendValue(register);
                writeNotification(register);
				logCommunicationStablished();
			} catch (PLCCommunicationException ex) {
				logCommunicationMissed(ex);
			}
		}
	}

	public void startMonitoring() {
		monitoring = true;
	}
	
	public void stopMonitoring() {
		monitoring = false;
	}

    protected void writeNotification(int register) {
        int adviceThreshold = configurationService.getTemperatureAdviceThreshold();
        if (register >= adviceThreshold) {
            if (startAdviceThresholdExceeded == null) {
                startAdviceThresholdExceeded = DateTime.now();
            } else {
                int secondsBeforeSend = configurationService.getAdviceTimeBeforeSending();
                if (startAdviceThresholdExceeded.plusSeconds(secondsBeforeSend).isBeforeNow()) {
                    if (lastAdviceSend == null) {
                        String emailOnAdvice = configurationService.getEmailOnAdvice();
                        emailNotificationSender.sendNotification(emailOnAdvice);
                        lastAdviceSend = DateTime.now();
                    } else {
                        int secondsBeforeReplay = configurationService.getAdviceTimeBeforeReply();
                        if(lastAdviceSend.plusSeconds(secondsBeforeReplay).isBeforeNow()) {
                            String emailOnAdvice = configurationService.getEmailOnAdvice();
                            emailNotificationSender.sendNotification(emailOnAdvice);
                        }
                    }
                }
            }
        } else {
            startAdviceThresholdExceeded = null;
            lastAdviceSend = null;
        }
        int alarmThreshold = configurationService.getTemperatureAlarmThreshold();
        if (register >= alarmThreshold) {
            if (startAlarmThresholdExceeded == null) {
                startAlarmThresholdExceeded = DateTime.now();
            } else {
                int secondsBeforeSend = configurationService.getAlarmTimeBeforeSending();
                if (startAlarmThresholdExceeded.plusSeconds(secondsBeforeSend).isBeforeNow()) {
                    if (lastAlarmSend == null) {
                        String emailOnAlarm = configurationService.getEmailOnAlarm();
                        emailNotificationSender.sendNotification(emailOnAlarm);
                        lastAlarmSend = DateTime.now();
                    } else {
                        int secondsBeforeReplay = configurationService.getAlarmTimeBeforeReply();
                        if(lastAlarmSend.plusSeconds(secondsBeforeReplay).isBeforeNow()) {
                            String emailOnAlarm = configurationService.getEmailOnAlarm();
                            emailNotificationSender.sendNotification(emailOnAlarm);
                        }
                    }
                }
            }
        } else {
            startAlarmThresholdExceeded = null;
            lastAlarmSend = null;
        }
    }
	
	protected void logCommunicationStablished() {
		if(communicationError) {
			communicationError = false;
			logger.error("Communication with PLC established at " + new DateTime());
		}
	}
	
	protected void logCommunicationMissed(Exception ex) {
		if(!communicationError) {
			communicationError = true;
			logger.error("Communication with PLC missed at " + new DateTime(), ex);
		}
	}
}
