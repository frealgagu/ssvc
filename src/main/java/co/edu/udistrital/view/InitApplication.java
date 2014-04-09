package co.edu.udistrital.view;

import co.edu.udistrital.view.configuration.ConfigurationWindow;
import co.edu.udistrital.view.control.ControlWindow;
import co.edu.udistrital.view.memory.MemoryMapWindow;
import co.edu.udistrital.view.status.CurrentStatusWindow;
import co.edu.udistrital.view.status.LastRecordsWindow;
import com.vaadin.Application;
import com.vaadin.ui.*;

/**
 * Main application class.
 */
public class InitApplication extends Application {

	private static final long serialVersionUID = -2298393757220300723L;
	
	public static final int UNIT_ID = 1;

	private Window main;
	private VerticalLayout mainLayout;

	private LoginWindow loginWindow;
	private ControlWindow controlWindow;
	private ConfigurationWindow configurationWindow;
	private CurrentStatusWindow currentStatusWindow;
	private LastRecordsWindow lastRecordsWindow;
	private MemoryMapWindow memoryMapWindow;
	
	@Override
	public void init() {
		setTheme("plc");
		main = new Window("PLC");
		mainLayout = new VerticalLayout();
		main.setContent(mainLayout);
		setMainWindow(main);
        login();
	}
	
	public void login() {
		mainLayout.removeAllComponents();
		if(loginWindow == null) {
			loginWindow = new LoginWindow(this);
		}
		mainLayout.addComponent(loginWindow);
		mainLayout.setComponentAlignment(loginWindow, Alignment.MIDDLE_CENTER);
	}
	
	public void showControlWindow() {
		mainLayout.removeAllComponents();
		if(controlWindow == null) {
			controlWindow = new ControlWindow(this);
		}
		mainLayout.addComponent(controlWindow);
		mainLayout.setComponentAlignment(controlWindow, Alignment.TOP_CENTER);
	}
	
	public void showConfigurationWindow() {
		mainLayout.removeAllComponents();
		if(configurationWindow == null) {
			configurationWindow = new ConfigurationWindow(this);
		}
		mainLayout.addComponent(configurationWindow);
		mainLayout.setComponentAlignment(configurationWindow, Alignment.TOP_CENTER);
	}
	
	public void showCurrentStatusWindow() {
		mainLayout.removeAllComponents();
		if(currentStatusWindow == null) {
			currentStatusWindow = new CurrentStatusWindow(this);
		}
		mainLayout.addComponent(currentStatusWindow);
		mainLayout.setComponentAlignment(currentStatusWindow, Alignment.TOP_CENTER);
	}	
	
	public void showLastRecordsWindow() {
		mainLayout.removeAllComponents();
		if(lastRecordsWindow == null) {
			lastRecordsWindow = new LastRecordsWindow(this);
		}
		mainLayout.addComponent(lastRecordsWindow);
		mainLayout.setComponentAlignment(lastRecordsWindow, Alignment.TOP_CENTER);
	}	
	
	public void showMemoryMapWindow() {
		mainLayout.removeAllComponents();
		if(memoryMapWindow == null) {
			memoryMapWindow = new MemoryMapWindow(this);
		}
		mainLayout.addComponent(memoryMapWindow);
		mainLayout.setComponentAlignment(memoryMapWindow, Alignment.TOP_CENTER);
	}
}

