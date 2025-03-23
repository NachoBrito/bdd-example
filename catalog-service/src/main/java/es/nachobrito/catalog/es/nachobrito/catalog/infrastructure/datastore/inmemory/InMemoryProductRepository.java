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

package es.nachobrito.catalog.es.nachobrito.catalog.infrastructure.datastore.inmemory;

import es.nachobrito.catalog.es.nachobrito.catalog.domain.model.product.Product;
import es.nachobrito.catalog.es.nachobrito.catalog.domain.model.product.ProductId;
import es.nachobrito.catalog.es.nachobrito.catalog.domain.model.product.ProductRepository;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class InMemoryProductRepository implements ProductRepository {

    private final Map<ProductId, Product> storage = new HashMap<>();
    @Override
    public Optional<Product> get(ProductId id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void save(Product product) {
        storage.put(product.getId(), product);
    }
}
