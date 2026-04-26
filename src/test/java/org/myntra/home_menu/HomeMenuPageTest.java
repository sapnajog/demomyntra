package org.myntra.home_menu;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.myntra.base.TestBase;
import com.myntra.dataprovider.DataProviderForCategoryFilter;
import com.myntra.homemenupages.CartPage;
import com.myntra.homemenupages.HomePage;
import com.myntra.homemenupages.PlaceOrderPage;
import com.myntra.homemenupages.ProductDetailsPage;
import com.myntra.homemenupages.ProductFilterPage;
import com.myntra.homemenupages.ProductListingPage;
import com.myntra.listeners.MyListeners;
import com.myntra.utils.LoggerUtil;
import com.myntra.utils.ScreenshotFor;

@Listeners(MyListeners.class)
public class HomeMenuPageTest extends TestBase {
	private static final Logger log = (Logger) LoggerUtil.getLogger(HomeMenuPageTest.class);
	@Test
	public void verifyHomeMenuPageElements() throws InterruptedException {
		HomePage homepage = new HomePage();
		homepage.clickOnLogo();
		homepage.searchProduct("BedSheets");
		String actualHeader = homepage.getSearchResultHeader();
		homepage.waitForSearchResults();
		log.info("Actual Search Result Text: " + actualHeader);
		Assert.assertTrue(actualHeader.toLowerCase().contains("bedsheets"),
				"SearchResult text does not contains expected text");
		homepage.hoverOnHomeMenu();
		homepage.isHomeSubMenuDisplayed();
		Assert.assertTrue(homepage.isHomeSubMenuDisplayed(), "Home submenu is not displayed after hover");
		homepage.clickOnBedRunners();
		String actualText = homepage.getTextRunners();
		Assert.assertTrue(actualText.contains("Runners"));

		ProductListingPage plp = new ProductListingPage();
		int totalProduct = plp.getTotalProductCount();
		log.info("Total Bed Runner Products: " + totalProduct);
		Assert.assertTrue(totalProduct > 0, "No products are displayed on Bed Runners page");
		boolean imagesDisplayed = plp.verifyProductImages();
		Assert.assertTrue(imagesDisplayed, "The images are not displayed.");
		boolean firstProductImage = plp.isFirstProductImageDisplayed();
		Assert.assertTrue(firstProductImage);
		plp.clickOnFirstProductImage();

		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.getFirstProductDetails();
		boolean details = pdp.verifyFirstProductDetails();
		Assert.assertTrue(details, "First product details are not displayed properly");

		CartPage cart = new CartPage();
		cart.clickOnAddToBagButton();
		cart.clickOnBagIcon();
		boolean status = cart.verifyProductAddedToBag1();
		Assert.assertTrue(status, "Product is not added to bag");

		PlaceOrderPage placeOrder = new PlaceOrderPage();
		placeOrder.clickOnPlaceOrderButton();
		boolean loginPage = placeOrder.verifyLoginPageDisplayed();
		Assert.assertTrue(loginPage, "Login page is not displayed");
	}

	@Test(dataProvider = "CategoryFilterData", dataProviderClass = DataProviderForCategoryFilter.class)
	public void verifyFilterCategoryForProducts(String categoryName) throws InterruptedException {
		HomePage homepage = new HomePage();
		homepage.clickOnBedRunners();
		ProductFilterPage filterPage = new ProductFilterPage();
		filterPage.clearAll();
		filterPage.applyCategoryFilter(categoryName);
		boolean categoryfilterStatus = filterPage.verifyProductByCategoryFilters(categoryName);
		Assert.assertTrue(categoryfilterStatus, "The filter is not applied properly for : " + categoryName);
	}

	@Test
	public void verifyInvalidCategoryNameForProducts() {

		HomePage homepage = new HomePage();
		homepage.clickOnBedRunners();
		String categoryName = "xyz123";
		ProductFilterPage filterPage = new ProductFilterPage();
		filterPage.clearAll();
		filterPage.applyCategoryFilter(categoryName);
		boolean categoryfilterStatus = filterPage.verifyProductByCategoryFilters(categoryName);
		Assert.assertFalse(categoryfilterStatus, "invalid category should not be applied");

	}

	@Test
	public void verifyNullCategoryFilterForProduct() {

		HomePage homepage = new HomePage();
		homepage.clickOnBedRunners();
		String categoryName = null;
		ProductFilterPage filterPage = new ProductFilterPage();
		filterPage.clearAll();
		try {
			filterPage.applyCategoryFilter(categoryName);
			boolean categoryfilterStatus = filterPage.verifyProductByCategoryFilters(categoryName);
			Assert.assertFalse(categoryfilterStatus, "null category should not be applied");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof NullPointerException, "Expected NullPointerException for null category");
		}
	}

	@Test
	public void verifyFilterBrandAndPriceForProducts() throws InterruptedException {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductFilterPage filterPage = new ProductFilterPage();
		filterPage.applyBrandFilter("Aura");
		filterPage.clickOnBrand();
		boolean filterStatus = filterPage.verifyProductByBrandFilters("Aura");
		Assert.assertTrue(filterStatus, "The brand filter is not applied properly");
		filterPage.openFilteredPage();
		filterPage.applyPriceFilter();
		List<Integer> prices = filterPage.getAllPrices();
		for (int price : prices) {
			Assert.assertTrue(price >= 500 && price <= 1000, "Invalid price: " + price);
		}

	}

	@Test
	public void verifyColorFilter() throws InterruptedException {
		HomePage homepage = new HomePage();
		homepage.clickOnBedRunners();
		ProductFilterPage filterPage = new ProductFilterPage();
		String colorName = "Green";
		filterPage.applyColorFilter(colorName);
		boolean status = filterPage.isColorFilterApplied(colorName);
		Assert.assertTrue(status, "Color filter not applied: " + colorName);

	}
}