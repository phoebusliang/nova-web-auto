package web.auto.stepdefs.cn;


import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import web.auto.pageobjects.us.ICareHomePageObject;
import web.auto.pageobjects.us.ICareLoginPageObject;
import web.auto.runtime.SharedDriver;

import java.util.List;

public class ICareHomeStepDefs {
    private ICareHomePageObject careHomePage;
    private WebDriver webDriver;

    public ICareHomeStepDefs() {
        this.webDriver = new SharedDriver();
        careHomePage = new ICareHomePageObject(webDriver);
    }

    @Given("The table looks like")
    public void checkTable(DataTable table) {
        careHomePage.checkMonitorManagementTable(table);
    }

    @When("Go to the navigator \"(.*?)\"")
    public void clickNav(String nav) {
        careHomePage.selectItem(nav);
    }

    @Then("The screen status should be \"(.*?)\"")
    public void checkScreenStatus(String status) {
        careHomePage.checkStatus(status);
    }
}
