package co.edu.udistrital.plc.communication;

import co.edu.udistrital.exception.PLCCommunicationException;
import co.edu.udistrital.exception.PLCConnectionException;

public interface PLCCommunication {

    static final int DEFAULT_UNIT_ID = 1;

	/**
	 * Read single coil
	 * @param position
	 * @param unitID
	 * @return
	 * @throws PLCConnectionException
	 */
	boolean readCoil(int position, int unitID) throws PLCCommunicationException;

	/**
	 * Read multiple coils
	 * @param position
	 * @param quantity
	 * @param unitID
	 * @return
	 * @throws PLCConnectionException
	 */
    boolean[] readCoils(int position, int quantity, int unitID) throws PLCCommunicationException;

    /**
     * Write a coil
     * @param position
     * @param valueToWrite
     * @param unitID
     * @throws PLCConnectionException
     */
	void writeCoil(int position, boolean valueToWrite, int unitID) throws PLCCommunicationException;
	
	/**
	 * Read a register
	 * @param position
	 * @param unitID
	 * @return
	 * @throws PLCConnectionException
	 */
	int readRegister(int position, int unitID) throws PLCCommunicationException;

	/**
	 * Write a register
	 * @param position
	 * @param valueToWrite
	 * @param unitID
	 * @throws PLCConnectionException
	 */
	void writeRegister(int position, int valueToWrite, int unitID) throws PLCCommunicationException;		

}
