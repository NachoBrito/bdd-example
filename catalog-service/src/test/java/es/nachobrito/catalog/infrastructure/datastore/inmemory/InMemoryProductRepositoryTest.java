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

package es.nachobrito.catalog.infrastructure.datastore.inmemory;

import es.nachobrito.catalog.domain.model.product.ProductMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryProductRepositoryTest {

    @Test
    void expectProductsLifecycleHandled()
    {
        var store = new InMemoryProductRepository();
        var createdProduct = ProductMother.random();

        assertTrue(store.get(createdProduct.getId()).isEmpty());

        store.save(createdProduct);

        var storedProduct = store.get(createdProduct.getId());
        assertTrue(storedProduct.isPresent());

        assertEquals(createdProduct, storedProduct.get());

        store.delete(createdProduct.getId());
        assertTrue(store.get(createdProduct.getId()).isEmpty());

    }


}