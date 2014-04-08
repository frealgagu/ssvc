package co.edu.udistrital.view;

import co.edu.udistrital.domain.user.User;
import co.edu.udistrital.exception.UserNotFoundException;
import co.edu.udistrital.service.ApplicationServices;
import co.edu.udistrital.service.UserService;

import com.vaadin.ui.LoginForm;
import com.vaadin.ui.Window.Notification;

public class LoginWindow extends LoginForm {

	private static final long serialVersionUID = 383056138303136389L;	
	
	private static final String USERNAME_KEY = "username";
	private static final String PASSWORD_KEY = "password";
	
	private final InitApplication initApplication;

	public LoginWindow(InitApplication initApplication) {
		this.initApplication = initApplication;
		setWidth("140px");
		setHeight("120px");
		setUsernameCaption("Usuario:");
		setPasswordCaption("Contrase\u00F1a:");
		setLoginButtonCaption("Ingresar");
		addListener(new LoginListener() {
			
			private static final long serialVersionUID = 6501818732904398089L;

			@Override
			public void onLogin(LoginEvent event) {
				validateLogin(event.getLoginParameter(USERNAME_KEY), event.getLoginParameter(PASSWORD_KEY));
			}
		});
	}
	
	protected void validateLogin(String username, String password) {
		try {
			UserService userService = ApplicationServices.getUserService();
			User user = userService.retrieveUserByUsername(username);
			if(user.getPassword().equals(password)) {
				initApplication.showCurrentStatusWindow();
			} else {
				Notification notification = new Notification("Usuario o contrase\u00F1a incorrecto");
				notification.setDelayMsec(0);
				notification.setStyleName("error");
				getWindow().showNotification(notification);
			}
		} catch (UserNotFoundException ex) {
			Notification notification = new Notification("Usuario o contrase\u00F1a incorrecto");
			notification.setDelayMsec(0);
			notification.setStyleName("error");
			getWindow().showNotification(notification);
		}
	}
}
