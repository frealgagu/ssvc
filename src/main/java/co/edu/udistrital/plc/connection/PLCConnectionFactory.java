package co.edu.udistrital.plc.connection;

import co.edu.udistrital.exception.PLCConnectionException;

public interface PLCConnectionFactory {

	PLCConnection obtainPLCConnection() throws PLCConnectionException;
}