package hw8;

import com.google.gson.*;
import hw7.jdi.entites.Color;
import hw7.jdi.entites.Elements;
import hw7.jdi.entites.User;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TestDataConverter implements JsonDeserializer<TestData> {

    @Override
    public TestData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Field[] declaredFields = TestData.class.getDeclaredFields();
        ArrayList<Object> construct = new ArrayList<>();
        for (Field field : declaredFields) {
            Object objects;
            JsonElement jsonElement1 = object.get(field.getName());
            if (field.getType().isArray()) {
                String className = field.getType().getTypeName().substring(0, field.getType().getTypeName().length() - 2);
              /*  if (className.equals("java.lang.String")) {

                    objects = new String[]{"1", "2"};
                } else {*/
                    Class c = null;
                    try {
                        c = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    objects = Array.newInstance(c, jsonElement1.getAsJsonArray().size());
                    //Object[] objects = new Object[jsonElement1.getAsJsonArray().size()];
                    Iterator<JsonElement> iterator = jsonElement1.getAsJsonArray().iterator();
                    int i = 0;
                    while (iterator.hasNext()) {
                        Object o = null;
                            JsonElement next = iterator.next();

                            if (className.equals(String.class.getTypeName())) {o = next.getAsString();}
                            else {
                            o = Enum.valueOf(c, next.getAsString()); }


                        Array.set(objects, i, o);
                        //objects[i] = o;
                        i++;
                    }
                }

            else {
                String className = field.getType().getTypeName();
                    Class c = null;
                    try {
                        c = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    objects = Enum.valueOf(c, jsonElement1.getAsString());


                }


            construct.add(objects);

        }
        return new TestData(construct);
    }
}
