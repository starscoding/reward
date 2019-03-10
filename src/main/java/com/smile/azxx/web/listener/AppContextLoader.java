package com.smile.azxx.web.listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class AppContextLoader {
	private static final Logger logger = LoggerFactory
			.getLogger(AppContextLoader.class);

	private static AppContextLoader instance;

	private ApplicationContext sc;

	private AppContextLoader() {
		logger.info("Init ServletContextLoader");
	}

	public static AppContextLoader getInstance() {
		if (instance == null) {
			instance = new AppContextLoader();
		}
		return instance;
	}

	public ApplicationContext getApplicationContext() {
		return sc;
	}

	public void setApplicationContext(ApplicationContext sc) {
		this.sc = sc;
	}
	
	public static Object getServiceByName(String name){
		return getInstance().getApplicationContext().getBean(name);
	}
}
