package web.auto.stepdefs.us;

import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import web.auto.pageobjects.us.BasePage;
import web.auto.pageobjects.us.ICareHomePageObject;
import web.auto.runtime.SharedDriver;

public class CommonStepDefs {
    private BasePage basePage;
    private ICareHomePageObject careHomePage;
    private WebDriver webDriver;

    public CommonStepDefs() {
        this.webDriver = new SharedDriver();
        basePage = new BasePage(webDriver);
        careHomePage = new ICareHomePageObject(webDriver);
    }

    @Given("^I open the \"(.*?)\" page \"(.*?)\"$")
    public void I_am_on_the_website(String page, String url) throws Throwable {
        basePage.openPage(url);
        basePage.setWindowSize(1280, 1024);
    }
}
