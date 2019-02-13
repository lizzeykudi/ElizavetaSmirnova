package hw4;

import com.codeborne.selenide.Configuration;
import hw4.differentElementPage.ElementsOnDifferentElementsPage;
import globalVariables.Texts;
import globalVariables.Users;
import hw4.differentElementPage.DifferentElementsPageSelenide;
import hw4.indexPage.IndexPageSelenide;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class ServicePageInterfaceTest {
    IndexPageSelenide indexPageSelenide;
    DifferentElementsPageSelenide differentElementsPage;

    @BeforeTest
    public void beforeTest() {
        Configuration.browser = "CHROME";
        Configuration.startMaximized = true;
    }

    @Test
    public void jdiTest() {
        //1 Open test site by URL
        indexPageSelenide = open(IndexPageSelenide.URL, IndexPageSelenide.class);

        //2 Assert Browser title
        indexPageSelenide.assertBrowserTitle();

        //3 Perform login
        // TODO Basically, it will be better to parametrise the methods by enums instead of strings.
        // TODO You can pass the whole user in method login(...) nad assertUserName(...)
        indexPageSelenide.login(Users.PETER.login, Users.PETER.password);

        //4 Assert User name in the left-top side of screen that user is loggined
        indexPageSelenide.assertUserName(Users.PETER.nick);

        //5 Click on "Service" subcategory in the header and check that drop down contains options
        indexPageSelenide.assertHeaderServiceContainsOptions(Texts.SERVICE_TEXTS.texts);

        //6 Click on Service subcategory in the left section and check that drop down contains options
        indexPageSelenide.assertLeftServiceContainsOptions(Texts.SERVICE_TEXTS.texts);

        //7 Open through the header menu Service -> Different Elements Page
        // TODO It will be better to split PO initialisation and web page opening,
        // TODO it is tot make sense open the same page twice.
        indexPageSelenide.openThroughHeader(DifferentElementsPageSelenide.RELATIVE_URL, DifferentElementsPageSelenide.URL);
        differentElementsPage = open(DifferentElementsPageSelenide.URL, DifferentElementsPageSelenide.class);

        //8 Check interface on Different elements page, it contains all needed elements
        differentElementsPage.checkInterfaceOnDifferentElementsPage();

        //9 Assert that there is Right Section
        differentElementsPage.assertRightSectionIsDisplayed();

        //10 Assert that there is Left Section
        differentElementsPage.assertLeftSectionIsDisplayed();

        //11 Select checkboxes
        differentElementsPage.select(ElementsOnDifferentElementsPage.CHECKBOXES, new String[]{"Water", "Wind"});

        //12 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        differentElementsPage.assertLog(ElementsOnDifferentElementsPage.CHECKBOXES);

        //13 Select radio
        differentElementsPage.select(ElementsOnDifferentElementsPage.RADIOS,  new String[]{"Selen"});

        //14 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton
        differentElementsPage.assertLog(ElementsOnDifferentElementsPage.RADIOS);

        //15 Select in dropdown
        differentElementsPage.select(ElementsOnDifferentElementsPage.DROPDOWN, new String[]{"Yellow"});

        //16 Assert that for dropdown there is a log row and value is corresponded to the select value
        differentElementsPage.assertLog(ElementsOnDifferentElementsPage.DROPDOWN);

        //17 Unselect and assert checkboxes
        differentElementsPage.select(ElementsOnDifferentElementsPage.CHECKBOXES, new String[]{"Water", "Wind"}, false);

        //18 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox. 
        differentElementsPage.assertLogUnselected(ElementsOnDifferentElementsPage.CHECKBOXES, new String[]{"Water", "Wind"});

    }
}
