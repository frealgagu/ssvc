package co.edu.udistrital.view.configuration;

import co.edu.udistrital.service.ApplicationServices;
import co.edu.udistrital.service.ConfigurationService;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.*;

public class NotificationConfigurationDialog extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private NativeButton btnCancel;

	@AutoGenerated
	private NativeButton btnOk;

    @AutoGenerated
    private TextField txtTimeBeforeReplayAlarm;

    @AutoGenerated
    private TextField txtTimeBeforeSendAlarm;

	@AutoGenerated
	private TextField txtSMSToSendAlarm;

	@AutoGenerated
	private TextField txtEmailToSendAlarm;

    @AutoGenerated
    private Label lblTimeBeforeReplay;

    @AutoGenerated
    private Label lblTimeBeforeSend;

    @AutoGenerated
    private Label lblSMSToSend;

    @AutoGenerated
    private Label lblEmailToSend;

	@AutoGenerated
	private Label lblTitle;

	private static final long serialVersionUID = -4830893180862135404L;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public NotificationConfigurationDialog() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
        initialize();
	}

    private void initialize() {
        btnOk.addListener(new Button.ClickListener() {

            private static final long serialVersionUID = -8577869814107324983L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                ok();
            }
        });
        btnCancel.addListener(new Button.ClickListener() {

            private static final long serialVersionUID = -4972802977202955811L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                cancel();
            }
        });

        ConfigurationService configurationService = ApplicationServices.getConfigurationService();
        txtTimeBeforeSendAlarm.setValue(configurationService.getAlarmTimeBeforeSending());
        txtTimeBeforeReplayAlarm.setValue(configurationService.getAlarmTimeBeforeReply());
        txtEmailToSendAlarm.setValue(configurationService.getEmailOnAlarm());
        txtSMSToSendAlarm.setValue(configurationService.getSmsOnAlarm());
    }

    private void ok() {
        ConfigurationService configurationService = ApplicationServices.getConfigurationService();
        if(txtTimeBeforeSendAlarm.getValue() instanceof Integer) {
            configurationService.setAlarmTimeBeforeSending((Integer)txtTimeBeforeSendAlarm.getValue());
        } else {
            try {
                configurationService.setAlarmTimeBeforeSending(Integer.parseInt(String.valueOf(txtTimeBeforeSendAlarm.getValue())));
            } catch(NumberFormatException ignore) {
                configurationService.setAlarmTimeBeforeSending(0);
            }
        }
        if(txtTimeBeforeReplayAlarm.getValue() instanceof Integer) {
            configurationService.setAlarmTimeBeforeReply((Integer)txtTimeBeforeReplayAlarm.getValue());
        } else {
            try {
                configurationService.setAlarmTimeBeforeReply(Integer.parseInt(String.valueOf(txtTimeBeforeReplayAlarm.getValue())));
            } catch(NumberFormatException ignore) {
                configurationService.setAlarmTimeBeforeReply(0);
            }
        }
        if(txtEmailToSendAlarm.getValue() != null) {
            configurationService.setEmailOnAlarm(String.valueOf(txtEmailToSendAlarm.getValue()));
        } else {
            configurationService.setEmailOnAlarm("");
        }
        if(txtSMSToSendAlarm.getValue() != null) {
            configurationService.setSmsOnAlarm(String.valueOf(txtSMSToSendAlarm.getValue()));
        } else {
            configurationService.setSmsOnAlarm("");
        }

        if(getWindow() != null && getWindow().getParent() != null) {
            getWindow().getParent().removeWindow(getWindow());
        }
    }

    private void cancel() {
        if(getWindow() != null && getWindow().getParent() != null) {
            getWindow().getParent().removeWindow(getWindow());
        }
    }

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("360px");
		mainLayout.setHeight("250px");
		
		// top-level component properties
		setWidth("360px");
		setHeight("250px");

        // lblTitle
        lblTitle = new Label();
        lblTitle.setImmediate(false);
        lblTitle.setWidth("100.0%");
        lblTitle.setHeight("20px");
        lblTitle.setValue("<font size=\"4\"><center><b>Configuraci\u00F3n de Notificaciones</b></center>");
        lblTitle.setContentMode(3);
        mainLayout.addComponent(lblTitle, "top:20.0px;right:20.0px;left:20.0px;");

        // lblEmailToSend
        lblEmailToSend = new Label();
        lblEmailToSend.setImmediate(false);
        lblEmailToSend.setWidth("160px");
        lblEmailToSend.setHeight("25px");
        lblEmailToSend.setValue("E-mail de notificaci\u00F3n:");
        mainLayout.addComponent(lblEmailToSend, "top:60.0px;left:20.0px;");

        // lblSMSToSend
        lblSMSToSend = new Label();
        lblSMSToSend.setImmediate(false);
        lblSMSToSend.setWidth("160px");
        lblSMSToSend.setHeight("25px");
        lblSMSToSend.setValue("SMS de notificaci\u00F3n:");
        mainLayout.addComponent(lblSMSToSend, "top:90.0px;left:20.0px;");

		// lblTimeBeforeSend
		lblTimeBeforeSend = new Label();
		lblTimeBeforeSend.setImmediate(false);
		lblTimeBeforeSend.setWidth("160px");
		lblTimeBeforeSend.setHeight("25px");
		lblTimeBeforeSend.setValue("Tiempo antes de enviar:");
		mainLayout.addComponent(lblTimeBeforeSend, "top:120.0px;left:20.0px;");

        // lblTimeBeforeReplay
        lblTimeBeforeReplay = new Label();
        lblTimeBeforeReplay.setImmediate(false);
        lblTimeBeforeReplay.setWidth("160px");
        lblTimeBeforeReplay.setHeight("25px");
        lblTimeBeforeReplay.setValue("Tiempo antes de re-enviar:");
        mainLayout.addComponent(lblTimeBeforeReplay, "top:150.0px;left:20.0px;");

        // txtEmailToSendAlarm
        txtEmailToSendAlarm = new TextField();
        txtEmailToSendAlarm.setImmediate(false);
        txtEmailToSendAlarm.setWidth("100%");
        txtEmailToSendAlarm.setHeight("25px");
        mainLayout.addComponent(txtEmailToSendAlarm, "top:60.0px;left:190.0px;right:20px");

        // txtSMSToSendAlarm
        txtSMSToSendAlarm = new TextField();
        txtSMSToSendAlarm.setImmediate(false);
        txtSMSToSendAlarm.setWidth("100%");
        txtSMSToSendAlarm.setHeight("25px");
        mainLayout.addComponent(txtSMSToSendAlarm, "top:90.0px;left:190.0px;right:20");

        // txtTimeBeforeSendAlarm
        txtTimeBeforeSendAlarm = new TextField();
        txtTimeBeforeSendAlarm.setImmediate(false);
        txtTimeBeforeSendAlarm.setWidth("100%");
        txtTimeBeforeSendAlarm.setHeight("25px");
        mainLayout.addComponent(txtTimeBeforeSendAlarm, "top:120.0px;left:190.0px;right:20");

        // txtTimeBeforeReplayAlarm
        txtTimeBeforeReplayAlarm = new TextField();
        txtTimeBeforeReplayAlarm.setImmediate(false);
        txtTimeBeforeReplayAlarm.setWidth("100%");
        txtTimeBeforeReplayAlarm.setHeight("25px");
        mainLayout.addComponent(txtTimeBeforeReplayAlarm, "top:150.0px;left:190.0px;right:20");

		// btnOk
		btnOk = new NativeButton();
		btnOk.setCaption("Aceptar");
		btnOk.setImmediate(false);
		btnOk.setWidth("120px");
		btnOk.setHeight("38px");
		mainLayout.addComponent(btnOk, "top:192.0px;left:40.0px;");
		
		// btnCancel
		btnCancel = new NativeButton();
		btnCancel.setCaption("Cancelar");
		btnCancel.setImmediate(true);
		btnCancel.setWidth("120px");
		btnCancel.setHeight("38px");
		mainLayout.addComponent(btnCancel, "top:192.0px;left:200.0px;");
		
		return mainLayout;
	}

}
