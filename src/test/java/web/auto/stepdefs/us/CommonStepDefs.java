package web.auto.stepdefs.us;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import web.auto.pageobjects.us.BasePage;
import web.auto.pageobjects.us.OrderPageObject;
import web.auto.runtime.SharedDriver;

public class CommonStepDefs {
    private BasePage basePage;
    private OrderPageObject serviceOrderPage;
    private WebDriver webDriver;

    public CommonStepDefs() {
        this.webDriver = new SharedDriver();
        basePage = new BasePage(webDriver);
        serviceOrderPage = new OrderPageObject(webDriver);
    }

    @Given("^I open the \"(.*?)\" page \"(.*?)\"$")
    public void I_am_on_the_website(String page, String url) throws Throwable {
        basePage.openPage(url);
        basePage.setWindowSize(1280, 1024);
    }
}
