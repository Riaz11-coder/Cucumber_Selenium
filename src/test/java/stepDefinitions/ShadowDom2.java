package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pageObjects.ShadowDOMPage;
import pageObjects.ShadowDom2Page;
import utilities.BrowserUtils;

import static org.junit.Assert.assertEquals;

public class ShadowDom2 {

    TestContext testContext;
    ShadowDom2Page ShadowDom2Page;

    BrowserUtils browserUtils;

    public ShadowDom2(){
        testContext = TestContext.getInstance();
        ShadowDom2Page = testContext.getPageObjectManager().getShadowDom2Page();
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
    }

    @Given("I navigate to the Expand Practice page")
    public void iNavigateToTheExpandPracticePage() {
        ShadowDom2Page.navigateToShadowDomPage();
    }

    @When("I initialize the shadow element")
    public void iInitializeTheShadowElement() {
        ShadowDom2Page.initializeShadowElement();
    }

    @Then("I should see the text {string} in the button")
    public void iShouldSeeTheTextInTheButton(String expectedText) {
        String actualText = ShadowDom2Page.getButtonText();
        assertEquals(expectedText, actualText);
        System.out.println("Button text inside the shadow DOM element: " + actualText);
    }
}
