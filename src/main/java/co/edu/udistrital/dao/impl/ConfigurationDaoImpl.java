package co.edu.udistrital.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import co.edu.udistrital.dao.ConfigurationDao;
import co.edu.udistrital.domain.plc.DataBits;
import co.edu.udistrital.domain.plc.Encoding;
import co.edu.udistrital.domain.plc.Parity;
import co.edu.udistrital.domain.plc.StopBits;

@Repository
public class ConfigurationDaoImpl extends JdbcDaoSupport implements ConfigurationDao {

	protected static final String PLC_PORT = "plc.port";
	protected static final String PLC_BAUD_RATE = "plc.baud.rate";
	protected static final String PLC_DATA_BITS = "plc.databits";
	protected static final String PLC_PARITY = "plc.parity";
	protected static final String PLC_STOP_BITS = "plc.stopbits";
	protected static final String PLC_ENCODING = "plc.encoding";
	protected static final String PLC_TIMEOUT = "plc.receive.timeout";

    protected static final String PLC_MACHINE_TURN_ON_OFF = "plc.machine.turn.on.off";
    protected static final String PLC_PRESSURE_READ = "plc.pressure.read.register";
    protected static final String PLC_PRESSURE_WRITE = "plc.pressure.write.register";
    protected static final String PLC_TEMPERATURE_READ = "plc.temperature.read.register";
    protected static final String PLC_TEMPERATURE_WRITE = "plc.temperature.write.register";

    protected static final String NOTIFICATION_PRESSURE_ALARM_THRESHOLD = "notification.pressure.alarm.register";
    protected static final String NOTIFICATION_TEMPERATURE_ALARM_THRESHOLD = "notification.temperature.alarm.register";

    protected static final String NOTIFICATION_ALARM_TIME_BEFORE_SENDING = "notification.alarm.time.before.sending";
    protected static final String NOTIFICATION_ALARM_TIME_BEFORE_REPLY = "notification.alarm.time.before.reply";

	protected static final String NOTIFICATION_EMAIL_ON_ADVICE = "notification.send.email.on.advice";
	protected static final String NOTIFICATION_EMAIL_ON_ALARM = "notification.send.email.on.alarm";
	protected static final String NOTIFICATION_SMS_ON_ADVICE = "notification.send.sms.on.advice";
	protected static final String NOTIFICATION_SMS_ON_ALARM = "notification.send.sms.on.alarm";

    protected static final String MAIL_SMTP_HOST = "mail.smtp.host";
    protected static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    protected static final String MAIL_SMTP_PORT = "mail.smtp.port";
    protected static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    protected static final String MAIL_SMTP_FROM = "mail.smtp.from";
    protected static final String MAIL_SMTP_USERNAME = "mail.smtp.username";
    protected static final String MAIL_SMTP_PASSWORD = "mail.smtp.password";

    protected static final String SMS_USERNAME = "sms.username";
    protected static final String SMS_PASSWORD = "sms.password";

	protected static final String TABLE_NAME = "configuration";
	protected static final String KEY_NAME = "c_key";
	protected static final String VALUE_NAME = "c_value";
	
	protected static final String SELECT_PROPERTY =
			"SELECT " + VALUE_NAME + " " +
			"FROM " + TABLE_NAME + " " +
			"WHERE " + KEY_NAME + " = ? ";
	
	protected static final String UPDATE_PROPERTY =
			"UPDATE " + TABLE_NAME + " " +
			"SET " + VALUE_NAME + " = ? " +
			"WHERE " + KEY_NAME + " = ? ";
	
	protected static final String INSERT_PROPERTY =
			"INSERT INTO configuration " +
			"SET " + VALUE_NAME + " = ? " +
			"WHERE " + KEY_NAME + " = ? ";
	
	@Autowired
	protected DataSource dataSource;
	
	@PostConstruct
	public void loadDataSource() {
		setDataSource(dataSource);
	}
	
    @Override
    public String getPort() {
    	return getProperty(PLC_PORT, String.class);
    }

	@Override
    public void setPort(String port) {
		setProperty(PLC_PORT, port);
    }

	@Override
    public int getBaudRate() {
		return getProperty(PLC_BAUD_RATE, Integer.class);
    }

	@Override
    public void setBaudRate(int baudRate) {
		setProperty(PLC_BAUD_RATE, baudRate);
    }

	@Override
    public DataBits getDataBits() {
		return getProperty(PLC_DATA_BITS, DataBits.class);
    }

	@Override
    public void setDataBits(DataBits dataBits) {
		setProperty(PLC_DATA_BITS, dataBits);
    }

	@Override
    public Parity getParity() {
		return getProperty(PLC_PARITY, Parity.class);
    }

	@Override
    public void setParity(Parity parity) {
		setProperty(PLC_PARITY, parity);
    }

	@Override
    public StopBits getStopBits() {
		return getProperty(PLC_STOP_BITS, StopBits.class);
    }

