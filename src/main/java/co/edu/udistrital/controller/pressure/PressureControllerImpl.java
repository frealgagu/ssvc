package co.edu.udistrital.controller.pressure;

import co.edu.udistrital.controller.MeasureController;
import co.edu.udistrital.controller.measure.Measure;
import co.edu.udistrital.notification.NotificationSender;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.spinn3r.log5j.Logger;

import co.edu.udistrital.exception.PLCCommunicationException;
import co.edu.udistrital.service.ConfigurationService;
import co.edu.udistrital.service.PLCService;

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

    protected DateTime startAdviceThresholdExceeded = null;
    protected DateTime lastAdviceSend = null;
    protected boolean adviceReplied = false;

    protected DateTime startAlarmThresholdExceeded = null;
    protected DateTime lastAlarmSend = null;
    protected boolean alarmReplied = false;
	
	@Scheduled(fixedRate = 500)
	public void process() {
		if(monitoring) {
			int registerNumber = configurationService.getPressureRead();
			try {
				int register = plcService.readRegister(registerNumber, UNIT_ID);
				secondsMeasure.appendValue(register);
				minutesMeasure.appendValue(register);
				hoursMeasure.appendValue(register);
                writeNotification(register);
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

    protected void writeNotification(int register) {
        int alarmThreshold = configurationService.getPressureAlarmThreshold();
        if (register >= alarmThreshold) {
            if (startAlarmThresholdExceeded == null) {
                startAlarmThresholdExceeded = DateTime.now();
            } else {
                int secondsBeforeSend = configurationService.getAlarmTimeBeforeSending();
                if (startAlarmThresholdExceeded.plusSeconds(secondsBeforeSend).isBeforeNow()) {
                    if (lastAlarmSend == null) {
                        String subject = "La presi\u00F3n ha superado el l\u00EDmite de alarma";
                        String message = "La presi\u00F3n ha superado el l\u00EDmite de alarma. Por favor ingrese a http://ssvc.frealgagu.com/ssvc/ para corregirlo.";
                        String emailOnAlarm = configurationService.getEmailOnAlarm();
                        String smsOnAlarm = configurationService.getSmsOnAlarm();
                        emailNotificationSender.sendNotification(subject, message, emailOnAlarm);
                        smsNotificationSender.sendNotification(subject, message, smsOnAlarm);
                        lastAlarmSend = DateTime.now();
                    } else {
                        int secondsBeforeReplay = configurationService.getAlarmTimeBeforeReply();
                        if(lastAlarmSend.plusSeconds(secondsBeforeReplay).isBeforeNow()) {
                            if(!alarmReplied) {
                                String subject = "La presi\u00F3n ha superado el l\u00EDmite de alarma y no se ha corregido";
                                String message = "La presi\u00F3n ha superado el l\u00EDmite de alarma y no se ha corregido. Por favor ingrese a http://ssvc.frealgagu.com/ssvc/ para corregirlo.";
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

            int adviceThreshold = configurationService.getPressureAdviceThreshold();
            if (register >= adviceThreshold) {
                if (startAdviceThresholdExceeded == null) {
                    startAdviceThresholdExceeded = DateTime.now();
                } else {
                    int secondsBeforeSend = configurationService.getAdviceTimeBeforeSending();
                    if (startAdviceThresholdExceeded.plusSeconds(secondsBeforeSend).isBeforeNow()) {
                        if (lastAdviceSend == null) {
                            String subject = "La presi\u00F3n ha superado el l\u00EDmite de advertencia";
                            String message = "La presi\u00F3n ha superado el l\u00EDmite de advertencia. Por favor ingrese a http://ssvc.frealgagu.com/ssvc/ para corregirlo.";
                            String emailOnAdvice = configurationService.getEmailOnAdvice();
                            String smsOnAdvice = configurationService.getSmsOnAdvice();
                            emailNotificationSender.sendNotification(subject, message, emailOnAdvice);
                            smsNotificationSender.sendNotification(subject, message, smsOnAdvice);
                            lastAdviceSend = DateTime.now();
                        } else {
                            int secondsBeforeReplay = configurationService.getAdviceTimeBeforeReply();
                            if(lastAdviceSend.plusSeconds(secondsBeforeReplay).isBeforeNow()) {
                                if(!adviceReplied) {
                                    String subject = "La presi\u00F3n ha superado el l\u00EDmite de advertencia y no se ha corregido";
                                    String message = "La presi\u00F3n ha superado el l\u00EDmite de advertencia y no se ha corregido. Por favor ingrese a http://ssvc.frealgagu.com/ssvc/ para corregirlo.";
                                    String emailOnAdvice = configurationService.getEmailOnAdvice();
                                    String smsOnAdvice = configurationService.getSmsOnAdvice();
                                    emailNotificationSender.sendNotification(subject, message, emailOnAdvice);
                                    smsNotificationSender.sendNotification(subject, message, smsOnAdvice);
                                    lastAdviceSend = DateTime.now();
                                    adviceReplied = true;
                                }
                            }
                        }
                    }
                }
            } else {
                adviceReplied = false;
                startAdviceThresholdExceeded = null;
                lastAdviceSend = null;
            }
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
