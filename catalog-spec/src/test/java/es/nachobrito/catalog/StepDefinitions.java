package es.nachobrito.catalog;

import es.nachobrito.catalog.application.invoker.ApiException;
import es.nachobrito.catalog.application.model.Product;
import es.nachobrito.catalog.application.rest.ProductsApi;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;

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

  @And("I create a product with")
  public void iCreateAProductWith(DataTable datatable) throws ApiException {
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
    assertThat(productFound).extracting("name").isEqualTo(price);
  }
}
