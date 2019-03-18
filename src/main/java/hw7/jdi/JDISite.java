package hw7.jdi;

import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.ui.html.complex.Menu;
import hw7.jdi.site.pages.HomePage.HomePage;
import hw7.jdi.site.pages.metalsAndColors.MetalsAndColorsPage;

@JSite("https://epam.github.io/JDI/")
public class JDISite {
    @Url("index.html")
    public static HomePage homePage;
    @Url("metals-colors.html")
    public static MetalsAndColorsPage metalAndColorsPage;
    @FindBy(css = ".m-l8")
    public static Menu menu;
}
