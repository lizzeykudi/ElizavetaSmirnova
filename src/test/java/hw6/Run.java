package hw6;
import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeSuite;
//@CucumberOptions(features = "classpath:hw6/features/ServicePageInterfaceTest.feature", glue = "classpath:hw6.steps")
@CucumberOptions(features = "classpath:hw6/features/UserTableInterfaceTest.feature", glue = "classpath:hw6.steps")

public class Run extends AbstractTestNGCucumberTests{
    @BeforeSuite()
    public void beforeSuite() {
        Configuration.browser = "CHROME";
    }
}
