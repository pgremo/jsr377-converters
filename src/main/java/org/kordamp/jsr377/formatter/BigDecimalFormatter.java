/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kordamp.jsr377.formatter;

import java.math.BigDecimal;

/**
 * @author Andres Almiray
 */
public class BigDecimalFormatter extends AbstractNumberFormatter<BigDecimal> {
    public BigDecimalFormatter() {
        this(null);
    }

    public BigDecimalFormatter(String pattern) {
        super(pattern);
    }

    @Override
    public BigDecimal parse(String str) throws ParseException {
        if (isBlank(str)) { return null; }
        try {
            Number number = numberFormat.parse(str);
            if (number instanceof BigDecimal) {
                return (BigDecimal) number;
            }
            return new BigDecimal(number.longValue());
        } catch (java.text.ParseException e) {
            throw new ParseException(e);
        }
    }
}
