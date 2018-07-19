package com.epay.ls.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epay.ls.base.SeBase;

public class LuckyScratchPage extends SeBase {

	public LuckyScratchPage() {

	}

	private static String getChildWindow() {
		Set<String> allWindow = driver.getWindowHandles();
		Object[] window = allWindow.toArray();
		return window[1].toString();
	}

	public static void userLogin() {

		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(properties.getProperty("usr_login"));
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(properties.getProperty("usr_pwd"));
		driver.findElement(By.xpath("//div//img[@src='imgs/login.gif']")).click();
	}

	public static String getTransactionDetails() {
		String[] outputParams = { "trans_id", "error_code" };
		String outputDetails = "Details ";
		Document document = Jsoup.parse(driver.getPageSource());
		for (String currentParameter : outputParams) {
			Elements nodes = document.getElementsByTag(currentParameter);
			for (Element nodeElt : nodes) {
				String cdata = nodeElt.ownText();
				outputDetails = outputDetails.concat(cdata).concat(" ");
			}
		}
		System.out.println(outputDetails);
		return outputDetails;
	}

	public static void navigateToCashier() {

		driver.navigate().to("http://luckyscratch.office-cons.net/cashier8.jsp");
	}

	public static void navigateToPostTransaction() {

		WebElement submit = driver.findElement(By.xpath("//input[@name='Submit' and @value='Submit']"));
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(submit)).click();
	}

	public static String transaction2D(String Name, String street, String city, String state, String zipCode,
			String mobile, String currency, String cc, String amounts) {

		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 20); wait.until(
		 * ExpectedConditions.visibilityOfElementLocated(By.
		 * xpath("//input[@name='postUrl' and @id='postUrl']"))) .clear();
		 * WebElement birthDate =
		 * driver.findElement(By.xpath("//input[@id='birthdate']"));
		 * clear(birthDate);
		 */
		driver.switchTo().window(getChildWindow());
		WebElement url = driver.findElement(By.xpath("//input[@name='postUrl' and@id='postUrl']"));
		sendKeys(url, properties.getProperty("action_URL"));
		WebElement firstName = driver.findElement(By.xpath("//input[@name='first_name']"));
		sendKeys(firstName, getFirstName(Name).toString());
		WebElement lastName = driver.findElement(By.xpath("//input[@id='last_name']"));
		sendKeys(lastName, getLastName(Name));
		WebElement emailId = driver.findElement(By.xpath("//input[@name='customer_email']"));
		sendKeys(emailId, getEmail(Name));
		WebElement address = driver.findElement(By.xpath("//input[@id='address']"));
		sendKeys(address, street);
		WebElement cityName = driver.findElement(By.xpath("//input[@id='city']"));
		sendKeys(cityName, city);
		WebElement stateName = driver.findElement(By.xpath("//input[@name='province_state']"));
		sendKeys(stateName, state);
		WebElement postalCode = driver.findElement(By.xpath("//input[@name='postal_code']"));
		sendKeys(postalCode, zipCode);
		WebElement mobileNo = driver.findElement(By.xpath("//input[@name='phone']"));
		sendKeys(mobileNo, getPhoneNo(mobile));
		WebElement amount = driver.findElement(By.xpath("//input[@id='amount']"));
		sendKeys(amount, amounts.toString());
		WebElement currencyCode = driver.findElement(By.xpath("//input[@id='currency']"));
		sendKeys(currencyCode, currency);
		WebElement profile = driver.findElement(By.xpath("//input[@id='profile_id']"));
		sendKeys(profile, properties.getProperty("profileId"));
		WebElement passWord = driver.findElement(By.xpath("//input[@name='pwd']"));
		sendKeys(passWord, properties.getProperty("password"));
		WebElement enableKey = driver.findElement(By.xpath(
				"//input[@value='generate']//parent::td//following-sibling::td//following-sibling::td//input[@value='Enable']"));
		enableKey.click();
		WebElement generate = driver.findElement(By.xpath("//input[@value='generate']"));
		generate.click();
		WebElement callbackUrl = driver.findElement(By.xpath("//input[@name='callbackurl']"));
		sendKeys(callbackUrl, properties.getProperty("callback_Url"));
		WebElement remoteIP = driver.findElement(By.xpath("//input[@name='ip_addr']"));
		sendKeys(remoteIP, getRemoteAddress());
		WebElement methodID = driver.findElement(By.xpath("//input[@name='methodid']"));
		sendKeys(methodID, properties.getProperty("methodId"));
		WebElement testMode = driver.findElement(By.xpath("//input[@id='test_mode']"));
		sendKeys(testMode, properties.getProperty("test_Mode"));
		WebElement cardName = driver.findElement(By.xpath("//input[@id='field_5']"));
		sendKeys(cardName, getLastName(Name));
		WebElement creditCard = driver.findElement(By.xpath("//input[@name='field_1']"));
		sendKeys(creditCard, cc.toString());
		WebElement cvvValue = driver.findElement(By.xpath("//input[@name='field_2']"));
		sendKeys(cvvValue, getCvv(mobile));
		WebElement year = driver.findElement(By.xpath("//select[@name='field_4']"));
		selectDropdown(year, "2021");
		driver.findElement(By.xpath("//input[@name='Submit']")).click();
		return getEmail(Name);

	}

	private static String getRemoteAddress() {
		String ip = properties.getProperty("remote_Address");
		Random random = new Random();
		ip = ip.concat(Integer.toString(random.nextInt(225)));
		return ip.toString();
	}

	private static String getEmail(String email) {
		String[] mailId = email.split("[^\\w]+");
		return mailId[0].concat("@").concat(mailId[1]).concat("mail.com").toLowerCase().toString();
	}

	public static String getErrorDetails() {
		String cdata = null;
		Document document = Jsoup.parse(driver.getPageSource());
		Elements nodes = document.getElementsByTag("error");
		for (Element nodeElt : nodes) {
			cdata = nodeElt.ownText();
			cdata = cdata.replaceAll("[^\\w]+", "-").toString();
		}
		return cdata;
	}

	private static String getPhoneNo(String phone) {
		String[] mobileNo = phone.split("-");
		phone = "";
		for (int i = 0; i < mobileNo.length; i++) {
			phone = phone.concat(mobileNo[i]);
		}
		phone = phone.toString();
		return phone;
	}

	private static String getFirstName(String name) {
		String n = new String(name);
		String[] fName = n.split("[^\\w]+");
		return fName[0];
	}

	private static String getLastName(String name) {
		String[] lName = name.split("[^\\w]+");
		return lName[2].toString();
	}

	private static String getCvv(String cvv) {
		String[] cvvNo = cvv.split("-");
		return cvvNo[0].toString();
	}

	public static String getCallBackStatus(String Id) {
		String dbUrl = properties.getProperty("dbUrl");
		String usrName = properties.getProperty("dbUser");
		String password = properties.getProperty("dbPwd");
		String queryStrings = "select httpcode from tbl_callbackQueue where int_transactionid=" + Id;
		String callBackStatus = "NO";
		try {
			// int_transactionid/httpcode//
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(dbUrl, usrName, password);
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(queryStrings);
			while (resultset.next()) {
				String s = resultset.getString(1);
				if (s.equalsIgnoreCase("200")) {
					callBackStatus = "YES";
				} else if (!s.equalsIgnoreCase("200")) {
					callBackStatus = "NO";
				}
			}
			resultset = null;
			System.out.println(callBackStatus);
		} catch (SQLException se) {

		} catch (ClassNotFoundException ce) {

		}
		return callBackStatus;
	}

}
