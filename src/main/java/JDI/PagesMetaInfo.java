package JDI;

public enum PagesMetaInfo {
    HOME("Home Page", "index.html"),
    DATES("Dates", "dates.html"),
    DIFFERENT_ELEMENTS("Different Elements", "different-elements.html"),
    USER_TABLE("User Table", "user-table.html");


    public String title;
    public String url;
    public String relativeUrl;

    PagesMetaInfo(String title, String relativeUrl) {
        this.title = title;
        this.url = "https://epam.github.io/JDI/" + relativeUrl;
        this.relativeUrl = relativeUrl;
    }
}
