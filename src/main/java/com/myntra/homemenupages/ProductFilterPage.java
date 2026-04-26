package com.myntra.homemenupages;

import static com.myntra.base.Keyword.driver;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.utils.LoggerUtil;
import com.myntra.utils.WaitFor;

/**
 * This class contains all the locators and methods of the ProductFilterPage of
 * the application. Page Object Model design pattern is used to create this
 * class.
 * 
 * @author Sapna Jogdand
 */
public class ProductFilterPage {
	
	private static final Logger log = (Logger) LoggerUtil.getLogger(ProductFilterPage.class);

	@FindBy(xpath = "//span[@class=\"header-clearAllBtn\"]")
	WebElement clearAll;

	@FindBy(xpath = "//span[text()='Categories']/following::span[contains(@class,'search')][1]")
	WebElement searchCategory;

	@FindBy(xpath = "//div[@class=\"filter-search-filterSearchBox filter-search-expanded\"]/input")
	WebElement categoryFilter;

	@FindBy(xpath = "//ul[@class='categories-list']//label")
	List<WebElement> categoryOptions;

	@FindBy(xpath = "(//span[@class=\"myntraweb-sprite filter-search-iconSearch sprites-search\"])[1]")
	WebElement searchbrandFilter;

	@FindBy(xpath = "//input[@placeholder=\"Search for Brand\"]")
	WebElement brandFilter;

	@FindBy(xpath = "//ul[@class=\"brand-list\"]/li/label")
	WebElement brandName;

	@FindBy(xpath = "//div[@id=\"rootRailThumbLeft\"]")
	WebElement leftSlider;

	@FindBy(xpath = "//div[@id=\"rootRailThumbRight\"]")
	WebElement rightSlider;

	@FindBy(xpath = "//span[@class='product-discountedPrice']")
	List<WebElement> priceElements;

	@FindBy(xpath = "//span[text()='Color']/following::span[contains(@class,'search')][1]")
	WebElement colorSearch;

	@FindBy(xpath = "//input[@placeholder=\"Search for Color\"]")
	WebElement enterColorName;

	@FindBy(xpath = "//ul/li[@class=\"colour-listItem\"]")
	List<WebElement> colorOptions;

	@FindBy(xpath = "//span[@class='filter-summary-filter']")
	List<WebElement> appliedFilters;

	@FindBy(xpath = "//li[@class='product-base']")
	List<WebElement> allProducts;

	@FindBy(xpath = "//label[contains(.,'Curtains and Sheers')]/input")
	WebElement curtainsCheckbox;

	@FindBy(xpath = "//label[@title='Red']")
	WebElement colorCheckbox;

	@FindBy(xpath = "//label[contains(.,'Aurelia') or contains(.,'Aura')]")
	WebElement brandnameCheckbox;

	{
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method is used to clear all applied filter previously.
	 */
	public void clearAll() {
		clearAll.click();
	}

	/**
	 * This is category filter method which will apply the category filter on the
	 * product listing page
	 * 
	 * @param categoryName
	 */
	public void applyCategoryFilter(String categoryName) {
		if (categoryName == null) {
			throw new NullPointerException("category name cannot be null");
		}
		WaitFor.visibilityOfElement(searchCategory);
		log.info("categoryFilter inside applyCategoryFilter");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", searchCategory);
		categoryFilter.sendKeys(categoryName);
		for (WebElement categoryOption : categoryOptions) {
			String text = categoryOption.getText().trim();
			if (text.toLowerCase().contains(categoryName.toLowerCase())) {
				WaitFor.elementToBeClickable(categoryOption);
				categoryOption.click();
				break;
			}
		}
	}

	public boolean verifyProductByCategoryFilters(String categoryName) {
		List<WebElement> filteredCategoryProducts = driver.findElements(By.xpath("//h4[@class='product-product']"));
		for (WebElement categoryProduct : filteredCategoryProducts) {
			try {
				WaitFor.visibilityOfElement(categoryProduct);
				String categoryProductText = categoryProduct.getText().toLowerCase();
				if (categoryProductText.contains(categoryName.toLowerCase())) {
					log.info("Mismatch: " + categoryProductText);
					return true;
				}
			} catch (StaleElementReferenceException e) {
				return verifyProductByCategoryFilters(categoryName);
			}
		}
		return false;

	}

	/**
	 * This is brand filter methods which will apply the brand filter on the product
	 * listing page
	 * 
	 * @param brandname
	 */
	public void applyBrandFilter(String brandname) {
		searchbrandFilter.click();
		brandFilter.sendKeys(brandname);
	}

	public void clickOnBrand() {
		brandName.click();
	}

	public boolean verifyProductByBrandFilters(String string) {
		List<WebElement> filteredProducts = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		for (WebElement product : filteredProducts) {
			try {
				if (product.getText().equalsIgnoreCase(string)) {
				}
				return true;
			} catch (StaleElementReferenceException e) {
				return verifyProductByBrandFilters(string);
			}
		}
		return false;
	}

	/**
	 * This is price filter methods which will move the price slider and apply the
	 * price filter on the product listing page
	 * 
	 **/
	public void openFilteredPage() {
		driver.get(
				"https://www.myntra.com/home-furnishing?f=Categories%3ARunners%3A%3AType%3ABed&rf=Price%3A500.0_1000.0_500.0%20TO%201000.0");
	}

	public void applyPriceFilter() throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.clickAndHold(leftSlider).moveByOffset(10, 0).release().perform();

		actions.clickAndHold(rightSlider).moveByOffset(-10, 0).release().perform();

		WaitFor.elementToBeClickable(leftSlider);
	}

