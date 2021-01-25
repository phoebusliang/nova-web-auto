package web.auto.pageobjects.us;

import org.apache.tools.ant.taskdefs.LoadProperties;
import org.jsoup.Connection;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.auto.runtime.LoadConfig;

import java.util.List;

public class HomePageObject extends BasePage {
    public HomePageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickMenuNav() {
        basicOperation.findElementsByScript(LoadConfig.load("menuNav")).get(0).click();
    }

    public void checkNavItems(String items) {
        basicOperation.getFromByScript(LoadConfig.load("navItems"), result -> result.toString().contains(items.replace(",", "")));
    }

    public void checkPassedCanvasNum(String number) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        String num = js.executeScript("return myBar.config.data.datasets[0].data[0];").toString();
        Assert.assertEquals(number, num);
    }

    public void clickLogin() {
        basicOperation.findElementsByScript(LoadConfig.load("loginBtn")).get(0).click();
    }

    public void checkLoginErrorMsg(String msg) {
        basicOperation.getFromByScript(LoadConfig.load("errorMsg"), result -> result.toString().contains(msg));
    }

    public void skipTips() {
        basicOperation.findElementsByScript(LoadConfig.load("skipTips")).get(0).click();
    }

    public void selectComponent(String component) {
        basicOperation.findElementsByScript(LoadConfig.load("selectComponentLeft") + component + LoadConfig.load("selectComponentRight")).get(0).click();
    }
}