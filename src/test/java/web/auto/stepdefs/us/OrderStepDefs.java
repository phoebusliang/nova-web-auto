package web.auto.stepdefs.us;

import org.openqa.selenium.WebDriver;
import web.auto.pageobjects.us.OrderPageObject;
import web.auto.runtime.SharedDriver;

public class OrderStepDefs {
    private OrderPageObject orderPage;
    private WebDriver webDriver;

    public OrderStepDefs() {
        this.webDriver = new SharedDriver();
        orderPage = new OrderPageObject(webDriver);
    }
}
