package co.edu.udistrital.service;

import org.springframework.web.context.WebApplicationContext;

import co.edu.udistrital.exception.SpringContextNotInitializedException;

public class ApplicationServices {

	private static WebApplicationContext springContext;
	
	public static void initialize(WebApplicationContext springContext) {
		ApplicationServices.springContext = springContext;
	}

	public static <T> T getBean(String beanName) {
		@SuppressWarnings("unchecked")
        T bean = (T) springContext.getBean(beanName);
		return bean;
	}

	public static <T> T getBean(Class<T> clazz) {
		if(springContext != null) {
			return springContext.getBean(clazz);
		} else {
			throw new SpringContextNotInitializedException("Spring context is not initialized");
		}
	}
	
	@SuppressWarnings("unused")
    private static <T> T getBean(String beanName, Class<T> clazz) {
		return springContext.getBean(beanName, clazz);
	}
	
	public static UserService getUserService() {
		return getBean(UserService.class);
	}
	
	public static ConfigurationService getConfigurationService() {
		return getBean(ConfigurationService.class);
	}

	public static PLCService getPLCService() {
		return getBean(PLCService.class);
	}
	
	public static MeasureService getMeasureService() {
		return getBean(MeasureService.class);
	}
}
