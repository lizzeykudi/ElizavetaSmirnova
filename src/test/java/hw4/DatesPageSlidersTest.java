package hw4;

import com.codeborne.selenide.Configuration;
import globalVariables.Users;
import hw4.datesPage.DatesPageSelenide;
import hw4.datesPage.slider.Direction;
import hw4.datesPage.slider.Slider;
import hw4.indexPage.IndexPageSelenide;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

// TODO Code convention !!
public class DatesPageSlidersTest {
    IndexPageSelenide indexPageSelenide;
    DatesPageSelenide datesPage;

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
        indexPageSelenide.login(Users.PETER.login, Users.PETER.password);

        //4 Assert User name in the left-top side of screen that user is loggined
        indexPageSelenide.assertUserName(Users.PETER.nick);

        //5 Open through the header menu Service -> Dates Page
        indexPageSelenide.openThroughHeader(DatesPageSelenide.RELATIVE_URL, DatesPageSelenide.URL);
        datesPage = open(DatesPageSelenide.URL, DatesPageSelenide.class);

        //6 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most rigth position
        datesPage.setSlider(Slider.FROM, Direction.LEFT);
        datesPage.setSlider(Slider.TO, Direction.RIGHT);

        //7 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.assertLog();

        //8 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most left position.
        datesPage.setSlider(Slider.FROM, Direction.LEFT);
        datesPage.setSlider(Slider.TO, Direction.LEFT);

        //9 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.assertLog();

        //10 Using drag-and-drop set Range sliders. left sliders - the most rigth position, right slider - the most rigth position.
        datesPage.setSlider(Slider.TO, Direction.RIGHT);
        datesPage.setSlider(Slider.FROM, Direction.RIGHT);

        //11 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.assertLog();

        //12 Using drag-and-drop set Range sliders.
        datesPage.setSlider(Slider.FROM, 30);
        datesPage.setSlider(Slider.TO, 70);

        // TODO This wont work, you have to check that log has a message about slider position
        // TODO and the values are equal to expected (30, 70)
        //13 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.assertLog();
        }
}
