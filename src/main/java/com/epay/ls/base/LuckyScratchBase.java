package com.epay.ls.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LuckyScratchBase {

	public static WebDriver driver = null;
	public static Properties properties = null;
	// public static WebEventListener eventListener = null;
	// public static EventFiringWebDriver e_driver = null;
	public FileInputStream file;

	// public static Logger log = Logger.getLogger(WalletBase.class.getClass());
	// protected static InternetExplorerDriverService service;

	public LuckyScratchBase() {

		try {
			file = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\epay\\config\\config.properties");
			properties = new Properties();
			properties.load(file);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public static void initilization() {
		System.out.println("===================================================================");

		DesiredCapabilities capability;
		String browsername = properties.getProperty("browser");
		if (browsername.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe");

			/*
			 * FirefoxProfile profile = new FirefoxProfile();
			 * profile.setPreference(
			 * "capability.policy.default.Window.QueryInterface", "allAccess");
			 * profile.setPreference(
			 * "capability.policy.default.Window.frameElement.get","allAccess");
			 * profile.setPreference(
			 * "capability.policy.default.HTMLDocument.compatMode.get",
			 * "allAccess"); profile.setPreference(
			 * "capability.policy.default.Window.pageYOffset.get", "allAccess");
			 * profile.setPreference(
			 * "capability.policy.default.Window.mozInnerScreenY.get",
			 * "allAccess");
			 * profile.setPreference("network.proxy.type",ProxyType.AUTODETECT.
			 * ordinal()); profile.setAcceptUntrustedCertificates(false);
			 * profile.setAssumeUntrustedCertificateIssuer(true);
			 * capability.setCapability(FirefoxDriver.PROFILE, profile);
			 */

			capability = DesiredCapabilities.firefox();
			capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			driver = new FirefoxDriver(capability);

		} else if (browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver");
			capability = DesiredCapabilities.chrome();
			capability.setPlatform(Platform.WINDOWS);
			capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
					org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT);
			driver = new ChromeDriver(capability);

		} else if (browsername.equals("IE")) {
			System.setProperty("webdriver.ie.driver", ".\\src\\main\\resources\\IEdriver");
			/*
			 * service = new
			 * InternetExplorerDriverService.Builder().usingAnyFreePort()
			 * .withLogFile(new File("./TestOutput/Logs/IEDriver.log"))
			 * .withLogLevel(InternetExplorerDriverLogLevel.TRACE).build(); try
			 * { service.start(); } catch (IOException e) { throw
			 * Throwables.propagate(e); driver = new
			 * InternetExplorerDriver(service, capability); }
			 */
			capability = DesiredCapabilities.internetExplorer();
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(capability);
		}

		/*
		 * e_driver = new EventFiringWebDriver(driver);
		 * 
		 * // create object of EventListner to register it with
		 * EventFiringWebDriver eventListener = new WebEventListener();
		 * e_driver.register(eventListener); driver = e_driver;
		 */

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty("page_timeout")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("element_timeout")),
				TimeUnit.SECONDS);
		driver.get(properties.getProperty("LS_URL"));
	}

}
