package web.auto.pageobjects.us;

import cucumber.api.DataTable;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import web.auto.runtime.LoadConfig;

import java.util.List;

public class ICareHomePageObject extends BasePage {
    public ICareHomePageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkNoTip() {
        basicOperation.findElementsByScript(LoadConfig.load("neverShow")).get(0).click();
    }

    public void closeTips(String str) {
        basicOperation.findElementsByScript(LoadConfig.load("closeTipsLeft") + str + LoadConfig.load("closeTipsRight")).get(0).click();
    }

    public void checkStatusDevicesNum(String status, String num) {
        basicOperation.getFromByScript(LoadConfig.load("careBarStatusLeft") + status + LoadConfig.load("careBarStatusRight"), result -> result.toString().equalsIgnoreCase(num));
    }

    public void goSpecificManagementPanel(String item) {
        selectHeader(1);
        basicOperation.findElementsByScript(LoadConfig.load("itemLeft") + item + LoadConfig.load("itemRight")).get(0).click();
    }

    private void selectHeader(Integer index) {
        Actions builder = new Actions(webDriver);
        WebElement headerBtn = basicOperation.findElementsByScript(LoadConfig.load("headerLeft") + index.toString() + LoadConfig.load("headerRight")).get(0);
        builder.moveToElement(headerBtn).build().perform();
    }

    public void clickManagementSubMenu(String menu) {
        basicOperation.findElementsByScript(LoadConfig.load("mgrSubMenuLeft") + menu + LoadConfig.load("mgrSubMenuRight")).get(0).click();
    }

    public void selectProfileSetting(String setting) {
        basicOperation.findElementsByScript(LoadConfig.load("profileSettingLeft") + setting + LoadConfig.load("profileSettingRight")).get(0).click();
    }

    public void searchWithText(String criteria) {
        basicOperation.findElementsByScript(LoadConfig.load("searchBox")).get(0).sendKeys(criteria);
        clickSearchBtn();
    }

    private void clickSearchBtn() {
        basicOperation.findElementsByScript(LoadConfig.load("searchBtn")).get(0).click();
    }

    public void selectLang(String langItem) {
        Actions builder = new Actions(webDriver);
        WebElement lang = basicOperation.findElementsByScript(LoadConfig.load("langMenu")).get(0);
        builder.moveToElement(lang).build().perform();
        basicOperation.findElementsByScript(LoadConfig.load("langItemLeft") + langItem + LoadConfig.load("langItemRight")).get(0).click();
    }

    public void selectItem(String item) {
        basicOperation.findElementsByScript(LoadConfig.load("navLeft") + item + LoadConfig.load("navRight")).get(0).click();
    }

    public void checkMonitorManagementTable(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);

        for (int i = 1; i < rows.size(); i++) {
            List<String> row = rows.get(i);
            String recordString = "";
            for (String s : row) {
                recordString += s + "";
            }
            String finalRecordString = recordString;
            basicOperation.getFromByScript(LoadConfig.load("monitorTableLeft") + String.valueOf(i-1) + LoadConfig.load("monitorTableRight"), result -> result.toString().equalsIgnoreCase(finalRecordString));
        }
    }
}