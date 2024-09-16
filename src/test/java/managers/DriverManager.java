package managers;

import dataProvider.ConfigFileReader;
import enums.DriverType;
import enums.EnvironmentType;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;


    public DriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public void getScreenShot(Scenario scenario){

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());

    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case REMOTE : driver = createRemoteDriver();
                break;
            case BROWSERSTACK:
                try{
                    driver = createBrowserStackDriver();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability("platform", Platform.ANY);

        switch (driverType) {
            case REMOTECHROME:
                desiredCapabilities.setBrowserName(BrowserType.CHROME);
                desiredCapabilities.setCapability("chromeOptions", createChromeOptions(true));
                break;
            case REMOTEFIREFOX:
                desiredCapabilities.setBrowserName(BrowserType.FIREFOX);
                desiredCapabilities.setCapability("marionette", true);
                break;
            default:
                throw new RuntimeException("Invalid driver type");
        }

        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL for remote webDriver", e);
        }

    }

    private ChromeOptions createChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless"); // Set Chrome to run in headless mode
        }
        // You can add more Chrome options here if needed
        return options;
    }


    private WebDriver createLocalDriver() {
        switch (driverType) {

            case FIREFOX :
                WebDriverManager.firefoxdriver().setup();
                driver= new FirefoxDriver();
                break;
            case CHROME :
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case INTERNETEXPLORER : driver = new InternetExplorerDriver();
                break;
        }

        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver createBrowserStackDriver() throws MalformedURLException {
        String username = FileReaderManager.getInstance().getConfigReader().getProperty("browserstack.username");
        String accessKey = FileReaderManager.getInstance().getConfigReader().getProperty("browserstack.access_key");
        String buildNumber = System.getenv("BUILD_NUMBER");
        String buildName = "Build_" + (buildNumber != null ? buildNumber : "Local_" + System.currentTimeMillis());
        String projectName = "Cucumber-Selenium";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName",FileReaderManager.getInstance().getConfigReader().getProperty("browserstack.device"));
        caps.setCapability("browser",FileReaderManager.getInstance().getConfigReader().getProperty("browserstack.browser3"));
        caps.setCapability("browser_version", FileReaderManager.getInstance().getConfigReader().getProperty("browserstack.browser_version"));
        caps.setCapability("os", FileReaderManager.getInstance().getConfigReader().getProperty("browserstack.os3"));
        caps.setCapability("os_version", FileReaderManager.getInstance().getConfigReader().getProperty("browserstack.os_version5"));
        caps.setCapability("resolution", FileReaderManager.getInstance().getConfigReader().getProperty("browserstack.resolution"));
        caps.setCapability("project", projectName);
        caps.setCapability("build", buildName);
        caps.setCapability("name", "BrowserStack-test");
        caps.setCapability("browserstack.debug",true);
        HashMap<String, Boolean> networkLogsOptions = new HashMap<>();
        networkLogsOptions.put("captureContent", true);
        caps.setCapability("browserstack.networkLogs", true);
        caps.setCapability("browserstack.networkLogsOptions", networkLogsOptions);
        caps.setCapability("browserstack.console","verbose");
        caps.setCapability("browserstack.video",true);
        caps.setCapability("browserstack.selfHeal", true);


        URL browserStackUrl = new URL("https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub");
        return new RemoteWebDriver(browserStackUrl, caps);
    }
    public void closeDriver() {

        driver.quit();
    }

}
