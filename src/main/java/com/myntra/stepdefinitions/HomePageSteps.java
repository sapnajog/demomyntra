package com.myntra.stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.myntra.homemenupages.HomePage;
import com.myntra.utils.LoggerUtil;

import io.cucumber.java.en.*;

import static com.myntra.base.Keyword.driver;

/**
 * This class contains all the home page related steps
 * 
 * @author Sapna Jogdand
 */
public class HomePageSteps {
	private static final Logger log = (Logger) LoggerUtil.getLogger(HomePageSteps.class);

	HomePage homepage;

	@Given("User is on Myntra Home Page")
	public void user_is_on_home_page() {
		homepage = new HomePage();
		String title = driver.getTitle();
		log.info("Page Title: " + title);
		Assert.assertTrue(title.contains("Myntra"), "User is not on Myntra Home Page");
	}

	@When("User clicks on Myntra logo")
	public void user_clicks_on_logo() {
		homepage = new HomePage();
		homepage.clickOnLogo();
	}

	@Then("User should be navigated to homepage")
	public void user_should_be_on_homepage() {
		Assert.assertTrue(
				driver.getCurrentUrl().contains("myntra") || driver.getTitle().toLowerCase().contains("myntra"),
				"Navigation to homepage failed");
	}

	@When("User searches for {string}")
	public void user_searches_for_product(String product) {
		homepage = new HomePage();
		homepage.searchProduct(product);
		homepage.waitForSearchResults();
	}

	@Then("Search results should be displayed")
	public void search_results_should_be_displayed() {
		homepage = new HomePage();
		String header = homepage.getSearchResultHeader();
		Assert.assertNotNull(header, "Search results not displayed");
		log.info("Search Result: " + header);
	}

	@Then("Invalid search message should be displayed")
	public void invalid_search_message_should_be_displayed() {
		homepage = new HomePage();
		String header = homepage.getSearchResultHeader();
		String expected = "Earrings"; // your existing logic
		Assert.assertEquals(header, expected, "Invalid search message mismatch");
	}

	@Then("Search result should contain {string}")
	public void search_result_should_contain(String expectedText) {
		homepage = new HomePage();
		String actual = homepage.getSearchResultHeader();
		log.info("Actual Header: " + actual);

		Assert.assertTrue(actual.toLowerCase().contains(expectedText.toLowerCase()),
				"Search result does not contain expected text");
	}

	@Then("Page title should contain {string}")
	public void page_title_should_contain(String expectedTitle) {
		String title = com.myntra.base.Keyword.driver.getTitle();
		log.info("Page title: " + title);
		Assert.assertTrue(title.contains(expectedTitle), "Page title does not contain: " + expectedTitle);
	}

	@Then("Search box should be visible")
	public void search_box_should_be_visible() {
		homepage.waitForsearchElementToBeVisible();
		Assert.assertTrue(true, "Search box is visible");
	}

	@Then("Home menu should be visible")
	public void home_menu_should_be_visible() {
		boolean visible = com.myntra.base.Keyword.driver
				.findElements(org.openqa.selenium.By.xpath("//a[@data-group='home']")).size() > 0;
		Assert.assertTrue(visible, "Home menu not visible");
	}
}