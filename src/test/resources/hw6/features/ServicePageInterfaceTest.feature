Feature: ServicePafeInterface

  Scenario: Login verification

    #//1 Open test site by URL
    Given I am on "Home Page"

    #//2 Assert Browser title
    Then Browser title 'Home Page'

    #//3 Perform login
    When I login as user 'PETER'

    #//4 Assert User name in the left-top side of screen that user is loggined
    Then User name should be 'PETER'

    #//5 Check interface on Home Page, it contains all needed elements
    Then Home Page contains 4 pictures, 4 texts under them, 2 texts above

    #//6 Click on "Service" subcategory in the header and check that drop down contains options
    Then Click on "Service" subcategory in the header and check that drop down contains SUPPORT, DATES, COMPLEX TABLE, SIMPLE TABLE, USER TABLE, TABLE WITH PAGES, DIFFERENT ELEMENTS, PERFORMANCE

    #//7 Click on "Service" subcategory in the left section and check that drop down contains options
    Then Click on "Service" subcategory in the left section and check that drop down contains SUPPORT, DATES, COMPLEX TABLE, SIMPLE TABLE, USER TABLE, TABLE WITH PAGES, DIFFERENT ELEMENTS, PERFORMANCE

    #//8 Open through the header menu Service -> Different Elements Page
    Given I open Different Elements Page

    #//9 Check interface on Different elements page, it contains all needed elements
    Then Page contains 4 checkboxes, 4 radios, 1 dropdown, 2 buttons

    #//10 Assert that there is Right Section
    Then there is right Section

    #//10 Assert that there is Left Section
    Then there is left Section



