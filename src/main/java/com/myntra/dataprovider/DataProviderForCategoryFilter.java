package com.myntra.dataprovider;

import org.testng.annotations.DataProvider;

import com.myntra.utils.ExcelUtils;

import java.util.List;

public class DataProviderForCategoryFilter {

	@DataProvider(name = "CategoryFilterData")
	public Object[][] getCategoryFilterData() {

		String filePath = "src/test/resources/CategoryFilterData.xlsx";
		String sheetName = "categoryFilterInvalidData";

		List<List<String>> allData = ExcelUtils.getAllRowsData(filePath, sheetName);

		Object[][] data = new Object[allData.size()][1];

		for (int i = 0; i < allData.size(); i++) {
			data[i][0] = allData.get(i).get(0);
		}

		return data;
	}
}
