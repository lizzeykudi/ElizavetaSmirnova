package globalVariables;

public enum Texts {
    ITEMS(new String[]{"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"}),
    TEXTS(new String[]{
            "To include good practices\n" +
                    "and ideas from successful\n" +
                    "EPAM project",

            "To be flexible and\n" +
                    "customizable",

            "To be multiplatform",

            "Already have good base\n" +
                    "(about 20 internal and\n" +
                    "some external projects),\n" +
                    "wish to get more…"}),
    // TODO You can create different enum with this items
    SERVICE_TEXTS(new String[]{"SUPPORT, DATES, COMPLEX TABLE, SIMPLE TABLE, USER TABLE, TABLE WITH PAGES, DIFFERENT ELEMENTS, PERFORMANCE"});
    public String[] texts;
    public int size;

    Texts(String[] texts) {
        this.texts = texts;
        size = texts.length;
    }
}
