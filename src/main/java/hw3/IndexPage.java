package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IndexPage {
    private final String TITLE = "Home Page";

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

    // TODO This locator can be improved
    @FindBy(css = ".uui-navigation.nav.navbar-nav.m-l8 > li > a")
    private List<WebElement> items;

    // TODO This locator can be improved
    @FindBy(css = "body > div > div.uui-main-container > main > div.main-content > div > div > div > div > span")
    private List<WebElement> images;

    // TODO This locator can be improved
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

    // TODO This locator can be improved, take a look on html element attributes...
    @FindBy(css = ".uui-side-bar.mCustomScrollbar._mCS_1.mCS_no_scrollbar")
    private WebElement leftSection;

    @FindBy(css = "footer")
    private WebElement footer;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
    }

    private String getTitle() {
        return driver.getTitle();
    }

    private String getUserNickName() {
        return userNickName.getText();
    }

    private int countOfItems() {
        return items.size();
    }

    private int countOfImages() {
        return images.size();
    }

    private int countOfTexts() {
        return texts.size();
    }

    private boolean containsAllInItems(String[] strings) {
        return items.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList()).containsAll(Arrays.asList(strings));
    }

    private int countOfDisplayedImages() {
        return images.stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList()).size();
    }

    private boolean containsAllInTexts(String[] strings) {
        return texts.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList()).containsAll(Arrays.asList(strings));
    }

    private String getTextOfCenterTitle() {
        return textCenterTitle.getText();
    }

    private String getTextOfCenterTxt() {
        return textCenterTxt.getText();
    }

    private boolean isLeftSectionDisplayed() {
        return leftSection.isDisplayed();
    }

    private boolean isIframeDisplayed() {
        return iFrame.isDisplayed();
    }

    private boolean isFooterDisplayed() {
        return footer.isDisplayed();
    }

    private boolean isEpamLogoDisplayed() {
        return epamLogo.isDisplayed();
    }

    private String getAttributeOfSubHeaderText() {
        return subHeaderText.getAttribute("href");
    }

    private String getTextOfSubHeaderText() {
        return subHeaderText.getText();
    }

    public void open() {
        driver.navigate().to("https://epam.github.io/JDI/");
    }

    public void login(String userName, String userPassword) {
        userIconField.click();
        userNameField.sendKeys(userName);
        userPassportField.sendKeys(userPassword);
        submitButton.click();
    }

    public void assertBrowserTitle() {
        Assert.assertEquals(getTitle(), TITLE);
    }

    public void assertUserName(String nick) {
        Assert.assertEquals(getUserNickName(), nick);
    }

    public void assertItemsCount(int itemsCount) {
        Assert.assertEquals(countOfItems(), itemsCount);
    }

    public void assertItemsTexts(String[] items) {
        Assert.assertTrue((containsAllInItems(items)));
    }

    public void asserImagesCount(int imagesCount) {
        Assert.assertEquals(countOfImages(), imagesCount);
    }

    public void assertDisplayedImagesCount(int imagesCount) {
        Assert.assertEquals(countOfDisplayedImages(), imagesCount);
    }

    public void assertTextsUnderIconsCount(int size) {
        Assert.assertEquals(countOfTexts(), size);
    }

    public void assertTextsUnderIcons(String[] texts) {
        Assert.assertTrue((containsAllInTexts(texts)));
    }

    public void assertTextOfCenterTitle(String textOfCenterTitle) {
        Assert.assertEquals(getTextOfCenterTitle(), textOfCenterTitle);
    }

    public void assertTextOfCenterTxt(String startTextOfCenterTitle) {
        Assert.assertTrue(getTextOfCenterTxt().startsWith(startTextOfCenterTitle));
    }

    public void assertIframeIsDisplayed() {
        Assert.assertTrue(isIframeDisplayed());
    }

    public void assertEpamLogoIsDisplayed() {
        Assert.assertTrue(isEpamLogoDisplayed());
    }

    public void assertTextOfSubHeader(String jdiGithubText) {
        Assert.assertEquals(getTextOfSubHeaderText(), jdiGithubText);
    }

    public void assertJDIGithubLink(String jdiGithubRef) {
        Assert.assertEquals(getAttributeOfSubHeaderText(), jdiGithubRef);
    }

    public void assertLeftSectionIsDisplayed() {
        Assert.assertTrue(isLeftSectionDisplayed());
    }

    public void assertFooterIsDisplayed() {
        Assert.assertTrue(isFooterDisplayed());
    }
}
