package Pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;
import step_definitions.BaseTest;

import static helpers.Device.device.android;
import static helpers.Device.device.ios;
import static helpers.Device.platform;
import static helpers.Helpers.*;

public class LoginPage extends BaseTest{


    @AndroidFindBy(id = "com.phone.dev:id/username")
    @iOSFindBy(id = "cross")
    public static WebElement username;

    @AndroidFindBy(id = "com.phone.dev:id/password")
    @iOSFindBy(id = "cross")
    public static WebElement password;

    @AndroidFindBy(id = "com.android.packageinstaller:id/submit")
    public static WebElement submit;


    public void loginToApp()
    {

        setText(username, "user");
        setText(password,"password");
        if(platform ==android)
        {
            click(submit);
        }

    }
}