package co.edu.udistrital.notification;

public interface NotificationSender {
	
	void sendNotification(String subject, String message, String destination);
}
