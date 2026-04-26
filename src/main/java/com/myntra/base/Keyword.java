package com.myntra.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.myntra.exceptions.InvalidBrowserNameException;
import com.myntra.utils.LoggerUtil;

/**
 * This class contains all reusable actions.
 * @author Sapna Jogdand
 */
public class Keyword {
	private static final Logger log = (Logger) LoggerUtil.getLogger(Keyword.class);

	public static RemoteWebDriver driver;
	public static void openBrowser(String browserName) {
		 if(browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		}else {
			throw new InvalidBrowserNameException(browserName);
		}
	}
	
	public static void launchUrl(String url) {
		driver.get(url);
		log.info("The launched Url : " + url);
		driver.manage().window().maximize();
		
	}
	
	public static void enterText(String locatorType,String locatorValue,String textToEnter){
		if(locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locatorValue)).sendKeys(textToEnter);
		}else if(locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locatorValue)).sendKeys(textToEnter);
		}else if(locatorType.equalsIgnoreCase("className")) {
			driver.findElement(By.className(locatorValue)).sendKeys(textToEnter);
		}else if(locatorType.equalsIgnoreCase("tagName")) {
			driver.findElement(By.tagName(locatorValue)).sendKeys(textToEnter);
		}else if(locatorType.equalsIgnoreCase("linkText")) {
			driver.findElement(By.linkText(locatorValue)).sendKeys(textToEnter);
		}else if(locatorType.equalsIgnoreCase("partialLinkText")) {
			driver.findElement(By.partialLinkText(locatorValue)).sendKeys(textToEnter);
		}else if(locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locatorValue)).sendKeys(textToEnter);
		}else if(locatorType.equalsIgnoreCase("cssSelector")) {
			driver.findElement(By.cssSelector(locatorValue)).sendKeys(textToEnter);
		}else {
			throw new InvalidSelectorException(locatorType);
		}
	}
	
 public static void clickOn(String locatorType,String locatorValue) {
		
		if(locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locatorValue)).click();
		}else if(locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locatorValue)).click();
		}else if(locatorType.equalsIgnoreCase("className")) {
			driver.findElement(By.className(locatorValue)).click();
		}else if(locatorType.equalsIgnoreCase("tagName")) {
			driver.findElement(By.tagName(locatorValue)).click();
		}else if(locatorType.equalsIgnoreCase("linkText")) {
			driver.findElement(By.linkText(locatorValue)).click();
		}else if(locatorType.equalsIgnoreCase("partialLinkText")) {
			driver.findElement(By.partialLinkText(locatorValue)).click();
		}else if(locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locatorValue)).click();
		}else if(locatorType.equalsIgnoreCase("cssSelector")) {
			driver.findElement(By.cssSelector(locatorValue)).click();
		}else {
			throw new InvalidSelectorException(locatorType);
		}

	}
		
 public static void clear(String locatorType,String locatorValue) {
		if(locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locatorValue)).clear();
		}else if(locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locatorValue)).clear();
		}else if(locatorType.equalsIgnoreCase("className")) {
			driver.findElement(By.className(locatorValue)).clear();
		}else if(locatorType.equalsIgnoreCase("tagName")) {
			driver.findElement(By.tagName(locatorValue)).clear();
		}else if(locatorType.equalsIgnoreCase("linkText")) {
			driver.findElement(By.linkText(locatorValue)).clear();
		}else if(locatorType.equalsIgnoreCase("partialLinkText")) {
			driver.findElement(By.partialLinkText(locatorValue)).clear();
		}else if(locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locatorValue)).clear();
		}else if(locatorType.equalsIgnoreCase("cssSelector")) {
			driver.findElement(By.cssSelector(locatorValue)).clear();
		}else {
			throw new InvalidSelectorException(locatorType);
		}
		
	}

 public static String getText(String locatorType,String locatorValue) {
		String text = "";
		if(locatorType.equalsIgnoreCase("id")) {
			text = driver.findElement(By.id(locatorValue)).getText();
		}else if(locatorType.equalsIgnoreCase("name")) {
			text = driver.findElement(By.name(locatorValue)).getText();
		}else if(locatorType.equalsIgnoreCase("className")) {
			text = driver.findElement(By.className(locatorValue)).getText();
		}else if(locatorType.equalsIgnoreCase("tagName")) {
			text = driver.findElement(By.tagName(locatorValue)).getText();
		}else if(locatorType.equalsIgnoreCase("linkText")) {
			text = driver.findElement(By.linkText(locatorValue)).getText();
		}else if(locatorType.equalsIgnoreCase("partialLinkText")) {
			text = driver.findElement(By.partialLinkText(locatorValue)).getText();
		}else if(locatorType.equalsIgnoreCase("xpath")) {
			text = driver.findElement(By.xpath(locatorValue)).getText();
		}else if(locatorType.equalsIgnoreCase("cssSelector")) {
			text = driver.findElement(By.cssSelector(locatorValue)).getText();
		}else {
			throw new InvalidSelectorException(locatorType);
		}
		
		return text;
		
	}
 
 

 public static void quitBrowser() {
     if (driver != null) {
         driver.quit();
         driver = null;   
     }
 }

}
