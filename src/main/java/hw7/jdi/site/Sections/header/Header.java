package hw7.jdi.site.Sections.header;

import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.ui.html.common.Icon;
import hw7.jdi.entites.User;
import hw7.jdi.site.Sections.header.forms.LoginForm;
import org.openqa.selenium.support.FindBy;


public class Header extends Section {

    public HeaderMenu headerMenu;
    @FindBy(css = "[id='user-icon']")
    public Icon loginIcon;
    public LoginForm loginForm;

    public void login(User user) {
        loginIcon.click();
        loginForm.login(user);
    }
}
