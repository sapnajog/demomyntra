package com.myntra.utils;

import static com.myntra.base.Keyword.driver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitFor {

	static WebDriverWait wait;

	/**
	 * This static block is used to initialize the WebDriverWait object and set the
	 * polling time and ignoring exception Static block will execute only once when
	 * the class is loaded in the memory
	 */

	private static WebDriverWait getWait() {
		return new WebDriverWait(driver, Duration.ofSeconds(60));
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

	public static void elementToBeVisible(By element) {
		getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	}

	public static void elementToBeVisible(WebElement element) {
		getWait().until(ExpectedConditions.visibilityOf(element));
	}

	public static void elementToBeClickable(By element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	public static WebElement elementToBeClickableOn(By element) {
		return getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void elementToBeClickable(WebElement element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void elementToBePresent(By element) {
		getWait().until(ExpectedConditions.presenceOfElementLocated(element));
	}

	public static void stalenessOfElement(WebElement element) {
		getWait().until(ExpectedConditions.stalenessOf(element));
	}

	public static void visibilityOfElement(WebElement element) {
		getWait().until(ExpectedConditions.visibilityOf(element));
	}

	public static void presenceOfElementLocated(By element) {
		getWait().until(ExpectedConditions.presenceOfElementLocated(element));

	}

	public static void elementToBeVisible(List<WebElement> elements) {
		getWait().until(ExpectedConditions.visibilityOfAllElements(elements));

	}

	public static void visibilityOfElement(List<WebElement> elements) {
		getWait().until(ExpectedConditions.visibilityOfAllElements(elements));

	}

	public static void pageToLoad() {
		getWait().until(ExpectedConditions.urlContains("myntra.com"));
	}

	public static void waitForListToLoad(List<WebElement> productList) {
		getWait().until(
				ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//li[contains(@class,'product-base')]"), 0));

	}

	public static List<WebElement> waitForListToLoadBy(By productList) {
		return getWait().until(
				ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//li[contains(@class,'product-base')]"), 0));
	}

	public static void waitForListToLoad(WebElement element) {
		getWait().until(ExpectedConditions.visibilityOf(element));

	}

	public static boolean elementToBeVisibleOrInvisible(By element) {
		return getWait().until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(element),
				ExpectedConditions.invisibilityOfElementLocated(element)));

	}

	public static void ignoringStaleElementreference(By element) {
		getWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(element));

	}

}
