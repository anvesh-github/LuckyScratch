package com.epay.ls.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Samp {
	public static FileInputStream files;
	public static Properties properties;
	public static WebDriver driver;

	public static void main(String[] k) throws IOException, AWTException, InterruptedException {

		files = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\epay\\config\\config.properties");
		properties = new Properties();
		properties.load(files);

		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty("page_timeout")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("element_timeout")),
				TimeUnit.SECONDS);
		driver.get("http://luckyscratch:vp3tk01808ZD8Fa@luckyscratch.office-cons.net/default.jsp");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(properties.getProperty("usr_login"));
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(properties.getProperty("usr_pwd"));
		driver.findElement(By.xpath("//div//img[@src='imgs/login.gif']")).click();
		driver.navigate().to("http://luckyscratch.office-cons.net/cashier8.jsp");
		WebElement submit = driver.findElement(By.xpath("//input[@name='Submit' and @value='Submit']"));
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(submit)).click();
		Set<String> allWindow = driver.getWindowHandles();
		Object[] window = allWindow.toArray();
		driver.switchTo().window(window[1].toString());
		WebElement url = driver.findElement(By.xpath("//input[@name='postUrl' and@id='postUrl']"));
		url.clear();
		url.sendKeys(properties.getProperty("action_URL"));

		WebElement firstName = driver.findElement(By.xpath("//input[@name='first_name']"));
		firstName.clear();
		firstName.sendKeys("A");

		WebElement lastName = driver.findElement(By.xpath("//input[@id='last_name']"));
		lastName.clear();
		lastName.sendKeys("B");
		WebElement emailId = driver.findElement(By.xpath("//input[@name='customer_email']"));
		emailId.clear();
		emailId.sendKeys("a@b.com");

		WebElement address = driver.findElement(By.xpath("//input[@id='address']"));
		address.clear();
		address.sendKeys("xyz");
		WebElement cityName = driver.findElement(By.xpath("//input[@id='city']"));
		cityName.clear();
		cityName.sendKeys("xyz");
		WebElement stateName = driver.findElement(By.xpath("//input[@name='province_state']"));
		stateName.clear();
		stateName.sendKeys("IU");
		WebElement postalCode = driver.findElement(By.xpath("//input[@name='postal_code']"));
		postalCode.clear();
		postalCode.sendKeys("12345");
		WebElement mobileNo = driver.findElement(By.xpath("//input[@name='phone']"));
		mobileNo.clear();
		mobileNo.sendKeys("77738399");
		WebElement amount = driver.findElement(By.xpath("//input[@id='amount']"));
		amount.clear();
		amount.sendKeys("20");
		WebElement currencyCode = driver.findElement(By.xpath("//input[@id='currency']"));

		WebElement profile = driver.findElement(By.xpath("//input[@id='profile_id']"));
		profile.clear();
		profile.sendKeys("1127");
		WebElement passWord = driver.findElement(By.xpath("//input[@name='pwd']"));
		passWord.clear();
		passWord.sendKeys("G0fLQGGVPVQ3jmqrP8bA");
		WebElement enableKey = driver.findElement(By.xpath(
				"//input[@value='generate']//parent::td//following-sibling::td//following-sibling::td//input[@value='Enable']"));
		enableKey.click();
		WebElement generate = driver.findElement(By.xpath("//input[@value='generate']"));
		generate.click();
		WebElement callbackUrl = driver.findElement(By.xpath("//input[@name='callbackurl']"));
		callbackUrl.clear();
		callbackUrl.sendKeys("https://www.virtualterminal.cc/api/orphanedCallbacks.php");
		WebElement remoteIP = driver.findElement(By.xpath("//input[@name='ip_addr']"));
		remoteIP.clear();
		remoteIP.sendKeys("207.244.66.34");
		WebElement methodID = driver.findElement(By.xpath("//input[@name='methodid']"));
		methodID.clear();
		methodID.sendKeys("1006");
		WebElement testMode = driver.findElement(By.xpath("//input[@id='test_mode']"));
		testMode.clear();
		testMode.sendKeys("1");
		WebElement cardName = driver.findElement(By.xpath("//input[@id='field_5']"));
		cardName.clear();
		cardName.sendKeys("ABCGADU");
		WebElement creditCard = driver.findElement(By.xpath("//input[@name='field_1']"));
		creditCard.clear();
		creditCard.sendKeys("4711100000000000");
		WebElement cvvValue = driver.findElement(By.xpath("//input[@name='field_2']"));
		cvvValue.clear();
		cvvValue.sendKeys("321");
		WebElement year = driver.findElement(By.xpath("//select[@name='field_4']"));
		Select select = new Select(year);
		select.selectByValue("2021");
		driver.findElement(By.xpath("//input[@name='Submit']")).click();

		WebElement otpselect = driver.findElement(By.xpath("//select[@name='returnCode' and @id='returnCode']"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(otpselect));
		Select selects = new Select(otpselect);
		/*
		 * wait.until(ExpectedConditions.elementSelectionStateToBe(otpselect,
		 * true));
		 */
		selects.selectByValue("000.000.000");

		WebElement otpsubmit = driver.findElement(By.xpath("//input[@name='B2' and @type='submit']"));
		otpsubmit.click();
		// driver.switchTo().alert().accept();
		Thread.sleep(4000);
		new Robot().keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(10000);
		WebElement ele = driver.findElement(By.xpath("//td//p"));
		String s = ele.getText();
		System.out.println(s);
	}

}
