package co.edu.udistrital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udistrital.exception.PLCCommunicationException;
import co.edu.udistrital.plc.communication.PLCCommunication;
import co.edu.udistrital.service.PLCService;

@Service
public class PLCServiceImpl implements PLCService {

	@Autowired
	private PLCCommunication plcCommunication;
	
	@Override
    public boolean readCoil(int position, int unitID) throws PLCCommunicationException {
	    return plcCommunication.readCoil(position, unitID);
    }

	@Override
    public boolean[] readCoils(int position, int quantity, int unitID) throws PLCCommunicationException {
	    return plcCommunication.readCoils(position, quantity, unitID);
    }

	@Override
    public void writeCoil(int position, boolean valueToWrite, int unitID) throws PLCCommunicationException {
		plcCommunication.writeCoil(position, valueToWrite, unitID);
    }

	@Override
    public int readRegister(int position, int unitID) throws PLCCommunicationException {
	    return plcCommunication.readRegister(position, unitID);
    }

	@Override
    public void writeRegister(int position, int valueToWrite, int unitID) throws PLCCommunicationException {
		plcCommunication.writeRegister(position, valueToWrite, unitID);
    }
}
