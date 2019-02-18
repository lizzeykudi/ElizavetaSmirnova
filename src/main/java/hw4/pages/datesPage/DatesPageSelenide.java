package hw4.pages.datesPage;

import hw4.conditions.Conditions;
import hw4.pages.datesPage.slider.Direction;
import hw4.pages.datesPage.slider.Slider;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import java.util.ArrayList;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
// TODO The same comments like DifferentElementsPageSelenide
// TODO Basically, this PO should have only 2 public methods:
// TODO setSliderRange(int, int)
// TODO checkLog(...)

// TODO The same comments like DifferentElementsPageSelenide
// TODO Basically, this PO should have only 2 public methods:
// TODO setSliderRange(int, int)
// TODO checkLog(...)
public class DatesPageSelenide {
    public final static String TITLE = "Dates";
    public final static String URL = "https://epam.github.io/JDI/dates.html";
    public static final String RELATIVE_URL = "dates.html";


    @Step("Using drag-and-drop set Range sliders")
    public void setSlider(Slider slider, Direction direction) {
        $(slider.cssSelector).dragAndDropTo(direction.cssSelector);
    }

    @Step("Using drag-and-drop set Range sliders")
    public void setSlider(Slider slider, int position) {
        int currentPos = Integer.parseInt($(slider.cssSelector + " span").text());
        int shift = currentPos - position;
        if (shift > 0) {
            for (int i = 0; i < shift + 3; i++) {
                $(slider.cssSelector).sendKeys(Keys.ARROW_LEFT);
            }
        } else {
            for (int i = 0; i < Math.abs(shift); i++) {
                $(slider.cssSelector).sendKeys(Keys.ARROW_RIGHT);
            }
            for (int i = 0; i < 3; i++) {
                $(slider.cssSelector).sendKeys(Keys.ARROW_LEFT);
            }
        }
        $(slider.cssSelector).dragAndDropTo($(slider.cssSelector));
    }


    @Step("Assert that for \"From\" and \"To\" sliders there are logs rows with corresponding values")
    public void assertLog() {
        ArrayList<String> expectedLog = new ArrayList<>();
        for (Slider slider : Slider.values()) {
            expectedLog.add(String.format(slider.logStatus, $(slider.cssSelector + " span").getText()));
        }
        $$(".logs > li").shouldHave(Conditions.containsLog(expectedLog));
        }

}
