package org.myntra.home_menu;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.myntra.base.TestBase;
import com.myntra.dataprovider.DataProviderForCategoryFilter;
import com.myntra.homemenupages.HomePage;
import com.myntra.homemenupages.ProductFilterPage;
import com.myntra.listeners.MyListeners;
import com.myntra.utils.LoggerUtil;
import com.myntra.utils.WaitFor;

@Listeners(MyListeners.class)
public class ProductFilterPageTest extends TestBase {
	private static final Logger log = (Logger) LoggerUtil.getLogger(ProductFilterPageTest.class);

	@Test(dataProvider = "CategoryFilterData", dataProviderClass = DataProviderForCategoryFilter.class)
	public void verifyFilterCategoryForProducts(String categoryName) throws InterruptedException {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductFilterPage filterPage = new ProductFilterPage();
		filterPage.clearAll();
		filterPage.applyCategoryFilter(categoryName);
		boolean isFilterPresent = filterPage.isCategoryFilterPresent(categoryName);
		if (isFilterPresent) {
			boolean categoryfilterStatus = filterPage.verifyProductByCategoryFilters(categoryName);
			Assert.assertTrue(categoryfilterStatus, "The filter is not applied properly for : " + categoryName);
		} else {
			log.info("Invalid category handled correctly: " + categoryName);
			Assert.assertFalse(isFilterPresent, "Invalid filter should not be present: " + categoryName);
		}
	}

	@Test
	public void verifyBrandFilterForProducts() throws InterruptedException {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductFilterPage filterPage = new ProductFilterPage();
		filterPage.applyBrandFilter("Aura");
		filterPage.clickOnBrand();
		boolean filterStatus = filterPage.verifyProductByBrandFilters("Aura");
		Assert.assertTrue(filterStatus, "The brand filter is not applied properly");

	}

	@Test
	public void verifyPriceFilterForProducts() throws InterruptedException {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductFilterPage filterPage = new ProductFilterPage();
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
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductFilterPage filterPage = new ProductFilterPage();
		String colorName = "Green";
		filterPage.applyColorFilter(colorName);
		boolean status = filterPage.isColorFilterApplied(colorName);
		Assert.assertTrue(status, "Color filter not applied: " + colorName);

	}

	@Test
	public void verifyApplyFiltersOnProduct() {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductFilterPage filterPage = new ProductFilterPage();
		String categoryName = "Curtains";
		String brandName = "Aura";
		String colorName = "Red";
		filterPage.clearAll();
		filterPage.applyCategoryFilter(categoryName);
		filterPage.clickOnCategoryFilter(categoryName);
		boolean isCategoryFilterPresent = filterPage.isCategoryFilterPresent(categoryName);
		Assert.assertTrue(isCategoryFilterPresent, "Category filter not applied: " + categoryName);
		filterPage.applyBrandFilter(brandName);
		filterPage.clickOnBrandName();
		boolean isBrandFilterApplied = filterPage.verifyProductByBrandFilters(brandName);
		Assert.assertTrue(isBrandFilterApplied, "Brand filter not applied: " + brandName);
		filterPage.applyColorFilter(colorName);
		filterPage.clickOnColorFilter(colorName);
		boolean isColorFilterApplied = filterPage.isColorFilterApplied(colorName);
		Assert.assertTrue(isColorFilterApplied, "Color filter not applied: " + colorName);

	}

}
