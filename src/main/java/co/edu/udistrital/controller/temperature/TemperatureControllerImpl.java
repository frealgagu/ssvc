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

import java.math.BigDecimal;

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
    @Autowired
    @Qualifier("smsNotificationSender")
    protected NotificationSender smsNotificationSender;

	protected boolean monitoring;
	protected boolean communicationError;

    protected DateTime startAlarmThresholdExceeded = null;
    protected DateTime lastAlarmSend = null;
    protected boolean alarmReplied;

	@Scheduled(fixedRate = 500)
	public void process() {
		if(monitoring) {
			int registerNumber = configurationService.getTemperatureReadRegister();
			try {
				int registerValue = plcService.readRegister(registerNumber, UNIT_ID);
                BigDecimal temperatureRegisterValue = BigDecimal.valueOf(registerValue).divide(BigDecimal.TEN, 1, BigDecimal.ROUND_HALF_UP);
				secondsMeasure.appendValue(temperatureRegisterValue);
				minutesMeasure.appendValue(temperatureRegisterValue);
				hoursMeasure.appendValue(temperatureRegisterValue);
                writeNotification(registerValue);
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

    protected void writeNotification(double register) {
        int alarmThreshold = configurationService.getTemperatureAlarmRegister();
        if (register >= alarmThreshold) {
            if (startAlarmThresholdExceeded == null) {
                startAlarmThresholdExceeded = DateTime.now();
            } else {
                int secondsBeforeSend = configurationService.getAlarmTimeBeforeSending();
                if (startAlarmThresholdExceeded.plusSeconds(secondsBeforeSend).isBeforeNow()) {
                    if (lastAlarmSend == null) {
                        String subject = "La temperatura ha superado el l\u00EDmite de alarma";
                        String message = "La temperatura ha superado el l\u00EDmite de alarma. Por favor ingrese a http://ssvc.frealgagu.com/ssvc/ para corregirlo.";
                        String emailOnAlarm = configurationService.getEmailOnAlarm();
                        String smsOnAlarm = configurationService.getSmsOnAlarm();
                        emailNotificationSender.sendNotification(subject, message, emailOnAlarm);
                        smsNotificationSender.sendNotification(subject, message, smsOnAlarm);
                        lastAlarmSend = DateTime.now();
                    } else {
                        int secondsBeforeReplay = configurationService.getAlarmTimeBeforeReply();
                        if(lastAlarmSend.plusSeconds(secondsBeforeReplay).isBeforeNow()) {
                            if(!alarmReplied) {
                                String subject = "La temperatura ha superado el l\u00EDmite de alarma y no se ha corregido";
                                String message = "La temperatura ha superado el l\u00EDmite de alarma y no se ha corregido. Por favor ingrese a http://ssvc.frealgagu.com/ssvc/ para corregirlo.";
                                String emailOnAlarm = configurationService.getEmailOnAlarm();
                                String smsOnAlarm = configurationService.getSmsOnAlarm();
                                emailNotificationSender.sendNotification(subject, message, emailOnAlarm);
                                smsNotificationSender.sendNotification(subject, message, smsOnAlarm);
                                lastAlarmSend = DateTime.now();
                                alarmReplied = true;
                            }
                        }
                    }
                }
            }
        } else {
            startAlarmThresholdExceeded = null;
            lastAlarmSend = null;
            alarmReplied = false;
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
