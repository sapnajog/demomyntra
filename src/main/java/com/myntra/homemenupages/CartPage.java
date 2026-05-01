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
 * This class contains all the locators and methods of the Cart page of the
 * application. Page Object Model design pattern is used to create this class.
 * 
 * @author Sapna Jogdand
 */
public class CartPage {
	private static final Logger log = (Logger) LoggerUtil.getLogger(CartPage.class);

	@FindBy(xpath = "//div[text()='ADD TO BAG']")
	WebElement addToBagButton;

	@FindBy(xpath = "//span[@class='myntraweb-sprite desktop-iconBag sprites-headerBag']")
	WebElement bagIcon;

	@FindBy(xpath = "//div/div[text()=\"Kuber Industries\"]")
	WebElement kuberIndustriesBrand;

	@FindBy(xpath = "//div[contains(@class,'size-buttons-size')]//button")
	List<WebElement> sizeOptions;

	@FindBy(xpath = "//a[contains(@href,'/checkout/cart')]")
	WebElement goToBagButton;

	@FindBy(xpath = "//div[contains(@class,'cart-product')]")
	List<WebElement> productlist;

	By removeBtn = By.xpath("//button[contains(text(),'REMOVE')][1]");

	By confirmRemoveBtn = By.xpath("//button[contains(text(),'REMOVE')]");

	By emptyCartMsg = By.xpath("//div[contains(text(),'empty')]");

	By popup = By.xpath("//div[@role='dialog']");

	{
		PageFactory.initElements(driver, this);

	}

	public void clickOnAddToBagButton() {
		WaitFor.elementToBeVisible(addToBagButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addToBagButton);
		WaitFor.elementToBeClickable(addToBagButton);
		try {
			addToBagButton.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToBagButton);
		}
	}

	public void clickOnBagIcon() {
		WaitFor.elementToBeClickable(By.xpath("//span[@class='myntraweb-sprite desktop-iconBag sprites-headerBag']"));
		bagIcon.click();
	}

	public boolean verifyProductAddedToBag1() {
		return kuberIndustriesBrand.isDisplayed();

	}

	public void addProductToCart() {
		for (WebElement size : sizeOptions) {
			if (size.isDisplayed() && size.isEnabled()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", size);
				WaitFor.elementToBeClickable(size);
				try {
					size.click();
				} catch (Exception e) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", size);
				}

				break;
			}
		}

		try {
			addToBagButton.click();
			log.info("Clicked on ADD TO BAG");
			WaitFor.elementToBeVisible(goToBagButton);
			log.info("Product added to cart successfully");

		} catch (Exception e) {
			log.info("Error while adding product to cart: " + e.getMessage());
		}
	}

	public void openCart() {
		try {
			WaitFor.elementToBeClickable(goToBagButton);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});",
					goToBagButton);

			try {
				goToBagButton.click();
			} catch (Exception e) {
				log.info("Normal click failed, using JS click");
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", goToBagButton);
			}
			log.info("Clicked on GO TO BAG");

		} catch (Exception e) {
			throw new RuntimeException("Unable to click on GO TO BAG button", e);
		}
	}

	public boolean isProductDisplayed() {
		return kuberIndustriesBrand.isDisplayed();
	}

	public void clickRemoveButton() {
		WaitFor.elementToBeClickableOn(removeBtn).click();

	}

	public void confirmRemove() {
		WaitFor.elementToBeVisible(popup);
		WaitFor.elementToBeClickableOn(confirmRemoveBtn).click();
	}

	public boolean isItemRemoved() {
		return WaitFor.elementToBeVisibleOrInvisible(emptyCartMsg);
	}
}
