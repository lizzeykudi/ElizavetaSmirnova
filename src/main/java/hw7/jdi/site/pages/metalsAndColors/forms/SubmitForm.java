package hw7.jdi.site.pages.metalsAndColors.forms;

import com.epam.jdi.light.elements.complex.Droplist;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.ui.html.common.Button;
import hw7.jdi.entites.Submit;

public class SubmitForm extends Form<Submit> {
    @JDropdown(root = "#colors",
            value = ".filter-option",
            list = "li",
            expand = ".caret")
    public static Droplist color;

    @JDropdown(root = "#metals",
            value = "input",
            list = "li",
            expand = ".caret")
    public static Droplist metal;

    @JDropdown(root = "#vegetables",
            value = "button",
            list = "li",
            expand = ".caret")
    public static Droplist vegetables;

    @Css(".checkbox label")
    public static WebList elements;

    @Css("#submit-button")
    public static Button submitButton;

}
