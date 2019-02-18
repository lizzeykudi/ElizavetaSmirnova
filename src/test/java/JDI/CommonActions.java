package JDI;

import hw4.conditions.Conditions;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public abstract class CommonActions {

    public static void pageIsOpened(String actualTitle, PagesMetaInfo page) {
        Assert.assertEquals(actualTitle, page.title);
    }

    public static void checkLog(List<String> substrings) {
        $$(GlobalVariable.LOG.css).shouldHave(Conditions.containsLog(substrings));
    }
}
