package hw1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JDITestWithSoftAssert {
    @Test
    public void jdiTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        SoftAssert softAssert = new SoftAssert();

        //1 Open test site by URI
        driver.navigate().to("https://epam.github.io/JDI/");

        //2 Assert Browser title

        softAssert.assertEquals(driver.getTitle(), "Home Page");

        //3 Perform login
        driver.findElement(By.cssSelector("[id='user-icon']")).click();
        driver.findElement(By.cssSelector("[id='name']")).sendKeys("epam");
        driver.findElement(By.cssSelector("[id='password']")).sendKeys("1234");
        driver.findElement(By.cssSelector("[id='login-button']")).click();

        //4 Assert User name in the left-top(I`d say right-top) side of screen that user is loggined
        softAssert.assertEquals(driver.findElement(By.cssSelector("[id='user-name']")).getText(), "PITER CHAILOVSKII");

        //5 Assert Browser title
        softAssert.assertEquals(driver.getTitle(), "Home Page");

        //6 Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> webElements = driver.findElements(By.cssSelector(".uui-navigation.nav.navbar-nav.m-l8 > li > a"));
        softAssert.assertEquals(webElements.size(), 4);
        softAssert.assertEquals((webElements.stream().map(WebElement::getText).collect(Collectors.toList()))
                .containsAll(Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS")), true);

        //7 Assert that there are 4 images on the Index Page and they are displayed
        webElements = driver.findElements(By.cssSelector("body > div > div.uui-main-container > main > div.main-content > div > div > div > div > span"));
        softAssert.assertEquals(webElements.size(), 4);
        softAssert.assertEquals(webElements.stream().filter(WebElement::isDisplayed).collect(Collectors.toList()).size(), 4);

        //8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        webElements = driver.findElements(By.cssSelector(".row.clerafix.benefits > div > div > span"));
        softAssert.assertEquals(webElements.size(), 4);
        softAssert.assertEquals((webElements.stream().map(WebElement::getText).collect(Collectors.toList()))
                        .containsAll(Arrays.asList(
                                "To include good practices\n" +
                                        "and ideas from successful\n" +
                                        "EPAM project",

                                "To be flexible and\n" +
                                        "customizable",

                                "To be multiplatform",

                                "Already have good base\n" +
                                        "(about 20 internal and\n" +
                                        "some external projects),\n" +
                                        "wish to get more…")),
                true);

        //9 Assert a text of the main headers
        softAssert.assertEquals(driver.findElement(By.cssSelector(".main-title.text-center")).getText(), "EPAM FRAMEWORK WISHES…");
        softAssert.assertEquals(driver.findElement(By.cssSelector(".main-txt.text-center")).getText().startsWith("LOREM IPSUM"), true);

        //10 Assert that there is the iframe in the center of page
        softAssert.assertEquals(driver.findElement(By.cssSelector("#iframe")).isDisplayed(), true);

        //11 Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        driver.switchTo().frame("iframe");
        softAssert.assertEquals(driver.findElement(By.cssSelector("#epam_logo")).isDisplayed(), true);

        //12 Switch to original window back
        driver.switchTo().defaultContent();

        //13 Assert a text of the sub header
        softAssert.assertEquals(driver.findElement(By.cssSelector
                ("body > div > div.uui-main-container > main > div.main-content > h3:nth-child(3) > a")).getText(), "JDI GITHUB");

        //14 Assert that JDI GITHUB is a link and has a proper URL
        softAssert.assertEquals(driver.findElement(By.cssSelector
                ("body > div > div.uui-main-container > main > div.main-content > h3:nth-child(3) > a")).getAttribute("href"), "https://github.com/epam/JDI");

        //15 Assert that there is Left Section
        softAssert.assertEquals(driver.findElement(By.cssSelector(".uui-side-bar.mCustomScrollbar._mCS_1.mCS_no_scrollbar")).isDisplayed(), true);

        //16 Assert that there is Footer
        softAssert.assertEquals(driver.findElement(By.cssSelector("footer")).isDisplayed(), true);

        //17 Close Browser
        driver.close();
    }
}
