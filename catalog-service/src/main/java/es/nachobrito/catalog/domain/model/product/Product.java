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

import java.util.*;

/**
 * Product entity. Represents a product in the system and is identified by a unique id.
 * Equals and HashCode only take the id into account.
 */
public class Product {
    private final ProductId id;
    private ProductName name;
    private ProductDescription description;
    private ProductPrice price;
    private final List<ProductDiscount> discounts;

    public Product(ProductId id, ProductName name, ProductPrice price) {
        Objects.requireNonNull(id, "Id cannot be null");
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(price, "price cannot be null");
        this.id = id;
        this.name = name;
        this.price = price;

        this.discounts = new ArrayList<>();
    }

    public static Product copyOf(Product other) {
        var product = new Product(other.getId(), other.getName(), other.getPrice());
        if(other.getDescription().isPresent()){
            product.setDescription(other.getDescription().get());
        }
        return product;
    }


    public ProductId getId() {
        return id;
    }

    public ProductName getName() {
        return name;
    }

    public void setName(ProductName name) {
        this.name = name;
    }

    public Optional<ProductDescription> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setDescription(ProductDescription description) {
        this.description = description;
    }

    public ProductPrice getPrice() {
        return price;
    }

    public void setPrice(ProductPrice price) {
        this.price = price;
    }

    public List<ProductDiscount> getDiscounts() {
        return Collections.unmodifiableList(discounts);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
