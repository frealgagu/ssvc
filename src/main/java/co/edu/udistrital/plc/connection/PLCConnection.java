package co.edu.udistrital.plc.connection;

import net.wimpi.modbus.io.ModbusTransaction;
import co.edu.udistrital.exception.PLCConnectionException;

public interface PLCConnection {

	/**
	 * Open the connection with PLC
	 * @throws PLCConnectionException
	 */
	void open() throws PLCConnectionException;

	/**
	 * Close the connection with PLC
	 * @throws PLCConnectionException
	 */
	void close() throws PLCConnectionException;

	/**
	 * Create a transaction
	 * @return
	 */
	ModbusTransaction createTransaction();
}
