package com.myntra.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

public class PropUtils {
	private static final Logger log = (Logger) LoggerUtil.getLogger(PropUtils.class);

	public String getProperty(String filePath, String Key) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		String value = null;

		Properties prop = new Properties();
		prop.load(fis);

		value = prop.getProperty(Key);
		return value;

	}

	public static void main(String[] args) throws IOException {
		String value = new PropUtils().getProperty("./src/main/resources/App.properties", "browser.name");
		log.info("BrowserName : " + value);
	}

}
