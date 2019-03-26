package step_definitions;

import Pages.LoginPage;
import cucumber.api.java.en.Given;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Jaya on 04/01/18.
 */
public class step_login {

    LoginPage login;

    public step_login()
    {
        login = new LoginPage();

        PageFactory.initElements(new AppiumFieldDecorator(BaseTest.driver),login);

    }

    @Given("^I login to the app")
    public void LoginToApplication() throws Exception {
        login.loginToApp();
    }


}


