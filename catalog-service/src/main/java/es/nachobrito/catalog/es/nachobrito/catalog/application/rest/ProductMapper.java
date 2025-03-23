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

package es.nachobrito.catalog.es.nachobrito.catalog.application.rest;

import es.nachobrito.catalog.application.rest.model.Product;

public class ProductMapper {

    static Product from(es.nachobrito.catalog.es.nachobrito.catalog.domain.model.product.Product productEntity){
        var product = new Product(productEntity.getId().value(), productEntity.getName().value(), productEntity.getPrice().cents());
        if(productEntity.getDescription().isPresent()){
            product.setDescription(productEntity.getDescription().get().value());
        }
        return product;
    }
}
