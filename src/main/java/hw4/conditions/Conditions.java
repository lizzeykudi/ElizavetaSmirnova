package hw4.conditions;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.impl.WebElementsCollection;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class Conditions {
    public static CollectionCondition containsTexts(List<String> substrings) {
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
