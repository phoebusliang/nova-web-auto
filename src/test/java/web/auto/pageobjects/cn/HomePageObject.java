package web.auto.pageobjects.cn;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import web.auto.runtime.LoadConfig;

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

    public void goSpecificManagementPanel(String item) {
        selectHeader(1);
        basicOperation.findElementsByScript(LoadConfig.load("itemLeft") + item + LoadConfig.load("itemRight")).get(0).click();
    }

    private void selectHeader(Integer index) {
        Actions builder = new Actions(webDriver);
        WebElement headerBtn = basicOperation.findElementsByScript(LoadConfig.load("headerLeft") + index.toString() + LoadConfig.load("headerRight")).get(0);
        builder.moveToElement(headerBtn).build().perform();
    }
}