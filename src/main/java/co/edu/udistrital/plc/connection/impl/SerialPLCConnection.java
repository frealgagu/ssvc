package co.edu.udistrital.plc.connection.impl;

import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.io.ModbusTransaction;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.util.SerialParameters;
import co.edu.udistrital.exception.PLCConnectionException;
import co.edu.udistrital.plc.connection.PLCConnection;

public class SerialPLCConnection implements PLCConnection {

	private SerialConnection serialConnection;
	
	public SerialPLCConnection(SerialParameters serialParameters) {
		serialConnection = new SerialConnection(serialParameters);
	}
	
	@Override
	public void open() throws PLCConnectionException {
		try {
			serialConnection.open();
		} catch (Exception ex) {
			throw new PLCConnectionException("Error opening connection with PLC", ex);
		}
	}

	@Override
	public void close() throws PLCConnectionException {
		try {
			serialConnection.close();
		} catch (Exception ex) {
			throw new PLCConnectionException("Error closing connection with PLC", ex);
		}
	}

	@Override
	public ModbusTransaction createTransaction() {
		return new ModbusSerialTransaction(serialConnection);
	}
}
