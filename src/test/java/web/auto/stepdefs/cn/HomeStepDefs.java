package web.auto.stepdefs.cn;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import web.auto.pageobjects.us.HomePageObject;
import web.auto.runtime.BasicOperation;
import web.auto.runtime.SharedDriver;

public class HomeStepDefs {
    private HomePageObject homePage;
    private WebDriver webDriver;

    public HomeStepDefs() {
        this.webDriver = new SharedDriver();
        homePage = new HomePageObject(webDriver);
    }

    @When("^Go to menu item$")
    public void navToMenu() {
        homePage.clickMenuNav();
    }

    @Then("The nav items should be \"(.*?)\"$")
    public void checkNavItems(String items) {
        homePage.checkNavItems(items);
    }

    @Then("The passed case should be \"(.*?)\"$")
    public void checkPassedCaseInCanvas(String passeedNum) {
        homePage.checkPassedCanvasNum(passeedNum);
    }

    @Then("The Login message should be \"(.*?)\"$")
    public void checkErrorMsg(String msg){
        homePage.checkLoginErrorMsg(msg);
    }
}
