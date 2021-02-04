package web.auto.stepdefs.cn;


import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import web.auto.pageobjects.cn.ICareMonitorMgtPageObject;
import web.auto.runtime.SharedDriver;

public class ICareMonitorMgtStepDefs {
    private ICareMonitorMgtPageObject careMonitorMgtPage;
    private WebDriver webDriver;

    public ICareMonitorMgtStepDefs() {
        this.webDriver = new SharedDriver();
        careMonitorMgtPage = new ICareMonitorMgtPageObject(webDriver);
    }

    @Given("The table looks like$")
    public void checkTable(DataTable table) {
        careMonitorMgtPage.checkMonitorManagementTable(table);
    }

    @When("Go to the navigator \"(.*?)\"$")
    public void clickNav(String nav) {
        careMonitorMgtPage.selectItem(nav);
    }

    @Then("The screen status should be \"(.*?)\"$")
    public void checkScreenStatus(String status) {
        careMonitorMgtPage.checkStatus(status);
    }

    @When("Go to the detail of the \"(.*?)\" monitor$")
    public void clickMonitorItemDetail(String index) {
        careMonitorMgtPage.clickMonitorDetailByIndex(String.valueOf(Integer.parseInt(index) - 1));
    }

    @Then("The work information should be \"(.*?)\"$")
    public void checkWorkInfo(String workInfo) {
        careMonitorMgtPage.checkWorkSpaceInfo(workInfo);
    }

    @Then("The send card information should be \"(.*?)\"$")
    public void checkSendCardInfo(String cardInfo) {
        careMonitorMgtPage.checkDeviceInfo(cardInfo);
    }

    @Then("^The hint message in monitor management page should be \"(.*?)\"$")
    public void checkHintMsg(String msg){
        careMonitorMgtPage.checkMonitorDetailHintMessage(msg);
    }

    @Then("The receive card number should be \"(.*?)\"$")
    public void checkReceiveCardTotal(String num) {
        careMonitorMgtPage.checkReceiveCardNum(num);
    }

    @Then("Switch the card tab to \"(.*?)\"$")
    public void clickCardTab(String status) {
        careMonitorMgtPage.toggleCardTab(status);
    }

    @Then("The status of the receive cards should be \"(.*?)\"$")
    public void checkCardStatus(String status) {
        careMonitorMgtPage.checkReceiveStatus(status);
    }

    @Then("The basic information of receive cards should be \"(.*?)\"$")
    public void checkCardBasicInfo(String status) {
        careMonitorMgtPage.checkBasicInfo(status);
    }

    @Then("Switch the visual of receive cards to the radio \"(.*?)\"$")
    public void switchVisualRadio(String status) {
        careMonitorMgtPage.clickVisualRadio(status);
    }

    @Then("The visual line number should be \"(.*?)\"$")
    public void checkTotalLineNum(String lines){
        careMonitorMgtPage.checkTotalLineNum(lines);
    }

    @Then("The line number with color Green, Yellow and Red should be \"(.*?)\"$")
    public void checkLineNumberWithColor(String colorLine){
        careMonitorMgtPage.checkLineNumWithColor(colorLine);
    }

    @When("^Swith the monitor management tab to \"(.*?)\"$")
    public void switchMgtTab(String tab){
        careMonitorMgtPage.switchPageTab(tab);
    }
}
