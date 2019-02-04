package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class IndexPage {
    private final WebDriver driver;

    @FindBy(css = "[id='user-icon']")
    private WebElement userIconField;

    @FindBy(css = "[id='name']")
    private WebElement userNameField;

    @FindBy(css = "[id='password']")
    private WebElement userPassportField;

    @FindBy(css = "[id='login-button']")
    private WebElement submitButton;

    @FindBy(css = "[id='user-name']")
    private WebElement userNickName;

    @FindBy(css = ".uui-navigation.nav.navbar-nav.m-l8 > li > a")
    private List<WebElement> items;

    @FindBy(css = "body > div > div.uui-main-container > main > div.main-content > div > div > div > div > span")
    private List<WebElement> images;

    @FindBy(css = ".row.clerafix.benefits > div > div > span")
    private List<WebElement> texts;

    @FindBy(css = ".main-title.text-center")
    private WebElement textCenterTitle;

    @FindBy(css = ".main-txt.text-center")
    private WebElement textCenterTxt;

    @FindBy(css = "#iframe")
    private WebElement iFrame;

    @FindBy(css = "#epam_logo")
    private WebElement epamLogo;

    @FindBy(css = ".text-center [href='https://github.com/epam/JDI']")
    private WebElement subHeaderText;

    @FindBy(css = ".uui-side-bar.mCustomScrollbar._mCS_1.mCS_no_scrollbar")
    private WebElement leftSection;

    @FindBy(css = "footer")
    private WebElement footer;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.navigate().to("https://epam.github.io/JDI/");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void login(String userName, String userPassword) {
        userIconField.click();
        userNameField.sendKeys(userName);
        userPassportField.sendKeys(userPassword);
        submitButton.click();
    }

    public String getUserNickName() {
        return userNickName.getText();
    }

    public int countOfItems() {
        return items.size();
    }

    public int countOfImages() {
        return images.size();
    }

    public int countOfTexts() {
        return texts.size();
    }

    public boolean containsAllInItems(String[] strings) {
        return items.stream().map(WebElement::getText).collect(Collectors.toList())
                .containsAll(Arrays.asList(strings));
    }

    public int countOfDisplayedImages() {
        return images.stream().filter(WebElement::isDisplayed).collect(Collectors.toList()).size();
    }

    public boolean containsAllInTexts(String[] strings) {
        return texts.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList(strings));
    }

    public String getTextOfCenterTitle() {
        return textCenterTitle.getText();
    }

    public String getTextOfCenterTxt() {
        return textCenterTxt.getText();
    }

    public boolean isLeftSectionDisplayed() {
        return leftSection.isDisplayed();
    }

    public boolean isFooterDisplayed() {
        return footer.isDisplayed();
    }

    public boolean isEpamLogoDisplayed() {
        return epamLogo.isDisplayed();
    }

    public String getAttributeOfSubHeaderText(String attribute) {
        return subHeaderText.getAttribute(attribute);
    }

    public String getTextOfSubHeaderText() {
        return subHeaderText.getText();
    }
}
