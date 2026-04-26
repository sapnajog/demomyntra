package org.myntra.home_menu;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.myntra.base.TestBase;
import com.myntra.homemenupages.CartPage;
import com.myntra.homemenupages.HomePage;
import com.myntra.homemenupages.PlaceOrderPage;
import com.myntra.homemenupages.ProductDetailsPage;
import com.myntra.homemenupages.ProductListingPage;

public class PlaceOrderPageTest extends TestBase {
	@Test
	public void verifyPlaceOrderPage() throws InterruptedException {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductListingPage plp = new ProductListingPage();
		plp.clickOnFirstProductImage();
		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.getFirstProductDetails();
		CartPage cart = new CartPage();
		cart.clickOnAddToBagButton();
		cart.clickOnBagIcon();
		PlaceOrderPage placeOrder = new PlaceOrderPage();
		placeOrder.clickOnPlaceOrderButton();
		boolean loginPage = placeOrder.verifyLoginPageDisplayed();
		Assert.assertTrue(loginPage, "Login page is not displayed");
	}

}
