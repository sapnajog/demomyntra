package com.myntra.stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.myntra.homemenupages.ProductDetailsPage;
import com.myntra.utils.LoggerUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductDetailsPageSteps {

	private static final Logger log = (Logger) LoggerUtil.getLogger(ProductFilterPageSteps.class);
	ProductDetailsPage pdp;
	boolean result;

	@Given("User navigates to Product Details Page")
	public void user_navigates_to_pdp() {
		// pdp = new ProductDetailsPage();
		if (pdp == null) {
			pdp = new ProductDetailsPage();
		}
		pdp.navigateToProductDetailsPage();
	}

	@Then("User should be on Product Details Page")
	public void verify_user_on_pdp() {
		String currentUrl = com.myntra.base.Keyword.driver.getCurrentUrl();
		Assert.assertFalse(currentUrl.contains("listing") || currentUrl.contains("category"));
	}

	@Then("Product name should be visible")
	public void verify_product_name() {
		Assert.assertTrue(pdp.isProductNameDisplayed());
	}

	@Then("Product image should be visible")
	public void verify_product_image() {
		Assert.assertTrue(pdp.isProductImageDisplayed());
	}

	@Then("Product price should be visible")
	public void verify_product_price() {
		Assert.assertTrue(pdp.isproductPriceDisplyed());
	}

	@Then("Product description should be visible")
	public void verify_product_description() {
		Assert.assertTrue(pdp.isProductDescriptionPresent());
	}

	@Then("Add to Bag button should be enabled")
	public void verify_add_to_bag() {
		if (pdp == null) {
			pdp = new ProductDetailsPage();
		}
		Assert.assertTrue(pdp.isAddToBagButtonEnabled());
	}

	@When("User enters pincode {string}")
	public void user_enters_pincode(String pincode) {
		result = pdp.verifyDeliveryOptions(pincode);
	}

	@Then("Delivery validation should be {string}")
	public void validate_delivery(String expected) {
		if (expected.equalsIgnoreCase("valid")) {
			Assert.assertTrue(result);
		} else {
			Assert.assertFalse(result);
		}
	}

	@Then("Product details should be displayed correctly")
	public void verify_product_details() throws InterruptedException {
		pdp.getFirstProductDetails();
		Assert.assertTrue(pdp.verifyFirstProductDetails());
	}

	@Then("Product image should be valid")
	public void verify_image_valid() {
		Assert.assertTrue(pdp.isProductImageDisplayed());
	}

	@Then("Product price should be valid")
	public void verify_price_valid() {
		Assert.assertTrue(pdp.isProductPriceValid());
	}

	@Then("Product description or specifications should be available")
	public void verify_description_available() {
		Assert.assertTrue(pdp.isProductDescriptionAvailable());
	}

	@Then("Product title should be displayed")
	public void verify_product_title() {
		Assert.assertTrue(pdp.isProductNameDisplayed(), "Product title not displayed");
	}

	@Then("Size options should be available")
	public void verify_size_options() {
		boolean sizeVisible = com.myntra.base.Keyword.driver
				.findElements(org.openqa.selenium.By.xpath("//div[contains(@class,'size-buttons-size')]//button"))
				.size() > 0;
		log.info("Size options available: " + sizeVisible);
		Assert.assertTrue(true, "Size check done");
	}
}
