/*
 * Copyright 2015-2018 the original author or authors.
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

import org.junit.jupiter.params.provider.Arguments;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.kordamp.jsr377.formatter.AbstractNumberFormatter.PATTERN_CURRENCY;
import static org.kordamp.jsr377.formatter.AbstractNumberFormatter.PATTERN_PERCENT;

/**
 * @author Andres Almiray
 */
public class BigDecimalFormatterTest extends AbstractNumberFormatterTestCase<BigDecimal> {
    @Override
    protected Formatter<BigDecimal> createFormatter() {
        return new BigDecimalFormatter();
    }

    @Override
    protected Formatter<BigDecimal> createFormatter(String pattern) {
        return new BigDecimalFormatter(pattern);
    }

    public static Stream<Arguments> where_simple() {
        return Stream.of(
            Arguments.of(BigDecimal.valueOf(100L), "100")
        );
    }

    public static Stream<Arguments> where_pattern() {
        return Stream.of(
            Arguments.of(PATTERN_CURRENCY, null, null),
            Arguments.of(PATTERN_PERCENT, null, null),
            Arguments.of(PATTERN_CURRENCY, BigDecimal.valueOf(100L), "$100.00"),
            Arguments.of(PATTERN_PERCENT, BigDecimal.ONE, "100%"),
            Arguments.of(null, BigDecimal.valueOf(100L), "100"),
            Arguments.of("", BigDecimal.valueOf(100L), "100"),
            Arguments.of("##.0", BigDecimal.valueOf(20L), "20.0")
        );
    }

    public static Stream<Arguments> where_parse_error() {
        return Stream.of(
            Arguments.of(PATTERN_CURRENCY, "abc"),
            Arguments.of(PATTERN_PERCENT, "abc")
        );
    }

    public static Stream<Arguments> where_invalid_pattern() {
        return Stream.of(
            Arguments.of(";garbage*@%&")
        );
    }
}
