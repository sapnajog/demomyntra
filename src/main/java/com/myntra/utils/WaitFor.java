package com.myntra.utils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.myntra.base.Keyword.*;

public class WaitFor {
	
	static WebDriverWait wait;
	
	/** This static block is used to initialize the WebDriverWait object and set 
	 * the polling time and ignoring exception Static block will execute only once 
	 * when the class is loaded in the memory
	 */
	static {
		 
		wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.pollingEvery(Duration.ofMillis(500));
		wait.ignoring(NoSuchElementException.class);
	}
	
  /**
   * Private constructor restrict to create the object
   */
	private WaitFor() {
		
	}
	
	public static void waitForSeconds(int seconds) {
	    try {
	        Thread.sleep(seconds * 1000);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}
	
	public static void waitBeforeQuit() {
		
		
	}
 
	public static void elementToBeVisible(By element) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	}
	public static void elementToBeVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void elementToBeClickable(By element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static void elementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
    public static void elementToBePresent(By element) {
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}
	public static void stalenessOfElement(WebElement element) {
		wait.until(ExpectedConditions.stalenessOf(element));
	}

	public static void visibilityOfElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
		
	public static void presenceOfElementLocated(By element) {
	 wait.until(ExpectedConditions.presenceOfElementLocated(element));
		
	}

	public static void elementToBeVisible(List<WebElement> elements) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		
		
	}

	public static void visibilityOfElement(List<WebElement> elements) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		
	}

	public static void pageToLoad() {
		wait.until(ExpectedConditions.urlContains("myntra.com"));
		
	}

	public static void waitForListToLoad(List<WebElement> productList) {
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//li[contains(@class,'product-base')]"), 0
));

		
	}
	
	public static List<WebElement> waitForListToLoadBy(By productList) {
		return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//li[contains(@class,'product-base')]"), 0
));

		
	}


	public static void waitForListToLoad(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}

	
	
}
