package co.edu.udistrital.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import co.edu.udistrital.controller.MeassureController;
import co.edu.udistrital.plc.connection.impl.DummyPLCConnection;
import co.edu.udistrital.service.ApplicationServices;

public class ControlListener implements ServletContextListener {

	private static final String PRESSION_CONTROLLER_BEAN = "pressionController";
	private static final String TEMPERATURE_CONTROLLER_BEAN = "temperatureController";
	
	private MeassureController pressionController;
	private MeassureController temperatureController;
	
	@Override
    public void contextInitialized(ServletContextEvent event) {
        DummyPLCConnection.startRandom();
		pressionController = ApplicationServices.getBean(PRESSION_CONTROLLER_BEAN);
		pressionController.startMonitoring();
		temperatureController = ApplicationServices.getBean(TEMPERATURE_CONTROLLER_BEAN);
		temperatureController.startMonitoring();
    }

	@Override
    public void contextDestroyed(ServletContextEvent sce) {
        DummyPLCConnection.stopRandom();
		pressionController.stopMonitoring();
		temperatureController.stopMonitoring();
    }
}