package web.auto.stepdefs.us;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import web.auto.pageobjects.us.HomePageObject;
import web.auto.pageobjects.us.ICareHomePageObject;
import web.auto.runtime.SharedDriver;

public class ICareHomeStepDefs {
    private ICareHomePageObject careHomePage;
    private WebDriver webDriver;

    public ICareHomeStepDefs() {
        this.webDriver = new SharedDriver();
        careHomePage = new ICareHomePageObject(webDriver);
    }
}
