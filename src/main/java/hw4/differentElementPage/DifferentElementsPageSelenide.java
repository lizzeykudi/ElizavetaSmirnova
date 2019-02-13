package hw4.differentElementPage;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementsCollection;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

// TODO Code convention !
// TODO Basically, this is not a PO
// TODO You have to specify all page element as fields here, use @FindBy annotation
// TODO You should not find elements in PO methods(actions)
// TODO Page actions should be named with reference to it purpose, business value
public class DifferentElementsPageSelenide{
    public final static String TITLE = "Different Elements";
    public final static String URL = "https://epam.github.io/JDI/different-elements.html";
    public static final String RELATIVE_URL = "different-elements.html";



    public void checkInterfaceOnDifferentElementsPage() {
        for (ElementsOnDifferentElementsPage element : ElementsOnDifferentElementsPage.values()) {
            $$(element.cssSelector).shouldHaveSize(element.expectedCount);
        }
    }

    public void assertRightSectionIsDisplayed() {
        $("[name='log-sidebar']").shouldBe(Condition.visible);
    }

    public void assertLeftSectionIsDisplayed() {
        $(".sidebar-menu").shouldBe(Condition.visible);
    }

    public void select(ElementsOnDifferentElementsPage element, String[] strings) {
        String cssSelector = element == ElementsOnDifferentElementsPage.DROPDOWN ? element.cssSelector+" > option" : element.cssSelector;
        $$(cssSelector).stream()
                .filter(selenideElement -> Arrays.asList(strings).contains(selenideElement.text()))
                .forEach(SelenideElement::click);
        }

    public void select(ElementsOnDifferentElementsPage element, String[] strings, boolean select) {
        String cssSelector = element == ElementsOnDifferentElementsPage.DROPDOWN ? element.cssSelector+" > option" : element.cssSelector;
        $$(cssSelector).stream()
                .filter(selenideElement -> Arrays.asList(strings).contains(selenideElement.text()))
                .forEach(SelenideElement::click);
        $$(cssSelector).stream()
                .filter(selenideElement -> Arrays.asList(strings).contains(selenideElement.text()))
                .forEach(selenideElement -> {
                    $(element.cssSelector + element.cssSelectorSelect).shouldNotBe(Condition.selected);
                });
    }


    private ArrayList<String> assertLogDropDown() {
        ArrayList<String> expectedLog = new ArrayList<>();
        for (SelenideElement next : $$(ElementsOnDifferentElementsPage.DROPDOWN.cssSelector
                + ElementsOnDifferentElementsPage.DROPDOWN.cssSelectorSelect)) {
            if (next.isSelected()) {
                expectedLog.add(String.format(ElementsOnDifferentElementsPage.DROPDOWN.logStatus, next.text()));
            }
        }
        return expectedLog;
    }
    public void assertLog(ElementsOnDifferentElementsPage element) {
        ArrayList<String> expectedLog = new ArrayList<>();
        if (element == ElementsOnDifferentElementsPage.DROPDOWN) {
            expectedLog = assertLogDropDown();
        } else {
            for (SelenideElement next : $$(element.cssSelector)) {
                if (next.find(element.cssSelectorSelect).isSelected()) {
                    expectedLog.add(String.format(element.logStatus, next.text()));
                }
            }

        }
        $$(".logs > li").shouldHave(containsTexts(expectedLog));
    }

    private static CollectionCondition containsTexts(List<String> substrings) {
        return new CollectionCondition() {
            @Override
            public void fail(WebElementsCollection webElementsCollection, List<WebElement> list, Exception e, long l) {
                System.out.println("Actual: " + list.stream()
                        .map(webElement -> webElement.getText().substring(7))
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

    public void assertLogUnselected(ElementsOnDifferentElementsPage checkboxes, String[] strings) {
        List<String> expected = $$(checkboxes.cssSelector).stream()
                .filter(selenideElement -> Arrays.asList(strings).contains(selenideElement.text()))
                .map(selenideElement -> String.format(checkboxes.logStatus, selenideElement.text()))
                .collect(Collectors.toList());
        $$(".logs > li").shouldHave(containsTexts(expected));
    }
}
