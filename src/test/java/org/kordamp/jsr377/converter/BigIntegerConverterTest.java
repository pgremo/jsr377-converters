/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 the original author or authors.
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
package org.kordamp.jsr377.converter;

import org.junit.jupiter.params.provider.Arguments;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.kordamp.jsr377.formatter.AbstractNumberFormatter.PATTERN_CURRENCY;
import static org.kordamp.jsr377.formatter.AbstractNumberFormatter.PATTERN_PERCENT;

/**
 * @author Andres Almiray
 */
public class BigIntegerConverterTest extends AbstractNumberConverterTestCase<BigInteger> {
    @Override
    protected NumberConverter<BigInteger> createConverter() {
        return new BigIntegerConverter();
    }

    @Override
    protected NumberConverter<BigInteger> createConverter(String format) {
        NumberConverter<BigInteger> converter = createConverter();
        converter.setFormat(format);
        return converter;
    }

    public static Stream<Arguments> where_value_format_result() {
        return Stream.of(
            Arguments.of(null, null, null),
            Arguments.of("", null, null),
            Arguments.of("1", null, BigInteger.ONE),
            Arguments.of("100%", PATTERN_PERCENT, BigInteger.ONE),
            Arguments.of("$1.00", PATTERN_CURRENCY, BigInteger.ONE),
            Arguments.of(BigInteger.ONE, null, BigInteger.ONE),
            Arguments.of(1L, null, BigInteger.ONE)
        );
    }

    public static Stream<Arguments> where_invalid_value() {
        return Stream.of(
            Arguments.of("garbage"),
            Arguments.of("1, 2, 3"),
            Arguments.of(Collections.emptyList()),
            Arguments.of(Collections.emptyMap()),
            Arguments.of(Arrays.asList(1, 2, 3)),
            Arguments.of(new Object())
        );
    }
}
