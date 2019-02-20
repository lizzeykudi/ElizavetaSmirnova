package hw4;

import JDI.PagesMetaInfo;
import com.codeborne.selenide.Configuration;
import globalVariables.Users;
import hw4.pages.datesPage.*;
import hw4.pages.datesPage.slider.Direction;
import hw4.pages.datesPage.slider.Slider;
import hw4.pages.indexPage.IndexPage;
import hw5.listeners.AllureTestListener;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
// TODO Code convention !!


@Listeners(value = AllureTestListener.class)
@Feature("Dates page sliders test")
public class DatesPageSlidersTest {
    IndexPage indexPageSelenide;
    DatesPage datesPage;

    @BeforeTest
    public void beforeTest() {
        Configuration.browser = "CHROME";
        Configuration.startMaximized = true;
        Configuration.baseUrl = PagesMetaInfo.HOME.url;
        Configuration.browserSize = "1366x768";
    }

    @Story("Slider`s work Test")
    @Test(groups = "HW5")
    public void jdiTest() {
        Configuration.browser = "CHROME";
        Configuration.baseUrl = PagesMetaInfo.HOME.url;
        //1 Open test site by URL
        indexPageSelenide = open(IndexPage.URL, IndexPage.class);

        //2 Assert Browser title
        indexPageSelenide.assertBrowserTitle();

        //3 Perform login
        indexPageSelenide.login(Users.PETER);

        //4 Assert User name in the left-top side of screen that user is loggined
        indexPageSelenide.assertUserName(Users.PETER);

        //5 Open through the header menu Service -> Dates Page
        indexPageSelenide.openThroughHeader(DatesPage.RELATIVE_URL, DatesPage.URL);
        datesPage = open(DatesPage.URL, DatesPage.class);

        //6 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most rigth position
        datesPage.setSliderRange(Slider.FROM, Direction.LEFT);
        datesPage.setSliderRange(Slider.TO, Direction.RIGHT);

        //7 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLog();

        //8 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most left position.
        datesPage.setSliderRange(Slider.FROM, Direction.LEFT);
        datesPage.setSliderRange(Slider.TO, Direction.LEFT);

        //9 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLog();

        //10 Using drag-and-drop set Range sliders. left sliders - the most rigth position, right slider - the most rigth position.
        datesPage.setSliderRange(Slider.TO, Direction.RIGHT);
        datesPage.setSliderRange(Slider.FROM, Direction.RIGHT);
        //11 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLog();
        // TODO This wont work, you have to check that log has a message about slider position
        // TODO and the values are equal to expected (30, 70)

        //12 Using drag-and-drop set Range sliders.
        datesPage.setSliderRange(Slider.FROM, 30);
        datesPage.setSliderRange(Slider.TO, 70);

        //13 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLog(Slider.FROM, 30);
        datesPage.checkLog(Slider.TO, 70);
    }
}
