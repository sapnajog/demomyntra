package com.myntra.listeners;

import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.myntra.utils.LoggerUtil;
import com.myntra.utils.ScreenshotFor;
/**
 * This class contains Listeners which is used to handle test execution events like pass,fail,and skip.
 * Used for logging,reporting,and capturing screenshots on failures.
 * @author Sapna Jogdand
 */
public class MyListeners implements ITestListener {
	private static final Logger log = (Logger) LoggerUtil.getLogger(MyListeners.class);

	@Override
	public void onTestStart(ITestResult result) {
		log.info("Test started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("Test passed: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("Test failed: " + result.getName());
		ScreenshotFor.takeScreenshot(result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info("Test skipped: " + result.getName());
	}

}
