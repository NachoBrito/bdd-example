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

import es.nachobrito.catalog.application.invoker.ApiException;
import es.nachobrito.catalog.application.model.Product;
import es.nachobrito.catalog.application.rest.ProductsApi;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StepDefinitions {

  private final ProductsApi productsApi = new ProductsApi();

  private Product productCreated, productFound;

  @Given("There is no product with id {string}")
  public void thereIsNoProductWithId(String id) {
    assertThatThrownBy(() -> productsApi.productIdGet(id))
        .extracting("code")
        .isEqualTo(404);
  }

  private void putProduct(DataTable datatable) throws ApiException {
    var map = datatable.asMaps().stream().findFirst().orElseThrow();
    productCreated = new Product();
    productCreated.setId(map.get("id"));
    productCreated.setName(map.get("name"));
    productCreated.setPrice(Integer.valueOf(map.get("price")));

    var response = productsApi.productIdPutWithHttpInfo(productCreated.getId(), productCreated);
    assertThat(response.getStatusCode() == 201);
  }

  @Then("A product exists with id {string}")
  public void aProductExistsWithId(String id) throws ApiException {
    productFound = productsApi.productIdGet(id);
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
  public void cleanup() throws ApiException {
    if(productCreated != null)
    {
      var response = productsApi.productIdDeleteWithHttpInfo(productCreated.getId());
      assertThat(response.getStatusCode()).isEqualTo(204);
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
  public void iDeleteTheProductWithId(String id) throws ApiException {
    var response = productsApi.productIdDeleteWithHttpInfo(id);
    assertThat(response.getStatusCode()).isEqualTo(204);

  }
}
