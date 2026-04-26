package com.myntra.homemenupages;

import static com.myntra.base.Keyword.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.utils.WaitFor;

/**  This class contains all the locators and methods of the PlaceOrderPage of the application.
 *   Page Object Model design pattern is used to create this class.
 *   @author Sapna Jogdand
 */
public class PlaceOrderPage {
	
	@FindBy(xpath="//div[text()='PLACE ORDER']")
	WebElement placeOrderButton ;
	
	@FindBy(xpath="//ol[@class='checkout-steps']/li[3]")
	WebElement addressPage;
	
	@FindBy(xpath="//div[text()=\"Login \"] ")
	WebElement loginPage;
	
	{
		PageFactory.initElements(driver, this);

	}

	public void clickOnPlaceOrderButton() {
		WaitFor.elementToBeClickable(By.xpath("//div[text()='PLACE ORDER']"));
		placeOrderButton.click();
		
	}

	public boolean verifyAddressPageDisplayed() {
		return addressPage.isDisplayed();
	}
	public boolean verifyLoginPageDisplayed() {
		return loginPage.isDisplayed();
	}
	

}
