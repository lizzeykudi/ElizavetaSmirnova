package hw4.indexPage;


import org.testng.Assert;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class IndexPageSelenide{
public static final String TITLE = "Home Page";
    public static final String URL = "https://epam.github.io/JDI/index.html";
    public static final String RELATIVE_URL = "index.html";


    public void assertBrowserTitle() {
        Assert.assertEquals(title(), TITLE);
    }

    public void login(String login, String password) {
        $("[id='user-icon']").click();
        $("[id='name']").sendKeys(login);
        $("[id='password']").sendKeys(password);
        $("[id='login-button']").click();
    }

    public void assertUserName(String nick) {
        $("[id='user-name']").shouldHave(text(nick));
    }

    public void assertHeaderServiceContainsOptions(String[] texts) {
        $(".dropdown-toggle").click();
        $$(".dropdown-menu > li > a").containsAll(Arrays.asList(texts));
    }

    public void assertLeftServiceContainsOptions(String[] texts) {
        $(".sidebar-menu [index='3'] > a > span").click();
        $$(".sub span").containsAll(Arrays.asList(texts));
    }

    public void openThroughHeader(String relativeURL, String url) {
        $(".dropdown-toggle").click();
        $(".dropdown-menu [href='"+relativeURL+"']").click();
        Assert.assertEquals(url(), url);
    }

}
