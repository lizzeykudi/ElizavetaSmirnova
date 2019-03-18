package hw7.jdi.site.pages.metalsAndColors.forms;

import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.light.ui.html.complex.RadioButtons;
import hw7.jdi.entites.Summary;

public class SummaryForm extends Form<Summary> {
    @Css(".radio label")
    public static RadioButtons summary;

    @Css("#calculate-button")
    public static Button calculateButton;
}
