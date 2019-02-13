package hw4.datesPage;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.impl.WebElementsCollection;
import hw4.datesPage.slider.Direction;
import hw4.datesPage.slider.Slider;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

// TODO The same comments like DifferentElementsPageSelenide
// TODO Basically, this PO should have only 2 public methods:
// TODO setSliderRange(int, int)
// TODO checkLog(...)
public class DatesPageSelenide {
    public final static String TITLE = "Dates";
    public final static String URL = "https://epam.github.io/JDI/dates.html";
    public static final String RELATIVE_URL = "dates.html";


    public void setSlider(Slider slider, Direction direction) {
        $(slider.cssSelector).dragAndDropTo(direction.cssSelector);
    }

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


    public void assertLog() {
        ArrayList<String> expectedLog = new ArrayList<>();
        for (Slider slider : Slider.values()) {
            expectedLog.add(String.format(slider.logStatus, $(slider.cssSelector + " span").getText()));
        }
        $$(".logs > li").shouldHave(containsTexts(expectedLog));
        }

    private static CollectionCondition containsTexts(List<String> substrings) {
        return new CollectionCondition() {
            @Override
            public void fail(WebElementsCollection webElementsCollection, List<WebElement> list, Exception e, long l) {
                System.out.println("Actual: " + list.stream()
                        .map(webElement -> webElement.getText().substring(8))
                        .collect(Collectors.toList()));
                System.out.println("Expected:" + substrings);
                throw new RuntimeException("fail");
            }

            @Override
            public boolean apply(@Nullable List<WebElement> webElements) {
                return webElements.stream()
                        .map(webElement -> webElement.getText().substring(9))
                        .collect(Collectors.toList()).containsAll(substrings);
            }
        };
    }

}
