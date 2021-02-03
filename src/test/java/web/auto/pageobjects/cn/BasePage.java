package web.auto.pageobjects.cn;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.auto.runtime.BasicOperation;
import web.auto.runtime.LoadConfig;

import java.util.function.Function;

public class BasePage {

    protected WebDriver webDriver;

    protected BasicOperation basicOperation;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        basicOperation = new BasicOperation(webDriver);
    }

    public void injectJQuery() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        String injection = "var scriptElt = document.createElement('script'); \n" +
                "scriptElt.src = '" + LoadConfig.load("jqueryCDN") + "'; \n" +
                "document.getElementsByTagName('head')[0].appendChild(scriptElt);";
        js.executeScript(injection);
        basicOperation.getFromByScript("return (typeof jQuery != \'undefined\')", result -> result.equals(true));

        this.waitJQuery();
    }

    private void waitJQuery() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        Function<WebDriver, Boolean> jQueryAvailable = WebDriver -> (
                (Boolean) js.executeScript("return (typeof jQuery != \'undefined\')")
        );
        wait.until(jQueryAvailable);
    }

    public void openPage(String url) {
        webDriver.get(LoadConfig.load(url));
    }

    public void setWindowSize(int width, int height) {
        webDriver.manage().window().setSize(new Dimension(width, height));
    }

    public void waitPageLoaded() {
        basicOperation.waitJSComplete();
    }
}