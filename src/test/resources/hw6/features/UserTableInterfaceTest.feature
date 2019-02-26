Feature: UserTableInterface

  Scenario: User Table Page test


    Given I am on "Home"
    And I login as user "PETER"
    When I click on "Service" button in Header
    And I click on "User Table" button in Service dropdown
    Then "USER_TABLE" page is opened
    And 6 "NumberType Dropdowns" are displayed on Users Table on User Table Page
    And 6 "User names" are displayed on Users Table on User Table Page
    And 6 "Description images" are displayed on Users Table on User Table Page
    And 6 "Description texts under images" are displayed on Users Table on User Table Page
    And 6 "checkboxes" are displayed on Users Table on User Table Page
    And User table contains following values:
      | Number | User             | Description                       |
      | 1      | Roman            | Wolverine                         |
      | 2      | Sergey Ivan      | Spider Man                        |
      | 3      | Vladzimir        | Punisher                          |
      | 4      | Helen Bennett    | Captain America\nsome description |
      | 5      | Yoshi Tannamuri  | Cyclope\nsome description         |
      | 6      | Giovanni Rovelli | Hulk\nsome description            |

    When I select 'vip' checkbox for "ivan"
    Then 1 log row has "Vip: condition changed to true" text in log section
    When I click on dropdown in column Type for user "Roman"
    Then droplist for user "Roman" contains values
      | Admin   |
      | User    |
      | Manager |
