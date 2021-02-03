package web.auto.stepdefs.cn;


import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import web.auto.pageobjects.cn.ICareLoginPageObject;
import web.auto.runtime.SharedDriver;

public class ICareLoginStepDefs {
    private ICareLoginPageObject careLoginPage;
    private WebDriver webDriver;

    public ICareLoginStepDefs() {
        this.webDriver = new SharedDriver();
        careLoginPage = new ICareLoginPageObject(webDriver);
    }

    @When("Login with username \"(.*?)\" and password \"(.*?)\"$")
    public void loginWithUserInfo(String username, String password) {
        careLoginPage.injectJQuery();
        careLoginPage.loginCare(username, password);
    }
}
