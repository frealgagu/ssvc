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
	 * @param stopBits PLC communication stop bits
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
     * Returns PLC machine turn on off coil indicator
     * @return PLC machine turn on off coil indicator
     */
    int getMachineTurnOnOff();

    /**
     * Sets PLC machine turn on off coil indicator
     * @param machineTurnOnOff PLC machine turn on off coil indicator
     */
    void setMachineTurnOnOff(int machineTurnOnOff);

    /**
     * Returns PLC pressure read register number
     * @return PLC pressure read register number
     */
    int getPressureRead();

    /**
     * Sets PLC pressure read register number
     * @param pressureRead PLC pressure read register number
     */
    void setPressureRead(int pressureRead);

    /**
     * Returns PLC pressure write register number
     * @return PLC pressure write register number
     */
    int getPressureWriteRegister();

    /**
     * Sets PLC pressure write register number
     * @param pressureWrite PLC pressure write register number
     */
    void setPressureWrite(int pressureWrite);

    /**
     * Returns PLC temperature read register number
     * @return PLC temperature read register number
     */
    int getTemperatureReadRegister();

    /**
     * Sets PLC temperature read register number
     * @param temperatureRead PLC temperature read register number
     */
    void setTemperatureRead(int temperatureRead);

    /**
     * Returns PLC temperature write register number
     * @return PLC temperature write register number
     */
    int getTemperatureWriteRegister();

    /**
     * Sets PLC temperature write register number
     * @param temperatureWrite PLC temperature write register number
     */
    void setTemperatureWrite(int temperatureWrite);

	//Notification
	
	/**
	 * Returns 
	 * @return
	 */
	int getPressureAlarmRegister();

	/**
	 * Sets 
	 * @param pressureAlarmRegister
	 */
	void setPressureAlarmRegister(int pressureAlarmRegister);

	/**
	 * Returns 
	 * @return
	 */
	int getTemperatureAlarmRegister();

	/**
	 * Sets 
	 * @param temperatureAlarmRegister
	 */
	void setTemperatureAlarmRegister(int temperatureAlarmRegister);

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
	int getAlarmTimeBeforeReply();

	/**
	 * Sets 
	 * @param alarmTimeBeforeReply
	 */
	void setAlarmTimeBeforeReply(int alarmTimeBeforeReply);

	String getEmailOnAlarm();

	void setEmailOnAlarm(String emailOnAlarm);

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

    //SMS Sender

    String getSMSSenderUsername();

    void setSMSSenderUsername(String username);

    String getSMSSenderPassword();

    void getSMSSenderPassword(String password);
}
