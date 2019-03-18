package hw8;


import com.google.gson.*;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestDataDataProvider {

    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {


        // TODO Your algorithm should not depends on data set names !
        JsonReader reader = new JsonReader(new FileReader("src/test/resources/hw8/JDI_ex8_metalsColorsDataSet.json"));
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(reader);
        Map<String, TestData> dataMap = new Gson().fromJson(jsonObject,
                new TypeToken<Map<String, TestData>>() {
                }.getType());
        List<TestData> list = new ArrayList<>(dataMap.values());
        Object[][] objects = new Object[dataMap.size()][1];
        for (int y = 0; y < list.size(); y++) {
            objects[y][0] = list.get(y);
        }
        return objects;
    }
}
