package web.auto.runtime;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import groovy.ui.SystemOutputInterceptor;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class SharedDriver extends EventFiringWebDriver {
    private static WebDriver REAL_DRIVER;
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };
    public static final ThreadLocal session = new ThreadLocal();

    public static Object getScenarioProperty(String key) {
        return ((HashMap<String, Object>) session.get()).get(key);
    }

    public static Object setScenarioProperty(String key, Object value) {
        return ((HashMap<String, Object>) session.get()).put(key, value);
    }

    static {
        String browserName = System.getenv("browser");

        if (browserName.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--whitelisted-ips");
            REAL_DRIVER = new ChromeDriver(chromeOptions);
        } else if (browserName.equals("firefox")) {
            REAL_DRIVER = new FirefoxDriver();
        } else if (browserName.equals("safari")) {
            SafariOptions options = new SafariOptions();
            options.setUseTechnologyPreview(true);
            REAL_DRIVER = new SafariDriver(options);
        } else if (browserName.equals("ie")) {
            REAL_DRIVER = new InternetExplorerDriver();
        }
        REAL_DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public SharedDriver() {
        super(REAL_DRIVER);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    @Before
    public void beforeScenario() {
        HashMap<String, Object> context = new HashMap<String, Object>();
        session.set(context);
        String browser = System.getenv("browser");
        if (browser.equals("chrome")) {
                manage().deleteAllCookies();
        } else if (browser.equals("firefox")) {
            if (REAL_DRIVER.toString().contains("(null)")) {
                REAL_DRIVER = new FirefoxDriver();
            }
        } else if (browser.equals("safari")) {
            if (REAL_DRIVER.toString().contains("(null)")) {
                REAL_DRIVER = new SafariDriver();
            }
        }
    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        session.remove();
        if (REAL_DRIVER instanceof WebStorage) {
            ((WebStorage) REAL_DRIVER).getLocalStorage().clear();
        } else if (REAL_DRIVER instanceof RemoteWebDriver) {
            ((RemoteWebDriver) REAL_DRIVER).executeScript("window.localStorage.clear();");
        }
        if (System.getenv("browser").equals("firefox") || System.getenv("browser").equals("safari")) {
            REAL_DRIVER.quit();
        }
        if (scenario.isFailed()) {
            File scrFile = ((TakesScreenshot) REAL_DRIVER).getScreenshotAs(OutputType.FILE);
            final byte[] embedScreenshot = ((TakesScreenshot) REAL_DRIVER).getScreenshotAs(OutputType.BYTES);
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + File.separator + "screenshot" + File.separator + scenario.getName() + ".png"));
            scenario.embed(embedScreenshot, "image/png");
        }
    }
}