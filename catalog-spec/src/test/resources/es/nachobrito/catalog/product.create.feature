Feature: Product creation

  Scenario: Product creation
    Given There is no product with id "test-product"
    And I create a product with
      |id            |name          |price |
      |test-product  |Test Product  |999   |
    Then A product exists with id "test-product"
    And The product name is "Test Product"
    And The product price is 999
