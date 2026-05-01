package com.myntra.stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.myntra.homemenupages.CartPage;
import com.myntra.homemenupages.PlaceOrderPage;
import com.myntra.homemenupages.ProductDetailsPage;
import com.myntra.utils.LoggerUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartPageSteps {
	private static final Logger log = (Logger) LoggerUtil.getLogger(CartPageSteps.class);
	CartPage cart;
	ProductDetailsPage pdp;
	PlaceOrderPage pop;

	@Given("User navigates to Product Details Page for cart")
	public void user_navigates_to_pdp_for_cart() {
		pdp = new ProductDetailsPage();
		pdp.navigateToProductDetailsPage();
	}

	@When("User adds product to cart")
	public void add_product_to_cart() {
		cart = new CartPage();
		cart.addProductToCart();
	}

	@When("User opens cart page")
	public void open_cart_page() {
		cart.openCart();
	}

	@Then("Product should be visible in cart")
	public void verify_product_visible() {
		Assert.assertTrue(cart.isProductDisplayed(), "Product not visible in cart");
	}

	@When("User clicks on Add To Bag button")
	public void click_add_to_bag() {
		cart = new CartPage();
		cart.addProductToCart();
	}

	@When("User clicks on Bag icon")
	public void click_bag_icon() {
		cart.clickOnBagIcon();
	}

	@Then("Product should be added to cart")
	public void verify_product_added() {
		Assert.assertTrue(cart.isProductDisplayed(), "Product not added to cart");
	}

	@Then("Place Order button should be visible")
	public void verify_place_order_visible() {
		boolean visible = com.myntra.base.Keyword.driver
				.findElements(org.openqa.selenium.By.xpath("//div[text()='PLACE ORDER']")).size() > 0;
		Assert.assertTrue(visible, "Place Order button not visible");
	}

	@When("User clicks on Place Order button")
	public void click_place_order_button() {
		pop = new PlaceOrderPage();
		pop.clickOnPlaceOrderButton();
	}

	@Then("Login page should be displayed after order")
	public void verify_login_page_after_order() {
		Assert.assertTrue(pop.verifyLoginPageDisplayed(), "Login page not displayed after Place Order");
	}
}