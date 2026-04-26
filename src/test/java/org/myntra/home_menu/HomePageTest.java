package org.myntra.home_menu;

import static com.myntra.base.Keyword.driver;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.myntra.base.TestBase;
import com.myntra.homemenupages.HomePage;
import com.myntra.listeners.MyListeners;
import com.myntra.utils.LoggerUtil;


/**
 * This class contains test cases related to HOME menu of Myntra application
 * The app url : https://www.myntra.com/
 */
@Listeners(MyListeners.class)
public class HomePageTest extends TestBase {
	private static final Logger log = (Logger) LoggerUtil.getLogger(HomePageTest.class);

	
	@Test
	public void verifyHomePageLogoIsClickable() {
	    HomePage homepage = new HomePage();
	    homepage.clickOnLogo();
	    Assert.assertTrue(driver.getCurrentUrl().contains("myntra") 
	        || driver.getTitle().toLowerCase().contains("myntra"),
	        "Clicking logo did not navigate to home page");
	}
	
	@Test
	public void verifyLogoClickAfterSearch() throws InterruptedException {
	    HomePage homepage = new HomePage();
	    homepage.searchProduct("BedSheets");
	    homepage.waitForSearchResults();
	    homepage.clickOnLogo();
	    Assert.assertTrue(driver.getCurrentUrl().contains("myntra") 
	        || driver.getTitle().toLowerCase().contains("myntra"),
	        "Logo click did not navigate back to home page after search");
	}
	
	@Test(timeOut = 10000) 
	public void verifySearchResultsLoadTime() throws InterruptedException {
	    HomePage homepage = new HomePage();
	    homepage.searchProduct("BedSheets");
	    homepage.waitForSearchResults();
	    String actualHeader = homepage.getSearchResultHeader();
	    Assert.assertNotNull(actualHeader, "Search results did not load within acceptable time");
	}
	
	@Test
	public void verifySearchWithInvalidProduct() throws InterruptedException {
	    HomePage homepage = new HomePage();
	    homepage.searchProduct("xyznonexistentproduct123");
	    homepage.waitForSearchResults();
	    String actualHeader = homepage.getSearchResultHeader();
	    String expectedMessage = "Earrings";
	    Assert.assertEquals(actualHeader, expectedMessage, "Expected 'no results' message for invalid product search");
	       
	        
	}
	
	@Test
	public void verifyHomePageFunctionality() throws InterruptedException {
		HomePage homepage = new HomePage();
		homepage.clickOnLogo();
		homepage.searchProduct("BedSheets");
		String actualHeader = homepage.getSearchResultHeader();
		homepage.waitForSearchResults();
		log.info("Actual Search Result Text: " + actualHeader);
		String expectedText = "BedSheets";
		Assert.assertTrue(actualHeader.toLowerCase().contains("bedsheets"),"SearchResult text does not contains expected text");
		
	}
} 