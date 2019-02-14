package hw4.differentElementPage;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementsCollection;
import hw5.listeners.AllureTestListener;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DifferentElementsPageSelenide {
    public final static String TITLE = "Different Elements";
    public final static String URL = "https://epam.github.io/JDI/different-elements.html";
    public static final String RELATIVE_URL = "different-elements.html";


    @Step("Check interface on Different elements page, it contains all needed elements")
    public void checkInterfaceOnDifferentElementsPage() {
        for (ElementsOnDifferentElementsPage element : ElementsOnDifferentElementsPage.values()) {
            $$(element.cssSelector).shouldHaveSize(element.expectedCount);
        }
    }

    @Step("Assert that there is Right Section")
    public void assertRightSectionIsDisplayed() {
        $("[name='log-sidebar']").shouldBe(Condition.visible);
    }

    @Step("Assert that there is Left Section")
    public void assertLeftSectionIsDisplayed() {
        $(".sidebar-menu").shouldBe(Condition.visible);
    }

    @Step("Select")
    public void select(ElementsOnDifferentElementsPage element, String... strings) {
        String cssSelector = element == ElementsOnDifferentElementsPage.DROPDOWN ? element.cssSelector + " > option" : element.cssSelector;
        $$(cssSelector).stream()
                .filter(selenideElement -> Arrays.asList(strings).contains(selenideElement.text()))
                .forEach(SelenideElement::click);
    }

    @Step("Unselect")
    public void select(ElementsOnDifferentElementsPage element, String[] strings, boolean select) {
        String cssSelector = element == ElementsOnDifferentElementsPage.DROPDOWN ? element.cssSelector + " > option" : element.cssSelector;
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

    @Step("Assert that for each element there is an individual log row and value is corresponded to the status of element")
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

    @Step("Assert unselect")
    public void assertLogUnselected(ElementsOnDifferentElementsPage checkboxes, String[] strings) {
        List<String> expected = $$(checkboxes.cssSelector).stream()
                .filter(selenideElement -> Arrays.asList(strings).contains(selenideElement.text()))
                .map(selenideElement -> String.format(checkboxes.logStatus, selenideElement.text()))
                .collect(Collectors.toList());
        $$(".logs > li").shouldHave(containsTexts(expected));
    }
}
