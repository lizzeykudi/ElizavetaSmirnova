package hw4.pages.differentElementPage;

public enum DifferentElements {
    CHECKBOXES(4,  " input", "%s: condition changed to true", "%s: condition changed to false"),
    RADIOS(4,  " input", "metal: value changed to %s", ""),
    DROPDOWN(1,  " ", "Colors: value changed to %s", ""),
    BUTTONS(2,  "", "", "");

    public int expectedCount;
    public String cssSelectorSelect;
    public String logStatus;
    public String logSatusUnselected;

    DifferentElements(int expectedCount, String cssSelectorSelect, String logStatus, String logSatusUnselected) {
        this.expectedCount = expectedCount;
        this.cssSelectorSelect = cssSelectorSelect;
        this.logStatus = logStatus;
        this.logSatusUnselected = logSatusUnselected;
    }
}
