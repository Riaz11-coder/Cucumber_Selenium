package pageObjects;

import cucumber.TestContext;
import managers.FileReaderManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.BrowserUtils;

public class ShadowDOMPage {

    WebDriver driver;
    WebElement shadowElement;
    BrowserUtils browserUtils;
    TestContext testContext;

    public ShadowDOMPage(WebDriver driver){
        testContext = TestContext.getInstance();
        this.driver = driver;
        this.browserUtils = testContext.getPageObjectManager().getBrowserUtils();
        PageFactory.initElements(driver, this);
    }

    public void initializeShadowElement() {
        shadowElement = browserUtils.getShadowElement(driver, "#content > my-paragraph:nth-child(4)", "p");
    }
    public void navigateToShadowDOMHomePage(){
        driver.get(FileReaderManager.getInstance().getConfigReader().getShadowDomUrl());
    }
    public String getShadowElementText() {
        return shadowElement.getText();
    }


}
