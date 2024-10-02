package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pageObjects.ShadowDOMPage;
import pageObjects.ShadowDom2Page;
import utilities.BrowserUtils;

public class ShadowDom2 {

    TestContext testContext;
    ShadowDom2Page ShadowDom2Page;

    BrowserUtils browserUtils;

    public ShadowDom2(){
        testContext = TestContext.getInstance();
        ShadowDom2Page = testContext.getPageObjectManager().getShadowDom2Page();
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
    }
    @When("I interact with Shadow Dom element")
    public void iInteractWithShadowDomElement() {
        ShadowDom2Page.navigateToShadowDOM2HomePage();
    }

    @Then("I get text and validate")
    public void iGetTextAndValidate() {
        // Access the shadow DOM element
        WebElement element = browserUtils.getShadowElement("#shadow-host", "#my-btn");

        // Perform some interaction with the element (e.g., click, get text)
        System.out.println("Text inside the shadow DOM element: " + element.getText());

        String actualText = element.getText();
        String expectedText = "This button is inside a Shadow DOM.";

        Assert.assertEquals(actualText,expectedText);
    }
}
