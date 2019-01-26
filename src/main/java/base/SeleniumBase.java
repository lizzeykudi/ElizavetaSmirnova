package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static java.lang.System.setProperty;

public abstract class SeleniumBase {

    @BeforeSuite
    public void beforeSuite() {
        setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println(System.currentTimeMillis());
    }
}
