package com.epay.ls.base;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

//import com.wallet.utils.WalletExceptionHandler;

public class SeBase extends LuckyScratchBase {

	public SeBase() {

	}

	/**
	 * Method to know the current executing Action/Method
	 * 
	 * @return
	 */
	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	/**
	 * To get the current date/time
	 * 
	 * @param currentTimeInstance
	 * @return
	 */
	public static String getCurrentTime() {
		String currentTimeDate = null;

		try {
			LocalDateTime dateTime = LocalDateTime.now();
			currentTimeDate = dateTime.toString();
			currentTimeDate = currentTimeDate.replace("T", " ");
			/*
			 * Calendar calendar = Calendar.getInstance(); currentTimeInstance =
			 * calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH)
			 * + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "-" +
			 * calendar.get(Calendar.HOUR_OF_DAY) + "-" +
			 * calendar.get(Calendar.MINUTE) + "-" +
			 * calendar.get(Calendar.SECOND) + "-" +
			 * calendar.get(Calendar.MILLISECOND);
			 */
		} catch (Exception e) {

		}

		return currentTimeDate;
	}

	/**
	 * @param element
	 * @param value
	 * 
	 */
	public static void sendKeys(WebElement element, String value) {
		// elementToBeClickable(driver, 20, element);
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * Method to select an option in dropdown
	 * 
	 * @param elemet
	 * @param option
	 */
	public static void selectDropdown(WebElement element, String option) {
		try {
			new Select(element).selectByValue(option);
		} catch (Exception e) {

		}
	}

	/**
	 * Method to switch frames
	 * 
	 * @param driver
	 * @param element
	 */
	public static void switchToFrame(WebDriver driver, WebElement element) {
		try {
			// ------>
			driver.switchTo().frame(element);
		} catch (Exception e) {

		}

	}

	/**
	 * Method to perform drag and drop operation
	 * 
	 * @param driver
	 * @param source
	 * @param target
	 */
	public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
		try {
			Actions action = new Actions(driver);
			action.dragAndDrop(source, target).build().perform();
			action.release();
		} catch (Exception e) {

		}
	}

	/**
	 * Method to get hoverMessage - ToolTip
	 * 
	 * @param element
	 * @param tooltip
	 */
	public static String getToolTip(WebElement element) {
		String tooltip = null;
		try {
			tooltip = element.getAttribute("title");
		} catch (Exception e) {

		}
		return tooltip;
	}

	public static void webDriverWait() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for DoubleClick
	 * 
	 * @param driver
	 * @param element
	 */
	public static void doubleClick(WebDriver driver, WebElement element) {
		try {
			// -----
			Actions action = new Actions(driver).doubleClick();
			action.build().perform();
		} catch (Exception e) {

		}

	}

	/**
	 * Method for clearing an element
	 * 
	 * @param element
	 */
	public static void clear(WebElement element) {
		element.clear();
	}

	/**
	 * Method for clicking on an element
	 * 
	 * @param element
	 */
	public static void click(WebElement element) {
		try {
			// ----
			if (element.isEnabled()) {
				element.click();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Method to switch to parent frame
	 * 
	 * @param driver
	 */
	public static void SwitchToParentFrame(WebDriver driver) {
		try {
			driver.switchTo().parentFrame();
		} catch (Exception e) {

		}
	}

	/**
	 * Method to navigate to URL
	 * 
	 * @param URL
	 */
	public static void getURL(WebDriver driver, String URL) {
		try {
			driver.get(URL);
		} catch (Exception e) {

		}
	}

	/**
	 * Method to navigate to URL
	 * 
	 * @param driver
	 * @param URL
	 */
	public static void navigateToURL(WebDriver driver, String URL) {
		try {
			driver.navigate().to(URL);
		} catch (Exception e) {

		}
	}

	/**
	 * Method to close a window
	 * 
	 * @param driver
	 */
	public static void closeWindow(WebDriver driver) {
		try {
			driver.close();
		} catch (Exception e) {

		}
	}

	/**
	 * Method to quit window
	 * 
	 * @param driver
	 */
	public static void quitWindow(WebDriver driver) {
		try {
			driver.quit();
		} catch (Exception e) {

		}
	}

	/**
	 * Method to get current window handle
	 * 
	 * @param driver
	 * @return windowHandle
	 */
	public static String getCurrentWindowHandle(WebDriver driver) {
		String windowHandle = null;
		try {
			windowHandle = driver.getWindowHandle();
		} catch (Exception e) {

		}
		return windowHandle;
	}

	/**
	 * Method to get all available window handles
	 * 
	 * @param driver
	 * @return windowHandles
	 */
	public static Set<String> getWindowHandles(WebDriver driver) {
		Set<String> windowHandles = null;
		try {
			windowHandles = driver.getWindowHandles();
		} catch (Exception e) {

		}
		return windowHandles;
	}

	/**
	 * Method to switch window
	 * 
	 * @param switchToNewWindow
	 * @param currentWindow
	 */
	public static void switchToNewWindow(WebDriver driver, String currentWindow) {
		String newWindow = null;
		try {
			Set<String> set = driver.getWindowHandles();
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()) {
				String s = iterator.next();
				if (!s.equals(currentWindow)) {
					newWindow = s;
				}
			}
			driver.switchTo().window(newWindow);
		} catch (Exception e) {

		}
	}

	/**
	 * @param driver
	 */
	public static void acceptAlert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {

		}
	}

	/**
	 * @param driver
	 */
	public static void dismissAlert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (Exception e) {

		}
	}

	/**
	 * @param driver
	 * @return pageTitle
	 */
	public static String getTitle(WebDriver driver) {
		String pageTitle = null;
		try {
			pageTitle = driver.getTitle();
		} catch (Exception e) {

		}
		return pageTitle;
	}

	/**
	 * @param driver
	 * @param element
	 * @param timeOutInSec
	 * 
	 */
	public static void explicitWaitElementClickable(WebDriver driver, WebElement element, int timeOutInSec) {

	}

}
