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

package es.nachobrito.catalog.es.nachobrito.catalog.domain.model.product;

import org.junit.jupiter.api.Test;

import static java.time.ZonedDateTime.now;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void expectExceptionIfMissingData() {
        assertThrows(NullPointerException.class, () -> new Product(null, new ProductName("product"), new ProductPrice(999)));
        assertThrows(NullPointerException.class, () -> new Product(new ProductId("the-id"), null, new ProductPrice(999)));
        assertThrows(NullPointerException.class, () -> new Product(new ProductId("the-id"), new ProductName("product"), null));
    }

    @Test
    void expectInitialized() {
        var product = new Product(new ProductId("id"), new ProductName("name"), new ProductPrice(999));

        assertNotNull(product.getDiscounts());
        assertTrue(product.getDiscounts().isEmpty());
        assertThrows(UnsupportedOperationException.class, () -> product.getDiscounts().add(new ProductDiscount(.25, now())));

        assertTrue(product.getDescription().isEmpty());
    }

    @Test
    void expectProductsComparedById(){
        var product1 = ProductMother.random();
        var product2 = ProductMother.random();

        assertNotEquals(product1, product2);

        var product1Clone = Product.copyOf(product1);
        assertEquals(product1, product1Clone);

        product1Clone.setDescription(new ProductDescription("New Description"));
        assertEquals(product1, product1Clone);
    }
}