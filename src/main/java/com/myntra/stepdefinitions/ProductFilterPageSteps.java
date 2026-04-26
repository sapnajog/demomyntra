
package com.myntra.stepdefinitions;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

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
    public void category_filter_should_be_validated(String categoryName) {

        boolean isPresent = filterPage.isCategoryFilterPresent(categoryName);

        if (isPresent) {
            boolean status = filterPage.verifyProductByCategoryFilters(categoryName);
            Assert.assertTrue(status, "Filter not applied for: " + categoryName);
        } else {
            log.info("Invalid category handled: " + categoryName);
            Assert.assertFalse(isPresent, "Invalid filter should not be present");
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
            Assert.assertTrue(price >= 500 && price <= 1000,
                    "Invalid price: " + price);
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
}
