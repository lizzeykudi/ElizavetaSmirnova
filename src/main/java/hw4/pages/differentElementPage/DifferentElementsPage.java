package hw4.pages.differentElementPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.stream.Collectors;

//import static com.codeborne.selenide.Selenide.$$;
import static hw4.conditions.Conditions.containsLog;

public class DifferentElementsPage {
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

    private Map<DifferentElements, ElementsCollection> map;


    @Step("Check interface on Different elements page, it contains all needed elements")
    public void checkInterfaceOnDifferentElementsPage() {
        checkboxes.shouldHaveSize(DifferentElements.CHECKBOXES.expectedCount);
        radios.shouldHaveSize(DifferentElements.RADIOS.expectedCount);
        dropdow.shouldHaveSize(DifferentElements.DROPDOWN.expectedCount);
        buttons.shouldHaveSize(DifferentElements.BUTTONS.expectedCount);
    }

    public void checkInterfaceOnDifferentElementsPage(int countCheckboxes, int countRadios, int countDropdown, int countButtons) {
        checkboxes.shouldHaveSize(countCheckboxes);
        radios.shouldHaveSize(countRadios);
        dropdow.shouldHaveSize(countDropdown);
        buttons.shouldHaveSize(countButtons);
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
        selectCheckboxes(selected, Arrays.asList(texts));
    }

    @Step("Select checkboxes")
    public void selectCheckboxes(boolean selected, List<String> texts) {
        checkboxes.stream()
                .filter(selenideElement -> texts.contains(selenideElement.text()))
                .forEach(selenideElement -> selenideElement.find(DifferentElements.CHECKBOXES.cssSelectorSelect).setSelected(selected));
    }

    @Step("Select radio")
    public void selectRadio(boolean selected, String text) {
        radios.stream()
                .filter(selenideElement -> selenideElement.text().equals(text))
                .forEach(selenideElement -> selenideElement.find(DifferentElements.RADIOS.cssSelectorSelect).setSelected(selected));
    }

    @Step("Select dropdown")
    public void selectDropdown(boolean selected, String text) {
        dropdowOption.stream()
                .filter(selenideElement -> selenideElement.text().equals(text))
                .forEach(selenideElement -> selenideElement.setSelected(selected));
    }


    private ArrayList<String> assertLogDropDown() {
        ArrayList<String> expectedLog = new ArrayList<>();
        for (SelenideElement next : dropdowOption) {
            if (next.isSelected()) {
                expectedLog.add(String.format(DifferentElements.DROPDOWN.logStatus, next.text()));
            }
        }
        return expectedLog;
    }

    public void assertLog(DifferentElements element) {
        if (map == null) {
            getMap();
        }
        ArrayList<String> expectedLog = new ArrayList<>();
        if (element == DifferentElements.DROPDOWN) {
            expectedLog = assertLogDropDown();
        } else {
            for (SelenideElement next : map.get(element)) {
                if (next.find(element.cssSelectorSelect).isSelected()) {
                    expectedLog.add(String.format(element.logStatus, next.text()));
                }
            }
        }
        log.shouldHave(containsLog(expectedLog));
    }

    public void assertLogUnselected(DifferentElements element, List<String> strings) {
        if (map == null) {
            getMap();
        }
        List<String> expected = map.get(element).stream()
                .filter(selenideElement -> strings.contains(selenideElement.text()))
                .map(selenideElement -> String.format(element.logStatus, selenideElement.text()))
                .collect(Collectors.toList());
        log.shouldHave(containsLog(expected));
    }

    // TODO Method 'Get...' should return smth.
    private void getMap() {
        map = new HashMap<>();
        map.put(DifferentElements.CHECKBOXES, checkboxes);
        map.put(DifferentElements.RADIOS, radios);
        map.put(DifferentElements.DROPDOWN, dropdow);
    }
}
