package com.myntra.homemenupages;

import static com.myntra.base.Keyword.driver;

import java.util.ArrayList;
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
 * This class contains all the locators and methods of the ProductListingPage of
 * the application. Page Object Model design pattern is used to create this
 * class.
 * 
 * @author Sapna Jogdand
 */
public class ProductListingPage {

	private static final Logger log = (Logger) LoggerUtil.getLogger(ProductListingPage.class);

	@FindBy(xpath = "//li[contains(@class,'product-base')]")
	List<WebElement> products;

	@FindBy(xpath = "//li[@class='product-base']/descendant::img")
	List<WebElement> productImages;

	@FindBy(xpath = "(//li[@class='product-base']//a)[1]")
	WebElement firstProductImage;

	@FindBy(xpath = "//li[contains(@class,'product-base')]")
	List<WebElement> productList;

	@FindBy(xpath = "//h4[@class='product-product']")
	List<WebElement> productNames;

	public ProductListingPage() {
		PageFactory.initElements(driver, this);

	}

	public int getTotalProductCount() {
		By productLocator = By.xpath("//li[contains(@class,'product-base')]");

		WaitFor.presenceOfElementLocated(productLocator);

		List<WebElement> list = driver.findElements(productLocator);

		int size = list.size();
		log.info("Total Products: " + size);

		return size;
	}

	public boolean verifyProductImages() {
		for (WebElement img : productImages) {
			String src = img.getAttribute("src");
			if (src == null || src.isEmpty()) {
				return false;
			}
		}
		return true;

	}

	public boolean isFirstProductImageDisplayed() {
		boolean firstImage = productImages.get(0).isDisplayed();
		log.info("firstImage :: " + firstImage);
		return firstImage;
	}

	public void clickOnFirstProductImage() {

		WaitFor.waitForListToLoad(productList);
		if (productList.size() == 0) {
			throw new RuntimeException("No products found on PLP");
		}
		WebElement firstProduct = productList.get(0).findElement(By.tagName("a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
		WaitFor.elementToBeClickable(firstProduct);
		firstProduct.click();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
	}

	public void navigatesBackAfterClickOnFirstProductImage() {
		String parentWindow = driver.getWindowHandle();
		WaitFor.elementToBeClickable(By.xpath("(//li[@class='product-base']//a)[1]"));
		firstProductImage.click();
		WaitFor.elementToBeClickable(By.xpath("(//li[@class='product-base']//a)[1]"));
		for (String window : driver.getWindowHandles()) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		driver.close();
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
		}
		WaitFor.elementToBeVisible(By.xpath("//h4[@class='product-product']"));

	}

	public boolean verifyAllProductNamesDisplayed() {
		WaitFor.elementToBeVisible(productNames);
		if (productNames.isEmpty()) {
			log.info("No product name elements found on the page.");
			return false;
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<String> failedProducts = new ArrayList<>();
		for (WebElement productName : productNames) {
			js.executeScript("arguments[0].scrollIntoView(true);", productName);
			String nameText = productName.getText().trim();
			if (nameText.isEmpty()) {
				log.info("Missing product name: " + productName);
				failedProducts.add(productName.toString());
			} else {
				log.info("Product Name: " + nameText);
			}
		}
		log.info("Total product names verified: " + productNames.size());
		return failedProducts.isEmpty();
	}

}
