package co.edu.udistrital.service;

import co.edu.udistrital.domain.plc.Encoding;
import co.edu.udistrital.domain.plc.DataBits;
import co.edu.udistrital.domain.plc.Parity;
import co.edu.udistrital.domain.plc.StopBits;

public interface ConfigurationService {

	//PLC Communication
	
	/**
	 * Returns PLC communication port
	 * @return PLC communication port
	 */
	String getPort();

	/**
	 * Sets PLC communication port
	 * @param port PLC communication port
	 */
	void setPort(String port);
	
	/**
	 * Returns PLC communication baud rate
	 * @return PLC communication baud rate
	 */
	int getBaudRate();

	/**
	 * Sets PLC communication baud rate
	 * @param baudRate PLC communication baud rate
	 */
	void setBaudRate(int baudRate);

	/**
	 * Returns PLC communication parity
	 * @return PLC communication parity
	 */
	Parity getParity();

	/**
	 * Sets PLC communication parity
	 * @param parity PLC communication parity
	 */
	void setParity(Parity parity);	
	
	/**
	 * Returns PLC communication data bits
	 * @return PLC communication data bits
	 */
	DataBits getDataBits();

	/**
	 * Sets PLC communication data bits
	 * @param dataBits PLC communication data bits
	 */
	void setDataBits(DataBits dataBits);
	
	/**
	 * Returns PLC communication stop bits
	 * @return
	 */
	StopBits getStopBits();

	/**
	 * Sets PLC communication stop bits
	 * @param stopbits PLC communication stop bits
	 */
	void setStopBits(StopBits stopBits);
	
	/**
	 * Returns PLC encoding
	 * @return PLC encoding
	 */
	Encoding getEncoding();

	/**
	 * Sets PLC encoding
	 * @param encoding PLC encoding
	 */
	void setEncoding(Encoding encoding);
	
	/**
	 * Returns PLC communication timeout
	 * @return PLC communication timeout
	 */
	int getTimeout();
	
	/**
	 * Sets PLC communication timeout
	 * @param timeout PLC communication timeout
	 */
	void setTimeout(int timeout);	
	
	/**
	 * Returns pressure register number
	 * @return Pressure register number
	 */
	int getPressureRegisterNumber();
	
	/**
	 * Sets pressure register number
	 * @param pressureRegisterNumber Pressure register number
	 */
	void setPressureRegisterNumber(int pressureRegisterNumber);
	
	/**
	 * Returns temperature register number
	 * @return Temperature register number
	 */
	int getTemperatureRegisterNumber();
	
	/**
	 * Sets temperature register number
	 * @param temperatureRegisterNumber temperature register number
	 */
	void setTemperatureRegisterNumber(int temperatureRegisterNumber);	
	
	//Notification
	
	/**
	 * Returns pressure advice threshold
	 * @return Pressure advice threshold
	 */
	int getPressureAdviceThreshold();
	
	/**
	 * Sets pressure advice threshold
	 * @param pressureAdviceThreshold
	 */
	void setPressureAdviceThreshold(int pressureAdviceThreshold);
	
	/**
	 * Returns 
	 * @return
	 */
	int getPressureAlarmThreshold();

	/**
	 * Sets 
	 * @param pressureAlarmThreshold
	 */
	void setPressureAlarmThreshold(int pressureAlarmThreshold);

	/**
	 * Returns 
	 * @return
	 */
	int getTemperatureAdviceThreshold();

	/**
	 * Sets 
	 * @param temperatureAdviceThreshold
	 */
	void setTemperatureAdviceThreshold(int temperatureAdviceThreshold);

	/**
	 * Returns 
	 * @return
	 */
	int getTemperatureAlarmThreshold();

	/**
	 * Sets 
	 * @param temperatureAlarmThreshold
	 */
	void setTemperatureAlarmThreshold(int temperatureAlarmThreshold);

	/**
	 * Returns 
	 * @return
	 */
	int getAdviceTimeBeforeSending();

	/**
	 * Sets 
	 * @param adviceTimeBeforeSending
	 */
	void setAdviceTimeBeforeSending(int adviceTimeBeforeSending);

	/**
	 * Returns 
	 * @return
	 */
	int getAlarmTimeBeforeSending();

	/**
	 * Sets 
	 * @param alarmTimeBeforeSending
	 */
	void setAlarmTimeBeforeSending(int alarmTimeBeforeSending);

	/**
	 * Returns 
	 * @return
	 */
	int getAdviceTimeBeforeReply();

	/**
	 * Sets 
	 * @param adviceTimeBeforeReply
	 */
	void setAdviceTimeBeforeReply(int adviceTimeBeforeReply);

	/**
	 * Returns 
	 * @return
	 */
	int getAlarmTimeBeforeReply();

	/**
	 * Sets 
	 * @param alarmTimeBeforeReply
	 */
	void setAlarmTimeBeforeReply(int alarmTimeBeforeReply);

	String getEmailOnAdvice();

	void setEmailOnAdvice(String emailOnAdvice);

	String getEmailOnAlarm();

	void setEmailOnAlarm(String emailOnAlarm);

	String getSmsOnAdvice();

	void setSmsOnAdvice(String smsOnAdvice);

	String getSmsOnAlarm();

	void setSmsOnAlarm(String smsOnAlarm);

	//Email Sender
	
	String getEmailSenderHost();
	
	void setEmailSenderHost(String host);

	String getEmailSenderPort();
	
	void setEmailSenderPort(String port);

	String getEmailSenderUsername();
	
	void setEmailSenderUsername(String username);

	String getEmailSenderPassword();
	
	void setEmailSenderPassword(String password);
	
	String getEmailSenderFrom();
	
	void setEmailSenderFrom(String from);
	
	String getEmailSenderStartTLS();
	
	void setEmailSenderStartTLS(String startTLS);	
	
	String getEmailSenderAuth();
	
	void setEmailSenderAuth(String auth);	
}
