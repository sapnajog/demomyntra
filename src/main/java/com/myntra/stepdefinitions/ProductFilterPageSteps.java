
package com.myntra.stepdefinitions;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Ignore;

import com.myntra.homemenupages.HomePage;
import com.myntra.homemenupages.ProductFilterPage;
import com.myntra.utils.LoggerUtil;

import io.cucumber.java.en.*;

public class ProductFilterPageSteps {

	private static final Logger log = (Logger) LoggerUtil.getLogger(ProductFilterPageSteps.class);

	HomePage homePage = new HomePage();
	ProductFilterPage filterPage = new ProductFilterPage();

	@Given("User is on Myntra home page")
	public void user_is_on_home_page() {
		log.info("User is on Myntra Home Page");
	}

	@When("User navigates to Bed Runners page")
	public void user_navigates_to_bed_runners_page() {
		homePage.hoverOnHomeMenu();
		homePage.clickOnBedRunners();
	}

	// ================= CATEGORY =================

	@When("User applies category filter {string}")
	public void user_applies_category_filter(String categoryName) {
		filterPage.clearAll();
		filterPage.applyCategoryFilter(categoryName);
	}

	@Then("Category filter should be validated for {string}")
	public void category_filter_should_be_validated(String category) {
		/*
		 * Assert.assertTrue( filterPage.isCategoryFilterPresent(category),
		 * "Filter not applied for: " + category );
		 */

		boolean result = filterPage.isCategoryFilterPresent(category);

		if (category.equalsIgnoreCase("InvalidCategory123")) {
			Assert.assertFalse(result, "Invalid category should NOT be applied");
		} else {
			Assert.assertTrue(result, "Filter not applied for: " + category);
		}
	}

	// ================= BRAND =================

	@When("User applies brand filter {string}")
	public void user_applies_brand_filter(String brand) {
		filterPage.applyBrandFilter(brand);
		filterPage.clickOnBrand();
	}

	@Then("Only {string} brand products should be displayed")
	public void only_brand_products_should_be_displayed(String brand) {
		boolean status = filterPage.verifyProductByBrandFilters(brand);
		Assert.assertTrue(status, "Brand filter failed");
	}

	// ================= PRICE =================

	@When("User applies price filter")
	public void user_applies_price_filter() throws InterruptedException {
		filterPage.openFilteredPage();
		filterPage.applyPriceFilter();
	}

	@Then("Products should be within price range 500 to 1000")
	public void products_should_be_within_price_range() {
		List<Integer> prices = filterPage.getAllPrices();
		for (int price : prices) {
			Assert.assertTrue(price >= 500 && price <= 1000, "Invalid price: " + price);
		}
	}

	// ================= COLOR =================

	@When("User applies color filter {string}")
	public void user_applies_color_filter(String color) {
		filterPage.applyColorFilter(color);
	}

	@Then("Color filter {string} should be applied")
	public void color_filter_should_be_applied(String color) {
		boolean status = filterPage.isColorFilterApplied(color);
		Assert.assertTrue(status, "Color filter not applied: " + color);
	}

	@Given("User opens price filtered page")
	public void user_opens_price_filtered_page() {
		filterPage = new ProductFilterPage();
		filterPage.openFilteredPage();
	}

	@Then("Filter panel should be visible")
	public void filter_panel_should_be_visible() {
		Assert.assertTrue(filterPage.isFilterPanelVisible(), "Filter panel not visible");
	}

	@When("User clears all filters")
	public void user_clears_all_filters() {
		try {
			filterPage.clearAll();
		} catch (Exception e) {
			log.info("Clear all not available: " + e.getMessage());
		}
	}

	@When("User clicks on category filter {string}")
	public void user_clicks_on_category_filter(String categoryName) {
		filterPage.clickOnCategoryFilter(categoryName);
	}

	@When("User clicks on brand filter {string}")
	public void user_clicks_on_brand_filter(String brandName) {
		filterPage.clickOnBrandName();
	}

	@When("User clicks on color filter {string}")
	public void user_clicks_on_color_filter(String colorName) {
		filterPage.clickOnColorFilter(colorName);
	}

	@Then("Color filter should be applied for {string}")
	public void colorfilter_should_be_applied(String colorName) {
		boolean result = com.myntra.base.Keyword.driver
				.findElements(org.openqa.selenium.By.xpath("//li[@class='product-base']")).size() > 0;
		Assert.assertTrue(result, "No products after color filter: " + colorName);
	}

	@Then("Brand filter should be validated for {string}")
	public void brand_filter_should_be_validated(String brandName) {
		boolean result = filterPage.verifyProductByBrandFilters(brandName);
		Assert.assertTrue(result, "Brand filter not applied for: " + brandName);
	}
}
