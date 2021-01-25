package web.auto.stepdefs.cn;


import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import web.auto.pageobjects.us.HomePageObject;
import web.auto.pageobjects.us.ICareLoginPageObject;
import web.auto.runtime.SharedDriver;

import java.util.List;

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
