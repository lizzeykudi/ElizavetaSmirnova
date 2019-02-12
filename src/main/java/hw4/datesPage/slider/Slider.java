package hw4.datesPage.slider;

public enum Slider {
    FROM(".ui-state-default.ui-corner-all:nth-child(1)", "Range 2(From):%s link clicked"),
    TO(".ui-state-default.ui-corner-all:nth-child(3)", "Range 2(To):%s link clicked");
    public String cssSelector;
    public String logStatus;

    Slider(String cssSelector, String logStatus) {
        this.cssSelector = cssSelector;
        this.logStatus = logStatus;
    }
}
