package co.edu.udistrital.view.configuration;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;

public class NotificationConfigurationDialog extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private NativeButton btnCancel;

	@AutoGenerated
	private NativeButton btnOk;

	@AutoGenerated
	private TextField textField_12;

	@AutoGenerated
	private TextField textField_11;

	@AutoGenerated
	private TextField textField_10;

	@AutoGenerated
	private TextField textField_9;

	@AutoGenerated
	private TextField textField_8;

	@AutoGenerated
	private TextField textField_7;

	@AutoGenerated
	private TextField textField_6;

	@AutoGenerated
	private TextField textField_5;

	@AutoGenerated
	private TextField textField_4;

	@AutoGenerated
	private TextField textField_3;

	@AutoGenerated
	private TextField textField_2;

	@AutoGenerated
	private Label lblAlarm;

	@AutoGenerated
	private TextField textField_1;

	@AutoGenerated
	private Label lblAdvice;

	@AutoGenerated
	private Label label_4;

	@AutoGenerated
	private Label label_2;

	@AutoGenerated
	private Label lblEmailToSend;

	@AutoGenerated
	private Label lblTimeBeforeReplay;

	@AutoGenerated
	private Label lblTimeBeforeSend;

	@AutoGenerated
	private Label lblTemperatureThreshold;

	@AutoGenerated
	private Label lblPressureThreshold;

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
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("540px");
		mainLayout.setHeight("360px");
		
		// top-level component properties
		setWidth("540px");
		setHeight("360px");
		
		// lblPressureThreshold
		lblPressureThreshold = new Label();
		lblPressureThreshold.setImmediate(false);
		lblPressureThreshold.setWidth("160px");
		lblPressureThreshold.setHeight("25px");
		lblPressureThreshold.setValue("Umbral de presión:");
		mainLayout
				.addComponent(lblPressureThreshold, "top:90.0px;left:20.0px;");
		
		// lblTemperatureThreshold
		lblTemperatureThreshold = new Label();
		lblTemperatureThreshold.setImmediate(false);
		lblTemperatureThreshold.setWidth("160px");
		lblTemperatureThreshold.setHeight("25px");
		lblTemperatureThreshold.setValue("Umbral de temperatura:");
		mainLayout.addComponent(lblTemperatureThreshold,
				"top:120.0px;left:20.0px;");
		
		// lblTimeBeforeSend
		lblTimeBeforeSend = new Label();
		lblTimeBeforeSend.setImmediate(false);
		lblTimeBeforeSend.setWidth("160px");
		lblTimeBeforeSend.setHeight("25px");
		lblTimeBeforeSend.setValue("Tiempo antes de enviar:");
		mainLayout.addComponent(lblTimeBeforeSend, "top:160.0px;left:20.0px;");
		
		// lblTimeBeforeReplay
		lblTimeBeforeReplay = new Label();
		lblTimeBeforeReplay.setImmediate(false);
		lblTimeBeforeReplay.setWidth("160px");
		lblTimeBeforeReplay.setHeight("25px");
		lblTimeBeforeReplay.setValue("Tiempo antes de re-enviar:");
		mainLayout
				.addComponent(lblTimeBeforeReplay, "top:190.0px;left:20.0px;");
		
		// lblEmailToSend
		lblEmailToSend = new Label();
		lblEmailToSend.setImmediate(false);
		lblEmailToSend.setWidth("160px");
		lblEmailToSend.setHeight("25px");
		lblEmailToSend.setValue("E-mail de notificación:");
		mainLayout.addComponent(lblEmailToSend, "top:230.0px;left:20.0px;");
		
		// label_2
		label_2 = new Label();
		label_2.setImmediate(false);
		label_2.setWidth("160px");
		label_2.setHeight("25px");
		label_2.setValue("E-mail de notificación:");
		mainLayout.addComponent(label_2, "top:260.0px;left:20.0px;");
		
		// label_4
		label_4 = new Label();
		label_4.setImmediate(false);
		label_4.setWidth("100.0%");
		label_4.setHeight("20px");
		label_4.setValue("<font size=\"4\"><center><b>Configuración de Notificaciones</b></center>");
		label_4.setContentMode(3);
		mainLayout
				.addComponent(label_4, "top:20.0px;right:20.0px;left:20.0px;");
		
		// lblAdvice
		lblAdvice = new Label();
		lblAdvice.setImmediate(false);
		lblAdvice.setWidth("160px");
		lblAdvice.setHeight("25px");
		lblAdvice.setValue("<center>Aviso</center>");
		lblAdvice.setContentMode(3);
		mainLayout.addComponent(lblAdvice, "top:55.0px;left:180.0px;");
		
		// textField_1
		textField_1 = new TextField();
		textField_1.setImmediate(false);
		textField_1.setWidth("160px");
		textField_1.setHeight("25px");
		mainLayout.addComponent(textField_1, "top:90.0px;left:180.0px;");
		
		// lblAlarm
		lblAlarm = new Label();
		lblAlarm.setImmediate(false);
		lblAlarm.setWidth("160px");
		lblAlarm.setHeight("25px");
		lblAlarm.setValue("<center>Alarma</center>");
		lblAlarm.setContentMode(3);
		mainLayout.addComponent(lblAlarm, "top:55.0px;left:360.0px;");
		
		// textField_2
		textField_2 = new TextField();
		textField_2.setImmediate(false);
		textField_2.setWidth("160px");
		textField_2.setHeight("25px");
		mainLayout.addComponent(textField_2, "top:90.0px;left:360.0px;");
		
		// textField_3
		textField_3 = new TextField();
		textField_3.setImmediate(false);
		textField_3.setWidth("160px");
		textField_3.setHeight("25px");
		mainLayout.addComponent(textField_3, "top:120.0px;left:180.0px;");
		
		// textField_4
		textField_4 = new TextField();
		textField_4.setImmediate(false);
		textField_4.setWidth("160px");
		textField_4.setHeight("25px");
		mainLayout.addComponent(textField_4, "top:120.0px;left:360.0px;");
		
		// textField_5
		textField_5 = new TextField();
		textField_5.setImmediate(false);
		textField_5.setWidth("160px");
		textField_5.setHeight("25px");
		mainLayout.addComponent(textField_5, "top:160.0px;left:180.0px;");
		
		// textField_6
		textField_6 = new TextField();
		textField_6.setImmediate(false);
		textField_6.setWidth("160px");
		textField_6.setHeight("25px");
		mainLayout.addComponent(textField_6, "top:160.0px;left:360.0px;");
		
		// textField_7
		textField_7 = new TextField();
		textField_7.setImmediate(false);
		textField_7.setWidth("160px");
		textField_7.setHeight("25px");
		mainLayout.addComponent(textField_7, "top:190.0px;left:180.0px;");
		
		// textField_8
		textField_8 = new TextField();
		textField_8.setImmediate(false);
		textField_8.setWidth("160px");
		textField_8.setHeight("25px");
		mainLayout.addComponent(textField_8, "top:190.0px;left:360.0px;");
		
		// textField_9
		textField_9 = new TextField();
		textField_9.setImmediate(false);
		textField_9.setWidth("160px");
		textField_9.setHeight("25px");
		mainLayout.addComponent(textField_9, "top:230.0px;left:180.0px;");
		
		// textField_10
		textField_10 = new TextField();
		textField_10.setImmediate(false);
		textField_10.setWidth("160px");
		textField_10.setHeight("25px");
		mainLayout.addComponent(textField_10, "top:230.0px;left:360.0px;");
		
		// textField_11
		textField_11 = new TextField();
		textField_11.setImmediate(false);
		textField_11.setWidth("160px");
		textField_11.setHeight("25px");
		mainLayout.addComponent(textField_11, "top:260.0px;left:180.0px;");
		
		// textField_12
		textField_12 = new TextField();
		textField_12.setImmediate(false);
		textField_12.setWidth("160px");
		textField_12.setHeight("25px");
		mainLayout.addComponent(textField_12, "top:260.0px;left:360.0px;");
		
		// btnOk
		btnOk = new NativeButton();
		btnOk.setCaption("Aceptar");
		btnOk.setImmediate(false);
		btnOk.setWidth("120px");
		btnOk.setHeight("38px");
		mainLayout.addComponent(btnOk, "top:300.0px;left:130.0px;");
		
		// btnCancel
		btnCancel = new NativeButton();
		btnCancel.setCaption("Cancelar");
		btnCancel.setImmediate(true);
		btnCancel.setWidth("120px");
		btnCancel.setHeight("38px");
		mainLayout.addComponent(btnCancel, "top:300.0px;left:290.0px;");
		
		return mainLayout;
	}

}
