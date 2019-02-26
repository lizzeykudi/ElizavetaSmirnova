package hw8;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import hw7.TestInit;
import hw7.jdi.entites.User;
import hw7.jdi.site.pages.metalsAndColors.MetalsAndColorsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.reflect.TypeToken;

import static hw7.jdi.JDISite.homePage;
import static hw7.jdi.JDISite.metalAndColorsPage;

public class JDITest extends TestInit {

    @Test(dataProvider = "getData")
    public void jdiTest(TestData data) {
        //System.out.println(data);

        List<String> expectedLog = new ArrayList<>();

        homePage.open();

        //1 Login on JDI site
        homePage.header.login(User.PITER_CHAILOVSKII);
        homePage.checkOpened();

        //2 Open Metal&Color page by Header menu
        homePage.header.headerMenu.metalsAndColors.click();
        metalAndColorsPage.checkOpened();

        //3 Submit form Metal&Color

        expectedLog.add(sum(data.getSummary()));

        MetalsAndColorsPage.elements.select(data.getElements());
        expectedLog.add(getExpectedLog(data.getElements()));

        MetalsAndColorsPage.colors.select(data.getColor());
        expectedLog.add(getExpectedLog(data.getColor()));

        MetalsAndColorsPage.metals.select(data.getMetals());
        expectedLog.add(getExpectedLog(data.getMetals()));

        Arrays.stream(MetalsAndColorsPage.vegetables.getSelected().split(", "))
                .forEach(string -> MetalsAndColorsPage.vegetables.select(string));
        Arrays.stream(data.getVegetables())
                .forEach(vegetables -> MetalsAndColorsPage.vegetables.select(vegetables));
        expectedLog.add(getExpectedLog(data.getVegetables()));


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


    @DataProvider(parallel = true)
    public Object[][] getData() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/hw8/JDI_ex8_metalsColorsDataSet.json"));
        ArrayList<TestData> list=new ArrayList<>();
        JsonElement dataSet = null;
        int i = 1;
        dataSet = jsonData.getAsJsonObject().get("data_"+i);
        while (dataSet!=null) {
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(TestData.class, new TestDataConverter());
        Gson gson = builder.create();
            TestData testData = gson.fromJson(dataSet, TestData.class);
            list.add(testData);
            i++;
            dataSet = jsonData.getAsJsonObject().get("data_"+i);
        }


        /*List<TestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestData>>(){}.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;*/
        Object[][] objects = new Object[list.size()][1];
        for (int y = 0; y<list.size(); y++) {
            objects[y][0] = list.get(y); 
        }
        
        return objects;
    }
}
