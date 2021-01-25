package web.auto.pageobjects.us;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import web.auto.runtime.LoadConfig;

import java.util.List;

public class ICareLoginPageObject extends BasePage {
    public ICareLoginPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void loginCare(String username, String password){
        basicOperation.findElementsByScript(LoadConfig.load("iCareUsername")).get(0).sendKeys(username);
        basicOperation.findElementsByScript(LoadConfig.load("iCarePassword")).get(0).sendKeys(password);
        clickLogin();
    }

    public void clickLogin(){
        basicOperation.findElementsByScript(LoadConfig.load("iCareLoginBtn")).get(0).click();
    }

}