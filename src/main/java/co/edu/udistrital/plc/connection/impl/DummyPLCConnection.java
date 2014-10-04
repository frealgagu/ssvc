package co.edu.udistrital.plc.connection.impl;

import co.edu.udistrital.dao.ConfigurationDao;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.io.ModbusTransaction;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.procimg.SimpleDigitalIn;
import net.wimpi.modbus.procimg.SimpleDigitalOut;
import net.wimpi.modbus.procimg.SimpleInputRegister;
import net.wimpi.modbus.procimg.SimpleProcessImage;
import net.wimpi.modbus.procimg.SimpleRegister;
import co.edu.udistrital.exception.PLCConnectionException;
import co.edu.udistrital.plc.connection.PLCConnection;

import javax.swing.*;
import java.awt.*;

public class DummyPLCConnection implements PLCConnection {

	protected static final DummyProcessImage PROCESS_IMAGE = new DummyProcessImage();

	@Override
	public void open() throws PLCConnectionException {
		ModbusCoupler.getReference().setProcessImage(PROCESS_IMAGE);
	}

	@Override
	public void close() throws PLCConnectionException {

	}

	@Override
	public ModbusTransaction createTransaction() {
		return new DummyModbusTransaction();
	}
	
	public static class DummyModbusTransaction implements ModbusTransaction {

		private int retries;
		private ModbusRequest request;
		private ModbusResponse response;
		private boolean checkingValidity;
		
		@Override
		public void setRetries(int retries) {
			this.retries = retries;
		}
		
		@Override
		public void setRequest(ModbusRequest request) {
			this.request = request;
		}
		
		@Override
		public void setCheckingValidity(boolean checkingValidity) {
			this.checkingValidity = checkingValidity;
		}
		
		@Override
		public boolean isCheckingValidity() {
			return checkingValidity;
		}
		
		@Override
		public int getTransactionID() {
			return request.getTransactionID();
		}
		
		@Override
		public int getRetries() {
			return retries;
		}
		
		@Override
		public ModbusResponse getResponse() {
			return response;
		}
		
		@Override
		public ModbusRequest getRequest() {
			return request;
		}
		
		@Override
		public void execute() throws ModbusException {
			response = request.createResponse();
		}
	}

    private static boolean readingData;
    private static JFrame frame;
    private static JSpinner pressureSpinner;
    private static JSpinner temperatureSpinner;

    public static void startReadingData(ConfigurationDao configurationDao) {
        stopReadingData();
        readingData = true;
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setPreferredSize(new Dimension(250, 70));
        frame.setSize(new Dimension(450, 70));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(2, 2));
        pressureSpinner = new JSpinner(new SpinnerNumberModel());
        pressureSpinner.setValue((int)(Math.random() * 200));
        temperatureSpinner = new JSpinner(new SpinnerNumberModel());
        temperatureSpinner.setValue((int)(Math.random() * 200 + 10));
        frame.add(new JLabel("Nivel de Presi\u00F3n:"), "1");
        frame.add(pressureSpinner, "2");
        frame.add(new JLabel("Nivel de Temperatura"), "3");
        frame.add(temperatureSpinner, "4");
        frame.setVisible(true);
        PROCESS_IMAGE.fillData(configurationDao);
    }

    public static void stopReadingData() {
        readingData = false;
        if(frame != null) {
            frame.dispose();
        }
    }

	public static class DummyProcessImage extends SimpleProcessImage {

		public DummyProcessImage() {
            for(int i = 0; i < 100000; i++) {
                addDigitalIn(new SimpleDigitalIn(false));
                addDigitalOut(new SimpleDigitalOut(false));
                addInputRegister(new SimpleInputRegister(0));
                addRegister(new SimpleRegister(0));
            }
		}
		
		protected void fillData(final ConfigurationDao configurationDao) {

			new Thread(new Runnable(){

				@Override
				public void run() {
					while(readingData) {
                        int pressureReadRegister = configurationDao.getPressureRead();
                        int pressureWriteRegister = configurationDao.getPressureWrite();
                        int pressureAlarmRegister = configurationDao.getPressureAlarmRegister();
                        int temperatureReadRegister = configurationDao.getTemperatureRead();
                        int temperatureWriteRegister = configurationDao.getTemperatureWrite();
                        int temperatureAlarmRegister = configurationDao.getTemperatureAlarmRegister();

                        int pressureRegisterValue = (Integer) pressureSpinner.getValue();
                        int desiredPressureRegisterValue = (int) (pressureRegisterValue * 1.2);
                        int pressureAlarmRegisterValue = 500;
                        int temperatureRegisterValue = (Integer) temperatureSpinner.getValue();
                        int desiredTemperatureRegisterValue = (int) (temperatureRegisterValue * 1.2);
                        int temperatureAlarmRegisterValue = 700;

                        setInputRegister(pressureReadRegister, new SimpleInputRegister(pressureRegisterValue));
                        setRegister(pressureReadRegister, new SimpleRegister(pressureRegisterValue));
                        setInputRegister(pressureWriteRegister, new SimpleInputRegister(desiredPressureRegisterValue));
                        setRegister(pressureWriteRegister, new SimpleRegister(desiredPressureRegisterValue));
                        setRegister(pressureAlarmRegister, new SimpleRegister(pressureAlarmRegisterValue));
                        setInputRegister(pressureAlarmRegister, new SimpleInputRegister(pressureAlarmRegisterValue));

                        setInputRegister(temperatureReadRegister, new SimpleInputRegister(temperatureRegisterValue));
                        setRegister(temperatureReadRegister, new SimpleRegister(temperatureRegisterValue));
                        setInputRegister(temperatureWriteRegister, new SimpleInputRegister(desiredTemperatureRegisterValue));
                        setRegister(temperatureWriteRegister, new SimpleRegister(desiredTemperatureRegisterValue));
                        setInputRegister(temperatureAlarmRegister, new SimpleInputRegister(temperatureAlarmRegisterValue));
                        setRegister(temperatureAlarmRegister, new SimpleRegister(temperatureAlarmRegisterValue));
					}
				}
				
			}).start();
		}
	}
}
