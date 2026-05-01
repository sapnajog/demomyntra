package com.myntra.dataprovider;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.myntra.utils.ExcelUtils;
import com.myntra.utils.LoggerUtil;

public class DataProviderForPincode {

	private static final Logger log = (Logger) LoggerUtil.getLogger(DataProviderForPincode.class);

	@DataProvider(name = "deliveryData")
	public Object[][] getDeliveryDataCallCommonmMethodsreadAllRowsFromExcelUtils() {

		String filePath = "src/test/resources/CategoryFilterData.xlsx";
		String sheetName = "pincodes";

		List<List<String>> allData = ExcelUtils.getAllRowsData(filePath, sheetName);
		log.info("Data read from Excel: " + allData);

		Object[][] data = new Object[allData.size()][1];

		for (int i = 0; i < allData.size(); i++) {
			data[i][0] = String.valueOf(allData.get(i).get(0)).trim();
		}

		return data;
	}

}
