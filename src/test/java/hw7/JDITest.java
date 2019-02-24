package hw7;

import hw7.jdi.entites.*;
import hw7.jdi.site.pages.metalsAndColors.MetalsAndColorsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static hw7.jdi.JDISite.*;

public class JDITest extends TestInit{
    private String[] summary = new String[]{"3", "8"};
    private Color color = Color.Red;
    private Metal metal = Metal.Selen;
    private Vegetables[] vegetables = new Vegetables[]{Vegetables.Cucumber, Vegetables.Tomato};
    private Elements[] elements = new Elements[]{Elements.Water, Elements.Fire};

    @Test
    public void jdiTest() {
        List<String> expectedLog = new ArrayList<>();

        homePage.open();

        //1 Login on JDI site
        homePage.header.login(User.PITER_CHAILOVSKII);
        homePage.checkOpened();

        //2 Open Metal&Color page by Header menu
        homePage.header.headerMenu.metalsAndColors.click();
        metalAndColorsPage.checkOpened();

        //3 Submit form Metal&Color

        expectedLog.add(sum(summary));

        MetalsAndColorsPage.elements.select(elements);
        expectedLog.add(getExpectedLog(elements));

        MetalsAndColorsPage.colors.select(color);
        expectedLog.add(getExpectedLog(color));

        MetalsAndColorsPage.metals.select(metal);
        expectedLog.add(getExpectedLog(metal));

        Arrays.stream(MetalsAndColorsPage.vegetables.getSelected().split(", "))
                .forEach(string -> MetalsAndColorsPage.vegetables.select(string));
        Arrays.stream(vegetables)
                .forEach(vegetables -> MetalsAndColorsPage.vegetables.select(vegetables));
        expectedLog.add(getExpectedLog(vegetables));


        //4 Result sections should contains
        metalAndColorsPage.calculateButton.click();
        metalAndColorsPage.submitButton.click();
        Assert.assertTrue(MetalsAndColorsPage.results.values().containsAll(expectedLog));

    }

    private String sum(String... strings) {
        int sum = 0;
        for (String string : strings) {
            MetalsAndColorsPage.summary.select(string);
            sum+=Integer.parseInt(string);
        }
        return "Summary: " + sum;
    }

    private String getExpectedLog(Enum... enums) {
        StringBuilder log = new StringBuilder(enums[0].getClass().getSimpleName() + ": ");
        for (Enum anEnum : enums) {
            log.append(", ").append(anEnum.name());
        }
        return log.toString().replaceFirst(", ", "");
    }
}
