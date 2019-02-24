package hw7.jdi.site.pages.metalsAndColors;

import com.epam.jdi.light.elements.complex.Droplist;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.light.ui.html.complex.*;
import hw7.jdi.site.Sections.header.Header;

public class MetalsAndColorsPage extends WebPage {
    public Header header;

    @JDropdown(root = "#colors",
            value = ".filter-option",
            list = "li",
            expand = ".caret")
    public static Droplist colors;

   @JDropdown(root = "#metals",
   value = "input",
           list = "li",
           expand = ".caret")
   public static Droplist metals;

   @JDropdown(root = "#vegetables",
            value = "button",
            list = "li",
            expand = ".caret")
   public static Droplist vegetables;

   @Css(".radio label")
   public static RadioButtons summary;

   @Css(".checkbox label")
   public static WebList elements;

   @Css("#calculate-button")
   public Button calculateButton;

   @Css("#submit-button")
   public Button submitButton;

   @Css(".results")
    public static WebList results;
}
