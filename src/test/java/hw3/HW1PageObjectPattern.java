package hw3;

import base.SeleniumBase;
import globalVariables.Texts;
import globalVariables.Users;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HW1PageObjectPattern extends SeleniumBase {
    private IndexPage indexPage;
    private WebDriver driver;
    public static final String JDI_GITHUB_TEXT = "JDI GITHUB";
    public static final String JDI_GITHUB_REF = "https://github.com/epam/JDI";
    public static final String TEXT_OF_CENTER_TITLE = "EPAM FRAMEWORK WISHES…";
    public static final String START_TEXT_OF_CENTER_TITLE = "LOREM IPSUM";
    public static final String FRAME = "iframe";
    public static final int IMAGES_COUNT = 4;


    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        indexPage = PageFactory.initElements(driver, IndexPage.class);
    }

    @Test
    public void jdiTest() {
        //1 Open test site by URI
        indexPage.open();

        //2 Assert Browser title
        indexPage.assertBrowserTitle();

        //3 Perform login
        indexPage.login(Users.PETER.login, Users.PETER.password);

        //4 Assert User name in the left-top(I`d say right-top) side of screen that user is loggined
        indexPage.assertUserName(Users.PETER.nick);

        //5 Assert Browser title
        indexPage.assertBrowserTitle();

        //6 Assert that there are 4 items on the header section are displayed and they have proper texts
        indexPage.assertItemsCount(Texts.ITEMS.size);
        indexPage.assertItemsTexts(Texts.ITEMS.texts);

        //7 Assert that there are 4 images on the Index Page and they are displayed
        indexPage.asserImagesCount(IMAGES_COUNT);
        indexPage.assertDisplayedImagesCount(IMAGES_COUNT);

        //8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        indexPage.assertTextsUnderIconsCount(Texts.TEXTS.size);
        indexPage.assertTextsUnderIcons(Texts.TEXTS.texts);

        //9 Assert a text of the main headers
        indexPage.assertTextOfCenterTitle(TEXT_OF_CENTER_TITLE);
        indexPage.assertTextOfCenterTxt(START_TEXT_OF_CENTER_TITLE);

        //10 Assert that there is the iframe in the center of page
        indexPage.assertIframeIsDisplayed();

        //11 Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        driver.switchTo().frame(FRAME);
        indexPage.assertEpamLogoIsDisplayed();

        //12 Switch to original window back
        driver.switchTo().defaultContent();

        //13 Assert a text of the sub header
        indexPage.assertTextOfSubHeader(JDI_GITHUB_TEXT);

        //14 Assert that JDI GITHUB is a link and has a proper URL
        indexPage.assertJDIGithubLink(JDI_GITHUB_REF);

        //15 Assert that there is Left Section
        indexPage.assertLeftSectionIsDisplayed();

        //16 Assert that there is Footer
        indexPage.assertFooterIsDisplayed();

        //17 Close Browser
        driver.quit();
    }
}
