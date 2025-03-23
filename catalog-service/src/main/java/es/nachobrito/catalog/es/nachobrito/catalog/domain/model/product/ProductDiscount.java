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

import java.time.ZonedDateTime;

/**
 * Product discount value object
 *
 * @param rate
 * @param dateFrom
 * @param dateTo
 */
public record ProductDiscount(double rate, ZonedDateTime dateFrom, ZonedDateTime dateTo) {
    static final int MIN_DIFF_SECONDS = 1;

    public ProductDiscount {
        if (rate <= 0) throw new IllegalArgumentException("Rate has to be positive");
        if (dateFrom == null) throw new IllegalArgumentException("dateFrom cannot be null");

        if (dateTo != null ) {
            var isAfter = dateTo.toInstant().getEpochSecond() - dateFrom.toInstant().getEpochSecond() > MIN_DIFF_SECONDS;
            if (!isAfter) {
                throw new IllegalArgumentException("dateTo must be posterior to dateFrom");
            }
        }
    }

    public ProductDiscount(double rate, ZonedDateTime dateFrom) {
        this(rate, dateFrom, null);
    }
}
