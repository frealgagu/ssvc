package co.edu.udistrital.plc.connection.impl;

import gnu.io.RXTXCommDriver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import net.wimpi.modbus.util.SerialParameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import co.edu.udistrital.dao.ConfigurationDao;
import co.edu.udistrital.exception.PLCConnectionException;
import co.edu.udistrital.plc.connection.PLCConnection;
import co.edu.udistrital.plc.connection.PLCConnectionFactory;

@Repository("plcConnectionFactory")
public class PLCConnectionFactoryImpl implements PLCConnectionFactory {

	private static final String DUMMY_CONNECTION_TYPE = "dummy";
	private static final String SERIAL_CONNECTION_TYPE = "serial";
	
    @Value("${plc.connection.type}")
	private String connectionType;

    @Autowired
    private ConfigurationDao configurationDao;

	//Dummy Connection
	
	//Serial Connection
	private SerialParameters serialParameters;

	@PostConstruct
	public void openConfiguration() {
		switch (connectionType) {
			case DUMMY_CONNECTION_TYPE:
				openDummyConfiguration();
				break;
			case SERIAL_CONNECTION_TYPE:
				openSerialConfiguration();
				break;
			default:
				break;
		} 
    }

    @PreDestroy
    public void closeConfiguration() {
        switch (connectionType) {
            case DUMMY_CONNECTION_TYPE:
                closeDummyConfiguration();
                break;
            case SERIAL_CONNECTION_TYPE:
                closeSerialConfiguration();
                break;
            default:
                break;
        }
    }
	
	private void openDummyConfiguration() {
		DummyPLCConnection.startReadingData(configurationDao);
	}

    private void closeDummyConfiguration() {
        DummyPLCConnection.stopReadingData();
    }
	
	private void openSerialConfiguration() {
		RXTXCommDriver driver = new RXTXCommDriver();
		driver.initialize();		
		
		serialParameters = new SerialParameters();		
		serialParameters.setPortName(configurationDao.getPort());
		serialParameters.setBaudRate(configurationDao.getBaudRate());
		serialParameters.setDatabits(configurationDao.getDataBits().getValue());
		serialParameters.setParity(configurationDao.getParity().getValue());
		serialParameters.setStopbits(configurationDao.getStopBits().getValue());
		serialParameters.setEncoding(configurationDao.getEncoding().getValue());
		serialParameters.setReceiveTimeout(configurationDao.getTimeout());
		serialParameters.setEcho(false);
	}

    private void closeSerialConfiguration() {
    }
	
	@Override
	public PLCConnection obtainPLCConnection() throws PLCConnectionException {
		switch (connectionType) {
			case DUMMY_CONNECTION_TYPE:
				return new DummyPLCConnection();
			case SERIAL_CONNECTION_TYPE:
				return new SerialPLCConnection(serialParameters);
			default:
				throw new PLCConnectionException("No connection type defined in configuration.properties");
		}
	}
}
