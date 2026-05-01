package com.myntra.homemenupages;

import static com.myntra.base.Keyword.driver;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.utils.LoggerUtil;
import com.myntra.utils.WaitFor;

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
		List<WebElement> products = driver.findElements(By.xpath("//li[contains(@class,'product-base')]//a"));
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
		log.info("ProductName : " + productName + "  " + "ProductBrand : " + productBrand + "  " + "ProductPrice: "
				+ productPrice);
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
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1200)");

			WaitFor.waitForSeconds(2);

			// Try clicking "View More" if present
			List<WebElement> viewMore = driver.findElements(By.xpath("//div[contains(text(),'View More')]"));

			if (!viewMore.isEmpty()) {
				viewMore.get(0).click();
			}

			List<WebElement> allContent = driver
					.findElements(By.xpath("//div[contains(@class,'pdp-product-description')] | "
							+ "//div[contains(@class,'pdp-productDescriptors')]"));

			return allContent.size() > 0;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyDeliveryOptions(String pincode) {

		try {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 800)");

			WaitFor.elementToBeVisible(deliverypincodeInput);

			deliverypincodeInput.clear();
			deliverypincodeInput.sendKeys(pincode);
			deliveryCheckButton.click();

			Thread.sleep(3000); // important for dynamic content

			List<WebElement> messages = driver.findElements(By.xpath("//ul[contains(@class,'pincode')]//li"));

			if (messages.isEmpty()) {
				log.info("No delivery messages found");
				return false;
			}

			for (WebElement msgEl : messages) {
				String msg = msgEl.getText().toLowerCase();
				log.info("Delivery message: " + msg);

				if (msg.contains("not available") || msg.contains("invalid") || msg.contains("enter valid")) {
					return false;
				}

				if (msg.contains("get it by") || msg.contains("delivery by") || msg.contains("pay on delivery")
						|| msg.contains("cash on delivery")) {
					return true;
				}
			}

			return false;

		} catch (Exception e) {
			log.error("Error in delivery validation: " + e.getMessage());
			return false;
		}
	}
}
