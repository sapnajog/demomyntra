package com.myntra.homemenupages;

import static com.myntra.base.Keyword.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.myntra.utils.LoggerUtil;
import com.myntra.utils.WaitFor;

import io.cucumber.messages.types.Duration;

/**
 * This class contains all the locators and methods of the ProductDetailsPage of
 * the application. Page Object Model design pattern is used to create this
 * class.
 * 
 * @author Sapna Jogdand
 */

public class ProductDetailsPage {
	private static final Logger log = (Logger) LoggerUtil.getLogger(ProductDetailsPage.class);
	
	@FindBy(xpath = "//div[@class=\"pdp-price-info\"]/h1[1]")
	WebElement firstProductBrand;

	@FindBy(xpath = "//div[@class=\"pdp-price-info\"]/h1[2]")
	WebElement firstProductName;

	@FindBy(xpath = "//p[@class=\"pdp-discount-container\"]/span[@class=\"pdp-price\"]/strong")
	WebElement firstProductPrice;

	@FindBy(xpath = "//h1[@class=\"pdp-title\"]")
	WebElement productTitle;

	@FindBy(xpath = "//img[contains(@class,'image')]")
	WebElement productImage;

	@FindBy(xpath = "//span[contains(@class,'price')]")
	WebElement productPrice;

	@FindBy(xpath = "//span[contains(@class,'price')]")
	List<WebElement> priceElements;

	@FindBy(xpath = "//p[@class=\"pdp-product-description-content\"]")
	WebElement productDesc;

	@FindBy(xpath = "//div[contains(@class,'pdp-product-description')]")
	List<WebElement> descriptionElements;

	@FindBy(xpath = "//div[text()='ADD TO BAG']")
	WebElement addToBagButton;

	@FindBy(xpath = "//h4[text()=\"Delivery Options\"]")
	WebElement deliveryOption;

	@FindBy(xpath = "//input[@placeholder=\"Enter pincode\"]")
	WebElement deliverypincodeInput;

	@FindBy(xpath = "//input[@class=\"pincode-check pincode-button\"]")
	WebElement deliveryCheckButton;

	@FindBy(xpath = "//ul[@class=\"pincode-serviceability-list\"]/li[1]")
	WebElement deliveryMessage;

	By productListLocator = By.xpath("//li[contains(@class,'product-base')]");

	public ProductDetailsPage() {
		PageFactory.initElements(driver, this);

	}

	public void navigateToProductDetailsPage() {
	    if (driver == null) {
	        throw new IllegalStateException("Driver is null before navigation!");
	    }
	    driver.get("https://www.myntra.com/bed-runners");
	    List<WebElement> products = driver.findElements(
	        By.xpath("//li[contains(@class,'product-base')]//a"));
	    if (!products.isEmpty()) {
	        String href = products.get(0).getAttribute("href");
	        driver.get(href);
	    }
	    PageFactory.initElements(driver, this);
	}
	
	
	public void getFirstProductDetails() throws InterruptedException {
		WaitFor.elementToBeVisible(firstProductBrand);
		String productBrand = firstProductBrand.getText();
		String productName = firstProductName.getText();
		String productPrice = firstProductPrice.getText();
		log.info("ProductName : " + productName + "  " + "ProductBrand : " + productBrand + "  "
				+ "ProductPrice: " + productPrice);
	}

	public boolean verifyFirstProductDetails() {

		return !firstProductBrand.getText().isEmpty() && !firstProductName.getText().isEmpty()
				&& !firstProductPrice.getText().isEmpty();
	}

	public boolean isProductNameDisplayed() {
		return firstProductName.isDisplayed();

	}

	public boolean isProductImageDisplayed() {
		WaitFor.elementToBeVisible(productImage);
		boolean isDisplayed = productImage.isDisplayed();
		String src = productImage.getAttribute("src");
		log.info("productImage src: " + src);
		return isDisplayed && src != null && !src.isEmpty();
	}

	public boolean isproductPriceDisplyed() {
		WaitFor.elementToBeVisible(productPrice);
		boolean isDisplayed = productPrice.isDisplayed();
		String priceText = productPrice.getText();
		log.info("Product price :" + priceText);
		return isDisplayed && priceText != null && !priceText.isEmpty();
	}

	public boolean isProductDescriptionPresent() {
		// WaitFor.elementToBeVisible(productDesc);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productDesc);
		boolean isDisplayed = productDesc.isDisplayed();
		String descText = productDesc.getText();
		log.info("Product description :" + descText);
		return isDisplayed && descText != null && !descText.isEmpty();

	}

	public boolean isAddToBagButtonEnabled() {
		// WaitFor.elementToBeVisible(addToBagButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToBagButton);
		boolean isEnabled = addToBagButton.isEnabled();
		log.info("Add to bag button enabled : " + isEnabled);
		return isEnabled;
	}

	public boolean isProductPriceValid() {
		if (priceElements.isEmpty()) {
			log.info("Price element not found");
			return false;
		}

		for (WebElement price : priceElements) {
			String priceText = price.getText().trim();
			log.info("Price Text: " + priceText);
			if (priceText.isEmpty()) {
				log.info("Price is empty");
				return false;
			}
			String numeric = priceText.replaceAll("[^0-9]", "");
			if (numeric.isEmpty()) {
				log.info("Invalid price format");
				return false;
			}
		}

		return true;
	}

	public boolean isProductDescriptionAvailable() {
	    try {
	        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 800)");

	        WaitFor.waitForSeconds(2); // add small wait

	        List<WebElement> desc = driver.findElements(
	                By.xpath("//div[contains(@class,'pdp-product-description')]//p"));

	        List<WebElement> specs = driver.findElements(
	                By.xpath("//div[contains(@class,'pdp-productDescriptors')]//li"));

	        boolean result = (!desc.isEmpty() && desc.get(0).isDisplayed()) ||
	                         (!specs.isEmpty() && specs.get(0).isDisplayed());

	        log.info("Description available: " + result);
	        return result;

	    } catch (Exception e) {
	        return false;
	    }
	}

	public boolean verifyDeliveryOptions(String pincode) {

		try {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 600)");

			WaitFor.elementToBeVisible(deliverypincodeInput);

			deliverypincodeInput.clear();
			deliverypincodeInput.sendKeys(pincode);
			deliveryCheckButton.click();

			WaitFor.elementToBeVisible(deliveryMessage);

			String message = deliveryMessage.getText().toLowerCase();

			log.info("-----------------");
			log.info("Pincode: " + pincode + " | Message: " + message);

			if (message.contains("not available") || message.contains("invalid") || message.contains("enter valid")
					|| message.contains("please enter")) {
				return false;
			}
			return message.contains("get it by") || message.contains("cash on delivery")
					|| message.contains("delivery by");

		} catch (Exception e) {
			return false;
		}
	}
}
