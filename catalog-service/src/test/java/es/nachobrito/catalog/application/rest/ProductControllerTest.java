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

package es.nachobrito.catalog.application.rest;

import es.nachobrito.catalog.domain.model.product.ProductDescription;
import es.nachobrito.catalog.domain.model.product.ProductMother;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class ProductControllerTest {

    @Inject
    ProductsClient productsClient;

    @Test
    void expectProductStored() {
        var product = ProductMother.random();
        var restProduct = ProductMapper.from(product);

        var response = productsClient.productIdPut(restProduct.getId(), restProduct);
        assertEquals(201, response.code());
    }

    @Test
    void expectProductsUpdate() {
        var product = ProductMother.random();
        var restProduct = ProductMapper.from(product);

        productsClient.productIdPut(restProduct.getId(), restProduct);
        product.setDescription(new ProductDescription("New Product Description"));
        restProduct = ProductMapper.from(product);

        var response = productsClient.productIdPut(restProduct.getId(), restProduct);
        assertEquals(200, response.code());
    }

    @Test
    void expectProductsRetrieved() {
        var product = ProductMother.random();
        var restProduct = ProductMapper.from(product);

        productsClient.productIdPut(restProduct.getId(), restProduct);
        var returnedProduct = productsClient.productIdGet(restProduct.getId());
        assertEquals(restProduct, returnedProduct);
    }

    @Test
    void expectProductDeleted() {
        var product = ProductMother.random();
        var restProduct = ProductMapper.from(product);

        var createResponse = productsClient.productIdPut(restProduct.getId(), restProduct);
        assertEquals(201, createResponse.code());
        assertNotNull(productsClient.productIdGet(restProduct.getId()));

        var deleteResponse = productsClient.productIdDelete(product.getId().value());
        assertEquals(204, deleteResponse.code());

        assertNull(productsClient.productIdGet(restProduct.getId()));
    }
}