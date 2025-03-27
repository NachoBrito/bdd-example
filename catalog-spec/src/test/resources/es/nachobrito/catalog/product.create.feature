Feature: Product management

  Scenario: New product creation
    Given There is no product with id "test-product-id"
    And I call the service with
      |id               |name          |price |
      |test-product-id  |Test Product  |999   |
    Then A product exists with id "test-product-id"
    And The product name is "Test Product"
    And The product price is 999

  Scenario: Product modification
    Given A product exists with
      |id               |name          |price |
      |test-product-id  |Test Product  |999   |
    When I call the service with
      |id               |name          |price |
      |test-product-id  |New name      |888   |
    Then A product exists with id "test-product-id"
    And The product name is "New name"
    And The product price is 888

  Scenario: Product deletion
    Given A product exists with
      |id               |name          |price |
      |test-product-id  |Test Product  |999   |
    When I delete the product with id "test-product-id"
    Then There is no product with id "test-product-id"

