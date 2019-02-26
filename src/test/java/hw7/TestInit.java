package hw7;

import com.epam.jdi.light.driver.WebDriverFactory;
import com.epam.jdi.light.ui.html.PageFactory;
import hw7.jdi.JDISite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestInit {
    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() {
        PageFactory.initElements(JDISite.class);
    }

    @AfterSuite
    public void afterSuite() {
        WebDriverFactory.close();
    }
}
