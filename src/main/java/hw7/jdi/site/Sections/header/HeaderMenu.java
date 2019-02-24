package hw7.jdi.site.Sections.header;

import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.ui.html.common.Button;
import org.openqa.selenium.support.FindBy;

public class HeaderMenu extends Section {
    @FindBy(css = ".m-l8 [href='metals-colors.html']")
    public Button metalsAndColors;
}
