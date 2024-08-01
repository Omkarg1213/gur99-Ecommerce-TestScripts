Feature: Mobile Sorting and Price Verification

  @VerifyMobileSorting
  Scenario: Sort mobile products by name
    Given I am on the homepage
    When I navigate to the mobile page
    And I sort the mobiles by name
    Then the products should be sorted by name

  @VerifyMobilePrice
  Scenario: Compare price of Sony Xperia on list and detail pages
    Given I am on the homepage
    When I navigate to the mobile page
    And I read the cost of Sony Xperia mobile on the list page
    And I click on the Sony Xperia mobile
    Then I read the cost of Sony Xperia mobile on the detail page
    And the prices should match

  @VerifyCartFunctionality
  Scenario: Verify error message for invalid quantity and empty cart
    Given I am on the homepage
    When I navigate to the mobile page
    And I click on the Sony Xperia mobile
    And I add Sony Xperia to the cart
    Then I change the quantity of a mobile to 1000
    Then I should see an error message for invalid quantity
    When I click on the empty cart link
    Then the cart should be empty
    
  @VerifyMobileComparison
  Scenario: Compare two mobile products
    Given I am on the homepage
    When I navigate to the mobile page
    And I add two mobiles to compare
    And I click on the compare button
    Then I should see a pop-up window with the selected products
    And I close the pop-up window
