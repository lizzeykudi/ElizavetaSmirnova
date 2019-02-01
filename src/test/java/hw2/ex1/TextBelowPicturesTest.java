package hw2.ex1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TextBelowPicturesTest {
    private List<WebElement> webElements;
    // TODO Pay attention on Code Convention, blank line should be here
    @BeforeClass
    public void beforeClass() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://epam.github.io/JDI/");
        webElements = driver.findElements(By.cssSelector(".row.clerafix.benefits > div > div > span"));
    }

    @DataProvider(parallel = true)
    public Object[][] getData() {
        return new Object[][] {
                {webElements.get(0),
                        "To include good practices\n" +
                                "and ideas from successful\n" +
                                "EPAM project"
                },
                {webElements.get(1),
                        "To be flexible and\n" +
                                "customizable"
                },
                {webElements.get(2),
                        "To be multiplatform"
                },
                {webElements.get(3),
                        "Already have good base\n" +
                                "(about 20 internal and\n" +
                                "some external projects),\n" +
                                "wish to get more…"
                }
        };
    }


    @Test(dataProvider = "getData")
    public void textBelowPicturesTest(WebElement text, String expectedText) {
        Assert.assertEquals(text.getText(), expectedText);
    }

}
