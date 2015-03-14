package co.edu.udistrital.controller.pressure;

import co.edu.udistrital.controller.MeasureController;
import co.edu.udistrital.controller.measure.Measure;
import co.edu.udistrital.notification.NotificationSender;
import co.edu.udistrital.service.ApplicationServices;
import co.edu.udistrital.view.InitApplication;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.spinn3r.log5j.Logger;

import co.edu.udistrital.exception.PLCCommunicationException;
import co.edu.udistrital.service.ConfigurationService;
import co.edu.udistrital.service.PLCService;

import java.math.BigDecimal;

@Controller("pressureController")
public class PressureControllerImpl implements MeasureController {

	protected static final Logger logger = Logger.getLogger();
	
	protected static final int UNIT_ID = 1;
	
	@Autowired
	protected ConfigurationService configurationService;
	@Autowired
	protected PLCService plcService;
	@Autowired
	@Qualifier("pressureSecondsMeasure")
	protected Measure secondsMeasure;
	@Autowired
	@Qualifier("pressureMinutesMeasure")
	protected Measure minutesMeasure;
	@Autowired
	@Qualifier("pressureHoursMeasure")
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
    protected boolean alarmReplied = false;
	
	@Scheduled(fixedRate = 500)
	public void process() {
		if(monitoring) {
			int registerNumber = configurationService.getPressureReadRegister();
			try {
				int registerValue = plcService.readRegister(registerNumber, UNIT_ID);
                BigDecimal pressureRegisterValue = BigDecimal.valueOf(registerValue).divide(BigDecimal.TEN, 1, BigDecimal.ROUND_HALF_UP);
				secondsMeasure.appendValue(pressureRegisterValue);
				minutesMeasure.appendValue(pressureRegisterValue);
				hoursMeasure.appendValue(pressureRegisterValue);
                writeNotification(pressureRegisterValue);
				logCommunicationEstablished();
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

    protected void writeNotification(BigDecimal register) {
        ConfigurationService configurationService = ApplicationServices.getConfigurationService();
        try {
            int pressureAlarm = plcService.readRegister(configurationService.getPressureAlarmRegister(), InitApplication.UNIT_ID);
            BigDecimal pressureAlarmValue = BigDecimal.valueOf(pressureAlarm).divide(BigDecimal.TEN, 1, BigDecimal.ROUND_HALF_UP);

            if (register.compareTo(pressureAlarmValue) > 0) {
                if (startAlarmThresholdExceeded == null) {
                    startAlarmThresholdExceeded = DateTime.now();
                } else {
                    int secondsBeforeSend = configurationService.getAlarmTimeBeforeSending();
                    if (startAlarmThresholdExceeded.plusSeconds(secondsBeforeSend).isBeforeNow()) {
                        if (lastAlarmSend == null) {
                            String subject = "La presi\u00F3n ha superado el l\u00EDmite de alarma";
                            String message = "La presi\u00F3n ha superado el l\u00EDmite de alarma. Por favor ingrese a http://ssvc.frealgagu.com:7171/ssvc/ para corregirlo.";
                            String emailOnAlarm = configurationService.getEmailOnAlarm();
                            String smsOnAlarm = configurationService.getSmsOnAlarm();
                            emailNotificationSender.sendNotification(subject, message, emailOnAlarm);
                            smsNotificationSender.sendNotification(subject, message, smsOnAlarm);
                            lastAlarmSend = DateTime.now();
                        } else {
                            int secondsBeforeReplay = configurationService.getAlarmTimeBeforeReply();
                            if (lastAlarmSend.plusSeconds(secondsBeforeReplay).isBeforeNow()) {
                                if (!alarmReplied) {
                                    String subject = "La presi\u00F3n ha superado el l\u00EDmite de alarma y no se ha corregido";
                                    String message = "La presi\u00F3n ha superado el l\u00EDmite de alarma y no se ha corregido. Por favor ingrese a http://ssvc.frealgagu.com:7171/ssvc/ para corregirlo.";
                                    String emailOnAlarm = configurationService.getEmailOnAlarm();
                                    String smsOnAlarm = configurationService.getSmsOnAlarm();
                                    emailNotificationSender.sendNotification(subject, message, emailOnAlarm);
                                    smsNotificationSender.sendNotification(subject, message, smsOnAlarm);
                                    alarmReplied = true;
                                }
                                //Avoid reset of sent alarm once alarm has replied
                                lastAlarmSend = DateTime.now();
                            }
                        }
                    }
                }
            } else {
                if (lastAlarmSend != null) {
                    int secondsBeforeWait = 3 * configurationService.getAlarmTimeBeforeSending();
                    if (alarmReplied) {
                        secondsBeforeWait += 3 * configurationService.getAlarmTimeBeforeReply();
                    }
                    if (lastAlarmSend.plusSeconds(secondsBeforeWait).isBeforeNow()) {
                        //Reset status of sent alarm
                        startAlarmThresholdExceeded = null;
                        lastAlarmSend = null;
                        alarmReplied = false;
                    }
                } else {
                    startAlarmThresholdExceeded = null;
                }
            }
        } catch (PLCCommunicationException ex) {
            logCommunicationMissed(ex);
        }
    }

    protected void logCommunicationEstablished() {
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
