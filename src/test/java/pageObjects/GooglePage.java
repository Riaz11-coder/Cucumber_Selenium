package pageObjects;

import managers.FileReaderManager;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage  {
    WebDriver driver;


    public GooglePage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "q")
    public WebElement googleSearch;

    @FindBy(css = "span.VuuXrf")
    public WebElement audi;

    @FindBy(css = "span.VuuXrf")
    public WebElement porsche;

    @FindBy(css = "h3[class='LC20lb MBeuO DKV0Md']")
    public WebElement porscheHomeButton;

    public void NavigateToGoogleHomePage(){
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
    }

    public void pageTitle(){
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
    }

    public void pageTitleValidation() {
        String expectedPageTitle = "Google";
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(expectedPageTitle,actualPageTitle);


    }

    public void searchGoogle(String query) {
        googleSearch.sendKeys(query, Keys.ENTER);
        if (query.equals("Porsche")) {
            Assert.assertEquals(porsche.getText(), "Porsche");
        } else if (query.equals("audi")) {
            Assert.assertEquals(audi.getText(), "Audi USA");
        }

    }

    public void searchPageTitleValidation() {
        porscheHomeButton.click();
        String expectedPageTitle = "Porsche HOME - Porsche USA";
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(expectedPageTitle,actualPageTitle);


    }
}
