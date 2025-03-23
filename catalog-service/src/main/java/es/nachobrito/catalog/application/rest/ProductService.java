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

import es.nachobrito.catalog.application.rest.model.Product;
import es.nachobrito.catalog.domain.model.product.*;
import jakarta.inject.Singleton;

@Singleton
public class ProductService {

    private final ProductRepository repository;


    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product get(String id) {
        var productEntity =  repository.get(new ProductId(id));

        return productEntity.map(ProductMapper::from).orElse(null);
    }

    public boolean exists(String id) {
        return repository.get(new ProductId(id)).isPresent();
    }

    public es.nachobrito.catalog.domain.model.product.Product save(Product product) {
        var entity = new es.nachobrito.catalog.domain.model.product.Product(
                new ProductId(product.getId()),
                new ProductName(product.getName()),
                new ProductPrice(product.getPrice())
        );

        if(product.getDescription() != null)
        {
            entity.setDescription(new ProductDescription(product.getDescription()));
        }

        repository.save(entity);
        
        return entity;
    }

    public void delete(String id) {
        repository.delete(new ProductId(id));
    }
}
