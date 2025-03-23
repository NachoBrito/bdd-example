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

import es.nachobrito.catalog.application.rest.ProductsApi;
import es.nachobrito.catalog.application.rest.model.Product;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;

import java.net.URI;

@Controller
public class ProductController implements ProductsApi {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    @Override
    public Product productIdGet(String id) {
        return service.get(id);

    }

    @Override
    public HttpResponse<Void> productIdPut(String id, Product product) {
        var isNew = !service.exists(id);

        var productEntity = service.save(product);

        return isNew ? HttpResponse.created(URI.create("/product/%s".formatted(productEntity.getId().value()))) : HttpResponse.ok();
    }
}
