package JDI.pagesInfo;

import java.util.Arrays;

public enum ServiceMenuPages {
    SUPPORT("Support", "support.html"),
    DATES("Dates", "dates.html"),
    COMPLEX_TABLE("Complex Table", "complex-table.html"),
    SIMPLE_TABLE("Simple Table", "simple-table.html"),
    USER_TABLE("User Table", "user-table.html"),
    TABLE_WITH_PAGES("Table with pages", "table-pages.html"),
    DIFFERENT_ELEMENTS("Different Elements", "different-elements.html"),
    PERFORMANCE("Paerfomance", "perfomance.html");


    public String title;
    public String url;
    public String relativeUrl;

    ServiceMenuPages(String title, String relativeUrl) {
        this.title = title;
        this.url = "https://epam.github.io/JDI/" + relativeUrl;
        this.relativeUrl = relativeUrl;
    }

    public static String[] getValues() {
        return Arrays.stream(values()).map(string ->string.toString().replaceAll("_", " ")).toArray(String[]::new);
    }
}
