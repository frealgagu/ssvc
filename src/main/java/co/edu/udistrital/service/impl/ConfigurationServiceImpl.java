package co.edu.udistrital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udistrital.dao.ConfigurationDao;
import co.edu.udistrital.domain.plc.Encoding;
import co.edu.udistrital.domain.plc.DataBits;
import co.edu.udistrital.domain.plc.Parity;
import co.edu.udistrital.domain.plc.StopBits;
import co.edu.udistrital.service.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	private ConfigurationDao configurationDao;

	//PLC Communication
	
	public String getPort() {
		return configurationDao.getPort();
	}

	public void setPort(String port) {
		configurationDao.setPort(port);
	}
	
	public int getBaudRate() {
		return configurationDao.getBaudRate();
	}

	public void setBaudRate(int baudRate) {
		configurationDao.setBaudRate(baudRate);
	}

	public DataBits getDataBits() {
		return configurationDao.getDataBits();
	}

	public void setDataBits(DataBits dataBits) {
		configurationDao.setDataBits(dataBits);
	}
	
	public Parity getParity() {
		return configurationDao.getParity();
	}

	public void setParity(Parity parity) {
		configurationDao.setParity(parity);
	}
	
	public StopBits getStopBits() {
		return configurationDao.getStopBits();
	}

	public void setStopBits(StopBits stopBits) {
		configurationDao.setStopBits(stopBits);
	}
	
	public Encoding getEncoding() {
		return configurationDao.getEncoding();
	}

	public void setEncoding(Encoding encoding) {
		configurationDao.setEncoding(encoding);
	}
	
	public int getTimeout() {
		return configurationDao.getTimeout();
	}
	
	public void setTimeout(int timeout) {
		configurationDao.setTimeout(timeout);
	}

    public int getMachineTurnOnOff() {
        return configurationDao.getMachineTurnOnOff();
    }

    public void setMachineTurnOnOff(int machineTurnOnOff) {
        configurationDao.setMachineTurnOnOff(machineTurnOnOff);
    }

    public int getPressureReadRegister() {
        return configurationDao.getPressureReadRegister();
    }

    public void setPressureReadRegister(int pressureReadRegister) {
        configurationDao.setPressureReadRegister(pressureReadRegister);
    }

    public int getPressureDesiredRegister() {
        return configurationDao.getPressureDesiredRegister();
    }

    public void setPressureDesiredRegister(int pressureDesiredRegister) {
        configurationDao.setPressureDesiredRegister(pressureDesiredRegister);
    }

    public int getTemperatureReadRegister() {
        return configurationDao.getTemperatureReadRegister();
    }

    public void setTemperatureReadRegister(int temperatureReadRegister) {
        configurationDao.setTemperatureReadRegister(temperatureReadRegister);
    }

    public int getTemperatureDesiredRegister() {
        return configurationDao.getTemperatureDesiredRegister();
    }

    public void setTemperatureDesiredRegister(int temperatureDesiredRegister) {
        configurationDao.setTemperatureDesiredRegister(temperatureDesiredRegister);
    }

    //Notification
	
	public int getPressureAlarmRegister() {
		return configurationDao.getPressureAlarmRegister();
	}

	public void setPressureAlarmRegister(int pressureAlarmRegister) {
		configurationDao.setPressureAlarmRegister(pressureAlarmRegister);
	}

	public int getTemperatureAlarmRegister() {
		return configurationDao.getTemperatureAlarmRegister();
	}

	public void setTemperatureAlarmRegister(int temperatureAlarmRegister) {
		configurationDao.setTemperatureAlarmRegister(temperatureAlarmRegister);
	}

	public int getAlarmTimeBeforeSending() {
		return configurationDao.getAlarmTimeBeforeSending();
	}

	public void setAlarmTimeBeforeSending(int alarmTimeBeforeSending) {
		configurationDao.setAlarmTimeBeforeSending(alarmTimeBeforeSending);
	}

	public int getAlarmTimeBeforeReply() {
		return configurationDao.getAlarmTimeBeforeReply();
	}

	public void setAlarmTimeBeforeReply(int alarmTimeBeforeReply) {
		configurationDao.setAlarmTimeBeforeReply(alarmTimeBeforeReply);
	}

	public String getEmailOnAdvice() {
		return configurationDao.getEmailOnAdvice();
	}

	public void setEmailOnAdvice(String emailOnAdvice) {
		configurationDao.setEmailOnAdvice(emailOnAdvice);
	}

	public String getEmailOnAlarm() {
		return configurationDao.getEmailOnAlarm();
	}

	public void setEmailOnAlarm(String emailOnAlarm) {
		configurationDao.setEmailOnAlarm(emailOnAlarm);
	}

	public String getSmsOnAdvice() {
		return configurationDao.getSmsOnAdvice();
	}

	public void setSmsOnAdvice(String smsOnAdvice) {
		configurationDao.setSmsOnAdvice(smsOnAdvice);
	}

	public String getSmsOnAlarm() {
		return configurationDao.getSmsOnAlarm();
	}

	public void setSmsOnAlarm(String smsOnAlarm) {
		configurationDao.setSmsOnAlarm(smsOnAlarm);
	}

	@Override
    public String getEmailSenderHost() {
	    return configurationDao.getEmailSenderHost();
    }

	@Override
    public void setEmailSenderHost(String host) {
		configurationDao.setEmailSenderHost(host);	    
    }

	@Override
    public String getEmailSenderPort() {
		return configurationDao.getEmailSenderPort();
    }

	@Override
    public void setEmailSenderPort(String port) {
		configurationDao.setEmailSenderPort(port);
    }

	@Override
    public String getEmailSenderUsername() {
		return configurationDao.getEmailSenderUsername();
    }

	@Override
    public void setEmailSenderUsername(String username) {
		configurationDao.setEmailSenderUsername(username);	    
    }

	@Override
    public String getEmailSenderPassword() {
		return configurationDao.getEmailSenderPassword();
    }

	@Override
    public void setEmailSenderPassword(String password) {
	    configurationDao.setEmailSenderPassword(password);	    
    }
	
	@Override
    public String getEmailSenderFrom() {
		return configurationDao.getEmailSenderFrom();
    }

	@Override
    public void setEmailSenderFrom(String from) {
		configurationDao.setEmailSenderFrom(from);	    
    }
	
	@Override
    public String getEmailSenderStartTLS() {
		return configurationDao.getEmailSenderStartTLS();
    }

	@Override
    public void setEmailSenderStartTLS(String startTLS) {
		configurationDao.setEmailSenderStartTLS(startTLS);	    
    }
	
	@Override
    public String getEmailSenderAuth() {
		return configurationDao.getEmailSenderAuth();
    }

	@Override
    public void setEmailSenderAuth(String auth) {
		configurationDao.setEmailSenderAuth(auth);	    
    }

    @Override
    public String getSMSSenderUsername() {
        return configurationDao.getSMSSenderUsername();
    }

    @Override
    public void setSMSSenderUsername(String username) {
        configurationDao.setSMSSenderUsername(username);
    }

    @Override
    public String getSMSSenderPassword() {
        return configurationDao.getSMSSenderPassword();
    }

    @Override
    public void getSMSSenderPassword(String password) {
        configurationDao.setSMSSenderPassword(password);
    }
}
