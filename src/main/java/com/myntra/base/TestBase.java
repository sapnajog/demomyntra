package com.myntra.base;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.myntra.utils.App;
import com.myntra.utils.LoggerUtil;

import static com.myntra.base.Keyword.*;

public class TestBase {
	private static final Logger log = (Logger) LoggerUtil.getLogger(TestBase.class);

	/*
	 * @BeforeMethod public void setUp() throws Exception {
	 * openBrowser(App.getBrowserName()); launchUrl(App.getAppUrl("qa"));
	 * log.info("Url is launched.....!");
	 * 
	 * }
	 * 
	 * @AfterMethod public void tearDown() throws Exception { quitBrowser();
	 * log.info("Driver is quit successfully......!"); }
	 */

}
