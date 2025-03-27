/*
 *    Copyright 2025 Nacho Brito
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package es.nachobrito.catalog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import es.nachobrito.catalog.application.invoker.ApiException;
import es.nachobrito.catalog.application.invoker.ApiResponse;
import es.nachobrito.catalog.application.model.Product;
import es.nachobrito.catalog.application.rest.ProductsApi;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Optional;

public class StepDefinitions {

  private final ProductsApi productsApi = new ProductsApi();

  private Product productCreated, productFound;
  private int responseCode;

  @Given("There is no product with id {string}")
  public void thereIsNoProductWithId(String id) {
    assertThatThrownBy(() -> productsApi.productIdGet(id))
        .extracting("code")
        .isEqualTo(404);
  }

  private void putProduct(DataTable datatable)  {
    var map = datatable.asMaps().stream().findFirst().orElseThrow();
    productCreated = new Product();
    productCreated.setId(Optional.ofNullable(map.get("id")).orElse(""));
    productCreated.setName(map.get("name"));
    productCreated.setPrice(Optional.ofNullable(map.get("price")).map(Integer::valueOf).orElse(0));

      try {
          var response = productsApi.productIdPutWithHttpInfo(productCreated.getId(), productCreated);
          responseCode = response.getStatusCode();
      } catch (ApiException e) {
          responseCode = e.getCode();
      }
  }

  @Then("A product exists with id {string}")
  public void aProductExistsWithId(String id) {
      try {
          productFound = productsApi.productIdGet(id);
      } catch (ApiException e) {
        this.responseCode = e.getCode();
      }
    assertThat(productFound).isNotNull();

  }

  @And("The product name is {string}")
  public void theProductNameIs(String name) {
    assertThat(productFound).extracting("name").isEqualTo(name);
  }

  @And("The product price is {int}")
  public void theProductPriceIs(int price) {
    assertThat(productFound).extracting("price").isEqualTo(price);
  }

  @After
  public void cleanup() {
    if(productCreated != null)
    {
        try {
            productsApi.productIdDeleteWithHttpInfo(productCreated.getId());
        } catch (ApiException ignored) {
        }
    }
  }

  @Given("A product exists with")
  public void aProductExistsWith(DataTable datatable) throws ApiException {
    putProduct(datatable);
  }

  @And("I call the service with")
  public void iCallTheServiceWith(DataTable datatable) throws ApiException {
    putProduct(datatable);
  }

  @When("I delete the product with id {string}")
  public void iDeleteTheProductWithId(String id) {
      ApiResponse<Void> response = null;
      try {
          response = productsApi.productIdDeleteWithHttpInfo(id);
          responseCode = response.getStatusCode();
      } catch (ApiException e) {
        responseCode = e.getCode();
      }

  }

  @Then("the response code is {int}")
  public void theResponseCodeIs(int code) {
    assertThat(responseCode).isEqualTo(code);
  }
}
