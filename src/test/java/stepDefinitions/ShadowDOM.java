package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pageObjects.ShadowDOMPage;
import utilities.BrowserUtils;

import static org.junit.Assert.assertEquals;

public class ShadowDOM {

    TestContext testContext;
    ShadowDOMPage shadowDOMPage;

    BrowserUtils browserUtils;

    public ShadowDOM(){
        testContext = TestContext.getInstance();
        shadowDOMPage = testContext.getPageObjectManager().getShadowDOMPage();
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
    }

    @Given("I navigate to the Shadow DOM page")
    public void iNavigateToTheShadowDOMPage() {
        shadowDOMPage.navigateToShadowDOMHomePage();
    }

    @Then("I should see the text {string} in the shadow element")
    public void iShouldSeeTheTextInTheShadowElement(String expectedText) {
        shadowDOMPage.initializeShadowElement();
        String actualText = shadowDOMPage.getShadowElementText();
        assertEquals(expectedText, actualText);
        System.out.println("Text inside the shadow DOM element: " + actualText);
    }
}
