package hw6.steps;

import JDI.CommonActions;
import JDI.PagesMetaInfo;
import cucumber.api.DataTable;
import cucumber.api.Transpose;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import globalVariables.Users;
import hw4.pages.differentElementPage.DifferentElementsPage;
import hw4.pages.differentElementPage.DifferentElements;
import hw4.pages.indexPage.IndexPage;
import hw6.UserTablePage;

import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

public class Steps {
    IndexPage indexPageSelenide;
    DifferentElementsPage differentElementsPageSelenide;
    UserTablePage userTablePage;

    @Given("^I am on \"Home Page\"$")
    public void iOpenEpamJdiPage() {
        indexPageSelenide = open(IndexPage.URL, IndexPage.class);
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

    @When("^I select checkboxes (.*)$")
    public void i_select_checkboxes(List<String> select) {
        differentElementsPageSelenide.selectCheckboxes(true, select);
    }

    @When("^I select radio \"([^\"]*)\"$")
    public void i_select_radio(String select) {
        differentElementsPageSelenide.selectRadio(true, select);
    }

    @When("^I select dropdown \"([^\"]*)\"$")
    public void i_select_dropdown(String select) {
        differentElementsPageSelenide.selectDropdown(true, select);
    }

    @When("^I unselect checkboxes (.*)$")
    public void i_unselect_checkboxes(List<String> texts) {
        differentElementsPageSelenide.selectCheckboxes(false, texts);
    }

    @Then("^there is log for \"([^\"]*)\"$")
    public void there_is_log_for(DifferentElements element) {
        differentElementsPageSelenide.assertLog(element);
    }

    @Then("^there is log unselect for \"([^\"]*)\" (.*)$")
    public void there_is_log_unselect_for(DifferentElements element, List<String> texts) {
        differentElementsPageSelenide.assertLogUnselected(element, texts);
    }

    @Then("Click on \"Service\" subcategory in the header and check that drop down contains (.*)$")
    public void ClickOnService(List<String> list) {
        indexPageSelenide.assertHeaderServiceContainsOptions(list);
    }

    @Then("Click on \"Service\" subcategory in the left section and check that drop down contains (.*)$")
    public void ClickOnServiceLeft(List<String> list) {
        indexPageSelenide.assertLeftServiceContainsOptions(list);
    }

    @Given("^I open Different Elements Page$")
    public void iOpenDifferentElementsPage() {
        differentElementsPageSelenide = open(DifferentElementsPage.URL, DifferentElementsPage.class);
    }

    @Then("^Page contains (\\d+) checkboxes, (\\d+) radios, (\\d+) dropdown, (\\d+) buttons$")
    public void PageContains(int countCheckboxes, int countRadios, int countDropdown, int countButton) {
        differentElementsPageSelenide.checkInterfaceOnDifferentElementsPage(countCheckboxes, countRadios, countDropdown, countButton);
    }

    @Then("^there is right Section$")
    public void rightSection() {
        differentElementsPageSelenide.assertRightSectionIsDisplayed();
    }

    @Then("^there is left Section$")
    public void leftSection() {
        differentElementsPageSelenide.assertLeftSectionIsDisplayed();
    }

    @When("^I click on \"Service\" button in Header$")
    public void clickServiceOnHeader() {
        indexPageSelenide.clickServiceOnHeader();
    }

    @When("^I click on \"User Table\" button in Service dropdown$")
    public void clickUserTable() {
        indexPageSelenide.clickUserTable();
        userTablePage = open("https://epam.github.io/JDI/user-table.html", UserTablePage.class);
    }

    @Then("^\"([^\"]*)\" page is opened$")
    public void pageIsOpened(PagesMetaInfo page) {
        CommonActions.pageIsOpened(title(), page);
    }

    @Then("^(\\d+) \"([^\"]*)\" are displayed on Users Table on User Table Page$")
    public void el_are_displayed_on_Users_Table_on_User_Table_Page(int numberType, String string) {
        userTablePage.checkCountTableElements(string, numberType);
    }


    @Then("^User table contains following values:$")
    public void user_table_contains_following_values(@Transpose DataTable arg) {
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

    @Then("^droplist for user \"([^\"]*)\" contains values$")
    public void droplist_contains_values(String name, List<String> strings) {
        userTablePage.dropdownContains(strings, name);
    }
}
