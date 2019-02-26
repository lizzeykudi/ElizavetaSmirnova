package hw4.pages.datesPage.slider;

// TODO This is not the best approach to store locators in enum
public enum Direction {
    LEFT(".sidebar-menu", "0"),
    RIGHT("[name='log-sidebar']", "100");

    public String cssSelector;
    public String number;

    Direction(String cssSelector, String number) {
        this.cssSelector = cssSelector;
        this.number = number;
    }
}
