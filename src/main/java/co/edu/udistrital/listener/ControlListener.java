package co.edu.udistrital.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import co.edu.udistrital.controller.MeasureController;
import co.edu.udistrital.plc.connection.impl.DummyPLCConnection;
import co.edu.udistrital.service.ApplicationServices;

public class ControlListener implements ServletContextListener {

	private static final String PRESSURE_CONTROLLER_BEAN = "pressureController";
	private static final String TEMPERATURE_CONTROLLER_BEAN = "temperatureController";
	
	private MeasureController pressureController;
	private MeasureController temperatureController;
	
	@Override
    public void contextInitialized(ServletContextEvent event) {
        DummyPLCConnection.startReadingData();
		pressureController = ApplicationServices.getBean(PRESSURE_CONTROLLER_BEAN);
		pressureController.startMonitoring();
		temperatureController = ApplicationServices.getBean(TEMPERATURE_CONTROLLER_BEAN);
		temperatureController.startMonitoring();
    }

	@Override
    public void contextDestroyed(ServletContextEvent sce) {
        DummyPLCConnection.stopReadingData();
		pressureController.stopMonitoring();
		temperatureController.stopMonitoring();
    }
}