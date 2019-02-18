package hw6;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

public class UserTable {
    private String user;
    private List<String> possibleTypes;
    private SelenideElement dropdown;

    public UserTable(String user, SelenideElement element) {
        this.user = user;
        dropdown = element.find("td select");
        possibleTypes = element.findAll(" td select option").texts();
    }

    public List<String> getPossibleTypes() {
        return possibleTypes;
    }

    public SelenideElement getDropdown() {
        return dropdown;
    }
}