	@Override
    public void setStopBits(StopBits stopBits) {
		setProperty(PLC_STOP_BITS, stopBits);
    }

	@Override
    public Encoding getEncoding() {
		return getProperty(PLC_ENCODING, Encoding.class);
    }

	@Override
    public void setEncoding(Encoding encoding) {
		setProperty(PLC_ENCODING, encoding);
    }

	@Override
    public int getTimeout() {
	    return getProperty(PLC_TIMEOUT, Integer.class);
    }

	@Override
    public void setTimeout(int timeout) {
		setProperty(PLC_TIMEOUT, timeout);
    }

    @Override
    public int getMachineTurnOnOff() {
        return getProperty(PLC_MACHINE_TURN_ON_OFF, Integer.class);
    }

    @Override
    public void setMachineTurnOnOff(int machineTurnOnOff) {
        setProperty(PLC_MACHINE_TURN_ON_OFF, machineTurnOnOff);
    }

    @Override
    public int getPressureRead() {
        return getProperty(PLC_PRESSURE_READ, Integer.class);
    }

    @Override
    public void setPressureRead(int pressureRead) {
        setProperty(PLC_PRESSURE_READ, pressureRead);
    }

    @Override
    public int getPressureWrite() {
        return getProperty(PLC_PRESSURE_WRITE, Integer.class);
    }

    @Override
    public void setPressureWrite(int pressureWrite) {
        setProperty(PLC_PRESSURE_WRITE, pressureWrite);
    }

    @Override
    public int getTemperatureRead() {
        return getProperty(PLC_TEMPERATURE_READ, Integer.class);
    }

    @Override
    public void setTemperatureRead(int temperatureRead) {
        setProperty(PLC_TEMPERATURE_READ, temperatureRead);
    }

    @Override
    public int getTemperatureWrite() {
        return getProperty(PLC_TEMPERATURE_WRITE, Integer.class);
    }

    @Override
    public void setTemperatureWrite(int temperatureWrite) {
        setProperty(PLC_TEMPERATURE_WRITE, temperatureWrite);
    }

	@Override
    public int getPressureAlarmRegister() {
		return getProperty(NOTIFICATION_PRESSURE_ALARM_THRESHOLD, Integer.class);
    }

	@Override
    public void setPressureAlarmRegister(int pressureAlarmRegister) {
		setProperty(NOTIFICATION_PRESSURE_ALARM_THRESHOLD, pressureAlarmRegister);
    }

	@Override
    public int getTemperatureAlarmRegister() {
		return getProperty(NOTIFICATION_TEMPERATURE_ALARM_THRESHOLD, Integer.class);
    }

	@Override
    public void setTemperatureAlarmRegister(int temperatureAlarmRegister) {
		setProperty(NOTIFICATION_TEMPERATURE_ALARM_THRESHOLD, temperatureAlarmRegister);
    }

	@Override
    public int getAlarmTimeBeforeSending() {
		return getProperty(NOTIFICATION_ALARM_TIME_BEFORE_SENDING, Integer.class);
    }

	@Override
    public void setAlarmTimeBeforeSending(int alarmTimeBeforeSending) {
		setProperty(NOTIFICATION_ALARM_TIME_BEFORE_SENDING, alarmTimeBeforeSending);
    }

	@Override
    public int getAlarmTimeBeforeReply() {
		return getProperty(NOTIFICATION_ALARM_TIME_BEFORE_REPLY, Integer.class);
    }

	@Override
    public void setAlarmTimeBeforeReply(int alarmTimeBeforeReply) {
		setProperty(NOTIFICATION_ALARM_TIME_BEFORE_REPLY, alarmTimeBeforeReply);
    }

	public String getEmailOnAdvice() {
		return getProperty(NOTIFICATION_EMAIL_ON_ADVICE, String.class);
	}

	public void setEmailOnAdvice(String emailOnAdvice) {
		setProperty(NOTIFICATION_EMAIL_ON_ADVICE, emailOnAdvice);
	}

	public String getEmailOnAlarm() {
		return getProperty(NOTIFICATION_EMAIL_ON_ALARM, String.class);
	}

	public void setEmailOnAlarm(String emailOnAlarm) {
		setProperty(NOTIFICATION_EMAIL_ON_ALARM, emailOnAlarm);
	}

	public String getSmsOnAdvice() {
		return getProperty(NOTIFICATION_SMS_ON_ADVICE, String.class);
	}

	public void setSmsOnAdvice(String smsOnAdvice) {
		setProperty(NOTIFICATION_SMS_ON_ADVICE, smsOnAdvice);
	}

	public String getSmsOnAlarm() {
		return getProperty(NOTIFICATION_SMS_ON_ALARM, String.class);
	}

	public void setSmsOnAlarm(String smsOnAlarm) {
		setProperty(NOTIFICATION_SMS_ON_ALARM, smsOnAlarm);
	}
	
