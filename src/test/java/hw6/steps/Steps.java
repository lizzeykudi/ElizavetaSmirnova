package hw6.steps;

import JDI.CommonActions;
import JDI.PagesMetaInfo;
import cucumber.api.DataTable;
import cucumber.api.Transpose;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import globalVariables.Users;
import hw4.pages.differentElementPage.DifferentElementsPageSelenide;
import hw4.pages.indexPage.IndexPageSelenide;
import hw6.UserTable;
import hw6.UserTablePage;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

public class Steps {
    IndexPageSelenide indexPageSelenide;
    DifferentElementsPageSelenide differentElementsPageSelenide;
    UserTablePage userTablePage;

    @Given("^I am on \"Home Page\"$")
    public void iOpenEpamJdiPage() {
        indexPageSelenide = open(IndexPageSelenide.URL, IndexPageSelenide.class);
    }

    @Then("Browser title '([^\"]*)'$")
    public void browserTitle(String title) {
        indexPageSelenide.assertBrowserTitle();
    }

    @When("^I login as user \"([^\"]*)\"$")
    public void iLoginAsUser(Users user) {
        indexPageSelenide.login(user);
    }

    @Then("^User name should be '([^\"]*)'$")
    public void UserNameShouldBeAsForUser(Users user) {
        indexPageSelenide.assertUserName(user);
    }

    @Then("^Home Page contains (\\d+) pictures, (\\d+) texts under them, (\\d+) texts above$")
    public void HomePageContains(int countPictures, int countTextsUnderItem, int countText) {
        indexPageSelenide.contains(countPictures, countTextsUnderItem, countText);
    }

    @Then("Click on \"Service\" subcategory in the header and check that drop down contains (.*)$")
    public void ClickOnSer(List<String> list) {
        indexPageSelenide.assertHeaderServiceContainsOptions(list);
    }

    @Then("Click on \"Service\" subcategory in the left section and check that drop down contains (.*)$")
    public void ClickOnService(List<String> list) {
        indexPageSelenide.assertLeftServiceContainsOptions(list);
    }

    @Given("^I open Different Elements Page$")
    public void iOpenDiff() {
        differentElementsPageSelenide = open(DifferentElementsPageSelenide.URL, DifferentElementsPageSelenide.class);
    }

    @Then("^Page contains (\\d+) checkboxes, (\\d+) radios, (\\d+) dropdown, (\\d+) buttons$")
    public void PageContains(int countCheckboxes, int countRadios, int countDropdown, int countButton) {
        differentElementsPageSelenide.checkInterfaceOnDifferentElementsPage(countCheckboxes, countRadios, countDropdown, countButton);
    }

    @Then("^there is right Section$")
    public void rightSectio() {
        differentElementsPageSelenide.assertRightSectionIsDisplayed();
    }

    @Then("^there is left Section$")
    public void leftSectio() {
        differentElementsPageSelenide.assertLeftSectionIsDisplayed();
    }

    @When("^I click on \"Service\" button in Header$")
    public void clickServiceOnHeader() {
        indexPageSelenide.clickServiceOnHeader();
    }

    @When("^I click on \"User Table\" button in Service dropdown$")
    public void clickUserTable() {
        indexPageSelenide.clickUserTable();
        userTablePage = open(PagesMetaInfo.USER_TABLE.url, UserTablePage.class);
    }

    @Then("^\"([^\"]*)\" page is opened$")
    public void pageIsOpened(PagesMetaInfo page) {
        CommonActions.pageIsOpened(title(), page);
    }

    @Then("^(\\d+) \"([^\"]*)\" Dropdowns are displayed on Users Table on User Table Page$")
    public void numbertype_Dropdowns_are_displayed_on_Users_Table_on_User_Table_Page(int numberType, String string) {
        userTablePage.checkCountTableElements(string, numberType);
    }

    @Then("^(\\d+) \"([^\"]*)\" are displayed on Users Table on User Table Page$")
    public void user_names_are_displayed_on_Users_Table_on_User_Table_Page(int userNames) throws Throwable {
        //userTablePage.checkCountUserNames(userNames);
    }

    @Then("^(\\d+) Description images are displayed on Users Table on User Table Page$")
    public void description_images_are_displayed_on_Users_Table_on_User_Table_Page(int arg1) throws Throwable {

    }

    @Then("^(\\d+) checkboxes are displayed on Users Table on User Table Page$")
    public void checkboxes_are_displayed_on_Users_Table_on_User_Table_Page(int arg1) throws Throwable {

    }



    @Then("^(\\d+) Description texts under images are displayed on Users Table on User Table Page$")
    public void description_texts_under_images_are_displayed_on_Users_Table_on_User_Table_Page(int arg1) throws Throwable {

    }

    @Then("^User table contains following values:$")
    public void user_table_contains_following_values(@Transpose DataTable arg) {
        List<List<String>> table = arg.asLists(String.class);
        userTablePage.checkUserTable(arg.asLists(String.class));
    }

    @When("^I select 'vip' checkbox for \"([^\"]*)\"$")
    public void i_select_vip_checkbox_for(String name) {
        userTablePage.selectVipfor(name, true);
    }

    @Then("^1 log row has \"([^\"]*)\" text in log section$")
    public void log_row_has_text_in_log_section(String log) {
        CommonActions.checkLog(Collections.singletonList(log));
    }

    @When("^I click on dropdown in column Type for user \"([^\"]*)\"$")
    public void i_click_on_dropdown_in_column_Type_for_user(String name) {
        userTablePage.clickDropdown(name);
    }

    @Then("^droplist contains values$")
    public void droplist_contains_values(List<String> strings) {
        userTablePage.dropdownContains(strings);
    }
}