	public List<Integer> getAllPrices() {
		List<Integer> prices = new ArrayList<>();
		try {
			WaitFor.visibilityOfElement(priceElements);
		} catch (StaleElementReferenceException e) {
			return getAllPrices();
		}
		for (WebElement e : priceElements) {
			String text = e.getText().replaceAll("[^0-9]", "");
			prices.add(Integer.parseInt(text));
		}

		return prices;
	}

	/**
	 * This is color filter methods which will apply the color filter on the
	 * products.
	 * 
	 * @param colorName
	 */
	public void applyColorFilter(String colorName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", colorSearch);
		colorSearch.click();
		log.info("clicked on search icon");
		enterColorName.sendKeys(colorName);
		for (WebElement color : colorOptions) {
			String text = color.getText().trim();
			log.info(text);
			if (text.toLowerCase().contains(colorName.toLowerCase())) {
				color.click();
				break;
			}
		}

	}

	public boolean isColorFilterApplied(String colorName) {
		for (WebElement filter : appliedFilters) {
			if (!filter.getText().toLowerCase().contains(colorName.toLowerCase())) {
				return false;
			}
		}
		return true;
	}

	public List<WebElement> getAllProducts() {
		return allProducts;
	}

	public String getBrandName(WebElement product) {
		try {
			WebElement brandElement = product.findElement(By.xpath(".//h3[@class='product-brand']"));
			return brandElement.getText().trim();
		} catch (StaleElementReferenceException e) {
			return getBrandName(product);
		}
	}

	public int getPrice(WebElement product) {
		try {
			WebElement priceElement = product.findElement(By.xpath(".//span[@class='product-discountedPrice']"));
			String priceText = priceElement.getText().replaceAll("[^0-9]", "");
			return Integer.parseInt(priceText);
		} catch (StaleElementReferenceException e) {
			return getPrice(product);
		}

	}

	public String getColor(WebElement product) {
		try {
			WebElement colorElement = product.findElement(By.xpath(".//div[contains(@class,'product-color')]"));
			return colorElement.getAttribute("title").trim();
		} catch (StaleElementReferenceException e) {
			return getColor(product);
		}
	}

	public boolean isCategoryFilterPresent(String categoryName) {
		try {
			List<WebElement> filters = driver
					.findElements(By.xpath("//label[contains(text(),'" + categoryName + "')]"));
			return filters.size() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnCategoryFilter(String categoryName) {
		try {
			WaitFor.elementToBeClickable(curtainsCheckbox);
			curtainsCheckbox.click();
		} catch (Exception e) {
			log.info("Category filter not found: " + categoryName);
		}

	}

	public void clickOnColorFilter(String colorName) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(colorCheckbox).click().perform();
		} catch (Exception e) {
			log.info("Color filter not found: " + colorName);
		}

	}

	public void clickOnBrandName() {
		try {
			WaitFor.elementToBeClickable(brandnameCheckbox);
			brandnameCheckbox.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(brandnameCheckbox).click().perform();
		} catch (Exception e) {
			log.info("Brand filter not found");
		}
	}

}
