package web.auto.stepdefs.cn;

import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import web.auto.pageobjects.cn.BasePage;
import web.auto.pageobjects.cn.ICareMonitorMgtPageObject;
import web.auto.pageobjects.cn.ICareLoginPageObject;
import web.auto.runtime.SharedDriver;

public class CommonStepDefs {
    private BasePage basePage;
    private ICareMonitorMgtPageObject careMonitorMgtPage;
    private ICareLoginPageObject loginPg;
    private WebDriver webDriver;

    public CommonStepDefs() {
        this.webDriver = new SharedDriver();
        basePage = new BasePage(webDriver);
        loginPg = new ICareLoginPageObject(webDriver);
    }

    @Given("^I open the \"(.*?)\" page \"(.*?)\"$")
    public void I_am_on_the_website(String page, String url) throws Throwable {
        basePage.openPage(url);
        basePage.setWindowSize(1860, 1080);
    }

    @Given("^Login iCare \"(.*?)\" page with username \"(.*?)\" and password \"(.*?)\"$")
    public void loginICare(String url, String username, String password) {
        basePage.openPage(url);
        basePage.setWindowSize(1860, 1080);
        basePage.injectJQuery();
        loginPg.loginCare(username, password);
    }
}
