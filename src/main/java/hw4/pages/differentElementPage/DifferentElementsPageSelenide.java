package hw4.pages.differentElementPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import hw4.conditions.Conditions;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

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

public class DifferentElementsPageSelenide {
    public final static String TITLE = "Different Elements";
    public final static String URL = "https://epam.github.io/JDI/different-elements.html";
    public static final String RELATIVE_URL = "different-elements.html";

    @FindBy(css = ".label-checkbox")
    private ElementsCollection checkboxes;

    @FindBy(css = ".label-radio")
    private ElementsCollection radios;

    @FindBy(css = ".colors .uui-form-element")
    private ElementsCollection dropdow;

    @FindBy(css = ".colors .uui-form-element > option")
    private ElementsCollection dropdowOption;

    @FindBy(css = ".main-content-hg .uui-button")
    private ElementsCollection buttons;

    @FindBy(css = "[name='log-sidebar']")
    private SelenideElement rightSection;

    @FindBy(css = ".sidebar-menu")
    private SelenideElement leftSection;

    @FindBy(css = ".logs > li")
    private ElementsCollection log;


    @Step("Check interface on Different elements page, it contains all needed elements")
    public void checkInterfaceOnDifferentElementsPage() {
        for (ElementsOnDifferentElementsPage element : ElementsOnDifferentElementsPage.values()) {
            $$(element.cssSelector).shouldHaveSize(element.expectedCount);
        }
        checkboxes.shouldHaveSize(ElementsOnDifferentElementsPage.CHECKBOXES.expectedCount);
        radios.shouldHaveSize(ElementsOnDifferentElementsPage.RADIOS.expectedCount);
        dropdow.shouldHaveSize(ElementsOnDifferentElementsPage.DROPDOWN.expectedCount);
        buttons.shouldHaveSize(ElementsOnDifferentElementsPage.BUTTONS.expectedCount);
    }

    public void checkInterfaceOnDifferentElementsPage(int countCheckboxes, int countRadios, int countDropdown, int countButtons) {
        checkboxes.shouldHaveSize(ElementsOnDifferentElementsPage.CHECKBOXES.expectedCount);
        radios.shouldHaveSize(ElementsOnDifferentElementsPage.RADIOS.expectedCount);
        dropdow.shouldHaveSize(ElementsOnDifferentElementsPage.DROPDOWN.expectedCount);
        buttons.shouldHaveSize(ElementsOnDifferentElementsPage.BUTTONS.expectedCount);
    }

    @Step("Assert that there is Right Section")
    public void assertRightSectionIsDisplayed() {
        rightSection.shouldBe(Condition.visible);
    }

    @Step("Assert that there is Left Section")
    public void assertLeftSectionIsDisplayed() {
        leftSection.shouldBe(Condition.visible);
    }

    @Step("Select checkboxes")
    public void selectCheckboxes(boolean selected, String... texts) {
        checkboxes.stream()
                .filter(selenideElement -> Arrays.asList(texts).contains(selenideElement.text()))
                .forEach(selenideElement -> selenideElement.find(ElementsOnDifferentElementsPage.CHECKBOXES.cssSelectorSelect).setSelected(selected));
    }

    @Step("Select radio")
    public void selectRadio(boolean selected, String text) {
        radios.stream()
                .filter(selenideElement -> selenideElement.text().equals(text))
                .forEach(selenideElement -> selenideElement.find(ElementsOnDifferentElementsPage.RADIOS.cssSelectorSelect).setSelected(selected));
    }

    @Step("Select dropdown")
    public void selectDropdown(boolean selected, String text) {
        dropdowOption.stream()
                .filter(selenideElement -> selenideElement.text().equals(text))
                .forEach(selenideElement -> selenideElement.setSelected(selected));
    }



    private ArrayList<String> assertLogDropDown() {
        ArrayList<String> expectedLog = new ArrayList<>();
        /*for (SelenideElement next : $$(ElementsOnDifferentElementsPage.DROPDOWN.cssSelector
                + ElementsOnDifferentElementsPage.DROPDOWN.cssSelectorSelect)) {*/
        for (SelenideElement next : dropdowOption) {
            if (next.isSelected()) {
                expectedLog.add(String.format(ElementsOnDifferentElementsPage.DROPDOWN.logStatus, next.text()));
                return expectedLog;
            }
        }
        return expectedLog;
    }
    public void checkLogDropdown() {
        log.shouldHave(Conditions.containsLog(expectedLog(dropdowOption, ElementsOnDifferentElementsPage.DROPDOWN)));
    }
    /*public ArrayList<String> checkLogCheckboxesUnselect() {
        log.shouldHave(Conditions.containsTexts(expectedLog(dropdowOption, ElementsOnDifferentElementsPage.DROPDOWN)));
    }
    public ArrayList<String> checkLogRadios() {

    }
    public ArrayList<String> checkLogCheckboxesUnselect() {

    }*/

    private ArrayList<String> expectedLog(ElementsCollection collection, ElementsOnDifferentElementsPage element) {
        ArrayList<String> expectedLog = new ArrayList<>();
        for (SelenideElement next : collection) {
            if (next.find(element.cssSelectorSelect) != null && next.find(element.cssSelectorSelect).isSelected()) {
                expectedLog.add(String.format(element.logStatus, next.text()));
            } else {
                if (next.isSelected()) {
                    expectedLog.add(String.format(element.logStatus, next.text()));
                }
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
        //.shouldHave(Conditions.containsTexts(expectedLog));
    }

    @Step("Assert unselect")
    public void assertLogUnselected(ElementsOnDifferentElementsPage checkboxes, String[] strings) {
        List<String> expected = $$(checkboxes.cssSelector).stream()
                .filter(selenideElement -> Arrays.asList(strings).contains(selenideElement.text()))
                .map(selenideElement -> String.format(checkboxes.logStatus, selenideElement.text()))
                .collect(Collectors.toList());
        log.shouldHave(Conditions.containsLog(expected));
    }
}
