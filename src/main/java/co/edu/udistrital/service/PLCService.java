package co.edu.udistrital.service;

import co.edu.udistrital.exception.PLCCommunicationException;
import co.edu.udistrital.exception.PLCConnectionException;

public interface PLCService {

	/**
	 * Read single coil
	 * @param position
	 * @param unitID
	 * @return
	 * @throws PLCCommunicationException
	 */
	boolean readCoil(int position, int unitID) throws PLCCommunicationException;

	/**
	 * Read multiple coils
	 * @param position
	 * @param quantity
	 * @param unitID
	 * @return
     * @throws PLCCommunicationException
	 */
    boolean[] readCoils(int position, int quantity, int unitID) throws PLCCommunicationException;

    /**
     * Write a coil
     * @param position
     * @param valueToWrite
     * @param unitID
     * @throws PLCCommunicationException
     */
	void writeCoil(int position, boolean valueToWrite, int unitID) throws PLCCommunicationException;
	
	/**
	 * Read a register
	 * @param position
	 * @param unitID
	 * @return
     * @throws PLCCommunicationException
	 */
	int readRegister(int position, int unitID) throws PLCCommunicationException;

	/**
	 * Write a register
	 * @param position
	 * @param valueToWrite
	 * @param unitID
     * @throws PLCCommunicationException
	 */
	void writeRegister(int position, int valueToWrite, int unitID) throws PLCCommunicationException;

}
