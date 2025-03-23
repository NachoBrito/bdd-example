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

/**
 * Product Description Value Object.
 * @param value
 */
public record ProductDescription(String value) {
    static final int MIN_LENGTH = 10;
    static final int MAX_LENGTH = 5000;

    public ProductDescription {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }

        var length = value.length();
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            throw new IllegalArgumentException("Description size must be between %d and %d".formatted(MIN_LENGTH, MAX_LENGTH));
        }
    }
}
