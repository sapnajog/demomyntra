package org.myntra.home_menu;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.myntra.base.TestBase;
import com.myntra.homemenupages.CartPage;
import com.myntra.homemenupages.HomePage;
import com.myntra.homemenupages.ProductDetailsPage;
import com.myntra.homemenupages.ProductListingPage;

/**
 * This class represents cart page actions like add/remove items.
 */
public class CartPageTest extends TestBase {
	@Test
	public  void verifyCartPage() {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductListingPage plp = new ProductListingPage();
		plp.clickOnFirstProductImage();
		CartPage cart = new CartPage();
		cart.clickOnAddToBagButton();
		cart.clickOnBagIcon();
		boolean status = cart.verifyProductAddedToBag1();
		Assert.assertTrue(status, "Product is not added to bag");
	}
	
	@Test(description = "Verify product is added to cart")
    public void verifyProductAddedToCart() {
		ProductDetailsPage pdp = new ProductDetailsPage();
        pdp.navigateToProductDetailsPage();
        CartPage cart = new CartPage();
        cart.addProductToCart();
        cart.openCart();
        Assert.assertTrue(cart.isProductDisplayed(), "Product is not visible in cart");
    }


}
