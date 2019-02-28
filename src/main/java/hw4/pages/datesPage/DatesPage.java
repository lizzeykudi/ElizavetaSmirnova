package hw4.pages.datesPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import hw4.conditions.Conditions;
import hw4.pages.datesPage.slider.Slider;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @FindBy(css = ".sidebar-menu")
    private SelenideElement left;

    @FindBy(css = "[name='log-sidebar']")
    private SelenideElement right;

    Map<Slider, SelenideElement> sliders;

    private void initSliders() {
        sliders = new HashMap<>();
        sliders.put(Slider.FROM, fromSlider);
        sliders.put(Slider.TO, toSlider);
    }


    @Step("Using drag-and-drop set Range sliders")
    public void setSliderRange(Slider slider, int position) {

        if (sliders == null) {
            initSliders();
        }

        if (position == 100) {
            sliders.get(slider).dragAndDropTo(right);
            return;
        }
        if (position == 0) {
            sliders.get(slider).dragAndDropTo(left);
            return;
        }
        SelenideElement sliderSelenideElement = sliders.get(slider);
        int currentPos = Integer.parseInt(sliderSelenideElement.find(" span").text());
        int shift = currentPos - position;
        if (shift > 0) {
            for (int i = 0; i < shift; i++) {
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

    public void checkLog(Slider slider, int position) {
        if (sliders == null) {
            initSliders();
        }
        ArrayList<String> expectedLog = new ArrayList<>();
        expectedLog.add(String.format(slider.logStatus, position));
        log.shouldHave(Conditions.containsLog(expectedLog));
    }

}
