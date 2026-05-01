package com.myntra.homemenupages;

import static com.myntra.base.Keyword.driver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.utils.LoggerUtil;
import com.myntra.utils.WaitFor;

/**
 * This class contains all the locators and methods of the home page of the
 * application. We are using page object model design pattern to create this
 * class
 * 
 * @author Sapna Jogdand
 */
public class HomePage {
	private static final Logger log = (Logger) LoggerUtil.getLogger(HomePage.class);
	@FindBy(xpath = "//div[@data-reactid=\"15\"]")
	WebElement logo;

	@FindBy(xpath = "//input[@placeholder=\"Search for products, brands and more\"]")
	WebElement searchBox;

	@FindBy(xpath = "//span[@data-reactid=\"1041\"]")
	WebElement searchIcon;

	@FindBy(xpath = "//h1[@class=\"title-title\"] ")
	WebElement header;

	@FindBy(xpath = "//span[@data-reactid=\"988\"]")
	WebElement profile;

	@FindBy(xpath = "//a[@class=\"desktop-wishlist\"]")
	WebElement wishlist;

	@FindBy(xpath = "//a[@class=\"desktop-cart\"]")
	WebElement bag;

	@FindBy(xpath = "//a[@data-group='home']")
	WebElement homeMenu;

	@FindBy(xpath = "//div[contains(@class,'desktop-paneContent')]")
	WebElement homeSubMenu;

	@FindBy(xpath = "//a[contains(text(),'Bed Runners')]")
	WebElement bedRunners;

	@FindBy(xpath = "//li[@class=\"breadcrumbs-item\"][3]")
	WebElement runners;

	By resultsHeader = By.xpath("//h1[contains(@class,'title-title')]");

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isHomePage() {
		return driver.findElements(By.xpath("//input[@placeholder='Search for products, brands and more']")).size() > 0;
	}

	public void clickOnLogo() {
		logo.click();
	}

	public void searchProduct(String product) {
		WaitFor.elementToBeVisible(searchBox);
		searchBox.clear();
		searchBox.sendKeys(product);
		searchIcon.click();
	}

	public void waitForsearchElementToBeVisible() {
		WaitFor.elementToBeVisible(searchBox);
	}

	public String getSearchResultHeader() {
		String text = header.getText().trim();
		return text;
	}

	public void waitForSearchResults() {

		By productList = By.xpath("//li[contains(@class,'product-base')]");

		WaitFor.presenceOfElementLocated(productList);

		int count = driver.findElements(productList).size();

		System.out.println("Products loaded: " + count);
	}

	public void handlePopupIfPresent() {
		try {
			WebElement closeBtn = driver
					.findElement(By.xpath("//span[@class='myntraweb-sprite desktop-iconClose sprites-remove']"));
			if (closeBtn.isDisplayed()) {
				closeBtn.click();
			}
		} catch (Exception e) {
			log.info("No popup found, continuing with the test.");
		}
	}

	public void hoverOnHomeMenu() {
		handlePopupIfPresent();
		WaitFor.elementToBeVisible(homeMenu);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)", homeMenu);
		WaitFor.elementToBeClickable(homeMenu);
		Actions actions = new Actions(driver);
		actions.moveToElement(homeMenu).perform();
	}

	public boolean isHomeSubMenuDisplayed() {
		return homeSubMenu.isDisplayed();

	}

	public void clickOnBedRunners() {
		WaitFor.elementToBeVisible(bedRunners);
		WaitFor.elementToBeClickable(bedRunners);
		bedRunners.click();
	}

	public String getTextRunners() {
		String text = runners.getText();
		log.info(text);
		return text;

	}

}
