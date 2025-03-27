Feature: Product management

  #Happy paths
  Scenario: New product creation
    Given There is no product with id "test-product-id"
    And I call the service with
      |id               |name          |price |
      |test-product-id  |Test Product  |999   |
    Then the response code is 201
    And A product exists with id "test-product-id"
    And The product name is "Test Product"
    And The product price is 999

  Scenario: Product modification
    Given A product exists with
      |id               |name          |price |
      |test-product-id  |Test Product  |999   |
    When I call the service with
      |id               |name          |price |
      |test-product-id  |New name      |888   |
    Then the response code is 200
    Then A product exists with id "test-product-id"
    And The product name is "New name"
    And The product price is 888

  Scenario: Product deletion
    Given A product exists with
      |id               |name          |price |
      |test-product-id  |Test Product  |999   |
    When I delete the product with id "test-product-id"
    Then the response code is 204
    Then There is no product with id "test-product-id"

  # Validation Errors
  Scenario: Product id cannot be empty
    Given There is no product with id "test-product-id"
    And I call the service with
      |id               |name          |price |
      |                 |Test Product  |999   |
    Then the response code is 404
    And There is no product with id "test-product-id"

  Scenario: Product name cannot be empty
    Given There is no product with id "test-product-id"
    And I call the service with
      |id               |name          |price |
      |test-product-id  |              |999   |
    Then the response code is 400
    And There is no product with id "test-product-id"

  Scenario: Product price cannot be negative
    Given There is no product with id "test-product-id"
    And I call the service with
      |id               |name          |price |
      |test-product-id  |Test Product  |-1    |
    Then the response code is 400
    And There is no product with id "test-product-id"
