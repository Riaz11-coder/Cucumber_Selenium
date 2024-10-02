package pageObjects;

import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ShadowDom2Page {

    WebDriver driver;

    public ShadowDom2Page(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void navigateToShadowDOM2HomePage(){
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());


    }
}
