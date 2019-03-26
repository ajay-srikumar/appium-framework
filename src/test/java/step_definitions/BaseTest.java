package step_definitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.Device;
import helpers.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.springframework.web.client.RestTemplate;
/**
 * Created by ajaysrikumar on 21/07/17.
 */
public class BaseTest {
    public  static AppiumDriver driver;

    @Before
    public void launchDriver() throws Exception
    {
            Device.getPlatform();
            File appDir = new File(new File(System.getProperty("user.dir")), "/apps");
            DesiredCapabilities capabilities = new DesiredCapabilities();

            switch (Device.platform)
            {
                case android:
                    System.out.println("Launching Android driver");
                    File androidApp = new File (appDir, "app-automation-debug.apk");
                    capabilities.setCapability("app", androidApp);
                    capabilities.setCapability("platformName", "Android");
                    capabilities.setCapability("deviceName", "Android");
                    capabilities.setCapability("platformVersion", "8.0");
                    capabilities.setCapability("automationName", "uiautomator2");
                    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                    break;

                case ios:
                    System.out.println("Launching IOS driver");
                    File iosApp = new File (appDir, "my-app.app");
                    capabilities.setCapability("platformVersion", "11.0");
                    capabilities.setCapability("platformName", "ios");
                    capabilities.setCapability("automationName", "XCUITest");
                    capabilities.setCapability("deviceName", "iPhone Simulator");
                    capabilities.setCapability("app", iosApp);
                    driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                    break;
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            Helpers.init(driver);

    }


    public AppiumDriver getDriver()
    {
        return driver;
    }
    @After
    public void tearDown(Scenario scenario) throws Exception{
        String filePath = System.getProperty("user.dir") + "/build/report/failureScreenshots/";

        if (scenario.isFailed()) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(filePath + scenario.getName()+".png"));
        }
        driver.quit();
    }

}
