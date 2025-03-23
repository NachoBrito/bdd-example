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

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductNameTest {

    @Test
    void expectExceptionIfNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new ProductName(null));
        assertThrows(IllegalArgumentException.class, () -> new ProductName(""));
    }

    @Test
    void expectExceptionIfNameInvalid() {
        Stream.of(
                "a",
                "ab".repeat(500)
        ).forEach(it -> assertThrows(IllegalArgumentException.class, () -> new ProductName(it)));
    }
}