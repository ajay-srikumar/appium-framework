package helpers;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public abstract class Helpers {

  public static AppiumDriver driver;
  private static WebDriverWait driverWait;
  private static WebDriverWait longWait;


  public static void init(AppiumDriver driver) {
    Helpers.driver = driver;
    int timeoutInSeconds = 4;
    int longTimeoutInSeconds = 9;
    driverWait = new WebDriverWait(Helpers.driver, timeoutInSeconds);
    longWait = new WebDriverWait(Helpers.driver, longTimeoutInSeconds);
  }

  public static void click(WebElement element) {
    longWait.until(ExpectedConditions.elementToBeClickable(element));
    element.click();
  }

  public static void scrollAndClick(WebElement element) {
    while (!isElementClickable(element)) {
      String currentPageSource = driver.getPageSource();
      scroll();
      String pageSourceAfterScroll = driver.getPageSource();
      if (currentPageSource.equals(pageSourceAfterScroll)) break;
    }
    click(element);
  }

  public static void scroll() {
    Dimension dimensions = driver.manage().window().getSize();
    int screenHeightStart = (int) (dimensions.getHeight() * 0.8);
    int screenHeightEnd = (int) (dimensions.getHeight() * 0.2);
    driver.swipe(200, screenHeightStart, 200, screenHeightEnd, 1500);
  }

  public static void scrollAndClick(By by) {
    while (!isElementClickable(by)) {
      String currentPageSource = driver.getPageSource();
      scroll();
      String pageSourceAfterScroll = driver.getPageSource();
      if (currentPageSource.equals(pageSourceAfterScroll)) break;
    }
    driver.findElement(by).click();
  }

  public static void waitForElement(WebElement element) {
    driverWait.until(ExpectedConditions.visibilityOf(element));
  }

  public static void longWaitForElement(WebElement element) {
    longWait.until(ExpectedConditions.visibilityOf(element));
  }

  public static boolean isElementClickable(WebElement element) {
    try {
      driverWait.until(ExpectedConditions.elementToBeClickable(element));
      return true;
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    }
  }

  public static boolean isElementClickableAfterWait(WebElement element) {
    try {
      longWait.until(ExpectedConditions.elementToBeClickable(element));
      return true;
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    }
  }

  public static boolean isElementPresent(WebElement element) {
    try {
      driverWait.until(ExpectedConditions.visibilityOf(element));
      return true;
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    }
  }

  public static boolean isElementClickable(By by) {
    try {
      driverWait.until(ExpectedConditions.elementToBeClickable(by));
      return true;
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    }
  }

  //scrolls up 5 times and checks if the element is present. This is done to avoid infinite scrolls if the particular element is not present.
  public static boolean isElementPresentWithScroll(By by) {
    while (!isElementClickable(by)) {
      String currentPageSource = driver.getPageSource();
      scroll();
      String pageSourceAfterScroll = driver.getPageSource();
      if (currentPageSource.equals(pageSourceAfterScroll)) {
        return false;
      }
    }
    return true;
  }


  //scrolls up 5 times and checks if the element is present. This is done to avoid infinite scrolls if the particular element is not present.
  public static boolean isElementPresentWithScroll(WebElement element) {
    while (!isElementPresent(element)) {
      String currentPageSource = driver.getPageSource();
      scroll();
      String pageSourceAfterScroll = driver.getPageSource();
      if (currentPageSource.equals(pageSourceAfterScroll)) {
        return false;
      }
    }
    return true;
  }


  public static List<WebElement> findElements(By element) {
    try {
      driverWait.until(ExpectedConditions.elementToBeClickable(element));
      return driver.findElements(element);
    } catch (TimeoutException | NoSuchElementException e) {
      return null;
    }

  }

  public static String getText(WebElement element, String text) {
    driverWait.until(ExpectedConditions.visibilityOf(element));
    return element.getText();
  }

  public static void setText(WebElement element, String text)
  {
    driverWait.until(ExpectedConditions.visibilityOf(element));
    element.sendKeys(text);
  }

  public static void clearText(WebElement element) {
    element.clear();
  }
  public static void confirmText(WebElement element, String text){
    driverWait.until(ExpectedConditions.visibilityOf(element));
    Assert.assertEquals("",element.getText(), text);
  }
}