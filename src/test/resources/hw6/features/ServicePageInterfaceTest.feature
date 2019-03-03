Feature: ServicePafeInterface

  Scenario: Login verification

    # TODO on 'home' PAGE, I assume...
    Given I am on "Home" page
    Then Browser title 'Home Page'
    When I login as user "PETER"
    Then User name should be 'PETER'
    Then Home Page contains 4 pictures, 4 texts under them, 2 texts above
    Then Click on "Service" subcategory in the header and check that drop down contains values
      | SUPPORT            |
      | DATES              |
      | COMPLEX TABLE      |
      | SIMPLE TABLE       |
      | USER TABLE         |
      | TABLE WITH PAGES   |
      | DIFFERENT ELEMENTS |
      | PERFORMANCE        |
    Then Click on "Service" subcategory in the left section and check that drop down contains values
      | SUPPORT            |
      | DATES              |
      | COMPLEX TABLE      |
      | SIMPLE TABLE       |
      | USER TABLE         |
      | TABLE WITH PAGES   |
      | DIFFERENT ELEMENTS |
      | PERFORMANCE        |
    Given I am on "Different_Elements" through header menu
    Then Page contains 4 checkboxes, 4 radios, 1 dropdown, 2 buttons
    Then there is right Section
    Then there is left Section
    When I select checkboxes Water, Wind
    Then there is log for "CHECKBOXES"
    When I select radio "Selen"
    Then there is log for "RADIOS"
    When I select dropdown "Yellow"
    Then there is log for "DROPDOWN"
    When I unselect checkboxes Water, Wind
    Then there is log unselect for "CHECKBOXES" Water, Wind




