package stepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {

    TestContext testContext;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before()
    public void BeforeSteps() {
		/*What all you can perform here
			Starting a webdriver
			Setting up DB connections
			Setting up test data
			Setting up browser cookies
			Navigating to certain page
			or anything before the test
		*/
    }

    @AfterStep()
    public void addScreenshot(Scenario scenario){

        testContext.getWebDriverManager().getScreenShot(scenario);
        ExtentTest extentTest = ExtentCucumberAdapter.getCurrentStep();
        String screenshotPath = "/Users/riazahmed/IdeaProjects/Cucumber_Selenium/target/ExtentReport/Screenshots.png";
        extentTest.addScreenCaptureFromPath(screenshotPath);

    }

    @After()
    public void AfterAll(Scenario scenario) {

        System.out.println("-----> After annotation: Closing browser");
        System.out.println("scenario.getName() = " + scenario.getName());
        System.out.println("scenario.getSourceTagNames() = " + scenario.getSourceTagNames());
        System.out.println("scenario.isFailed() = " + scenario.isFailed());

        testContext.getWebDriverManager().closeDriver();
    }
}
