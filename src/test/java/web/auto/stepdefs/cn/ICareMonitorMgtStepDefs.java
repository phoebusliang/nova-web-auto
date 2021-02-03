package web.auto.stepdefs.cn;


import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import web.auto.pageobjects.cn.ICareHomePageObject;
import web.auto.runtime.SharedDriver;

public class ICareHomeStepDefs {
    private ICareHomePageObject careHomePage;
    private WebDriver webDriver;

    public ICareHomeStepDefs() {
        this.webDriver = new SharedDriver();
        careHomePage = new ICareHomePageObject(webDriver);
    }

    @Given("The table looks like$")
    public void checkTable(DataTable table) {
        careHomePage.checkMonitorManagementTable(table);
    }

    @When("Go to the navigator \"(.*?)\"$")
    public void clickNav(String nav) {
        careHomePage.selectItem(nav);
    }

    @Then("The screen status should be \"(.*?)\"$")
    public void checkScreenStatus(String status) {
        careHomePage.checkStatus(status);
    }

    @When("Go to the detail of the \"(.*?)\" monitor$")
    public void clickMonitorItemDetail(String index) {
        careHomePage.clickMonitorDetailByIndex(String.valueOf(Integer.parseInt(index) - 1));
    }

    @Then("The work information should be \"(.*?)\"$")
    public void checkWorkInfo(String workInfo) {
        careHomePage.checkWorkSpaceInfo(workInfo);
    }

    @Then("The send card information should be \"(.*?)\"$")
    public void checkSendCardInfo(String cardInfo) {
        careHomePage.checkDeviceInfo(cardInfo);
    }

    @Then("The receive card number should be \"(.*?)\"$")
    public void checkReceiveCardTotal(String num) {
        careHomePage.checkReceiveCardNum(num);
    }

    @Then("Switch the card tab to \"(.*?)\"$")
    public void clickCardTab(String status) {
        careHomePage.toggleCardTab(status);
    }

    @Then("The status of the receive cards should be \"(.*?)\"$")
    public void checkCardStatus(String status) {
        careHomePage.checkReceiveStatus(status);
    }

    @Then("The basic information of receive cards should be \"(.*?)\"$")
    public void checkCardBasicInfo(String status) {
        careHomePage.checkBasicInfo(status);
    }

    @Then("Switch the visual of receive cards to the radio \"(.*?)\"$")
    public void switchVisualRadio(String status) {
        careHomePage.clickVisualRadio(status);
    }

    @Then("The visual line number should be \"(.*?)\"$")
    public void checkTotalLineNum(String lines){
        careHomePage.checkTotalLineNum(lines);
    }

    @Then("The line number with color Green, Yellow and Red should be \"(.*?)\"$")
    public void checkLineNumberWithColor(String colorLine){
        careHomePage.checkLineNumWithColor(colorLine);
    }

    @When("^Swith the monitor management tab to \"(.*?)\"$")
    public void switchMgtTab(String tab){
        careHomePage.switchPageTab(tab);
    }
}
