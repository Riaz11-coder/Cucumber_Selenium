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
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "listeners.TestFailureListener"},
        monochrome = true,
        dryRun = false,
        tags = "@ShadowDom2"
)
public class TestRunner {

    @BeforeClass
    public static void setup() {
        String environment = System.getProperty("environment", "local");
        System.out.println("Current environment: " + environment);
        if ("browserstack".equalsIgnoreCase(environment)) {
            loadBrowserstackConfig();
        } else {
            loadDefaultConfig();
        }

        System.setProperty("environment", environment);
    }

    private static void loadDefaultConfig() {
        loadPropertiesFile("configs/Configuration.properties");
        System.out.println("Loaded environment: " + System.getProperty("environment"));
    }

    private static void loadBrowserstackConfig() {
        loadPropertiesFile("browserstack.yml");
    }

    private static void loadPropertiesFile(String filePath) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(filePath));
            for (String key : props.stringPropertyNames()) {
                System.setProperty(key, props.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
