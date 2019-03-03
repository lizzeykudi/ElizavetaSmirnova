package hw4.pages.datesPage.slider;

// TODO I told you that it is NOT really good idea to store locators in enum. Why don't you use the common approach with PO ?
public enum Slider {
    FROM("Range 2(From):%s link clicked"),
    TO("Range 2(To):%s link clicked");
    public String logStatus;

    Slider(String logStatus) {
        this.logStatus = logStatus;
    }
}
