package hw4.indexPage;


import hw5.listeners.AllureTestListener;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;


public class IndexPageSelenide{
public static final String TITLE = "Home Page";
    public static final String URL = "https://epam.github.io/JDI/index.html";
    public static final String RELATIVE_URL = "index.html";

    @Step("Assert Browser title")
    public void assertBrowserTitle() {
        Assert.assertEquals(title(), TITLE);
    }

    @Step("Perform login")
    public void login(String login, String password) {
        $("[id='user-icon']").click();
        $("[id='name']").sendKeys(login);
        $("[id='password']").sendKeys(password);
        $("[id='login-button']").click();
    }

    @Step("Assert User name in the left-top side of screen that user is loggined")
    public void assertUserName(String nick) {
        $("[id='user-name']").shouldHave(text(nick));
    }

    @Step("Click on \"Service\" subcategory in the header and check that drop down contains options")
    public void assertHeaderServiceContainsOptions(String[] texts) {
        $(".dropdown-toggle").click();
        $$(".dropdown-menu > li > a").containsAll(Arrays.asList(texts));
    }

    @Step("Click on Service subcategory in the left section and check that drop down contains options")
    public void assertLeftServiceContainsOptions(String[] texts) {
        $(".sidebar-menu [index='3'] > a > span").click();
        $$(".sub span").containsAll(Arrays.asList(texts));
    }

    @Step("Open through the header menu Service -> Different Elements Page")
    public void openThroughHeader(String relativeURL, String url) {
        $(".dropdown-toggle").click();
        $(".dropdown-menu [href='"+relativeURL+"']").click();
        Assert.assertEquals(url(), url);
    }

}
