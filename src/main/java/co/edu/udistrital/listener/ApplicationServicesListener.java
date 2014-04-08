package co.edu.udistrital.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.edu.udistrital.service.ApplicationServices;

public class ApplicationServicesListener implements ServletContextListener {

	@Override
    public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		ApplicationServices.initialize(springContext);
    }

	@Override
    public void contextDestroyed(ServletContextEvent sce) {
		ApplicationServices.initialize(null);
    }
}
