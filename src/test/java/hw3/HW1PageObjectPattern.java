package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

// TODO Code convention, reformat your code via IDEA
// TODO Basically, you have to follow 'Best Practices' from lecture 4
public class HW1PageObjectPattern {
    private IndexPage indexPage;
    private WebDriver driver;
    public static final String TITLE = "Home Page";
    @Test
    public void jdiTest() {
        // TODO This should not be here, take a look on HW2-3
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        indexPage = PageFactory.initElements(driver, IndexPage.class);
        // !TODO

        //1 Open test site by URI
        indexPage.open();

        //2 Assert Browser title
        Assert.assertEquals(indexPage.getTitle(), TITLE);

        //3 Perform login
        indexPage.login("epam", "1234");

        //4 Assert User name in the left-top(I`d say right-top) side of screen that user is loggined
        Assert.assertEquals(indexPage.getUserNickName(), "PITER CHAILOVSKII");

        //5 Assert Browser title
        Assert.assertEquals(indexPage.getTitle(), "Home Page");

        //6 Assert that there are 4 items on the header section are displayed and they have proper texts
        Assert.assertEquals(indexPage.countOfItems(), 4);
        // TODO Take a look on IDEA warning
        Assert.assertEquals((indexPage.containsAllInItems(new String[]{"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"})), true);

        //7 Assert that there are 4 images on the Index Page and they are displayed
        Assert.assertEquals(indexPage.countOfImages(), 4);
        Assert.assertEquals(indexPage.countOfDisplayedImages(), 4);

        //8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        Assert.assertEquals(indexPage.countOfTexts(), 4);
        Assert.assertEquals((indexPage.containsAllInTexts(new String[]{
                                "To include good practices\n" +
                                        "and ideas from successful\n" +
                                        "EPAM project",

                                "To be flexible and\n" +
                                        "customizable",

                                "To be multiplatform",

                                "Already have good base\n" +
                                        "(about 20 internal and\n" +
                                        "some external projects),\n" +
                                        "wish to get more…"})),
                true);
        //9 Assert a text of the main headers
        Assert.assertEquals(indexPage.getTextOfCenterTitle(), "EPAM FRAMEWORK WISHES…");
        Assert.assertEquals(indexPage.getTextOfCenterTxt().startsWith("LOREM IPSUM"), true);

        //10 Assert that there is the iframe in the center of page
        Assert.assertEquals(indexPage.isEpamLogoDisplayed(), true);

        //11 Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        driver.switchTo().frame("iframe");
        Assert.assertEquals(indexPage.isEpamLogoDisplayed(), true);

        //12 Switch to original window back
        driver.switchTo().defaultContent();

        //13 Assert a text of the sub header
        Assert.assertEquals(indexPage.getTextOfSubHeaderText(), "JDI GITHUB");

        //14 Assert that JDI GITHUB is a link and has a proper URL
        Assert.assertEquals(indexPage.getAttributeOfSubHeaderText("href"), "https://github.com/epam/JDI");

        //15 Assert that there is Left Section
        Assert.assertEquals(indexPage.isLeftSectionDisplayed(), true);

        //16 Assert that there is Footer
        Assert.assertEquals(indexPage.isFooterDisplayed(), true);

        //17 Close Browser
        driver.quit();
    }
}
