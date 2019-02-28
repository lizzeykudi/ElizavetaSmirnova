package hw4;

import JDI.PagesMetaInfo;
import com.codeborne.selenide.Configuration;

import globalVariables.Texts;
import globalVariables.Users;

import hw4.pages.datesPage.DatesPage;
import hw4.pages.indexPage.IndexPage;
import hw4.pages.differentElementPage.*;
import hw5.listeners.AllureTestListener;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

@Listeners(value = AllureTestListener.class)
@Feature("Service Page Interface Test")
public class ServicePageInterfaceTest {

    //Test data
    String[] checkboxes = new String[]{"Water", "Wind"};
    String radio = "Selen";
    String dropdown = "Yellow";

    IndexPage indexPageSelenide;
    DifferentElementsPage differentElementsPage;

    @BeforeTest
    public void beforeTest() {
        Configuration.browser = "CHROME";
        Configuration.startMaximized = true;
        Configuration.baseUrl = PagesMetaInfo.HOME.url;
        Configuration.browserSize = "1366x768";
    }

    @Story("Select`s work test")
    @Test(groups = "HW5")
    public void jdiTest() {
        Configuration.browser = "CHROME";
        Configuration.baseUrl = PagesMetaInfo.HOME.url;
        indexPageSelenide = page(IndexPage.class);
        differentElementsPage = page(DifferentElementsPage.class);
        //1 Open test site by URL
        open(PagesMetaInfo.HOME.url);

        //2 Assert Browser title
        indexPageSelenide.assertBrowserTitle();

        //3 Perform login
        indexPageSelenide.login(Users.PETER);

        //4 Assert User name in the left-top side of screen that user is loggined
        indexPageSelenide.assertUserName(Users.PETER);

        //5 Click on "Service" subcategory in the header and check that drop down contains options
        indexPageSelenide.assertHeaderServiceContainsOptions(Texts.SERVICE_TEXTS.texts);

        //6 Click on Service subcategory in the left section and check that drop down contains options
        indexPageSelenide.assertLeftServiceContainsOptions(Texts.SERVICE_TEXTS.texts);

        //7 Open through the header menu Service -> Different Elements Page
        indexPageSelenide.openThroughHeader(DifferentElementsPage.RELATIVE_URL, DifferentElementsPage.URL);

        //8 Check interface on Different elements page, it contains all needed elements
        differentElementsPage.checkInterfaceOnDifferentElementsPage();

        //9 Assert that there is Right Section
        differentElementsPage.assertRightSectionIsDisplayed();

        //10 Assert that there is Left Section
        differentElementsPage.assertLeftSectionIsDisplayed();

        //11 Select checkboxes
        differentElementsPage.selectCheckboxes(true, checkboxes);

        //12 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        differentElementsPage.assertLog(DifferentElements.CHECKBOXES);

        //13 Select radio
        differentElementsPage.selectRadio(true, radio);

        //14 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton
        differentElementsPage.assertLog(DifferentElements.RADIOS);

        //15 Select in dropdown
        differentElementsPage.selectDropdown(true, dropdown);

        //16 Assert that for dropdown there is a log row and value is corresponded to the select value
        differentElementsPage.assertLog(DifferentElements.DROPDOWN);

        //17 Unselect and assert checkboxes
        differentElementsPage.selectCheckboxes(false, checkboxes);

        //18 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox. 
        differentElementsPage.assertLogUnselected(DifferentElements.CHECKBOXES, Arrays.asList(checkboxes));

    }
}
