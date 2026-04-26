package com.myntra.stepdefinitions;

import static com.myntra.base.Keyword.driver;
import static com.myntra.base.Keyword.launchUrl;
import static com.myntra.base.Keyword.openBrowser;
import static com.myntra.base.Keyword.quitBrowser;

import org.apache.logging.log4j.Logger;

import com.myntra.utils.App;
import com.myntra.utils.LoggerUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * This class contains all the home page related steps
 * 
 * @author Sapna Jogdand
 */
public class Hooks {

	private static final Logger log = (Logger) LoggerUtil.getLogger(Hooks.class);

	@Before
	public void setUp() throws Exception {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception ignored) {
			}
			driver = null;
		}
		openBrowser(App.getBrowserName());
		launchUrl(App.getAppUrl("qa"));
		log.info("Url is launched.....!");
	}

	@After
	public void tearDown() throws Exception {
		quitBrowser();
		driver = null;
		log.info("Driver is quit successfully......!");
	}

}
