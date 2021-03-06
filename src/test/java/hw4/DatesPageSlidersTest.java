package hw4;

import JDI.pagesInfo.MainPages;
import com.codeborne.selenide.Configuration;
import globalVariables.Users;
import hw4.pages.datesPage.*;
import hw4.pages.datesPage.slider.Slider;
import hw4.pages.indexPage.IndexPage;
import hw5.listeners.AllureTestListener;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

@Listeners(value = AllureTestListener.class)
@Feature("Dates page sliders test")
public class DatesPageSlidersTest {
    IndexPage indexPageSelenide;
    DatesPage datesPage;

    @BeforeTest
    public void beforeTest() {
        Configuration.browser = "CHROME";
        Configuration.startMaximized = true;
        Configuration.baseUrl = MainPages.HOME.url;


        //Configuration.browserSize = "1366x768";
    }

    // TODO This test wont work for me x2
    // Actual: [Range 2(To):70 link clicked, Range 2(From):28 link clicked, Range 2(From):100 link clicked, Range 2(To):100 link clicked, Range 2(To):0 link clicked, Range 2(From):0 link clicked, Range 2(To):100 link clicked, Range 2(From):0 link clicked]
    // Expected:[Range 2(From):30 link clicked]

    @Story("Slider`s work Test")
    @Test(groups = "HW5")
    public void jdiTest() {
        indexPageSelenide = page(IndexPage.class);
        datesPage = page(DatesPage.class);
        //1 Open test site by URL
        open(MainPages.HOME.url);

        //2 Assert Browser title
        indexPageSelenide.assertBrowserTitle();

        //3 Perform login
        indexPageSelenide.login(Users.PETER);

        //4 Assert User name in the left-top side of screen that user is loggined
        indexPageSelenide.assertUserName(Users.PETER);

        //5 Open through the header menu Service -> Dates Page
        indexPageSelenide.openThroughHeader(DatesPage.RELATIVE_URL, DatesPage.URL);

        //6 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most rigth position
        datesPage.setSliderRange(Slider.FROM, 0);
        datesPage.setSliderRange(Slider.TO, 100);

        //7 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLog(Slider.FROM, 0);
        datesPage.checkLog(Slider.TO, 100);

        //8 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most left position.
        datesPage.setSliderRange(Slider.FROM, 0);
        datesPage.setSliderRange(Slider.TO, 0);

        //9 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLog(Slider.FROM, 0);
        datesPage.checkLog(Slider.TO, 0);

        //10 Using drag-and-drop set Range sliders. left sliders - the most rigth position, right slider - the most rigth position.
        datesPage.setSliderRange(Slider.TO, 100);
        datesPage.setSliderRange(Slider.FROM, 100);
        //11 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLog(Slider.TO, 100);
        datesPage.checkLog(Slider.FROM, 100);

        //12 Using drag-and-drop set Range sliders.
        datesPage.setSliderRange(Slider.FROM, 30);
        datesPage.setSliderRange(Slider.TO, 70);

        //13 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLog(Slider.FROM, 30);
        datesPage.checkLog(Slider.TO, 70);
    }
}
