package com.myntra.stepdefinitions;

import static com.myntra.base.Keyword.driver;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.myntra.homemenupages.CartPage;
import com.myntra.homemenupages.HomePage;
import com.myntra.homemenupages.PlaceOrderPage;
import com.myntra.homemenupages.ProductDetailsPage;
import com.myntra.homemenupages.ProductListingPage;
import com.myntra.utils.LoggerUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartPageSteps {
	private static final Logger log = (Logger) LoggerUtil.getLogger(CartPageSteps.class);
	CartPage cart;
    ProductDetailsPage pdp;

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
}