package hw6;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;

public class UserTablePage {
    public static final String TITLE = "User Table";
    public static final String URL = "https://epam.github.io/JDI/user-table.html";
    public static final String RELATIVE_URL = "user-table.html";

    @FindBy(css = "#user-table tr td select")
    private ElementsCollection numberTypes;

    @FindBy(css = "#user-table tr td [href]")
    private ElementsCollection userNames;

    @FindBy(css = ".user-descr")
    private ElementsCollection descriptionImages;

    @FindBy(css = ".user-descr span")
    private ElementsCollection descriptionTextsUnderImages;

    @FindBy(css = ".user-descr [type='checkbox']")
    private ElementsCollection checkboxes;

    @FindBy(css = "#user-table tr")
    private ElementsCollection table;

    @FindBy(css = "#user-table tr td:nth-child(1)")
    private ElementsCollection numbers;

    Map<String, ElementsCollection> tableElements;
    Map<String, UserTable> userTable;


    private void getTableElements() {
        tableElements = new HashMap<>();
        tableElements.put("NumberType Dropdowns", numberTypes);
        tableElements.put("User names", userNames);
        tableElements.put("Description images", descriptionImages);
        tableElements.put("Description texts under images", descriptionTextsUnderImages);
        tableElements.put("checkboxes", checkboxes);
    }

    private void  getUserTable() {
        userTable = new HashMap<>();
        Iterator<SelenideElement> iterator = table.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            SelenideElement next = iterator.next();
            String name = next.find(" td [href]").text();
            userTable.put(name, new UserTable(name, next));
        }
    }

    public void checkCountTableElements(String element, int count) {
        if (tableElements==null) {getTableElements();}
        tableElements.get(element).shouldHaveSize(count);
    }


    public void checkUserTable(List<List<String>> lists) {
        lists = lists.stream()
                .map(strings -> strings.stream()
                        .skip(1)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        numbers.shouldBe(CollectionCondition.exactTexts(lists.get(0)));
        userNames.shouldBe(CollectionCondition.exactTexts(lists.get(1)));
        descriptionTextsUnderImages.shouldBe(CollectionCondition.exactTexts(lists.get(2)));
    }

    public void selectVipfor(String name, boolean selected) {
        $("#"+name).setSelected(selected);
    }

    public void clickDropdown(String name) {
        if (userTable==null) {getUserTable();}
        userTable.get(name).getDropdown().click();
    }

    public void dropdownContains(List<String> strings, String name) {
        if (userTable==null) {getUserTable();}
        userTable.get(name).getPossibleTypes().shouldHave(CollectionCondition.exactTexts(strings));
    }
}
