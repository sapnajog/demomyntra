package com.myntra.stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.myntra.homemenupages.CartPage;
import com.myntra.homemenupages.HomePage;
import com.myntra.homemenupages.PlaceOrderPage;
import com.myntra.homemenupages.ProductDetailsPage;
import com.myntra.homemenupages.ProductListingPage;
import com.myntra.utils.LoggerUtil;

import static com.myntra.base.Keyword.driver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class ProductListingPageSteps {
	private static final Logger log = (Logger) LoggerUtil.getLogger(ProductListingPageSteps.class);
	HomePage homepage;
	ProductListingPage plp;
	ProductDetailsPage pdp;
	CartPage cart;
	PlaceOrderPage placeOrder;

	String plpUrl;
	String pdpUrl;

	@Before
	public void initPages() {
		homepage = new HomePage();
		plp = new ProductListingPage();
		pdp = new ProductDetailsPage();
		cart = new CartPage();
		placeOrder = new PlaceOrderPage();
	}

	@Given("User is on Myntra ProductListingPage Home Page")
	public void user_is_on_home_page() {
		homepage = new HomePage();
	}

	@When("User hovers on Home menu")
	public void user_hovers_on_home_menu() {
		homepage.hoverOnHomeMenu();
	}

	@When("User clicks on Bed Runners category")
	public void user_clicks_on_bed_runners() {
		homepage.clickOnBedRunners();
		plp = new ProductListingPage();
	}

	@Then("Product listing page should display products")
	public void verify_products_loaded() {
		int total = plp.getTotalProductCount();
		log.info("Total Products: " + total);
		Assert.assertTrue(total > 0);
	}

	@Then("All product images should be displayed")
	public void verify_all_images() {
		Assert.assertTrue(plp.verifyProductImages());
	}

	@Then("First product image should be visible")
	public void verify_first_image() {
		Assert.assertTrue(plp.isFirstProductImageDisplayed());
	}

	@When("User clicks on first product")
	public void click_first_product() {
		plpUrl = driver.getCurrentUrl();
		plp.clickOnFirstProductImage();
	}

	@Then("User should navigate to Product Details Page")
	public void verify_navigation_to_pdp() {
		pdp = new ProductDetailsPage();
		pdpUrl = driver.getCurrentUrl();
		Assert.assertNotEquals(plpUrl, pdpUrl);
	}

	@Then("Product name should be displayed")
	public void verify_product_name() {
		Assert.assertTrue(pdp.isProductNameDisplayed());
	}

	@Then("Product image should be displayed")
	public void verify_product_image() {
		Assert.assertTrue(pdp.isProductImageDisplayed());
	}

	@Then("Product price should be displayed")
	public void verify_product_price() {
		Assert.assertTrue(pdp.isproductPriceDisplyed());
	}

	@When("User adds product to bag")
	public void add_product_to_bag() {
		cart = new CartPage();
		cart.clickOnAddToBagButton();
	}

	@When("User navigates to cart")
	public void go_to_cart() {
		cart.clickOnBagIcon();
	}

	@Then("Product should be added to bag")
	public void verify_product_added() {
		Assert.assertTrue(cart.verifyProductAddedToBag1());
	}

	@When("User clicks on Place Order")
	public void click_place_order() {
		placeOrder = new PlaceOrderPage();
		placeOrder.clickOnPlaceOrderButton();
	}

	@Then("Login page should be displayed")
	public void verify_login_page() {
		Assert.assertTrue(placeOrder.verifyLoginPageDisplayed());
	}
}
