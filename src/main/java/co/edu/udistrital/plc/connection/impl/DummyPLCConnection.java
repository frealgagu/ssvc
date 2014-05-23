package co.edu.udistrital.plc.connection.impl;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.io.ModbusTransaction;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.procimg.ProcessImage;
import net.wimpi.modbus.procimg.SimpleDigitalIn;
import net.wimpi.modbus.procimg.SimpleDigitalOut;
import net.wimpi.modbus.procimg.SimpleInputRegister;
import net.wimpi.modbus.procimg.SimpleProcessImage;
import net.wimpi.modbus.procimg.SimpleRegister;
import co.edu.udistrital.exception.PLCConnectionException;
import co.edu.udistrital.plc.connection.PLCConnection;

public class DummyPLCConnection implements PLCConnection {

	protected static final ProcessImage PROCESS_IMAGE = new DummyProcessImage(); 
	
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

    public static boolean randomizing = false;

    public static void startRandom() {
        randomizing = true;
    }

    public static void stopRandom() {
        randomizing = false;
    }

	public static class DummyProcessImage extends SimpleProcessImage {
		
		public DummyProcessImage() {
			 fillData();
		}
		
		protected void fillData() {
			for(int i = 0; i < 100000; i++) {
				addDigitalIn(new SimpleDigitalIn(false));
				addDigitalOut(new SimpleDigitalOut(false));
				addInputRegister(new SimpleInputRegister(0));
				addRegister(new SimpleRegister(0));
			}
			new Thread(new Runnable(){

				@Override
				public void run() {
					while(randomizing) {
						try{
							Thread.sleep(100);
							int register;
							//Pressure register
                            if(System.currentTimeMillis() % 60000 < 30000)
							    register = createRandom(70, 90);
                            else
                                register = createRandom(90, 110);
							setInputRegister(4000, new SimpleInputRegister(register));
							setRegister(4000, new SimpleRegister(register));
							//Temperature register
                            if(System.currentTimeMillis() % 60000 < 30000)
							    register = createRandom(110, 130);
                            else
                                register = createRandom(130, 150);
							setInputRegister(3000, new SimpleInputRegister(register));
							setRegister(3000, new SimpleRegister(register));
						} catch (InterruptedException ignore) {
						}
					}
				}
				
			}).start();
		}
	}
	
	public static int createRandom(int minValue, int maxValue) {
		return (int)((Math.random() * (maxValue - minValue)) + minValue);
	}
}
