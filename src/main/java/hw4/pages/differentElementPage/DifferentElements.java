package hw4.pages.differentElementPage;

public enum DifferentElements {
    CHECKBOXES(4, ".label-checkbox", " input", "%s: condition changed to true", "%s: condition changed to false"),
    RADIOS(4, ".label-radio", " input", "metal: value changed to %s", ""),
    DROPDOWN(1, ".colors .uui-form-element", " ", "Colors: value changed to %s", ""),
    BUTTONS(2, ".main-content-hg .uui-button", "", "", "");

    public int expectedCount;
    public String cssSelector;
    public String cssSelectorSelect;
    public String logStatus;
    public String logSatusUnselected;

    DifferentElements(int expectedCount, String cssSelector, String cssSelectorSelect, String logStatus, String logSatusUnselected) {
        this.expectedCount = expectedCount;
        this.cssSelector = cssSelector;
        this.cssSelectorSelect = cssSelectorSelect;
        this.logStatus = logStatus;
        this.logSatusUnselected = logSatusUnselected;
    }
}
