package web.auto.pageobjects.us;

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
}