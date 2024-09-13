package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/functionalTests"},
        glue= {"stepDefinitions"},
        plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-report.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        dryRun = false,
        tags = "@Login"
)
public class TestRunner {
    @BeforeClass
    public static void setup() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("configs/Configuration.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key : props.stringPropertyNames()) {
            System.setProperty(key, props.getProperty(key));
        }
    }
}
