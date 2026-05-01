package com.myntra.utils;

import java.io.IOException;

import org.apache.logging.log4j.Logger;

public class App {
	private static final Logger log = (Logger) LoggerUtil.getLogger(App.class);
	private static final String filePath = "./src/main/resources/App.properties";

	private App() {
	}

	public static String getBrowserName() {
		String browserName = null;
		PropUtils prop = new PropUtils();

		try {

			browserName = prop.getProperty(filePath, "browser.name");
			log.info("browserName ::" + browserName);
		}

		catch (IOException e) {

		}
		return browserName;

	}

	public static String getAppUrl(String env) {
		PropUtils prop = new PropUtils();
		String appUrl = null;

		try {
			appUrl = prop.getProperty(filePath, "app." + env + ".url");
			log.info("App url is : " + appUrl);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return appUrl;
	}

}
