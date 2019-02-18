package hw6;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

public class UserTable {
    private String user;
    private ElementsCollection possibleTypes;
    private SelenideElement dropdown;

    public UserTable(String user, SelenideElement element) {
        this.user = user;
        dropdown = element.find("td select");
        possibleTypes = element.findAll(" td select option");
    }

    public ElementsCollection getPossibleTypes() {
        return possibleTypes;
    }

    public SelenideElement getDropdown() {
        return dropdown;
    }
}
