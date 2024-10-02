package pageObjects;

import managers.FileReaderManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ShadowDOMPage {

    WebDriver driver;

    public ShadowDOMPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }




    public WebElement getShadowElement(String shadowHostSelector, String shadowElementSelector) {
        // Locate the shadow host element
        WebElement shadowHost = driver.findElement(By.cssSelector(shadowHostSelector));

        // Access the shadow root using JavaScriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Retrieve the shadow root and query the shadow DOM for the element
        WebElement shadowElement = (WebElement) js.executeScript(
                "return arguments[0].shadowRoot.querySelector(arguments[1])",
                shadowHost, shadowElementSelector
        );

        return shadowElement;
    }



    public void navigateToShadowDOMHomePage(){
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());


    }


}
