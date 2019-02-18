package hw6;

import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeTest;

@CucumberOptions(features = {"classpath:hw6/features/ServicePageInterfaceTest.feature"},
        glue = "classpath:hw6.steps")

public class RunServicePageInterfaceTest extends AbstractTestNGCucumberTests {
    @BeforeTest()
    public void beforeTest() {
        Configuration.browser = "CHROME";
    }
}
