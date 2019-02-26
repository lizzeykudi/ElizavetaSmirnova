package hw4.pages.datesPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import hw4.conditions.Conditions;
import hw4.pages.datesPage.slider.Direction;
import hw4.pages.datesPage.slider.Slider;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// TODO Basically, this PO should have only 2 public methods:
// TODO setSliderRange(int, int)
// TODO checkLog(...)

public class DatesPage {
    public final static String TITLE = "Dates";
    public final static String URL = "https://epam.github.io/JDI/dates.html";
    public static final String RELATIVE_URL = "dates.html";


    @FindBy(css = ".ui-state-default.ui-corner-all:nth-child(1)")
    private SelenideElement fromSlider;

    @FindBy(css = ".ui-state-default.ui-corner-all:nth-child(3)")
    private SelenideElement toSlider;

    @FindBy(css = ".logs > li")
    private ElementsCollection log;

    Map<Slider, SelenideElement> sliders;

    private void initSliders() {
        sliders = new HashMap<>();
        sliders.put(Slider.FROM, fromSlider);
        sliders.put(Slider.TO, toSlider);
    }

    @Step("Using drag-and-drop set Range sliders")
    public void setSliderRange(Slider slider, Direction direction) {
        if (sliders == null) {
            initSliders();
        }
        sliders.get(slider).dragAndDropTo(direction.cssSelector);
    }

    @Step("Using drag-and-drop set Range sliders")
    public void setSliderRange(Slider slider, int position) {
        if (position == 100) {
            setSliderRange(slider, Direction.RIGHT);
        }
        if (position == 0) {
            setSliderRange(slider, Direction.LEFT);
        }
        if (sliders == null) {
            initSliders();
        }
        SelenideElement sliderSelenideElement = sliders.get(slider);
        //int currentPos = Integer.parseInt($(slider.cssSelector + " span").text());
        int currentPos = Integer.parseInt(sliderSelenideElement.find(" span").text());
        int shift = currentPos - position;
        if (shift > 0) {
            for (int i = 0; i < shift + 3; i++) {
                sliderSelenideElement.sendKeys(Keys.ARROW_LEFT);
            }
        } else {
            for (int i = 0; i < Math.abs(shift); i++) {
                sliderSelenideElement.sendKeys(Keys.ARROW_RIGHT);
            }
            for (int i = 0; i < 3; i++) {
                sliderSelenideElement.sendKeys(Keys.ARROW_LEFT);
            }
        }
        sliderSelenideElement.dragAndDropTo(sliderSelenideElement);
    }


    @Step("Assert that for \"From\" and \"To\" sliders there are logs rows with corresponding values")
    public void checkLog() {
        if (sliders == null) {
            initSliders();
        }
        ArrayList<String> expectedLog = new ArrayList<>();
        for (Slider slider : Slider.values()) {
            // TODO This is NOT an expected values, this is values from the page.
            // TODO Basically, you have to parametrise this method..
            SelenideElement sliderSelenideElement = sliders.get(slider);
            expectedLog.add(String.format(slider.logStatus, sliderSelenideElement.find(" span").text()));
        }
        log.shouldHave(Conditions.containsLog(expectedLog));
    }

    public void checkLog(Slider slider, int position) {
        if (sliders == null) {
            initSliders();
        }
        ArrayList<String> expectedLog = new ArrayList<>();
        expectedLog.add(String.format(slider.logStatus, position));
        log.shouldHave(Conditions.containsLog(expectedLog));
    }

}
