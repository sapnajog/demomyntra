package com.myntra.utils;

import static com.myntra.base.Keyword.driver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;

public class ScreenshotFor {
	private static final Logger log = (Logger) LoggerUtil.getLogger(ScreenshotFor.class);
	public static void takeScreenshot(String testName) {
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("screenshots/" + testName + ".png");
		log.info("Taking screenshot for test: " + testName);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
