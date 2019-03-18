package hw7.jdi.site.pages.metalsAndColors;

import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import hw7.jdi.entites.Submit;
import hw7.jdi.entites.Summary;
import hw7.jdi.site.Sections.header.Header;
import hw7.jdi.site.pages.metalsAndColors.forms.SubmitForm;
import hw7.jdi.site.pages.metalsAndColors.forms.SummaryForm;
import hw8.TestData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO Code convention ?? Why don't you reformat your code before commit ??
public class MetalsAndColorsPage extends WebPage {
    public Header header;
    private static SubmitForm submitForm;
    private static SummaryForm summaryForm;

    @Css(".results")
    public static WebList log;

    public static void fillFormAndSubmitButtonClick(Submit submit) {
        SubmitForm.elements.select(submit.getElements());
        SubmitForm.color.select(submit.getColor());
        SubmitForm.metal.select(submit.getMetal());
        Arrays.stream(SubmitForm.vegetables.getSelected().split(", "))
                .forEach(string -> SubmitForm.vegetables.select(string));
        Arrays.stream(submit.getVegetables())
                .forEach(vegetables -> SubmitForm.vegetables.select(vegetables));
        SubmitForm.submitButton.click();
    }

    public static void fillFormAndCalculateButtonClick(Summary summary) {
        for (String string : summary.getSummary()) {
            SummaryForm.summary.select(string);
        }
        SummaryForm.calculateButton.click();
    }

    public static List<String> getLog() {
        return log.values();
    }

    public static List<String> getExpectedLog(TestData testData) {
        List<String> expectedLog = new ArrayList<>();
        expectedLog.add(createLog(testData.getColor()));
        expectedLog.add(createLog(testData.getElements()));
        expectedLog.add(createLog(testData.getMetals()));
        expectedLog.add(createLog(testData.getVegetables()));
        expectedLog.add(createLog(testData.getSummary()));
        return expectedLog;
    }

    private static String createLog(Enum... enums) {
        StringBuilder log = new StringBuilder(enums[0].getClass().getSimpleName() + ": ");
        for (Enum anEnum : enums) {
            log.append(", ").append(anEnum.name());
        }
        return log.toString().replaceFirst(", ", "");
    }

    private static String createLog(String... strings) {
        int sum = 0;
        for (String string : strings) {
            sum += Integer.parseInt(string);
        }
        return "Summary: " + sum;
    }

}
