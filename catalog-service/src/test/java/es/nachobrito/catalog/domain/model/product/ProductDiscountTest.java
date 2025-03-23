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

import es.nachobrito.catalog.domain.model.product.ProductDiscount;
import org.junit.jupiter.api.Test;

import static java.time.ZonedDateTime.now;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductDiscountTest {

    @Test
    void expectNoExceptionIfDiscountValid() {
        assertDoesNotThrow(() -> new ProductDiscount(.1, now()));
        assertDoesNotThrow(() -> new ProductDiscount(.25, now(), now().plusDays(1)));
    }

    @Test
    void expectExceptionIfDiscountInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new ProductDiscount(0, now(), now().plusDays(1)));
        assertThrows(IllegalArgumentException.class, () -> new ProductDiscount(-1, now(), now().plusDays(1)));
        assertThrows(IllegalArgumentException.class, () -> new ProductDiscount(.1, null, now().plusDays(1)));
        assertThrows(IllegalArgumentException.class, () -> new ProductDiscount(.1, now(), now()));
        assertThrows(IllegalArgumentException.class, () -> new ProductDiscount(.1, now(), now().minusSeconds(1)));
    }

}