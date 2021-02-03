package web.auto.pageobjects.us;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.auto.runtime.LoadConfig;

import java.util.List;

public class ICareWorkingOrderPageObject extends BasePage {
    public ICareWorkingOrderPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void createWorkingOrder(String name, String detail) {
        clickCreateOrderBtn();
        inputOrderName(name);
        inputOrderDetail(detail);
    }

    public void clickCreateOrderBtn() {
        basicOperation.findElementsByScript(LoadConfig.load("createAndEditWorkingOrderBtn")).get(0).click();
    }

    public void clickEditOrderBtn() {
        basicOperation.findElementsByScript(LoadConfig.load("createAndEditWorkingOrderBtn")).get(1).click();
    }

    public void inputOrderName(String name) {
        basicOperation.findElementsByScript(LoadConfig.load("orderName")).get(0).sendKeys(name);
    }

    public void inputOrderDetail(String detail) {
        basicOperation.findElementsByScript(LoadConfig.load("orderDetail")).get(0).sendKeys(detail);
    }

    public void confirmOrderCreation() {
        basicOperation.findElementsByScript(LoadConfig.load("OrderConfirmAndCancelBtn")).get(0).click();
    }

    public void cancelOrderCreation() {
        basicOperation.findElementsByScript(LoadConfig.load("orderConfirmAndCancelBtn")).get(1).click();
    }

    public void selectOrders(String index) {
        basicOperation.findElementsByScript(LoadConfig.load("orderItems")).get(Integer.parseInt(index) - 1).click();
    }

    public void checkListNum(String num) {
        basicOperation.getFromByScript(LoadConfig.load("orderItems"), result -> result.toString().equalsIgnoreCase(num));
    }


}