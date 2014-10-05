package co.edu.udistrital.view;

import co.edu.udistrital.exception.PLCCommunicationException;
import co.edu.udistrital.service.ApplicationServices;
import co.edu.udistrital.service.ConfigurationService;
import co.edu.udistrital.service.MeasureService;
import co.edu.udistrital.service.PLCService;
import co.edu.udistrital.view.configuration.ConfigurationWindow;
import co.edu.udistrital.view.control.ControlWindow;
import co.edu.udistrital.view.memory.MemoryMapWindow;
import co.edu.udistrital.view.status.CurrentStatusWindow;
import co.edu.udistrital.view.status.LastRecordsWindow;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.Application;
import com.vaadin.ui.*;
import org.apache.commons.lang.ObjectUtils;

import java.math.BigDecimal;

/**
 * Main application class.
 */
public class InitApplication extends Application implements Refresher.RefreshListener {

	private static final long serialVersionUID = -2298393757220300723L;
	
	public static final int UNIT_ID = 1;

	private Window main;
	private VerticalLayout mainLayout;

    private CustomComponent currentComponent;

    private LoginWindow loginWindow;
    private ControlWindow controlWindow;
    private ConfigurationWindow configurationWindow;
    private CurrentStatusWindow currentStatusWindow;
    private LastRecordsWindow lastRecordsWindow;
    private MemoryMapWindow memoryMapWindow;

    private String pressureText;
    private String temperatureText;
    private Label notificationLabel;

    private Refresher refresher;

	@Override
	public void init() {
		setTheme("plc");

		main = new Window("SSVC");
		mainLayout = new VerticalLayout();

		main.setContent(mainLayout);
		setMainWindow(main);

        pressureText = "<p align=\"left\"><font color=#FF0000 size=\"4\"></font></p>";
        temperatureText = "<p align=\"right\"><font color=#FF0000 size=\"4\"></font></p>";
        notificationLabel = new Label(pressureText + temperatureText);
        notificationLabel.setContentMode(Label.CONTENT_XHTML);

        mainLayout.addComponent(notificationLabel);

        refresher = new Refresher();
        refresher.setImmediate(false);
        refresher.setWidth("-1px");
        refresher.setHeight("-1px");
        refresher.setRefreshInterval(2000);
        refresher.addListener(this);

        mainLayout.addComponent(refresher);

        login();
	}

	public void login() {
        removeCurrentComponent();
        currentComponent = loginWindow != null ? loginWindow : (loginWindow = new LoginWindow(this));
        addCurrentComponent();
	}
	
	public void showControlWindow() {
        removeCurrentComponent();
        currentComponent = controlWindow != null ? controlWindow : (controlWindow = new ControlWindow(this));
        addCurrentComponent();
	}
	
	public void showConfigurationWindow() {
        removeCurrentComponent();
        currentComponent = configurationWindow != null ? configurationWindow : (configurationWindow = new ConfigurationWindow(this));
        addCurrentComponent();
	}
	
	public void showCurrentStatusWindow() {
        removeCurrentComponent();
        currentComponent = currentStatusWindow != null ? currentStatusWindow : (currentStatusWindow = new CurrentStatusWindow(this));
        addCurrentComponent();
	}	
	
	public void showLastRecordsWindow() {
        removeCurrentComponent();
        currentComponent = lastRecordsWindow != null ? lastRecordsWindow : (lastRecordsWindow = new LastRecordsWindow(this));
        addCurrentComponent();
	}	
	
	public void showMemoryMapWindow() {
        removeCurrentComponent();
        currentComponent = memoryMapWindow != null ? memoryMapWindow : (memoryMapWindow = new MemoryMapWindow(this));
        addCurrentComponent();
	}

    private void removeCurrentComponent() {
        if(currentComponent != null) {
            mainLayout.removeComponent(currentComponent);
        }
    }

    private void addCurrentComponent() {
        mainLayout.addComponent(currentComponent, 0);
        mainLayout.setComponentAlignment(currentComponent, Alignment.TOP_CENTER);
    }

    @Override
    public void refresh(Refresher source) {
        if(mainLayout.isVisible()) {
            source.attach();
            update();
        } else {
            source.detach();
        }
    }

    private void update() {
        MeasureService measureService = ApplicationServices.getMeasureService();
        try {
            BigDecimal pressureRegister = measureService.retrieveLastPressureSecondInterval().getValue();
            checkPressureNotification(pressureRegister);
            BigDecimal temperatureRegister = measureService.retrieveLastTemperatureSecondInterval().getValue();
            checkTemperatureNotification(temperatureRegister);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void checkPressureNotification(BigDecimal pressureRegisterValue) {
        ConfigurationService configurationService = ApplicationServices.getConfigurationService();
        PLCService plcService = ApplicationServices.getPLCService();
        try {
            int pressureAlarm = plcService.readRegister(configurationService.getPressureAlarmRegister(), UNIT_ID);
            BigDecimal pressureAlarmValue = BigDecimal.valueOf(pressureAlarm).divide(BigDecimal.TEN, 1, BigDecimal.ROUND_HALF_UP);
            if (pressureRegisterValue.compareTo(pressureAlarmValue) > 0) {
                pressureText = "<center><font color=#FF0000 size=\"5\">La <strong>presi\u00F3n</strong> ha superado el nivel de<br/><strong>alerta</strong></font></center>";
                updateNotificationLabel();
            } else {
                pressureText = "<center><font color=#000000 size=\"5\"></font></center>";
                updateNotificationLabel();
            }
        } catch (PLCCommunicationException ex) {
            pressureText = "<center><font color=#FF0000 size=\"5\"><strong>Ha ocurrido un error conectando con el PLC</strong></font></center>";
        }
    }

    private void checkTemperatureNotification(BigDecimal temperatureRegisterValue) {
        ConfigurationService configurationService = ApplicationServices.getConfigurationService();
        PLCService plcService = ApplicationServices.getPLCService();
        try {
            int temperatureAlarm = plcService.readRegister(configurationService.getTemperatureAlarmRegister(), UNIT_ID);
            BigDecimal temperatureAlarmValue = BigDecimal.valueOf(temperatureAlarm).divide(BigDecimal.TEN, 1, BigDecimal.ROUND_HALF_UP);
            if (temperatureRegisterValue.compareTo(temperatureAlarmValue) > 0) {
                temperatureText = "<center><font color=#FF0000 size=\"5\">La <strong>temperatura</strong> ha superado el nivel de<br/><strong>alerta</strong></font></center>";
                updateNotificationLabel();
            } else {
                temperatureText = "<center><font color=#000000 size=\"5\"></font></center>";
                updateNotificationLabel();
            }
        } catch (PLCCommunicationException ex) {
            temperatureText = "<center><font color=#FF0000 size=\"5\"><strong>Ha ocurrido un error conectando con el PLC</strong></font></center>";
        }
    }

    private void updateNotificationLabel() {
        if(ObjectUtils.equals(loginWindow, currentComponent)) {
            pressureText = "<center><font color=#000000 size=\"5\"><br/></font></center>";
            temperatureText = "<center><font color=#FF0000 size=\"5\"><br/></font></center>";
        } else {
            if(pressureText == null) {
                pressureText = "<center><font color=#000000 size=\"5\"><br/></font></center>";
            }
            if(temperatureText == null) {
                temperatureText = "<center><font color=#FF0000 size=\"4\"><br/></font></center>";
            }
        }
        notificationLabel.setValue("<table width=\"100%\"><tr><td width=\"50%\">" + pressureText + "</td><td width=\"50%\">" + temperatureText + "</td></tr></table>");
    }

}

