package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pageObjects.GooglePage;
import pageObjects.ShadowDOMPage;
import utilities.BrowserUtils;

public class ShadowDOM {

    TestContext testContext;
    ShadowDOMPage shadowDOMPage;

    BrowserUtils browserUtils;

    public ShadowDOM(){
        testContext = TestContext.getInstance();
        shadowDOMPage = testContext.getPageObjectManager().getShadowDOMPage();
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
    }

    @When("I interact with the element")
    public void iInteractWithTheElement() {
        shadowDOMPage.navigateToShadowDOMHomePage();
        
    }

    @Then("I should be able to validate the text")
    public void iShouldBeAbleToValidateTheText() {



        // Access the shadow DOM element
        WebElement element = shadowDOMPage.getShadowElement("#content > my-paragraph:nth-child(4)", "p");

        // Perform some interaction with the element (e.g., click, get text)
        System.out.println("Text inside the shadow DOM element: " + element.getText());

        String actualText = element.getText();
        String expectedText = "Let's have some different text!";

        Assert.assertEquals(actualText,expectedText);
    }
}
