package com.epay.ls.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epay.ls.base.LuckyScratchBase;
import com.epay.ls.library.LuckyScratchPage;
import com.epay.utils.ExcelUtils;

public class LuckyScratchTest extends LuckyScratchPage {

	LuckyScratchBase transactionPage = null;
	String transDetails, emailID;
	static int columnNo = 0;

	@BeforeMethod
	public void setUp() {
		transactionPage = new LuckyScratchBase();
		initilization();
		userLogin();
		navigateToCashier();
	}

	@Test(dataProvider = "transactionData")
	public void initiateTransactions(String name, String streetName, String cityName, String stateName, String zipCode,
			String mobileNo, String currencyID, String cc, String amount) {
		int intgMethod = Integer.parseInt(properties.getProperty("Integration_Flow_Method"));
		navigateToPostTransaction();
		switch (intgMethod) {

		case 1:
			emailID = transactionPayvision3D(name, streetName, cityName, stateName, zipCode, mobileNo, currencyID, cc,
					amount);
			break;
		case 2:
			emailID = transaction2D(name, streetName, cityName, stateName, zipCode, mobileNo, currencyID, cc, amount);
			break;
		}
		webDriverWait();
		transDetails = getTransactionDetails();
		columnNo++;
		new ExcelUtils().setCellData(columnNo, transDetails, name, currencyID, getErrorDetails(), emailID, cc, amount);
	}

	@DataProvider
	public Object[][] transactionData() {
		Object[][] data;
		data = ExcelUtils.getTestData(properties.getProperty("DataSheet_LS"));
		return data;
	}

	@AfterMethod
	public void tearDown() {
		// driver.close();
		driver.quit();
	}

}
