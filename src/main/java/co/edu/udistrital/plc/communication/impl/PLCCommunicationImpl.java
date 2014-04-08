package co.edu.udistrital.plc.communication.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.io.ModbusTransaction;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadCoilsResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.procimg.SimpleRegister;
import co.edu.udistrital.exception.PLCCommunicationException;
import co.edu.udistrital.exception.PLCConnectionException;
import co.edu.udistrital.plc.communication.PLCCommunication;
import co.edu.udistrital.plc.connection.PLCConnection;
import co.edu.udistrital.plc.connection.PLCConnectionFactory;

@Repository
public class PLCCommunicationImpl implements PLCCommunication {

	@SuppressWarnings("all")
	@Autowired
	@Qualifier("plcConnectionFactory")
	private PLCConnectionFactory plcConnectionFactory;
	
	protected static ReadCoilsRequest createReadCoilsRequest(int ref, int count, int unitID) {
        ReadCoilsRequest req = new ReadCoilsRequest(ref, count);
        req.setUnitID(unitID);
        req.setHeadless();
        return req;
    }
    
	protected static WriteCoilRequest createWriteCoilsRequest(int ref, boolean valueToWrite, int unitID) {
        WriteCoilRequest req = new WriteCoilRequest(ref, valueToWrite);
        req.setUnitID(unitID);
        req.setHeadless();
        return req;
    }
    
	protected static ReadMultipleRegistersRequest createReadRegistersRequest(int ref, int count, int unitID) {
    	ReadMultipleRegistersRequest req = new ReadMultipleRegistersRequest(ref, count);
        req.setUnitID(unitID);
        req.setHeadless();
        return req;
    }
    
	protected static WriteSingleRegisterRequest createWriteSingleRegisterRequest(int ref, int valueToWrite, int unitID) {
    	WriteSingleRegisterRequest req = new WriteSingleRegisterRequest(ref, new SimpleRegister(valueToWrite));
    	req.setUnitID(unitID);
    	req.setHeadless();
    	return req;
    }
    
	public synchronized boolean readCoil(int position, int unitID) throws PLCCommunicationException {
		PLCConnection plcConnection = null;
		ModbusTransaction transaction;
		ReadCoilsRequest request;
		ReadCoilsResponse res;
		try {
			plcConnection = plcConnectionFactory.obtainPLCConnection();
			plcConnection.open();
			transaction = plcConnection.createTransaction();

		    request = createReadCoilsRequest(position, 1, unitID);
            
            transaction.setRequest(request);
            transaction.execute();
            
            res = (ReadCoilsResponse) transaction.getResponse();
            return res.getCoilStatus(0);
		} catch(PLCConnectionException | ModbusException ex) {
			throw new PLCCommunicationException("Error in communication with PLC", ex);
		} finally {
			if(plcConnection != null) {
				try {
					plcConnection.close();
				} catch (PLCConnectionException ignore) {
				}
			}
		}
	}

    public synchronized boolean[] readCoils(int position, int quantity, int unitID) throws PLCCommunicationException {
		PLCConnection plcConnection = null;
		ModbusTransaction transaction;
		ReadCoilsRequest request;
		ReadCoilsResponse res;
		try {
			plcConnection = plcConnectionFactory.obtainPLCConnection();
			plcConnection.open();
			transaction = plcConnection.createTransaction();
		    
		    boolean[] data = new boolean[quantity];
		    int maxNumber = 100;
		    if(quantity < maxNumber) {
		    	request = createReadCoilsRequest(position, quantity, unitID);
	            
		    	transaction.setRequest(request);
	            transaction.execute();
	            
	            res = (ReadCoilsResponse) transaction.getResponse();
	            for(int i = 0; i < quantity; i++) {
	            	data[i] = res.getCoilStatus(i);
	            }
	            return data;	
		    } else {
		    	for(int offset = 0; offset < quantity; offset = offset + maxNumber) {
		    		int top;
		    		if(offset + maxNumber < quantity) {
		    			top = maxNumber;
		    		} else {
		    			top = quantity - offset;
		    		}
		    		request = createReadCoilsRequest(offset, top, unitID);
		            
			    	transaction.setRequest(request);
		            transaction.execute();
		            
		            res = (ReadCoilsResponse) transaction.getResponse();
		            for(int i = 0; i < top; i++) {
		            	data[offset + i] = res.getCoilStatus(i);
		            }
		    	}
		    	return data;
		    }
		} catch(PLCConnectionException | ModbusException ex) {
			throw new PLCCommunicationException("Error in communication with PLC", ex);
		} finally {
			if(plcConnection != null) {
				try {
					plcConnection.close();
				} catch (PLCConnectionException ignore) {
				}
			}
		}
	}
	
	public synchronized void writeCoil(int position, boolean valueToWrite, int unitID) throws PLCCommunicationException {
		PLCConnection plcConnection = null;
		ModbusTransaction transaction;
		WriteCoilRequest request;
		try {
			plcConnection = plcConnectionFactory.obtainPLCConnection();
			plcConnection.open();
			transaction = plcConnection.createTransaction();
		    		
		    request = createWriteCoilsRequest(position, valueToWrite, unitID);
            
		    transaction.setRequest(request);
            transaction.execute();
		} catch(PLCConnectionException | ModbusException ex) {
			throw new PLCCommunicationException("Error in communication with PLC", ex);
		} finally {
			if(plcConnection != null) {
				try {
					plcConnection.close();
				} catch (PLCConnectionException ignore) {
				}
			}
		}		
	}
	
	public synchronized int readRegister(int position, int unitID) throws PLCCommunicationException {
		PLCConnection plcConnection = null;
		ModbusTransaction transaction;
		ReadMultipleRegistersRequest request;
		ReadMultipleRegistersResponse res;
		try {
			plcConnection = plcConnectionFactory.obtainPLCConnection();
			plcConnection.open();
			transaction = plcConnection.createTransaction();
		    
		    request = createReadRegistersRequest(position, 2, unitID);
            
            transaction.setRequest(request);
            transaction.execute();
            
            res = (ReadMultipleRegistersResponse) transaction.getResponse();
            return res.getRegisterValue(0);
		} catch(PLCConnectionException | ModbusException ex) {
			throw new PLCCommunicationException("Error in communication with PLC", ex);
		} finally {
			if(plcConnection != null) {
				try {
					plcConnection.close();
				} catch (PLCConnectionException ignore) {
				}
			}
		}
	}
	
	public synchronized void writeRegister(int position, int valueToWrite, int unitID) throws PLCCommunicationException {
		PLCConnection plcConnection = null;
		ModbusTransaction transaction;
		WriteSingleRegisterRequest request;
		try {
			plcConnection = plcConnectionFactory.obtainPLCConnection();
			plcConnection.open();
		    transaction = plcConnection.createTransaction();
		    		
		    request = createWriteSingleRegisterRequest(position, valueToWrite, unitID);
            
		    transaction.setRequest(request);
            transaction.execute();
		} catch(PLCConnectionException | ModbusException ex) {
			throw new PLCCommunicationException("Error in communication with PLC", ex);
		} finally {
			if(plcConnection != null) {
				try {
					plcConnection.close();
				} catch (PLCConnectionException ignore) {
				}
			}
		}		
	}
}
