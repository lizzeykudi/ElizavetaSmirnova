package hw4.pages.indexPage;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import globalVariables.Users;
import hw4.conditions.Conditions;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;


public class IndexPageSelenide {
    public static final String TITLE = "Home Page";
    public static final String URL = "https://epam.github.io/JDI/index.html";
    public static final String RELATIVE_URL = "index.html";

    @FindBy(css = "[id='user-icon']")
    private SelenideElement userIconField;

    @FindBy(css = "[id='name']")
    private SelenideElement userNameField;

    @FindBy(css = "[id='password']")
    private SelenideElement userPassportField;

    @FindBy(css = "[id='login-button']")
    private SelenideElement submitButton;

    @FindBy(css = "[id='user-name']")
    private SelenideElement userNickName;

    @FindBy(css = ".dropdown-toggle")
    private SelenideElement headerSrvice;

    @FindBy(css = ".dropdown-menu > li > a")
    private ElementsCollection dropDownMenuElements;

    @FindBy(css = ".sidebar-menu [index='3'] > a > span")
    private SelenideElement leftService;

    @FindBy(css = ".sub span")
    private ElementsCollection leftServiceElements;

    @FindBy(css = ".benefit-icon")
    private ElementsCollection icons;

    @FindBy(css = ".benefit-txt")
    private ElementsCollection textsUnderIcons;

    @FindBy(css = ".main-title.text-center")
    private SelenideElement headerline;

    @FindBy(css = ".main-txt.text-center")
    private SelenideElement description;

    @FindBy(css = ".dropdown-menu li [href='user-table.html']")
    private SelenideElement userTable;

    @Step("Assert Browser title")
    public void assertBrowserTitle() {
        Assert.assertEquals(title(), TITLE);
    }

    @Step("Perform login")
    public void login(Users user) {
        userIconField.click();
        userNameField.sendKeys(user.login);
        userPassportField.sendKeys(user.password);
        submitButton.click();
    }

    @Step("Assert User name in the left-top side of screen that user is loggined")
    public void assertUserName(Users user) {
        userNickName.shouldHave(text(user.nick));
    }

    @Step("Click on \"Service\" subcategory in the header and check that drop down contains options")
    public void assertHeaderServiceContainsOptions(String[] texts) {
        assertHeaderServiceContainsOptions(Arrays.asList(texts));
    }
    public void assertHeaderServiceContainsOptions(List<String> texts) {
        clickServiceOnHeader();
        dropDownMenuElements.shouldHave(Conditions.containsTexts(texts));
    }

    @Step("Click on Service subcategory in the left section and check that drop down contains options")
    public void assertLeftServiceContainsOptions(String[] texts) {
        assertLeftServiceContainsOptions(Arrays.asList(texts));
    }

    public void assertLeftServiceContainsOptions(List<String> texts) {
        leftService.click();
        leftServiceElements.shouldHave(Conditions.containsTexts(texts));
    }

    @Step("Open through the header menu Service -> Different Elements Page")
    public void openThroughHeader(String relativeURL, String url) {
        clickServiceOnHeader();
        $(".dropdown-menu [href='"+relativeURL+"']").click();
        Assert.assertEquals(url(), url);
    }

    public void contains(int countPictures, int countTextsUnderItem, int countText) {
        icons.shouldHaveSize(countPictures);
        textsUnderIcons.shouldHaveSize(countTextsUnderItem);
        headerline.shouldBe(visible);
        description.shouldBe(visible);
    }

    public void clickServiceOnHeader() {
        headerSrvice.click();
    }

    public void clickUserTable() {
        userTable.click();
    }
}