	@Override
    public String getEmailSenderHost() {
	    return getProperty(MAIL_SMTP_HOST, String.class);
    }

	@Override
    public void setEmailSenderHost(String host) {
	    setProperty(MAIL_SMTP_HOST, host);
    }

	@Override
    public String getEmailSenderPort() {
		return getProperty(MAIL_SMTP_PORT, String.class);
    }

	@Override
    public void setEmailSenderPort(String port) {
		setProperty(MAIL_SMTP_PORT, port);
    }

	@Override
    public String getEmailSenderUsername() {
		return getProperty(MAIL_SMTP_USERNAME, String.class);
    }

	@Override
    public void setEmailSenderUsername(String username) {
		setProperty(MAIL_SMTP_USERNAME, username);
    }

	@Override
    public String getEmailSenderPassword() {
		return getProperty(MAIL_SMTP_PASSWORD, String.class);
    }

	@Override
    public void setEmailSenderPassword(String password) {
		setProperty(MAIL_SMTP_PASSWORD, password);
    }
	
	@Override
    public String getEmailSenderFrom() {
		return getProperty(MAIL_SMTP_FROM, String.class);
    }

	@Override
    public void setEmailSenderFrom(String from) {
		setProperty(MAIL_SMTP_FROM, from);
    }
	
	@Override
    public String getEmailSenderStartTLS() {
		return getProperty(MAIL_SMTP_STARTTLS_ENABLE, String.class);
    }

	@Override
    public void setEmailSenderStartTLS(String startTLS) {
		setProperty(MAIL_SMTP_STARTTLS_ENABLE, startTLS);
    }

	@Override
    public String getEmailSenderAuth() {
		return getProperty(MAIL_SMTP_AUTH, String.class);
    }

	@Override
    public void setEmailSenderAuth(String auth) {
		setProperty(MAIL_SMTP_AUTH, auth);
    }

    @Override
    public String getSMSSenderUsername() {
        return getProperty(SMS_USERNAME, String.class);
    }

    @Override
    public void setSMSSenderUsername(String username) {
        setProperty(SMS_USERNAME, username);
    }

    @Override
    public String getSMSSenderPassword() {
        return getProperty(SMS_PASSWORD, String.class);
    }

    @Override
    public void setSMSSenderPassword(String password) {
        setProperty(SMS_PASSWORD, password);
    }

    protected <T> T getProperty(String propertyName, Class<T> clazz) {
		return getJdbcTemplate().queryForObject(
				SELECT_PROPERTY,
				createRowMapper(clazz),
				propertyName
		);
	}
	
	protected void setProperty(String propertyName, Object propertyValue) {
		int updatedRows = getJdbcTemplate().update(
				UPDATE_PROPERTY,
				retrievePropertyValue(propertyValue),
				propertyName
		);
		if(updatedRows <= 0) {
			getJdbcTemplate().update(
					INSERT_PROPERTY,
					propertyName,
					propertyValue
			);
		}
	}
	
	protected static <T> RowMapper<T> createRowMapper(final Class<T> clazz) {
		if(clazz.equals(DataBits.class)) {
			return new RowMapper<T>() {
				@Override
                public T mapRow(ResultSet rs, int rowNum) throws SQLException {
					return clazz.cast(DataBits.getDataBits(rs.getInt(VALUE_NAME)));
                }
    		};
		} else if(clazz.equals(Parity.class)) {
			return new RowMapper<T>() {
				@Override
                public T mapRow(ResultSet rs, int rowNum) throws SQLException {
					return clazz.cast(Parity.getParity(rs.getString(VALUE_NAME)));
                }
    		};
		} else if(clazz.equals(StopBits.class)) {
			return new RowMapper<T>() {
				@Override
                public T mapRow(ResultSet rs, int rowNum) throws SQLException {
					return clazz.cast(StopBits.getStopBits(rs.getInt(VALUE_NAME)));
                }
    		};
		} else if(clazz.equals(Encoding.class)) {
			return new RowMapper<T>() {
				@Override
                public T mapRow(ResultSet rs, int rowNum) throws SQLException {
					return clazz.cast(Encoding.getEncoding(rs.getString(VALUE_NAME)));
                }
    		};
		} else {
			return new SingleColumnRowMapper<T>(clazz);
		}
	}
	
	protected static Object retrievePropertyValue(Object propertyValue) {
		if(DataBits.class.isInstance(propertyValue)) {
			return DataBits.class.cast(propertyValue).getValue();
		} else if(Parity.class.isInstance(propertyValue)) {
			return Parity.class.cast(propertyValue).getValue();
		} else if(StopBits.class.isInstance(propertyValue)) {
			return StopBits.class.cast(propertyValue).getValue();
		} else if(Encoding.class.isInstance(propertyValue)) {
			return Encoding.class.cast(propertyValue).getValue();
		} else {
			return propertyValue;
		}
	}
}
