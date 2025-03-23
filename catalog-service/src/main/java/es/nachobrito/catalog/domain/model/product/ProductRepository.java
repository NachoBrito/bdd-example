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

package es.nachobrito.catalog.domain.model.product;

import java.util.Optional;

/**
 * Represents a Product store.
 */
public interface ProductRepository {

    /**
     * Finds a product by its id
     * @param id the id to search for
     * @return the Product, if any
     */
    Optional<Product> get(ProductId id);

    /**
     * Saves the provided product. If a product is already stored with the given id, it's updated.
     *
     * @param product the product
     */
    void save(Product product);

    /**
     * Removes the product with the given id
     * @param id the Product id
     */
    void delete(ProductId id);
}
